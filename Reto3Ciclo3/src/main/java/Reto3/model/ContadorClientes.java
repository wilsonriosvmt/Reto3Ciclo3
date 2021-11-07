/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3.model;

/**
 *
 * @author Will
 */
public class ContadorClientes {
    private Long total;
    private Cliente client;

    public ContadorClientes(Long total, Cliente cliente) {
        this.total = total;
        this.client = cliente;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return client;
    }

    public void setCliente(Cliente cliente) {
        this.client = cliente;
    }
    
    
}
