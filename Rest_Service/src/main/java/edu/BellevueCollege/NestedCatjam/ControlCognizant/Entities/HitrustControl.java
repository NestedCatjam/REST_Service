package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "hitrust_controls")
public class HitrustControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "control_id")
    private long id;

    @Column(name = "control_function")
    private String controlFunction;

    @Column(name = "control_category")
    private String controlCategory;

    @Column(name = "control_name")
    private String controlName;

    @Column(name = "control_description")
    private String controlDescription;

    @Column(name = "is_satisfied")
    private boolean satisfied;

    @Column(name = "nist_mapping")
    public String nistMapping;

    public HitrustControl() {
    }

//   def __init__(self, control_function, control_category, nist_mapping, control_name, control_description):
//        self.control_function = control_function
//        self.control_category = control_category
//        self.nist_mapping = nist_mapping
//        self.control_name = control_name
//        self.control_description = control_description
    public HitrustControl(String control_function, String control_category, String control_name, String control_description, String nist_mapping) {
        this.controlFunction = control_function;
        this.controlCategory = control_category;
        this.controlName = control_name;
        this.controlDescription = control_description;
        this.nistMapping = nist_mapping;
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

    public boolean isSatisfied() {
        return satisfied;
    }

    public void setSatisfied(boolean satisfied) {
        this.satisfied = satisfied;
    }

    public String getNistMapping() {
        return nistMapping;
    }

    public void setNistMapping(String nistMapping) {
        this.nistMapping = nistMapping;
    }
}