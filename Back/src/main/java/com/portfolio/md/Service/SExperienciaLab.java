/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.md.Service;

import com.portfolio.md.Entity.ExperienciaLab;
import com.portfolio.md.Repository.RExperienciaLab;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SExperienciaLab {
    @Autowired
    RExperienciaLab rExperienciaLab;
    
    public List<ExperienciaLab> list(){
    return rExperienciaLab.findAll();
    }
    
    
    public Optional<ExperienciaLab> getOne(int id){
    return rExperienciaLab.findById(id);
    }
                  
    public Optional<ExperienciaLab> getByNombreExp(String nombreExp){
    return rExperienciaLab.findByNombreExp(nombreExp);
    }
    
    public void save(ExperienciaLab exp){
    rExperienciaLab.save(exp);
    }
    
     public void delete(int id){
         rExperienciaLab.deleteById(id);
     }
     
    public boolean existsById(int id){
    return rExperienciaLab.existsById(id);
    }
    
    public boolean existsByNombreExp(String nombreExp){
    return rExperienciaLab.existsByNombreExp(nombreExp);
    }
}
