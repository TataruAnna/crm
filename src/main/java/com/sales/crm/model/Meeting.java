package com.sales.crm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private MeetingType meetingType;

    @Column
    private LocalDateTime meetingTime;


    @OneToMany(mappedBy = "meeting",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("meeting-meetingObs")
    private List<MeetingObservation> meetingObservations;

    @ManyToOne
    @JsonBackReference("meeting-client")
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne
    @JsonBackReference("meeting-user")
    @JoinColumn(name="user_id")
    private User user;

    public Meeting() {
    }

    public Meeting(Long id, MeetingType meetingType, LocalDateTime meetingTime, List<MeetingObservation> meetingObservations, Client client, User user) {
        this.id = id;
        this.meetingType = meetingType;
        this.meetingTime = meetingTime;
        this.meetingObservations = meetingObservations;
        this.client = client;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<MeetingObservation> getMeetingObservations() {
        return meetingObservations;
    }

    public void setMeetingObservations(List<MeetingObservation> meetingObservations) {
        this.meetingObservations = meetingObservations;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
