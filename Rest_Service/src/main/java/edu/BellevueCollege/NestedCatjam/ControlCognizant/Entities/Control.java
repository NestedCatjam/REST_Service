package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "controls")
public class Control {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE)
    @Column(name = "control_id")
    private long id;

    @Column(name = "control_function")
    private String controlFunction;

    @Column(name = "control_category")
    private String controlCategory;

    @Column(name = "control_subcategory_description")
    private String controlCategoryDescription;

    @Column(name = "control_subcategory_id")
    private String controlSubCategoryId;

    @Column(name = "Control_Name")
    @Size(min = 2, message= "Name must be longer than 2 characters")
    private String name;

    @Column(name = "control_description")
    private String description;

    @Column(name= "control_mapping_id")
    private long mapping;
}
