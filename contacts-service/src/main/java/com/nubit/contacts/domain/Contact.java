package com.nubit.contacts.domain;

import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import jakarta.validation.constraints.*;

@Table("contacts")
public class Contact {

    @Id
    private UUID id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String nombre;

    @Size(max = 200)
    private String direccion;

    @Pattern(regexp = "^[0-9+()\\-\\s]{7,20}$", message = "telefono invalido")
    private String telefono;

    @NotBlank
    @Email
    private String email;

    public Contact() {}

    public Contact(UUID id, String nombre, String direccion, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
