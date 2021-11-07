/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reto3.interfaces;

import Reto3.model.Reservaciones;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author USUARIO
 */
public interface InterfaceReservaciones extends CrudRepository<Reservaciones,Integer>{
    public List<Reservaciones> findAllByStatus(String status);
    public List<Reservaciones> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);
    
    @Query("SELECT c.client, COUNT(c.client) FROM Reservaciones AS c GROUP BY c.client order by COUNT(c.client)DESC")

    public List<Object[]> countTotalReservationsByClient();
    
}
