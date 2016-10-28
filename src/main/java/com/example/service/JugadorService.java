package com.example.service;

import com.example.domain.Car;
import com.example.domain.Equipo;
import com.example.domain.Jugador;
import com.example.repository.CarRepository;
import com.example.repository.EquipoRepository;
import com.example.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private EquipoRepository equipoRepository;


    public void testJugadores() {

        //id nombre fecha canasta asistencia rebote posicion

        Equipo equipo1 = new Equipo("Los Txurruca", "Muchamiel", LocalDate.of(2000,11,13));
        equipoRepository.save(equipo1);
        Equipo equipo2 = new Equipo("All-Star",     "America",   LocalDate.of(1990,1,21));
        equipoRepository.save(equipo2);
        Equipo equipo3 = new Equipo("Equipo 3",     "Italia",    LocalDate.of(1980,3, 12));
        equipoRepository.save(equipo3);
        Equipo equipo4 = new Equipo("Chatarreros",  "España",    LocalDate.of(1977,4, 30));
        equipoRepository.save(equipo4);
        Equipo equipo5 = new Equipo("Jackson Five", "America",   LocalDate.of(1968,5,23));
        equipoRepository.save(equipo5);


        Jugador jugador1 = new Jugador("Eduardo-Amilcar", LocalDate.of(1992, 10, 21), 221, 454, 432, "Base");
        jugador1.setEquipo(equipo1);
        jugadorRepository.save(jugador1);

        Jugador jugador2 = new Jugador("Juanito Valderrama", LocalDate.of(1999, 3, 23), 891, 94, 4382, "Alero");
        jugador2.setEquipo(equipo2);
        jugadorRepository.save(jugador2);

        Jugador jugador3 = new Jugador("Tomate", LocalDate.of(1934, 5, 13), 781, 254, 32, "Pivot");
        jugador3.setEquipo(equipo3);
        jugadorRepository.save(jugador3);

        Jugador jugador4 = new Jugador("Pato Lucas", LocalDate.of(1934, 9, 18), 89, 44, 4382, "Alero");
        jugador4.setEquipo(equipo4);
        jugadorRepository.save(jugador4);

        Jugador jugador5 = new Jugador("Rukawa", LocalDate.of(2000, 9, 15), 721, 954, 432, "Pivot");
        jugador5.setEquipo(equipo5);
        jugadorRepository.save(jugador5);

        Jugador jugador6 = new Jugador("Pato Lucas ATK Mode", LocalDate.of(1922,9,9), 2399,2,32, "Base");
        jugador6.setEquipo(equipo4);
        jugadorRepository.save(jugador6);


        System.out.println("Buscar jugadores por nombre: ");
        System.out.println(jugadorRepository.findByNombre("Eduardo-Amilcar"));

        System.out.println("Buscar jugadores con canastas mayores que: ");
        System.out.println(jugadorRepository.findByCanastasGreaterThanEqual(222));

        System.out.println("Buscar jugadores con asistencias entre: ");
        System.out.println(jugadorRepository.findByAsistenciasBetween(200, 500));

        System.out.println("Buscar jugadores de la posición: ");
        System.out.println(jugadorRepository.findByPosicionEquals("Pivot"));

        System.out.println("Buscar jugadores nacidos antes de: ");
        System.out.println(jugadorRepository.findByFechaNacimientoLessThan(LocalDate.of(1992, 1, 1)));

        System.out.println("Media de canastas asistencias y rebotes de jugadores en la posición: ");
        System.out.println(jugadorRepository.AvgCanastasAsistenciasRebotesPorPosicion());
        jugadorRepository.AvgCanastasAsistenciasRebotesPorPosicion().forEach(posicion
                -> System.out.println(posicion[0] + " media canastas:" + posicion[1] + "media asistencia:" + posicion[2] + "media rebotes" + posicion[3]));

        System.out.println("Media máximo y mínimo de jugadores en la posición: ");
        System.out.println(jugadorRepository.AvgCanastasAsistenciasRebotesMinMaxPorPosicion());
        jugadorRepository.AvgCanastasAsistenciasRebotesMinMaxPorPosicion().forEach(posicion
                -> System.out.println(
                "Posicion: " + posicion[0] + " media canastas: " + posicion[1] + " media asistencia:" + posicion[2] + "media rebotes" + posicion[3] +
                        "Max canastas: " + posicion[4] + " Max asistencias: " + posicion[5] + " Max rebotes: " + posicion[6] +
                        "Min canastas: " + posicion[7] + " Min asistencias: " + posicion[8] + " Min rebotes: " + posicion[9]));


        System.out.println("Equipos existentes en una localidad determinada: ");
        System.out.println(equipoRepository.findByLocalidad("America"));

        System.out.println("Todos los jugadores del equipo: ");
        System.out.println(jugadorRepository.findByEquipoNombre("Chatarreros"));

        System.out.println("Todos los jugadores del equipo por posicion: ");
        System.out.println(jugadorRepository.findByEquipoNombreAndPosicion("Chatarreros", "Alero"));

        //System.out.println("Jugador con mas canastas por equipo: ");
        //System.out.println(jugadorRepository.JugadorConMasCanastasPorEquipo());

        System.out.println("Jugador con mas canastas por equipo: ");
        System.out.println(jugadorRepository.findCanastasOrderByCanastasTotales("Chatarreros").get(0));
    }}
