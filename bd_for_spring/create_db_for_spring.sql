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
    id_taxpayer bigint,
    name character varying(254) NOT NULL,
    place character varying(254),
    CONSTRAINT job_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE test_bd.job OWNER TO postgres;
-- ddl-end --

-- object: test_bd.income | type: TABLE --
-- DROP TABLE IF EXISTS test_bd.income CASCADE;
CREATE TABLE test_bd.income(
	amount double precision DEFAULT 0.00,
	id_taxpayer bigint

);
-- ddl-end --
ALTER TABLE test_bd.income OWNER TO postgres;
-- ddl-end --

-- object: test_bd.dues | type: TABLE --
-- DROP TABLE IF EXISTS test_bd.dues CASCADE;
CREATE TABLE test_bd.dues(
	income_taxes double precision DEFAULT 0.00, --(SELECT (amount*0.17) FROM test_bd.income, test_bd.dues  WHERE test_bd.income.id_taxpayer = test_bd.dues.id_taxpayer),
	id_taxpayer bigint

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

