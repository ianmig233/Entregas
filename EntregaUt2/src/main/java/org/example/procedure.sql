CREATE OR REPLACE PROCEDURE insert_data (cod integer, Nombre varchar, temporadas integer)
    LANGUAGE plpsql AS $lang$

    BEGIN
        INSERT INTO videojuegos(cod, Nombre, temporadas, valoración) VALUES (DEFAULT, cod, Nombre, temporadas);
END;
    $lang$