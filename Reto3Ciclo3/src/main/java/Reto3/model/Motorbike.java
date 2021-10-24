/*
 * Clase:       Motorbike
 * Autor:       Wilson Rios Valencia
 * Descripción: Clase que contiene la entidad Motorbike.
 */
package Reto3.model;

import Reto3.model.Categoria;
import Reto3.model.Mensaje;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * Entidad Motorbike, manejo de la tabla para la BdD
 */
@Entity
@Table(name="motorbike")
public class Motorbike implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String brand;
    private Integer year;
    private String description;
    //private Integer categoryId;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonIgnoreProperties("motorbikes")
    private Categoria category;

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "motorbike")
    @JsonIgnoreProperties({"motorbike", "client"})
    private List<Mensaje> messages;

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "motorbike")
    @JsonIgnoreProperties({"motorbike", "client"})
    private List<Reservaciones> reservations;
    
    public Integer getId() {
        return id;
    }

    //Set para asignar el id
    public void setId(Integer id) {
        this.id = id;
    }

    //Get para obtener la marca
    public String getBrand() {
        return brand;
    }

    //Set para asignar la marca
    public void setBrand(String brand) {
        this.brand = brand;
    }

    //Get para obtener el modelo
    public Integer getYear() {
        return year;
    }

    //Set para asignar el año
    public void setYear(Integer year) {
        this.year = year;
    }

    //Get para obtener el nombre
    public String getName() {
        return name;
    }

    //Set para asignar el nombre
    public void setName(String name) {
        this.name = name;
    }

    //Get para obtener la descripcion
    public String getDescription() {
        return description;
    }

    //Set para asignar la desripción
    public void setDescription(String description) {
        this.description = description;
    }

    public Categoria getCategory() {
        return category;
    }

    public void setCategory(Categoria category) {
        this.category = category;
    }

    public List<Mensaje> getMessages() {
        return messages;
    }

    public void setMessages(List<Mensaje> messages) {
        this.messages = messages;
    }

    public List<Reservaciones> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservaciones> reservations) {
        this.reservations = reservations;
    }
    
}
