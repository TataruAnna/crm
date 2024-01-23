package com.sales.crm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "meeting_obs")
public class MeetingObservation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String text;

    @ManyToOne
    @JsonBackReference("meeting-meetingObs")
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    public MeetingObservation() {
    }

    public MeetingObservation(Long id, LocalDateTime createdAt, String text, Meeting meeting) {
        this.id = id;
        this.createdAt = createdAt;
        this.text = text;
        this.meeting = meeting;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }
}
