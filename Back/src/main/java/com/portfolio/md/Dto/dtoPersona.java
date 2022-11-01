/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.md.Dto;

import javax.validation.constraints.NotBlank;

public class dtoPersona {
    
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
 
    @NotBlank
    private String img;
    
    
    //Const

    public dtoPersona() {
    }

    public dtoPersona(String nombre, String apellido, String img) {
        this.nombre = nombre;
        this.apellido = apellido;
        
        this.img = img;
    }
    
    
    //G&S

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
}
