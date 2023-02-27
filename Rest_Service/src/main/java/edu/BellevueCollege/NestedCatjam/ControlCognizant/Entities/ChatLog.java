//
//package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Data
//@Table(name = "chatlog")
//public class ChatLog {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "chatlog_id")
//    private long id;
//
//    @OneToMany(mappedBy = "chatlog", cascade = CascadeType.ALL)
//    private List<Post> posts = new ArrayList<>();
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "evidence_id")
//    private Evidence evidence;
//}