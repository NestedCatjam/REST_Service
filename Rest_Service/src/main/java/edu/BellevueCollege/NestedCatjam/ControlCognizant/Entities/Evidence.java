package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "evidence")
public class Evidence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evidence_id")
    private long id;

    @Column(name = "evidence_name")
    private String name;

    @Column(name = "evidence_description")
    private String description;

    @Column(name = "evidence_type")
    private String type;

    @Column(name = "evidence_file")
    String base64;

    @Column(name = "contributor_auth0_id")
    private String contributorAuth0ID;

    @Column(name = "nist_control_id")
    private long nistControlId;

    @Column(name = "chat_id")
    private String chatid;

    @Column(name = "organization_id")
    private long organizationID;

    public Evidence() {
    }

    public Evidence(String name, String description, String type, String base64,
                    String contributorAuth0ID, long nistControlId, String chatid, long organizationID) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.base64 = base64;
        this.contributorAuth0ID = contributorAuth0ID;
        this.nistControlId = nistControlId;
        this.chatid = chatid;
        this.organizationID = organizationID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getContributorAuth0ID() {
        return contributorAuth0ID;
    }

    public void setContributorAuth0ID(String contributorAuth0ID) {
        this.contributorAuth0ID = contributorAuth0ID;
    }

    public long getNistControlId() {
        return nistControlId;
    }

    public void setNistControlId(long nistControlId) {
        this.nistControlId = nistControlId;
    }

    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid;
    }

    public long getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(long organizationID) {
        this.organizationID = organizationID;
    }
}