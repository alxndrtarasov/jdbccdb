-- Function: upd(text, text, text)

-- DROP FUNCTION upd(text, text, text);

CREATE OR REPLACE FUNCTION upd(tab text, obj text, id text)
  RETURNS integer AS
$BODY$
BEGIN
 EXECUTE 'delete from '|| tab || ' where id = ' ||id || ';';
 EXECUTE 'insert into ' || tab || ' values(' || id || ',' || obj || ');';
RETURN 1;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION upd(text, text, text)
  OWNER TO postgres;
