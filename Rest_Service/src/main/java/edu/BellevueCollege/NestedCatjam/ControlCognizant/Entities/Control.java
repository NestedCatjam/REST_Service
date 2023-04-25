package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "controls")
public class Control {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "control_id")
    private long id;

    @Column(name = "control_function")
    private String control_function;

    @Column(name = "control_category")
    private String control_category;

    @Column(name = "control_subcategory_description")
    private String control_subcategory_description;

    @Column(name = "control_subcategory_id")
    private String control_subcategory_id;

    @Column(name = "control_name")
    private String control_name;

    @Column(name = "control_description")
    private String control_description;

    // Add a new field to reference many Control entities
    @OneToMany(mappedBy = "parentControl")
    private List<Control> mappedControls;

    // Add a new field to reference the parent Control entity
    @ManyToOne
    @JoinColumn(name = "parent_control_id")
    private Control parentControl;

    public Control() {
    }

    public Control(String control_function, String control_category, String control_subcategory_description,
                   String control_subcategory_id, String control_name, String control_description) {
        this.control_function = control_function;
        this.control_category = control_category;
        this.control_subcategory_description = control_subcategory_description;
        this.control_subcategory_id = control_subcategory_id;
        this.control_name = control_name;
        this.control_description = control_description;
    }
}