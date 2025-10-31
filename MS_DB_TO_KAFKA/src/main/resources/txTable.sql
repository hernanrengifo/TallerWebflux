CREATE TABLE PUBLIC.TX_TRANSACCION (
                                ID_TX            BIGINT AUTO_INCREMENT PRIMARY KEY,
                                CUENTA_ORIGEN    VARCHAR(32)     NOT NULL,
                                CUENTA_DESTINO   VARCHAR(32),
                                MONTO            DECIMAL(18,2)   NOT NULL,
                                MONEDA           VARCHAR(8)      NOT NULL,
                                TIPO_TX          VARCHAR(30)     NOT NULL, -- débito, crédito, reverso, etc.
                                ESTADO           VARCHAR(20)     NOT NULL DEFAULT 'APLICADA',
                                FECHA_TX         TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                REFERENCIA_EXT   VARCHAR(64),
                                CANAL_ORIGEN     VARCHAR(30),
                                VERSION_ROW      INT             NOT NULL DEFAULT 1,
                                FECHA_ULT_CAMBIO TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);
SELECT * FROM INFORMATION_SCHEMA.TABLES;
SHOW TABLES;