package com.nubit.integration.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class CdcTxLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Long cdcId;
    private final String sourceTable;
    private final String opType;
    private final String pkValue;
    private final String rowData;
    private final Timestamp eventTs;
    private final Long eventOrder;
    private final String originSystem;
    private final String processStatus;
    private final Timestamp processTs;
    private final String errorMsg;

    public CdcTxLog(
            Long cdcId,
            String sourceTable,
            String opType,
            String pkValue,
            String rowData,
            Timestamp eventTs,
            Long eventOrder,
            String originSystem,
            String processStatus,
            Timestamp processTs,
            String errorMsg
    ) {
        this.cdcId = cdcId;
        this.sourceTable = sourceTable;
        this.opType = opType;
        this.pkValue = pkValue;
        this.rowData = rowData;
        this.eventTs = eventTs;
        this.eventOrder = eventOrder;
        this.originSystem = originSystem;
        this.processStatus = processStatus;
        this.processTs = processTs;
        this.errorMsg = errorMsg;
    }

    // Accessor methods named like record accessors (e.g., cdcId()) to preserve compatibility,
    // plus conventional getters (getCdcId()) for frameworks that expect them.
    public Long cdcId() { return cdcId; }
    public Long getCdcId() { return cdcId(); }

    public String sourceTable() { return sourceTable; }
    public String getSourceTable() { return sourceTable(); }

    public String opType() { return opType; }
    public String getOpType() { return opType(); }

    public String pkValue() { return pkValue; }
    public String getPkValue() { return pkValue(); }

    public String rowData() { return rowData; }
    public String getRowData() { return rowData(); }

    public Timestamp eventTs() { return eventTs; }
    public Timestamp getEventTs() { return eventTs(); }

    public Long eventOrder() { return eventOrder; }
    public Long getEventOrder() { return eventOrder(); }

    public String originSystem() { return originSystem; }
    public String getOriginSystem() { return originSystem(); }

    public String processStatus() { return processStatus; }
    public String getProcessStatus() { return processStatus(); }

    public Timestamp processTs() { return processTs; }
    public Timestamp getProcessTs() { return processTs(); }

    public String errorMsg() { return errorMsg; }
    public String getErrorMsg() { return errorMsg(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CdcTxLog that = (CdcTxLog) o;
        return Objects.equals(cdcId, that.cdcId) &&
                Objects.equals(sourceTable, that.sourceTable) &&
                Objects.equals(opType, that.opType) &&
                Objects.equals(pkValue, that.pkValue) &&
                Objects.equals(rowData, that.rowData) &&
                Objects.equals(eventTs, that.eventTs) &&
                Objects.equals(eventOrder, that.eventOrder) &&
                Objects.equals(originSystem, that.originSystem) &&
                Objects.equals(processStatus, that.processStatus) &&
                Objects.equals(processTs, that.processTs) &&
                Objects.equals(errorMsg, that.errorMsg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cdcId, sourceTable, opType, pkValue, rowData, eventTs, eventOrder, originSystem, processStatus, processTs, errorMsg);
    }

    @Override
    public String toString() {
        return "CdcTxLog{" +
                "cdcId=" + cdcId +
                ", sourceTable='" + sourceTable + '\'' +
                ", opType='" + opType + '\'' +
                ", pkValue='" + pkValue + '\'' +
                ", rowData='" + rowData + '\'' +
                ", eventTs=" + eventTs +
                ", eventOrder=" + eventOrder +
                ", originSystem='" + originSystem + '\'' +
                ", processStatus='" + processStatus + '\'' +
                ", processTs=" + processTs +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
