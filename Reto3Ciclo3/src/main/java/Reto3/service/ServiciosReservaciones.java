/**
 * Clase:       ServiciosReservaciones.Java
 * Autor:       Wilson Rios Valencia
 * Descripción: Clase que contiene los servicios rest para las reservaciones.
 */
package Reto3.service;

import Reto3.model.ContadorClientes;
import Reto3.model.Reservaciones;
import Reto3.model.StatusReservas;
import Reto3.repository.crud.RepositorioReservaciones;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Anotación para que spring tome el servicio
 */
@Service
public class ServiciosReservaciones {

    /**
     * método que permite cargar los mpetodos CRUD de reservaciones
     */
    @Autowired
    private RepositorioReservaciones metodosCrud;

    /**
     * método que permite obtener una lista de todas las reservaciones
     */
    public List<Reservaciones> getAll() {
        return metodosCrud.getAll();
    }

    /**
     * método que permite cobtener una reservacion por su ID
     */
    public Optional<Reservaciones> getReservation(int reservationId) {
        return metodosCrud.getReservation(reservationId);
    }

    /**
     * método que permite guardar una reservación
     */
    public Reservaciones save(Reservaciones reservation) {
        if (reservation.getIdReservation() == null) {
            return metodosCrud.save(reservation);
        } else {
            Optional<Reservaciones> e = metodosCrud.getReservation(reservation.getIdReservation());
            if (e.isEmpty()) {
                return metodosCrud.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    /**
     * método que permite actualizar una reservación
     */
    public Reservaciones update(Reservaciones reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservaciones> e = metodosCrud.getReservation(reservation.getIdReservation());
            if (!e.isEmpty()) {

                if (reservation.getStartDate() != null) {
                    e.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    e.get().setStatus(reservation.getStatus());
                }
                metodosCrud.save(e.get());
                return e.get();
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
    }

    /**
     * método que permite eliminar un registro de reservación
     */
    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    //Método deleteReservation, recibe una entidad de tipo Reservaciones
    public StatusReservas getRepStatusRes(){
        List<Reservaciones> completed = metodosCrud.ReservationStatus("completed");
        List<Reservaciones> cancelled = metodosCrud.ReservationStatus("cancelled");
        return new StatusReservas(completed.size(), cancelled.size());
    }
    
    /**
     * método que permite obtener un reporte de reservaciones entre dos fechas dadas
     */
    public List<Reservaciones> reporteTiempoServicios(String dateA, String dateB) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date a = new Date();
        Date b = new Date();
        try{
            a = parser.parse(dateA);
            b = parser.parse(dateB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }
        if (a.before(b)) {
            return metodosCrud.ReservacionTiempoRepositorio(a, b);
        } else {
            return new ArrayList<>();
        }
    }
    
    /**
     * método que permite obtener un reporte de servicios por clientes
     */
    public List<ContadorClientes> reporteClientesServicios(){
        return metodosCrud.getClientesRepositorio();
    }
    
}
    
