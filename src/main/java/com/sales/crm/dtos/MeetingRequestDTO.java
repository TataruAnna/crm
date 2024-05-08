package com.sales.crm.dtos;

import com.sales.crm.model.MeetingType;

import java.time.LocalDateTime;
import java.util.List;

public class MeetingRequestDTO {

    private Long clientId;
    private MeetingType meetingType;
    private LocalDateTime meetingTime; //2022-03-08T10:08:02
    private List<MeetingObsRequestDTO> meetingObsRequestDTOList;

    public MeetingRequestDTO() {
    }

    public MeetingRequestDTO( Long clientId, MeetingType meetingType, LocalDateTime meetingTime, List<MeetingObsRequestDTO> meetingObsRequestDTOList) {

        this.clientId = clientId;
        this.meetingType = meetingType;
        this.meetingTime = meetingTime;
        this.meetingObsRequestDTOList = meetingObsRequestDTOList;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public MeetingType getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(MeetingType meetingType) {
        this.meetingType = meetingType;
    }

    public LocalDateTime getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(LocalDateTime meetingTime) {
        this.meetingTime = meetingTime;
    }

    public List<MeetingObsRequestDTO> getMeetingObsRequestDTOList() {
        return meetingObsRequestDTOList;
    }

    public void setMeetingObsRequestDTOList(List<MeetingObsRequestDTO> meetingObsRequestDTOList) {
        this.meetingObsRequestDTOList = meetingObsRequestDTOList;
    }
}
