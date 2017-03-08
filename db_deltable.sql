-- Function: delete_table(text)

-- DROP FUNCTION delete_table(text);

CREATE OR REPLACE FUNCTION delete_table(n text)
  RETURNS integer AS
$BODY$
BEGIN
 EXECUTE 'drop TABLE ' || n || ';';
RETURN 1;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION delete_table(text)
  OWNER TO postgres;
