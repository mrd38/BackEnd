/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.md.Controller;

import com.portfolio.md.Dto.dtoExperienciaLab;
import com.portfolio.md.Entity.ExperienciaLab;
import com.portfolio.md.Security.Controller.Mensaje;
import com.portfolio.md.Service.SExperienciaLab;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/explab")
@CrossOrigin(origins="https://front-a178b.web.app")
/*@CrossOrigin(origins = "http://localhost:4200")*/
public class CExperienciaLab {
    @Autowired
    SExperienciaLab sExperienciaLab;
    
    @GetMapping("/lista")
    public ResponseEntity<List<ExperienciaLab>> list(){
    List<ExperienciaLab> list = sExperienciaLab.list();
    return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/details/{id}")
    public ResponseEntity<ExperienciaLab> getById(@PathVariable("id") int id){
      if (!sExperienciaLab.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        ExperienciaLab experienciaLab = sExperienciaLab.getOne(id).get();
        return new ResponseEntity(experienciaLab, HttpStatus.OK);
    }
    
    
      @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sExperienciaLab.existsById(id)) {
            return new ResponseEntity(new Mensaje("Inexistente"), HttpStatus.NOT_FOUND);
        }
        sExperienciaLab.delete(id);
        return new ResponseEntity(new Mensaje("Eliminado correctamente"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoExperienciaLab dtoexperienciaLab) {
        if (StringUtils.isBlank(dtoexperienciaLab.getNombreExp())) 
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if (sExperienciaLab.existsByNombreExp(dtoexperienciaLab.getNombreExp())) 
            return new ResponseEntity(new Mensaje("Experiencia existente"), HttpStatus.BAD_REQUEST);
        
        ExperienciaLab experienciaLab = new ExperienciaLab(dtoexperienciaLab.getNombreExp(), dtoexperienciaLab.getDescripcionExp());
        sExperienciaLab.save(experienciaLab);
        
        return new ResponseEntity(new Mensaje("Experiencia agregada"), HttpStatus.OK);
    }

     @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperienciaLab dtoexperienciaLab){
        
        if(!sExperienciaLab.existsById(id))
            return new ResponseEntity(new Mensaje("ID no exististente"), HttpStatus.BAD_REQUEST);
        
        if(sExperienciaLab.existsByNombreExp(dtoexperienciaLab.getNombreExp()) && sExperienciaLab.getByNombreExp(dtoexperienciaLab.getNombreExp()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoexperienciaLab.getNombreExp()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        ExperienciaLab experienciaLab = sExperienciaLab.getOne(id).get();
        experienciaLab.setNombreExp(dtoexperienciaLab.getNombreExp());
        experienciaLab.setDescripcionExp((dtoexperienciaLab.getDescripcionExp()));
        
        sExperienciaLab.save(experienciaLab);
        return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
             
    }
    
}
