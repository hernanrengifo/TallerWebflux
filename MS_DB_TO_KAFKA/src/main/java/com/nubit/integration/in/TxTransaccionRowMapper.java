package com.nubit.integration.in;

import com.nubit.integration.domain.TxTransaccion;
import org.springframework.jdbc.core.RowMapper;

public class TxTransaccionRowMapper implements RowMapper<TxTransaccion> {
    @Override
    public TxTransaccion mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
       return new TxTransaccion(
                rs.getLong("id_tx"),
                rs.getString("cuenta_origen"),
                rs.getString("cuenta_destino"),
                rs.getBigDecimal("monto"),
                rs.getString("moneda"),
                rs.getString("tipo_tx"),
                rs.getString("estado"),
                rs.getTimestamp("fecha_tx"),
                rs.getString("referencia_ext"),
                rs.getString("canal_origen"),
                rs.getInt("version_row"),
                rs.getTimestamp("fecha_ult_cambio"));
    }
}
