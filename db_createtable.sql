-- Function: create_table(text)

-- DROP FUNCTION create_table(text);

CREATE OR REPLACE FUNCTION create_table(n text)
  RETURNS integer AS
$BODY$
BEGIN
 EXECUTE 'CREATE TABLE ' || n || ' (id integer primary key check(id>0), obj_name text, data date, description text);';
RETURN 1;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION create_table(text)
  OWNER TO postgres;
