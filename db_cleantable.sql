-- Function: clean_table(text)

-- DROP FUNCTION clean_table(text);

CREATE OR REPLACE FUNCTION clean_table(n text)
  RETURNS integer AS
$BODY$
BEGIN
 EXECUTE 'delete from ' || n || ';';
RETURN 1;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION clean_table(text)
  OWNER TO postgres;
