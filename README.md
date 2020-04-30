# How to run
```
$ sbt "testOnly scripts.PrintSlickTableSchemas"
```

# MySQL column types in Slick 3.3.2
The result of [ColumnTypesTable.scala](https://github.com/ponsea/print-slick-table-schemas/blob/master/src/main/scala/tables/ColumnTypesTable.scala)
```sql
CREATE TABLE `column_types`(
    `int`              INTEGER        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `long`             BIGINT         NOT NULL,
    `float`            REAL           NOT NULL,
    `double`           DOUBLE         NOT NULL,
    `big_decimal`      DECIMAL(21, 2) NOT NULL,
    `string`           TEXT           NOT NULL,
    `char`             CHAR(1)        NOT NULL,
    `boolean`          BOOLEAN        NOT NULL,
    `byte`             TINYINT        NOT NULL,
    `byte_array`       BLOB           NOT NULL,
    `blob`             BLOB           NOT NULL,
    `clob`             CLOB           NOT NULL,
    `uuid`             BINARY(16)     NOT NULL,
    `date`             DATE           NOT NULL,
    `time`             TIME           NOT NULL,
    `timestamp`        TIMESTAMP      NOT NULL,
    `local_time`       TEXT           NOT NULL,
    `local_date`       DATE           NOT NULL,
    `local_date_time`  TEXT           NOT NULL,
    `offset_date_time` TEXT           NOT NULL,
    `zoned_date_time`  TEXT           NOT NULL,
    `instant`          TEXT           NOT NULL
)
```

# MySQL table constraints in Slick 3.3.2
The result of [TableConstraintsTable.scala](https://github.com/ponsea/print-slick-table-schemas/blob/master/src/main/scala/tables/TableConstraintsTable.scala)
```sql
CREATE TABLE `table_constraints`(
    `unique`           BIGINT      NOT NULL UNIQUE,
    `option`           BIGINT,
    `default`          BIGINT      DEFAULT 0 NOT NULL,
    `varyingLength`    VARCHAR(16) NOT NULL,
    `notVaryingLength` CHAR(16)    NOT NULL,
    `primary_key_1`    BIGINT      NOT NULL,
    `primary_key_2`    BIGINT      NOT NULL,
    `foreign_key_1`    BIGINT      NOT NULL,
    `foreign_key_2`    BIGINT      NOT NULL,
    `foreign_key_3`    BIGINT      NOT NULL
)

ALTER TABLE `table_constraints`
    ADD CONSTRAINT `pk`
    PRIMARY KEY(`primary_key_1`,`primary_key_2`)

CREATE INDEX `idx`
    ON `table_constraints`(`foreign_key_1`)

CREATE UNIQUE INDEX `idx_unique`
    ON  `table_constraints`(`foreign_key_2`, `foreign_key_3`)

ALTER TABLE `table_constraints`
    ADD CONSTRAINT `fk1`
    FOREIGN KEY(`foreign_key_1`)
    REFERENCES `column_types`(`long`)
    ON UPDATE NO ACTION
    ON DELETE RESTRICT

ALTER TABLE `table_constraints`
    ADD CONSTRAINT `fk2`
    FOREIGN KEY(`foreign_key_2`)
    REFERENCES `column_types`(`long`)
    ON UPDATE SET DEFAULT
    ON  DELETE SET NULL

ALTER TABLE `table_constraints`
    ADD CONSTRAINT `fk3`
    FOREIGN KEY(`foreign_key_2`)
    REFERENCES `column_types`(`long`)
    ON UPDATE CASCADE
    ON DELETE CASCADE
```
Drop statements:
```sql
ALTER TABLE `table_constraints` DROP FOREIGN KEY `fk1`
ALTER TABLE `table_constraints` DROP FOREIGN KEY `fk2`
ALTER TABLE `table_constraints` DROP FOREIGN KEY `fk3`
ALTER TABLE `table_constraints` DROP PRIMARY KEY
DROP TABLE `table_constraints`
```
