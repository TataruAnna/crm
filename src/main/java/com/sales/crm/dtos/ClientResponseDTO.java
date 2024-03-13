package com.sales.crm.dtos;

import java.util.ArrayList;
import java.util.List;

public class ClientResponseDTO {
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private Long userId;
    private List<QuotationResponseDTO> quotationResponseDTOs;
    private List<MeetingResponseDTO> meetingResponseDTOs;

    public ClientResponseDTO() {
    }

    public ClientResponseDTO(String name, String phoneNumber, String email, String address, Long userId, List<QuotationResponseDTO> quotationResponseDTOs, List<MeetingResponseDTO> meetingResponseDTOs) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.userId = userId;
        this.quotationResponseDTOs = quotationResponseDTOs;
        this.meetingResponseDTOs = meetingResponseDTOs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<QuotationResponseDTO> getQuotationResponseDTOs() {
        return quotationResponseDTOs;
    }

    public void setQuotationResponseDTOs(List<QuotationResponseDTO> quotationResponseDTOs) {
        if(quotationResponseDTOs==null){
            quotationResponseDTOs = new ArrayList<>();
        }
        this.quotationResponseDTOs = quotationResponseDTOs;
    }

    public List<MeetingResponseDTO> getMeetingResponseDTOs() {
        return meetingResponseDTOs;
    }

    public void setMeetingResponseDTOs(List<MeetingResponseDTO> meetingResponseDTOs) {
        if(meetingResponseDTOs==null){
            meetingResponseDTOs = new ArrayList<>();
        }
        this.meetingResponseDTOs = meetingResponseDTOs;
    }
}
