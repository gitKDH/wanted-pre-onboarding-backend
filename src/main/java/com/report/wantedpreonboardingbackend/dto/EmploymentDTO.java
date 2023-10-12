package com.report.wantedpreonboardingbackend.dto;

public class EmploymentDTO {
    private Long company_id;

    private String position;
    private int reward;
    private String detail;
    private String skill;

    public EmploymentDTO() {
    }

    public EmploymentDTO(Long company_id, String position, int reward, String detail, String skill) {
        this.company_id = company_id;
        this.position = position;
        this.reward = reward;
        this.detail = detail;
        this.skill = skill;
    }

    public EmploymentDTO(String position, int reward, String detail, String skill) {
        this.position = position;
        this.reward = reward;
        this.detail = detail;
        this.skill = skill;
    }

    public String getPosition() {
        return position;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
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
}
