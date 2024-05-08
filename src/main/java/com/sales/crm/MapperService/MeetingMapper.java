package com.sales.crm.MapperService;

import com.sales.crm.dtos.MeetingObsRequestDTO;
import com.sales.crm.dtos.MeetingRequestDTO;
import com.sales.crm.dtos.MeetingResponseDTO;
import com.sales.crm.exceptions.ResourceNotFoundException;
import com.sales.crm.model.Client;
import com.sales.crm.model.Meeting;
import com.sales.crm.model.MeetingObservation;
import com.sales.crm.repository.ClientRepository;
import com.sales.crm.service.ClientService;
import com.sales.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingMapper {
    private ClientRepository clientRepository;
    private UserService userService;
    private MeetingObsMapper meetingObsMapper;
    @Autowired
    public MeetingMapper(ClientRepository clientRepository, UserService userService, MeetingObsMapper meetingObsMapper) {
        this.clientRepository = clientRepository;
        this.userService = userService;
        this.meetingObsMapper = meetingObsMapper;
    }
    @Transactional
    public Meeting mapFromDTOToMeeting(MeetingRequestDTO meetingRequestDTO){
        Meeting meeting = new Meeting();

        Client client = clientRepository.findById(meetingRequestDTO.getClientId()).orElseThrow(()->new ResourceNotFoundException("Client not found!"));
        meeting.setClient(client);
        meeting.setUser(userService.findLoggedInUser());
        meeting.setMeetingTime(meetingRequestDTO.getMeetingTime());
        meeting.setMeetingType(meetingRequestDTO.getMeetingType());
        List<MeetingObsRequestDTO> meetingObsRequestDTO = meetingRequestDTO.getMeetingObsRequestDTOList();
        if(meetingObsRequestDTO!=null) {


            List<MeetingObservation> meetingObs = meetingObsRequestDTO.stream()
                    .map(meetingReqDTO -> meetingObsMapper.mapFromDTOToMeetingObs(meetingReqDTO))
                    .collect(Collectors.toList());
            meeting.setMeetingObservations(meetingObs);
        }
        else{
            meetingObsRequestDTO = new ArrayList<>();
        }
        return meeting;
    }
    public MeetingResponseDTO mapFromMeetingToResponse(Meeting meeting){
        MeetingResponseDTO meetingResponseDTO = new MeetingResponseDTO();
        meetingResponseDTO.setMeetingType(meeting.getMeetingType());
        meetingResponseDTO.setMeetingTime(meeting.getMeetingTime());
        meetingResponseDTO.setMeetingObsResponseDTOList(meeting.getMeetingObservations().stream()
                .map(meetingObs -> meetingObsMapper.mapFromMeetingObsToDTO(meetingObs))
                .collect(Collectors.toList()));
       return meetingResponseDTO;
    }
}
