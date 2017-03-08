-- Function: del(text, text, text)

-- DROP FUNCTION del(text, text, text);

CREATE OR REPLACE FUNCTION del(tab text, field text, val text)
  RETURNS integer AS
$BODY$
BEGIN
 EXECUTE 'delete from ' || tab ||' where ' || field || ' = '''|| val||''';';
RETURN 1;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION del(text, text, text)
  OWNER TO postgres;
