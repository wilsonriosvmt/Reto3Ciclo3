/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3.service;

import java.util.List;
import java.util.Optional;
import Reto3.model.Motorbike;
import Reto3.model.Motorbike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Reto3.repository.crud.MotoRepository;


/**
 *
 * @author Will
 */
@Service
public class MotoService {
    
    @Autowired
    private MotoRepository motoRepository;
    
    public List<Motorbike> getAll(){
        return motoRepository.getAll();
    }
    
    public Optional<Motorbike> getMoto(int id){
        return motoRepository.getMoto(id);
    }
    
    public Motorbike save(Motorbike m){
        if(m.getId()==null){
            return motoRepository.save(m);
        }else{
            Optional<Motorbike> maux=motoRepository.getMoto(m.getId());
            if(maux.isEmpty()){
                return motoRepository.save(m); 
            }else{
                return m;
            }
        }
    }
    
    public Motorbike update(Motorbike motorbike){
        if(motorbike.getId()!=null){
            Optional<Motorbike> e=motoRepository.getMoto(motorbike.getId());
            if(!e.isEmpty()){
                if(motorbike.getName()!=null){
                    e.get().setName(motorbike.getName());
                }
                if(motorbike.getBrand()!=null){
                    e.get().setBrand(motorbike.getBrand());
                }
                if(motorbike.getYear()!=null){
                    e.get().setYear(motorbike.getYear());
                }
                if(motorbike.getDescription()!=null){
                    e.get().setDescription(motorbike.getDescription());
                }
                if(motorbike.getCategory()!=null){
                    e.get().setCategory(motorbike.getCategory());
                }
                motoRepository.save(e.get());
                return e.get();
            }else{
                return motorbike;
            }
        }else{
            return motorbike;
        }
    }

    public boolean deleteMoto(int motoId) {
        Boolean aBoolean = getMoto(motoId).map(motorbike -> {
            motoRepository.delete(motorbike);
            return true;
        }).orElse(false);
        return aBoolean;
    }

}
