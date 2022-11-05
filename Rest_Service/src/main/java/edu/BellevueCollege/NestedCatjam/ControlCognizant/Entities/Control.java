package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "control")
public class Control implements ControlCognizantEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "subtype", nullable = false)
    private String subtype;
    @OneToOne(cascade = CascadeType.ALL)
    private Control mapping;

    public Control() {
    }

    public Control(Long id, String name, String description, String type, Control mapping) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.mapping = mapping;
    }
}