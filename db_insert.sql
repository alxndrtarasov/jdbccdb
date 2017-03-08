-- Function: insert_into(text, integer, text)

-- DROP FUNCTION insert_into(text, integer, text);

CREATE OR REPLACE FUNCTION insert_into(tab text, id integer, object text)
  RETURNS integer AS
$BODY$
BEGIN
 EXECUTE 'insert into '|| tab || ' values(' || id ||',' || object || ');';
RETURN 1;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION insert_into(text, integer, text)
  OWNER TO postgres;
