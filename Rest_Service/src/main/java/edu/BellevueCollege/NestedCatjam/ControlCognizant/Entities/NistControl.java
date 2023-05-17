package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "nist_controls")
@Data
public class NistControl {
    public NistControl() {
    }

    @Column(name = "control_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "hitrust_mapping")
    @NonNull
    private String hitrustMapping;

    @Column(name = "is_satisfied")
    private boolean satisfied;

//    # def __init__(self, control_function, control_category, control_name, control_description, hitrust_mapping):
    public NistControl(String controlFunction, String controlCategory, String controlName, String controlDescription, String hitrustMapping) {
        this.controlFunction = controlFunction;
        this.controlCategory = controlCategory;
        this.controlName = controlName;
        this.controlDescription = controlDescription;
        this.hitrustMapping = hitrustMapping;
        this.satisfied = false;
    }
}
