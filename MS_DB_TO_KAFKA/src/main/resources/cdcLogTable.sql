CREATE TABLE PUBLIC.CDC_TX_LOG (
                            CDC_ID           BIGINT AUTO_INCREMENT PRIMARY KEY,
                            SOURCE_TABLE     VARCHAR(64)      NOT NULL,
                            OP_TYPE          VARCHAR(10)      NOT NULL,   -- INSERT | UPDATE | DELETE
                            PK_VALUE         VARCHAR(64)      NOT NULL,
                            ROW_DATA         CLOB,
                            EVENT_TS         TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            EVENT_ORDER      BIGINT           NOT NULL,
                            ORIGIN_SYSTEM    VARCHAR(30),
                            PROCESS_STATUS   VARCHAR(15)      NOT NULL DEFAULT 'PENDING', -- PENDING | SENT | ERROR
                            PROCESS_TS       TIMESTAMP,
                            ERROR_MSG        VARCHAR(255)
);