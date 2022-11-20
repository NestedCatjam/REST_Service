package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "control")
public class Control{
    @Id
    @Column(name = "control_id", nullable = false)
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "Control_Name", nullable = false)
    private String name;

    @Column(name = "control_description", nullable = false)
    private String description;

    @Column(name = "control_type", nullable = false)
    private String type;

    @Column(name = "control_subtype", nullable = false)
    private String subtype;

    @OneToOne(cascade = CascadeType.ALL)
    private Control mapping;
}