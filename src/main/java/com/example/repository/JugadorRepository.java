package com.example.repository;

import com.example.domain.Car;
import com.example.domain.Equipo;
import com.example.domain.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    //Spring Data Queries

    List<Jugador> findByNombre(String nombre);
    List<Jugador> findByCanastasGreaterThanEqual(int canastas);
    List<Jugador> findByAsistenciasBetween(int min, int max);
    List<Jugador> findByPosicionEquals(String posicion);
    List<Jugador> findByFechaNacimientoLessThan(LocalDate fechaNacimiento);

    //Query Equipo
    List<Jugador> findByEquipoNombre(String nombre);
    List<Jugador> findByEquipoNombreAndPosicion(String nombre, String posicion);
/*
    @Query( "SELECT jugador "+
            "FROM Jugador jugador "+
            "WHERE jugador.equipo.nombre= :nombre"
    )

    @Query( "SELECT jugador "+
            "FROM Jugador jugador "+
            "WHERE jugador.equipo.nombre=:nombre AND :maxCanastas"
    )
    List<Object[]> JugadorConMasCanastasPorEquipo();*/

    //@Query("SELECT j FROM Jugador j where j.equipo.nombre = :nombreEquipo order by j.canastasTotales desc ")
    //List<Jugador> findCanastasOrderByCanastasTotales(@Param("nombreEquipo") String nombreEquipo);
    @Query("SELECT jugador FROM Jugador jugador where jugador.equipo.nombre =:nombre order by jugador.canastas desc")
    List<Jugador> findCanastasOrderByCanastasTotales(@Param("nombre") String nombre);


    @Query("SELECT jugador.posicion, AVG(jugador.canastas), AVG(jugador.asistencias), AVG(jugador.rebotes) "+
            "FROM Jugador jugador " +
            "GROUP BY jugador.posicion")
    List<Object[]> AvgCanastasAsistenciasRebotesPorPosicion();

    @Query("SELECT jugador.posicion, AVG(jugador.canastas), AVG(jugador.asistencias), AVG(jugador.rebotes), MAX(jugador.canastas), MAX(jugador.asistencias), MAX(jugador.rebotes), MIN(jugador.canastas), MIN(jugador.asistencias), MIN(jugador.rebotes) "+
            "FROM Jugador jugador " +
            "GROUP BY jugador.posicion")
    List<Object[]> AvgCanastasAsistenciasRebotesMinMaxPorPosicion();


}

