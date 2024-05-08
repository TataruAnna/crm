package com.sales.crm.dtos;

import com.sales.crm.model.MeetingType;
import java.time.LocalDateTime;
import java.util.List;

public class MeetingResponseDTO {
    private MeetingType meetingType;
    private LocalDateTime meetingTime;
    private List<MeetingObsResponseDTO> meetingObsResponseDTOList;

    public MeetingResponseDTO() {
    }

    public MeetingResponseDTO(MeetingType meetingType, LocalDateTime meetingTime, List<MeetingObsResponseDTO> meetingObsResponseDTOList) {
        this.meetingType = meetingType;
        this.meetingTime = meetingTime;
        this.meetingObsResponseDTOList = meetingObsResponseDTOList;
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

    public List<MeetingObsResponseDTO> getMeetingObsResponseDTOList() {
        return meetingObsResponseDTOList;
    }

    public void setMeetingObsResponseDTOList(List<MeetingObsResponseDTO> meetingObsResponseDTOList) {
        this.meetingObsResponseDTOList = meetingObsResponseDTOList;
    }
}
