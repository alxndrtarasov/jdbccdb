-- Function: find(text, text, text)

-- DROP FUNCTION find(text, text, text);

CREATE OR REPLACE FUNCTION find(IN tabname text, IN pole text, IN value text)
  RETURNS TABLE(id integer, obj_name text, data date, description text) AS
$BODY$ 
DECLARE 
com text; 
BEGIN 
com = 'select * from ' || $1 || ' where (' || $2 || ' = ''' || $3 || ''');'; 
return query execute com; 
return; 
END 
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION find(text, text, text)
  OWNER TO postgres;
