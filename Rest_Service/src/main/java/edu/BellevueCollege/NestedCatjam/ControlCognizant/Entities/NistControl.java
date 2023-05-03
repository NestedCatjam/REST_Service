package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "nist_controls")
@Data
public class NistControl {
    @Column(name = "control_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "control_function")
    private String control_function;

    @Column(name = "control_category")
    private String control_category;

    @Column(name = "control_name")
    private String control_name;

    @Column(name = "control_description")
    private String control_description;

    @Column(name = "hitrust_mapping")
    private String hitrust_mapping;

    @Column(name = "is_satisfied")
    private boolean is_satisfied;


    public NistControl() {
    }

//         def __init__(self, control_function, control_category, control_name, control_description, hitrust_mapping):
//        self.control_function = control_function
//        self.control_category = control_category
//        self.control_name = control_name
//        self.control_description = control_description
//        self.hitrust_mapping = hitrust_mapping

    public NistControl(String control_function, String control_category, String control_name, String control_description, String hitrust_mapping) {
        this.control_function = control_function;
        this.control_category = control_category;
        this.control_name = control_name;
        this.control_description = control_description;
        this.hitrust_mapping = hitrust_mapping;
    }
}
