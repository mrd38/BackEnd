/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.md.Controller;

import com.portfolio.md.Dto.dtoHyS;
import com.portfolio.md.Entity.HyS;
import com.portfolio.md.Security.Controller.Mensaje;
import com.portfolio.md.Service.SHyS;
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
@CrossOrigin(origins="https://front-a178b.web.app")
/*@CrossOrigin(origins= "http://localhost:4200")*/
@RequestMapping("/skill")
public class CHyS {
    @Autowired
    SHyS sHyS;
    
      @GetMapping("/lista")
    public ResponseEntity<List<HyS>> list(){
    List<HyS> list = sHyS.list();
    return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<HyS> getById(@PathVariable("id") int id){
      if (!sHyS.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        HyS hys = sHyS.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }
    
    
      @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sHyS.existsById(id)) {
            return new ResponseEntity(new Mensaje("Inexistente"), HttpStatus.NOT_FOUND);
        }
        sHyS.delete(id);
        return new ResponseEntity(new Mensaje("Eliminado correctamente"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHyS dtohys) {
        if (StringUtils.isBlank(dtohys.getNombre())) 
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if (sHyS.existsByNombre(dtohys.getNombre())) 
            return new ResponseEntity(new Mensaje("Skill existente"), HttpStatus.BAD_REQUEST);
        
         HyS hys = new HyS(dtohys.getNombre(), dtohys.getPorcentaje());
         sHyS.save(hys);
        
        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }

     @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHyS dtohys){
        
        if(!sHyS.existsById(id))
            return new ResponseEntity(new Mensaje("Id inexististente"), HttpStatus.BAD_REQUEST);
        
        if(sHyS.existsByNombre(dtohys.getNombre()) && sHyS.getByNombre(dtohys.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Skill existente"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        HyS hys = sHyS.getOne(id).get();
        hys.setNombre(dtohys.getNombre());
        hys.setPorcentaje((dtohys.getPorcentaje()));
        
        sHyS.save(hys);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);
             
    }
}
