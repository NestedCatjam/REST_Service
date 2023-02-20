package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Evidence {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    private long id;

    @Column(name = "evidence_name")
    private String name;

    @Column(name = "evidence_description")
    private String description;

    @Column(name = "evidence_type")
    private String type;

    @OneToMany(mappedBy = "evidence")
    private List<ChatLog> chatlog;

    @Lob
    private Blob file;


    public Evidence(long id, String name, String description, String type, List<ChatLog> chatlog, String file) throws SerialException, SQLException {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.chatlog = chatlog;
        this.file = new SerialBlob(Base64.decodeBase64(file));
    }
}