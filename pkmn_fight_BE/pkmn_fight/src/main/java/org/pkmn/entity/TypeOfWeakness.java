package org.pkmn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "type_of_weakness")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeOfWeakness {
    @Id
    int id;
    @Column(name="type_of_weakness")
    int typeOfWeakness;
    int value;
}
