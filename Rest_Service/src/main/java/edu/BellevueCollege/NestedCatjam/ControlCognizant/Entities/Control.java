package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Data
@Table(name = "controls")
public class Control{
    @Id
    @GeneratedValue
    @Column(name = "control_id")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "Control_Name")
    @Size(min = 2, message= "Name must be longer than 2 characters")
    private String name;

    @Column(name = "control_description")
    private String description;

    @Column(name = "control_type")
    private String type;

    @Column(name = "control_subtype")
    private String subtype;

    @OneToOne(cascade = CascadeType.ALL)
    // @JsonIgnore might cause an infinite loop if removed.  I'm not sure.
    @JsonIgnore
    private Control mapping;
}
