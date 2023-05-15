package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "nist_controls")
public class NistControl {
    @Column(name = "control_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "control_function")
    private String controlFunction;

    @Column(name = "control_category")
    private String controlCategory;

    @Column(name = "control_name")
    private String controlName;

    @Column(name = "control_description")
    private String controlDescription;

    @Column(name = "hitrust_mapping")
    private String hitrustMapping;

    @Column(name = "is_satisfied")
    private boolean satisfied;


    public NistControl() {
    }

//         def __init__(self, control_function, control_category, control_name, control_description, hitrust_mapping):
//        self.control_function = control_function
//        self.control_category = control_category
//        self.control_name = control_name
//        self.control_description = control_description
//        self.hitrust_mapping = hitrust_mapping

    public NistControl(String control_function, String control_category, String control_name, String control_description, String hitrust_mapping) {
        this.controlFunction = control_function;
        this.controlCategory = control_category;
        this.controlName = control_name;
        this.controlDescription = control_description;
        this.hitrustMapping = hitrust_mapping;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getControlFunction() {
        return controlFunction;
    }

    public void setControlFunction(String controlFunction) {
        this.controlFunction = controlFunction;
    }

    public String getControlCategory() {
        return controlCategory;
    }

    public void setControlCategory(String controlCategory) {
        this.controlCategory = controlCategory;
    }

    public String getControlName() {
        return controlName;
    }

    public void setControlName(String controlName) {
        this.controlName = controlName;
    }

    public String getControlDescription() {
        return controlDescription;
    }

    public void setControlDescription(String controlDescription) {
        this.controlDescription = controlDescription;
    }

    public String getHitrustMapping() {
        return hitrustMapping;
    }

    public void setHitrustMapping(String hitrustMapping) {
        this.hitrustMapping = hitrustMapping;
    }

    public boolean isSatisfied() {
        return satisfied;
    }

    public void setSatisfied(boolean satisfied) {
        this.satisfied = satisfied;
    }
}
