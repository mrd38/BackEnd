/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.md.Interface;

import com.portfolio.md.Entity.Persona;
import java.util.List;


public interface IPersonaService {
       //traer lista persona
    public List<Persona> getPersona();
    
    //Guardar objeto tipo persona
    public void savePersona(Persona persona);
    
    //eliminar objeto ppor id
    public void deletePersona(Long id);
    
    //buscar por id
    public Persona findPersona(Long id);
    
}
