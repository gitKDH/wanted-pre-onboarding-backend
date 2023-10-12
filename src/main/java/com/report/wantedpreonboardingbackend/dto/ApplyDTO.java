package com.report.wantedpreonboardingbackend.dto;

import com.report.wantedpreonboardingbackend.entity.Apply;

public class ApplyDTO {
    private Long userId;
    private Long employmentId;

    public ApplyDTO() {
    }

    public ApplyDTO(Apply apply) {
        this.userId = apply.getUser().getId();
        this.employmentId = apply.getEmployment().getId();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEmploymentId() {
        return employmentId;
    }

    public void setEmploymentId(Long employmentId) {
        this.employmentId = employmentId;
    }
}
