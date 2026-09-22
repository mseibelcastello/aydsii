package com.aydsii.tp2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    @NotBlank(message = "no puede estar vacio")
    @Size(min = 2, message = "debe tener al menos 2 caracteres")
    private String nombre;

    @NotBlank(message = "no puede estar vacio")
    @Size(min = 2, message = "debe tener al menos 2 caracteres")
    private String apellido;

    @NotBlank(message = "es obligatorio")
    @Email(message = "debe ser un email valido")
    private String email;

    @Pattern(regexp = "\\d+", message = "solo debe contener digitos")
    private String telefono;
}
