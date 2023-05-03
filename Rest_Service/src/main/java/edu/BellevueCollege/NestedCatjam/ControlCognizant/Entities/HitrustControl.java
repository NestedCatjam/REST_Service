package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "hitrust_controls")
public class HitrustControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "control_id")
    private long id;

    @Column(name = "control_function")
    private String control_function;

    @Column(name = "control_category")
    private String control_category;

    @Column(name = "control_name")
    private String control_name;

    @Column(name = "control_description")
    private String control_description;

    @Column(name = "is_satisfied")
    private boolean is_satisfied;

    @Column(name = "nist_mapping")
    public String nist_mapping;

    public HitrustControl() {
    }

//   def __init__(self, control_function, control_category, nist_mapping, control_name, control_description):
//        self.control_function = control_function
//        self.control_category = control_category
//        self.nist_mapping = nist_mapping
//        self.control_name = control_name
//        self.control_description = control_description
    public HitrustControl(String control_function, String control_category, String control_name, String control_description, String nist_mapping) {
        this.control_function = control_function;
        this.control_category = control_category;
        this.control_name = control_name;
        this.control_description = control_description;
        this.nist_mapping = nist_mapping;
    }
}