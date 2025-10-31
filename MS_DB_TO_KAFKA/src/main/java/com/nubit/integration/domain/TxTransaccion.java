package com.nubit.integration.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class TxTransaccion implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Long idTx;
    private final String cuentaOrigen;
    private final String cuentaDestino;
    private final BigDecimal monto;
    private final String moneda;
    private final String tipoTx;
    private final String estado;
    private final Timestamp fechaTx;
    private final String referenciaExt;
    private final String canalOrigen;
    private final Integer versionRow;
    private final Timestamp fechaUltCambio;

    public TxTransaccion(
            Long idTx,
            String cuentaOrigen,
            String cuentaDestino,
            BigDecimal monto,
            String moneda,
            String tipoTx,
            String estado,
            Timestamp fechaTx,
            String referenciaExt,
            String canalOrigen,
            Integer versionRow,
            Timestamp fechaUltCambio
    ) {
        this.idTx = idTx;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
        this.moneda = moneda;
        this.tipoTx = tipoTx;
        this.estado = estado;
        this.fechaTx = fechaTx;
        this.referenciaExt = referenciaExt;
        this.canalOrigen = canalOrigen;
        this.versionRow = versionRow;
        this.fechaUltCambio = fechaUltCambio;
    }

    public Long getIdTx() { return idTx; }
    public String getCuentaOrigen() { return cuentaOrigen; }
    public String getCuentaDestino() { return cuentaDestino; }
    public BigDecimal getMonto() { return monto; }
    public String getMoneda() { return moneda; }
    public String getTipoTx() { return tipoTx; }
    public String getEstado() { return estado; }
    public Timestamp getFechaTx() { return fechaTx; }
    public String getReferenciaExt() { return referenciaExt; }
    public String getCanalOrigen() { return canalOrigen; }
    public Integer getVersionRow() { return versionRow; }
    public Timestamp getFechaUltCambio() { return fechaUltCambio; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TxTransaccion that = (TxTransaccion) o;
        return Objects.equals(idTx, that.idTx) &&
                Objects.equals(cuentaOrigen, that.cuentaOrigen) &&
                Objects.equals(cuentaDestino, that.cuentaDestino) &&
                Objects.equals(monto, that.monto) &&
                Objects.equals(moneda, that.moneda) &&
                Objects.equals(tipoTx, that.tipoTx) &&
                Objects.equals(estado, that.estado) &&
                Objects.equals(fechaTx, that.fechaTx) &&
                Objects.equals(referenciaExt, that.referenciaExt) &&
                Objects.equals(canalOrigen, that.canalOrigen) &&
                Objects.equals(versionRow, that.versionRow) &&
                Objects.equals(fechaUltCambio, that.fechaUltCambio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTx, cuentaOrigen, cuentaDestino, monto, moneda, tipoTx, estado, fechaTx, referenciaExt, canalOrigen, versionRow, fechaUltCambio);
    }

    @Override
    public String toString() {
        return "TxTransaccion{" +
                "idTx=" + idTx +
                ", cuentaOrigen='" + cuentaOrigen + '\'' +
                ", cuentaDestino='" + cuentaDestino + '\'' +
                ", monto=" + monto +
                ", moneda='" + moneda + '\'' +
                ", tipoTx='" + tipoTx + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaTx=" + fechaTx +
                ", referenciaExt='" + referenciaExt + '\'' +
                ", canalOrigen='" + canalOrigen + '\'' +
                ", versionRow=" + versionRow +
                ", fechaUltCambio=" + fechaUltCambio +
                '}';
    }
}
