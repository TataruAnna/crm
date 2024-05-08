package com.sales.crm.service;

import com.sales.crm.MapperService.MeetingMapper;
import com.sales.crm.MapperService.MeetingObsMapper;
import com.sales.crm.dtos.MeetingObsRequestDTO;
import com.sales.crm.dtos.MeetingRequestDTO;
import com.sales.crm.dtos.MeetingResponseDTO;
import com.sales.crm.exceptions.ResourceNotFoundException;
import com.sales.crm.model.Client;
import com.sales.crm.model.Meeting;
import com.sales.crm.model.MeetingObservation;
import com.sales.crm.model.User;
import com.sales.crm.repository.MeetingRepository;
import com.sales.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeetingService {
    private MeetingRepository meetingRepository;
    private UserService userService;
    private UserRepository userRepository;
    private MeetingMapper meetingMapper;
    private MeetingObsMapper meetingObsMapper;
    @Autowired
    public MeetingService(MeetingRepository meetingRepository, UserService userService, MeetingMapper meetingMapper,MeetingObsMapper meetingObsMapper,UserRepository userRepository) {
        this.meetingRepository = meetingRepository;
        this.userService = userService;
        this.meetingMapper = meetingMapper;
        this.meetingObsMapper = meetingObsMapper;
        this.userRepository=userRepository;
    }

    @Transactional
    public MeetingResponseDTO addMeeting(MeetingRequestDTO meetingRequestDTO){
        User user = userService.findLoggedInUser();
        Meeting meeting = meetingMapper.mapFromDTOToMeeting(meetingRequestDTO);
        user.getMeetings().add(meeting);
        Client client = meeting.getClient();
        client.getMeetings().add(meeting);
        MeetingResponseDTO meetingResponseDTO = meetingMapper.mapFromMeetingToResponse(meeting);
        return meetingResponseDTO;

    }
    @Transactional
    public MeetingResponseDTO addObsToMeeting(MeetingObsRequestDTO meetingObsRequestDTO){
        Meeting meeting = meetingRepository.findById(meetingObsRequestDTO.getMeetingId()).orElseThrow(()->new ResourceNotFoundException("Meeting not found"));
        MeetingObservation meetingObservation = meetingObsMapper.mapFromDTOToMeetingObs(meetingObsRequestDTO);
        meeting.getMeetingObservations().add(meetingObservation);
        meetingObservation.setMeeting(meeting);
        return meetingMapper.mapFromMeetingToResponse(meeting);

    }
    @Transactional
    public MeetingResponseDTO deleteMeetingObs(Long meetingObsId, Long meetingId){
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(()->new ResourceNotFoundException("Meeting not found"));
        List<MeetingObservation> meetingObservations = meeting.getMeetingObservations();
        Optional<MeetingObservation> meetingObs = meeting.getMeetingObservations().stream()
                .filter(obs -> obs.getId().equals(meetingObsId))
                .findFirst();
        if(meetingObs.isPresent()){
            meetingObservations.remove(meetingObs.get());

        }else{
            ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException("Meeting Observation not found");
        }
        meeting.setMeetingObservations(meetingObservations);
        return meetingMapper.mapFromMeetingToResponse(meeting);

    }
    @Transactional
    public List<MeetingResponseDTO> getAllMeetingsFromUser (Long userId){
        User user = userRepository.findUserById(userId).orElseThrow(()->new ResourceNotFoundException("User not found"));
        List<Meeting> meetings = user.getMeetings();
        List<MeetingResponseDTO> meetingsResponseDTO = meetings.stream()
                .map(meeting->meetingMapper.mapFromMeetingToResponse(meeting))
                .collect(Collectors.toList());
        return meetingsResponseDTO;

    }


}
