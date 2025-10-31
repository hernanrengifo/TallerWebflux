package com.nubit.integration.in;

import com.nubit.integration.domain.CdcTxLog;
import com.nubit.integration.domain.TxTransaccion;
import org.springframework.integration.jdbc.SqlParameterSourceFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class TxTransaccionSqlParameterSourceFactory implements SqlParameterSourceFactory {
    @Override
    public SqlParameterSource createParameterSource(Object input) {
        if (input == null || !(input instanceof TxTransaccion)) {
            throw new IllegalArgumentException("Input must be a non-null TxTransaccion object");
        }

        TxTransaccion tx = (TxTransaccion) input;
        CdcTxLog cdcLog = new CdcTxLog(tx.getIdTx(), "TxTransaccion", "I", String.valueOf(tx.getIdTx()),
                tx.toString(), new java.sql.Timestamp(System.currentTimeMillis()), 0L,
                "SourceSystem", "PENDING", null, null);
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("source_table", cdcLog.sourceTable());
        params.addValue("op_type", cdcLog.opType());
        params.addValue("pk_value", cdcLog.pkValue());
        params.addValue("row_data", cdcLog.rowData());
        params.addValue("event_ts", cdcLog.eventTs());
        params.addValue("event_order", cdcLog.eventOrder());
        params.addValue("origin_system", cdcLog.originSystem());
        params.addValue("process_status", cdcLog.processStatus());
        params.addValue("process_ts", cdcLog.processTs());
        params.addValue("error_msg", cdcLog.errorMsg());
        return params;
    }
}
