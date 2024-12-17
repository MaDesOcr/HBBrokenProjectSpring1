package com.mddapi.payload.request;

import javax.validation.constraints.NotNull;

public class SubscriptionRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long subjectId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}
