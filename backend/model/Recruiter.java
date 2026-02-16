package com.relink.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name ="recruiters")
@Getter
@Setter
public class Recruiter {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="recruiterId",nullable = false,updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(name="username",nullable = true,unique = true)
    private String username;

    @Column(nullable = false,unique = true)
    private String email;

    @OneToMany(mappedBy = "recruiter")
    private List<Job> listOfCreatedJobs;

    public Recruiter() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Recruiter recruiter = (Recruiter) o;
        return Objects.equals(id, recruiter.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Recruiter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", Username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
