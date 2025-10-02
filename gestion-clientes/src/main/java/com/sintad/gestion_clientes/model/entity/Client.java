package com.sintad.gestion_clientes.model.entity;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Table("clientes")
public class Client {
    @Id
    private Long id;

    @Column("nombre")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String nombre;

    @Column("apellido")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String apellido;

    @Column("correo")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
            message = "Invalid correo format")
    private String correo;

    @Column("telefono")
    @Pattern(regexp = "^\\d{9}$",
            message = "Invalid telefono format")
    private String telefono;

    @Column("fecha_registro")
    @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss")
    private LocalDateTime fechaRegistro;

    @Column("estado")
    private Boolean estado;

}
