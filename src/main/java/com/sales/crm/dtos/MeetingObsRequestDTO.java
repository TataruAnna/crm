package com.sales.crm.dtos;



import com.sales.crm.model.MeetingType;

import java.time.LocalDateTime;

public class MeetingObsRequestDTO {
    private Long meetingId;
    private String text;

    public MeetingObsRequestDTO() {
    }

    public MeetingObsRequestDTO(Long meetingId, String text) {
        this.meetingId = meetingId;
        this.text = text;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
