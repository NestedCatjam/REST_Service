package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "hitrust_controls")
@Data
public class HitrustControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "control_id")
    private long id;

    @Column(name = "control_function")
    @NonNull
    private String controlFunction;

    @Column(name = "control_category")
    @NonNull
    private String controlCategory;

    @Column(name = "control_name")
    @NonNull
    private String controlName;

    @Column(name = "control_description")
    @NonNull
    private String controlDescription;

    @Column(name = "is_satisfied")
    private boolean satisfied;

    @Column(name = "nist_mapping")
    @NonNull
    public String nistMapping;


//# def __init__(self, control_function, control_category, control_name, control_description, nist_mapping):
        public HitrustControl(String controlFunction, String controlCategory, String controlName, String controlDescription, String nistMapping) {
        this.controlFunction = controlFunction;
        this.controlCategory = controlCategory;
        this.controlName = controlName;
        this.controlDescription = controlDescription;
        this.nistMapping = nistMapping;
        this.satisfied = false;
    }

    public HitrustControl() {
    }
}