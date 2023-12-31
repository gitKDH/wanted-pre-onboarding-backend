package com.report.wantedpreonboardingbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Company")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nation;
    private String region;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Employment> employments = new ArrayList<>();

    public Company(long l) {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNation() {
        return nation;
    }

    public String getRegion() {
        return region;
    }

    public Company(){
    }

    public Company(String name, String nation, String region) {
        this.name = name;
        this.nation = nation;
        this.region = region;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setId(long l) {
    }
}
