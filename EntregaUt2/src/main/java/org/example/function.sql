CREATE OR REPLACE FUNCTION name_from_precio (cod int)
RETURNS VARCHAR
LANGUAGE PLPSQL AS $lang$
DECLARE
    Nombre VARCHAR;

BEGIN
    SELECT cod INTO Nombre from videojuegos
    WHERE temporada > 5;
    RETURN Nombre;
END;
$lang$;