package com.aydsii.tp2.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Datos para registrar un cliente")
public class ClienteDTO {
    @Schema(description = "Nombre del cliente (mínimo 2 caracteres)", example = "Ana")
    @NotBlank(message = "no puede estar vacío")
    @Size(min = 2, message = "debe tener al menos 2 caracteres")
    private String nombre;

    @Schema(description = "Apellido del cliente (mínimo 2 caracteres)", example = "Garcia")
    @NotBlank(message = "no puede estar vacío")
    @Size(min = 2, message = "debe tener al menos 2 caracteres")
    private String apellido;

    @Schema(description = "Email del cliente, no puede estar registrado previamente", example = "ana.garcia@mail.com")
    @NotBlank(message = "es obligatorio")
    @Email(message = "debe ser un email válido")
    private String email;

    @Schema(description = "Teléfono del cliente (opcional, solo dígitos)", example = "3814567890")
    @Pattern(regexp = "\\d+", message = "solo debe contener dígitos")
    private String telefono;
}
