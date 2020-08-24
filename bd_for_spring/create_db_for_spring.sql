-- PostgreSQL version: 12.0

-- object: test_bd | type: SCHEMA --
-- DROP SCHEMA IF EXISTS test_bd CASCADE;
CREATE SCHEMA test_bd
    AUTHORIZATION postgres;
-- ddl-end --

SET search_path TO pg_catalog,public,test_bd;
-- ddl-end --

-- object: test_bd.taxpayer | type: TABLE --
-- DROP TABLE IF EXISTS test_bd.taxpayer CASCADE;
CREATE TABLE test_bd.taxpayer(
     id bigint NOT NULL,
     name character varying(30),
     surname character varying(254) NOT NULL DEFAULT 'IVANOV',
     secname character varying(254),
     date date DEFAULT CURRENT_DATE,
     CONSTRAINT taxpayer_pk PRIMARY KEY (id)

);

ALTER TABLE test_bd.taxpayer OWNER to postgres;
-- ddl-end --

-- object: test_bd.job | type: TABLE --
-- DROP TABLE IF EXISTS test_bd.job CASCADE;
CREATE TABLE test_bd.job(
    id bigint NOT NULL,
    name character varying(254) NOT NULL,
    place character varying(254),
    id_taxpayer bigint,
    CONSTRAINT job_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE test_bd.job OWNER TO postgres;
-- ddl-end --

-- object: test_bd.income | type: TABLE --
-- DROP TABLE IF EXISTS test_bd.income CASCADE;
CREATE TABLE test_bd.income(
    id bigint NOT NULL,
	amount float DEFAULT 0.00,
	id_taxpayer bigint,
    id_bank bigint,
    CONSTRAINT income_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE test_bd.income OWNER TO postgres;
-- ddl-end --

-- object: test_bd.dues | type: TABLE --
-- DROP TABLE IF EXISTS test_bd.dues CASCADE;
CREATE TABLE test_bd.dues(
    id bigint NOT NULL,
	income_taxes float DEFAULT 0.00, --(SELECT (amount*0.17) FROM test_bd.income, test_bd.dues  WHERE test_bd.income.id_taxpayer = test_bd.dues.id_taxpayer),
	id_taxpayer bigint,
    id_institutions bigint,
    CONSTRAINT dues_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE test_bd.dues OWNER TO postgres;
-- ddl-end --

-- object: taxpayer_fk | type: CONSTRAINT --
-- ALTER TABLE test_bd.income DROP CONSTRAINT IF EXISTS taxpayer_fk CASCADE;
ALTER TABLE test_bd.income ADD CONSTRAINT taxpayer_fk FOREIGN KEY (id_taxpayer)
    REFERENCES test_bd.taxpayer (id) MATCH FULL
    ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: income_uq | type: CONSTRAINT --
-- ALTER TABLE test_bd.income DROP CONSTRAINT IF EXISTS income_uq CASCADE;
ALTER TABLE test_bd.income ADD CONSTRAINT income_uq UNIQUE (id_taxpayer);
-- ddl-end --

-- object: taxpayer_fk | type: CONSTRAINT --
-- ALTER TABLE test_bd.dues DROP CONSTRAINT IF EXISTS taxpayer_fk CASCADE;
ALTER TABLE test_bd.dues ADD CONSTRAINT taxpayer_fk FOREIGN KEY (id_taxpayer)
    REFERENCES test_bd.taxpayer (id) MATCH FULL
    ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: dues_uq | type: CONSTRAINT --
-- ALTER TABLE test_bd.dues DROP CONSTRAINT IF EXISTS dues_uq CASCADE;
ALTER TABLE test_bd.dues ADD CONSTRAINT dues_uq UNIQUE (id_taxpayer);
-- ddl-end --

-- object: taxpayer_fk | type: CONSTRAINT --
-- ALTER TABLE test_bd.job DROP CONSTRAINT IF EXISTS taxpayer_fk CASCADE;
ALTER TABLE test_bd.job ADD CONSTRAINT taxpayer_fk FOREIGN KEY (id_taxpayer)
    REFERENCES test_bd.taxpayer (id) MATCH FULL
    ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: test_bd.bank | type: TABLE --
-- DROP TABLE IF EXISTS test_bd.bank CASCADE;
CREATE TABLE test_bd.bank(
    id bigint NOT NULL,
    cash bigint NOT NULL DEFAULT 0,
    id_job bigint,
    id_institutions bigint,
    CONSTRAINT bank_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE test_bd.bank OWNER TO postgres;
-- ddl-end --

-- object: job_fk | type: CONSTRAINT --
-- ALTER TABLE test_bd.bank DROP CONSTRAINT IF EXISTS job_fk CASCADE;
ALTER TABLE test_bd.bank ADD CONSTRAINT job_fk FOREIGN KEY (id_job)
    REFERENCES test_bd.job (id) MATCH FULL
    ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: bank_uq | type: CONSTRAINT --
-- ALTER TABLE test_bd.bank DROP CONSTRAINT IF EXISTS bank_uq CASCADE;
ALTER TABLE test_bd.bank ADD CONSTRAINT bank_uq UNIQUE (id_job);
-- ddl-end --

-- object: bank_fk | type: CONSTRAINT --
-- ALTER TABLE test_bd.income DROP CONSTRAINT IF EXISTS bank_fk CASCADE;
ALTER TABLE test_bd.income ADD CONSTRAINT bank_fk FOREIGN KEY (id_bank)
    REFERENCES test_bd.bank (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: income_uq1 | type: CONSTRAINT --
-- ALTER TABLE test_bd.income DROP CONSTRAINT IF EXISTS income_uq1 CASCADE;
ALTER TABLE test_bd.income ADD CONSTRAINT income_uq1 UNIQUE (id_bank);
-- ddl-end --

-- object: test_bd.institutions | type: TABLE --
-- DROP TABLE IF EXISTS test_bd.institutions CASCADE;
CREATE TABLE test_bd.institutions(
    id bigint NOT NULL,
    name character varying(254),
    CONSTRAINT new_table_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE test_bd.institutions OWNER TO postgres;
-- ddl-end --

-- object: institutions_fk | type: CONSTRAINT --
-- ALTER TABLE test_bd.bank DROP CONSTRAINT IF EXISTS institutions_fk CASCADE;
ALTER TABLE test_bd.bank ADD CONSTRAINT institutions_fk FOREIGN KEY (id_institutions)
    REFERENCES test_bd.institutions (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: institutions_fk | type: CONSTRAINT --
-- ALTER TABLE test_bd.dues DROP CONSTRAINT IF EXISTS institutions_fk CASCADE;
ALTER TABLE test_bd.dues ADD CONSTRAINT institutions_fk FOREIGN KEY (id_institutions)
    REFERENCES test_bd.institutions (id) MATCH FULL
    ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: dues_uq1 | type: CONSTRAINT --
-- ALTER TABLE test_bd.dues DROP CONSTRAINT IF EXISTS dues_uq1 CASCADE;
ALTER TABLE test_bd.dues ADD CONSTRAINT dues_uq1 UNIQUE (id_institutions);
-- ddl-end --

