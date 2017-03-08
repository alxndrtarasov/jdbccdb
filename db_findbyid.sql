-- Function: find_by_id(text, integer)

-- DROP FUNCTION find_by_id(text, integer);

CREATE OR REPLACE FUNCTION find_by_id(IN tabname text, IN value integer)
  RETURNS TABLE(id integer, obj_name text, data date, description text) AS
$BODY$ 
DECLARE 
com text; 
BEGIN 
com = 'select * from ' || $1 || ' where (id = ' || $2 || ');'; 
return query execute com; 
return; 
END 
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION find_by_id(text, integer)
  OWNER TO postgres;
