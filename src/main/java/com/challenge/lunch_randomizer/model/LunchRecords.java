package com.challenge.lunch_randomizer.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "LUNCH_RECORDS")
public class LunchRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LUNCH_RECORDS_ID", unique = true, nullable = false)
    private Long lunchRecordId;

    @Column(name = "FINAL_LOCATION")
    private String finalLocation;

    @Column(name= "LOCATION_LINK")
    private String locationLink;

    @Column(name = "CREATED_DATETIME")
    private String createdDateTime;

    @OneToMany(mappedBy = "lunchRecords", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Choices> choicesList;
}
