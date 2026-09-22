package com.aydsii.tp2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class ClienteDTO {
    private String nombre;

    private String apellido;

    private String email;

    private String telefono;
}
