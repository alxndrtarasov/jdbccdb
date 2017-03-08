-- Function: del_by_id(text, text)

-- DROP FUNCTION del_by_id(text, text);

CREATE OR REPLACE FUNCTION del_by_id(tab text, val text)
  RETURNS integer AS
$BODY$
BEGIN
 EXECUTE 'delete from ' || tab ||' where id = '|| val||';';
RETURN 1;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION del_by_id(text, text)
  OWNER TO postgres;
