package com.report.wantedpreonboardingbackend.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "apply")
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "employment_id")
    private Employment employment;

    public Apply() {
    }

    public Apply(User user, Employment employment) {
        this.user = user;
        this.employment = employment;
    }

    public User getUser() {
        return user;
    }

    public Employment getEmployment() {
        return employment;
    }

    @Override
    public String toString() {
        return "Apply [id=" + id + ", user=" + user + ", employment=" + employment + "]";
    }
}
