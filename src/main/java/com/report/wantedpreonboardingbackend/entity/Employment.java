package com.report.wantedpreonboardingbackend.entity;

import javax.persistence.*;

@Entity
@Table(name = "Employment")
public class Employment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;
    private Integer reward;
    private String detail;
    private String skill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    // Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    // Constructors

    public Employment() {
    }

    public Employment(String position, Integer reward, String detail, String skill, Company company) {
        this.position = position;
        this.reward = reward;
        this.detail = detail;
        this.skill = skill;
        this.company = company;
    }
}
