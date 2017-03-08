-- Function: create_db(text)

-- DROP FUNCTION create_db(text);

CREATE OR REPLACE FUNCTION create_db(db_name text)
  RETURNS integer AS
$BODY$
declare
begin
create database db_name;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION create_db(text)
  OWNER TO postgres;
