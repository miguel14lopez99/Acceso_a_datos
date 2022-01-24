-----------------------------------------------------
--NOMBRE Y APELLIDOS: Miguel López Gutiérrez
--NOTA:
--OBSERVACIONES:
----------------------------------------------------

-----------------------------------------------------
--EJERCICIO 1:
--NOTA:
--OBSERVACIONES:
-----------------------------------------------------
--Escribe aquí tu respuesta:
drop type CARRERA force;/
create or replace type CARRERA as object(
    nombre_carrera varchar2(60),
    premio number,
    fecha_evento date,
    member procedure MOSTRAR_EVENTO,
    constructor function CARRERA(nombre_carrera varchar2, premio number) return self as result
)not final;/

create or replace type body CARRERA as
    member procedure MOSTRAR_EVENTO as 
    begin
        dbms_output.put_line(nombre_carrera || ' : ' || premio);
    end;
    
    constructor function CARRERA(nombre_carrera varchar2, premio number) return self as result is
    begin
        self.nombre_carrera:= nombre_carrera;
        self.premio := premio;
        self.fecha_evento := null;
        return;
    end;
end;/

-----------------------------------------------------
--EJERCICIO 2:
--NOTA:
--OBSERVACIONES:
-----------------------------------------------------
--Escribe aquí tu respuesta:
drop type CARRERA_NOCTURNA force;
create or replace type CARRERA_NOCTURNA under CARRERA(
    hora_comienzo varchar2(2),
    km number(3),
    overriding member procedure MOSTRAR_EVENTO
);/

create or replace type body CARRERA_NOCTURNA as
    overriding member procedure MOSTRAR_EVENTO as
    begin
        dbms_output.put_line(nombre_carrera || ' : ' || hora_comienzo || ' , ' || km);
    end;
end;/


-----------------------------------------------------
--EJERCICIO 3:
--NOTA:
--OBSERVACIONES:
-----------------------------------------------------
--Escribe aquí tu respuesta:
drop type V_CARRERAS force;/
create or replace type V_CARRERAS as varray(10) of CARRERA;/


-----------------------------------------------------
--EJERCICIO 4:
--NOTA:
--OBSERVACIONES:
-----------------------------------------------------
--Escribe aquí tu respuesta:
drop table CARRERAS_PAIS;/
create table CARRERAS_PAIS(
    pais varchar2(60) primary key,
    federacion varchar2(60),
    carreras V_CARRERAS
);/


-----------------------------------------------------
--EJERCICIO 5:
--NOTA:
--OBSERVACIONES:
-----------------------------------------------------
--Escribe aquí tu respuesta:
create or replace procedure INSERTAR_CARRERA(p_pais varchar2, p_carrera CARRERA) as
    v_carrera CARRERA;
    v_carreras V_CARRERAS;
    v_count number;
begin
    select carreras into v_carreras_array from CARRERAS_PAIS where pais = p_pais;
    --comprobar que la carrera esta dentro de v_carreras
    for i in 1..v_carreras.count loop
        if v_carreras(i).nombre_carrera = p_carrera.nombre_carrera and v_carreras(i).premio = p_carrera.premio then
            v_count := 1;
        end if;
    end loop;
    
    --si se ha encontrado una carrera con el mismo nombre y premio, error, si no se añade
    if v_count = 1 then
         dbms_output.put_line('LA CARRERA YA EXISTE EN ESTE PAIS');
    else
        v_carreras.extend;
        v_carreras(v_carreras.count) := p_carrera;
        update CARRERAS_PAIS set carreras = v_carreras where pais = p_pais;
    end if;
    
    commit;
    
exception 
    when no_data_found then
        dbms_output.put_line('EL PAIS NO EXISTE');
    when collection_is_null then
        dbms_output.put_line('EL PAIS NO TIENE CARRERAS PREVIAS');
        update CARRERAS_PAIS set carreras = V_CARRERAS(p_carrera) where pais = p_pais;
        commit;
end;
/


-----------------------------------------------------
--EJERCICIO 6:
--NOTA:
--OBSERVACIONES:
-----------------------------------------------------
--Escribe aquí tu respuesta:


