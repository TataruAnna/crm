package com.sales.crm.controller;

import com.sales.crm.dtos.MeetingObsRequestDTO;
import com.sales.crm.dtos.MeetingRequestDTO;
import com.sales.crm.dtos.MeetingResponseDTO;
import com.sales.crm.service.MeetingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meeting")
@Tag(name = "Meeting", description = "Endpoints for meetings ")
public class MeetingController {
    private MeetingService meetingService;
    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping("/add")
    public ResponseEntity<MeetingResponseDTO> addMeetingToUser(@RequestBody MeetingRequestDTO meetingRequestDTO){
        return ResponseEntity.ok(meetingService.addMeeting(meetingRequestDTO));
    }
    @PostMapping("/addObs")
        public ResponseEntity<MeetingResponseDTO> addObsToMeeting(@RequestBody MeetingObsRequestDTO meetingObsRequestDTO){
            return ResponseEntity.ok(meetingService.addObsToMeeting(meetingObsRequestDTO));
        }
    @DeleteMapping("/delete/{meetingObsId}/{meetingId}")
        public ResponseEntity<MeetingResponseDTO> deleteObsFromMeeting(@PathVariable Long meetingObsId, @PathVariable Long meetingId){
            return ResponseEntity.ok(meetingService.deleteMeetingObs(meetingObsId, meetingId));
    }
    @GetMapping("/all/{userId}")
        public ResponseEntity<List<MeetingResponseDTO>> getAllMeetingsFromUser(@PathVariable Long userId){
            return ResponseEntity.ok(meetingService.getAllMeetingsFromUser(userId));
    }


}


