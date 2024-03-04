package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cliente implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private Long totalVentas;
    private String estado;

}
