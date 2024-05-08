package com.sales.crm.MapperService;

import com.sales.crm.dtos.MeetingObsRequestDTO;
import com.sales.crm.dtos.MeetingObsResponseDTO;
import com.sales.crm.dtos.MeetingResponseDTO;
import com.sales.crm.exceptions.ResourceNotFoundException;
import com.sales.crm.model.Meeting;
import com.sales.crm.model.MeetingObservation;
import com.sales.crm.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MeetingObsMapper {
    public MeetingRepository meetingRepository;
    @Autowired
    public MeetingObsMapper(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public MeetingObservation mapFromDTOToMeetingObs (MeetingObsRequestDTO meetingObsRequestDTO){
        MeetingObservation meetingObservation = new MeetingObservation();
        Meeting meeting = meetingRepository.findById(meetingObsRequestDTO.getMeetingId()).orElseThrow(()->new ResourceNotFoundException("Meeting not found!"));
        meetingObservation.setText(meetingObsRequestDTO.getText());
        meetingObservation.setMeeting(meeting);
        meetingObservation.setCreatedAt(LocalDateTime.now());
        return meetingObservation;

    }
    public MeetingObsResponseDTO mapFromMeetingObsToDTO(MeetingObservation meetingObservation){
        MeetingObsResponseDTO meetingObsResponseDTO = new MeetingObsResponseDTO();
        meetingObsResponseDTO.setCreatedAt(meetingObservation.getCreatedAt());
        meetingObsResponseDTO.setText(meetingObservation.getText());
        return meetingObsResponseDTO;
    }
}
