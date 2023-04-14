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
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE)
    @Column(name = "control_id")
    private long control_id;

    @Column(name = "control_function")
    private String control_function;

    @Column(name = "control_category")
    private String control_category;

    @Column(name = "control_subcategory_description")
    private String control_subcategory_description;

    @Column(name = "control_subcategory_id")
    private String control_subcategory_id;

    @Column(name = "control_name")
    @Size(min = 2, message= "Name must be longer than 2 characters")
    private String control_name;

    @Column(name = "control_description")
    private String control_description;

    @OneToMany(mappedBy = "equivalent_control")
    private List<Control> mapped_controls;

    @ManyToOne
    @JoinColumn(name = "equivalent_control")
    private Control equivalent_control;

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