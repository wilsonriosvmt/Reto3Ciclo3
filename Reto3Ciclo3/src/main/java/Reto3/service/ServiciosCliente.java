/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3.service;

import Reto3.model.Client;
import Reto3.repository.crud.RepositorioCliente;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service
public class ServiciosCliente {
       @Autowired
     private RepositorioCliente metodosCrud;
     
     public List<Client> getAll(){
        return metodosCrud.getAll();
    }
     
      public Optional<Client> getClient(int clientId) {
        return metodosCrud.getCliente(clientId);
    }

    public Client save(Client client){
        if(client.getIdClient()==null){
            return metodosCrud.save(client);
        }else{
            Optional<Client> e= metodosCrud.getCliente(client.getIdClient());
            if(e.isEmpty()){
                return metodosCrud.save(client);
            }else{
                return client;
            }
        }
    }

    public Client update(Client client){
        if(client.getIdClient()!=null){
            Optional<Client> e= metodosCrud.getCliente(client.getIdClient());
            if(!e.isEmpty()){
                if(client.getName()!=null){
                    e.get().setName(client.getName());
                }
                if(client.getAge()!=null){
                    e.get().setAge(client.getAge());
                }
                if(client.getPassword()!=null){
                    e.get().setPassword(client.getPassword());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return client;
            }
        }else{
            return client;
        }
    }

    public boolean deleteClient(int clientId) {
        Boolean aBoolean = getClient(clientId).map(client -> {
            metodosCrud.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
