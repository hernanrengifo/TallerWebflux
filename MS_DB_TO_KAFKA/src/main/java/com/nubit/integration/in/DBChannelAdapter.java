package com.nubit.integration.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import javax.sql.DataSource;

import org.springframework.integration.dsl.PollerSpec;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.jdbc.JdbcOutboundGateway;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.integration.scheduling.PollerMetadata;

import java.time.Duration;

@EnableIntegration
@Configuration
public class DBChannelAdapter {


    public DBChannelAdapter() {
    }

    @Bean
    @DependsOn("eventDBDataSource")
    public JdbcPollingChannelAdapter getTXTransaccionReader(@Autowired DataSource dataSource) {
        final String selectQuery = "SELECT id_tx, cuenta_origen, cuenta_destino, monto, moneda, tipo_tx, estado, " +
                "fecha_tx, referencia_ext, canal_origen, version_row, fecha_ult_cambio FROM PUBLIC.TX_TRANSACCION " +
                "WHERE ESTADO = 'NUEVA'";
        final String updateQuery = "UPDATE tx_transaccion SET estado = 'PROCESADA' WHERE id_tx = :idTx";
        JdbcPollingChannelAdapter adapter = new JdbcPollingChannelAdapter(dataSource, selectQuery);
        adapter.setUpdateSql(updateQuery);
        adapter.setRowMapper(new TxTransaccionRowMapper());
        adapter.setLoggingEnabled(true);
        adapter.setUpdatePerRow(true);
        return adapter;
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerSpec getPoller() {
        return Pollers.fixedDelay(Duration.ofSeconds(1))
                .maxMessagesPerPoll(1);
    }

    @Bean
    @DependsOn("eventDBDataSource")
    public JdbcOutboundGateway getTXTransaccionWriter(@Autowired DataSource dataSource) {
        final String insertCDCTxLog = "INSERT INTO PUBLIC.cdc_tx_log (id_tx, cuenta_origen, cuenta_destino, monto, moneda, tipo_tx, estado, " +
                "fecha_tx, referencia_ext, canal_origen, version_row, fecha_ult_cambio) " +
                "VALUES (:id_tx, :cuenta_origen, :cuenta_destino, :monto, :moneda, :tipo_tx, :estado, " +
                ":fecha_tx, :referencia_ext, :canal_origen, :version_row, :fecha_ult_cambio)";
        JdbcOutboundGateway adapter = new JdbcOutboundGateway(dataSource, insertCDCTxLog);
        adapter.setRequestSqlParameterSourceFactory(new TxTransaccionSqlParameterSourceFactory());
        return adapter;
    }

    @Bean
    @DependsOn({"getTXTransaccionReader", "getTXTransaccionWriter"})
    public IntegrationFlow getEventChannelAdapter(@Autowired JdbcPollingChannelAdapter inAdapter,
                                            @Autowired JdbcOutboundGateway outAdapter) {
        return IntegrationFlow.from(inAdapter, c-> getPoller())
                .channel("txTransaccionEventChannel")
                .get();
    }

}
