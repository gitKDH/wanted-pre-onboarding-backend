package com.report.wantedpreonboardingbackend.dto;

public class CompanyDTO {
    private Long id;
    private String name;
    private String nation;
    private String region;

    public CompanyDTO() {
    }

    public CompanyDTO(Long id, String name, String nation, String region) {
        this.id = id;
        this.name = name;
        this.nation = nation;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
