package com.example.pc.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class App {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please fill")
    @Length(max = 2048, message = "too long (more than 2kB)")
    private String modelpc;
    @Length(max = 255, message = "too long (more than 255)")
    private String reason;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

   // private String filename;

    public App() {
    }

    public App(@NotBlank(message = "Please fill") @Length(max = 2048, message = "too long (more than 2kB)") String modelpc, @Length(max = 255, message = "too long (more than 255)") String reason, User author) {
        this.modelpc = modelpc;
        this.reason = reason;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelpc() {
        return modelpc;
    }

    public void setModelpc(String modelpc) {
        this.modelpc = modelpc;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }


}
