package com.barkovsky.serverWebcarrot.model;


import com.barkovsky.serverWebcarrot.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@NoArgsConstructor

@Entity
@Table(name = "collections")
public class Collections {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "published")
    private boolean published;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore

@JsonIgnore
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", referencedColumnName = "id")
@OnDelete(action = OnDeleteAction.CASCADE)
private User user;

    public Collections(String title, String description, boolean published, User user) {
        this.title = title;
        this.description = description;
        this.published = published;
        this.user = user;
    }

//    @Override
//    public String toString () {
//        return "Collections [id=" + id + ", title=" + title + ", desc=" + description + "]";
//    }

}
