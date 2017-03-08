-- Function: all_objs(text)

-- DROP FUNCTION all_objs(text);

CREATE OR REPLACE FUNCTION all_objs(IN tabname text)
  RETURNS TABLE(id integer, obj_name text, data date, description text) AS
$BODY$ 
DECLARE 
com text; 
BEGIN 
com = 'select * from ' || $1 || ';'; 
return query execute com; 
return; 
END 
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION all_objs(text)
  OWNER TO postgres;
