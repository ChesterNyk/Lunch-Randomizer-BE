package com.challenge.lunch_randomizer.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "CHOICES")
public class Choices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHOICES_ID", unique = true, nullable = false)
    private Long choicesId;

    @ManyToOne
    @JoinColumn(name = "LUNCH_RECORDS_ID", referencedColumnName = "LUNCH_RECORDS_ID", nullable = false)
    private LunchRecords lunchRecords;

    @Column(name= "RESTAURANT_NAME")
    private String restaurantName;

    @Column(name= "LOCATION_LINK")
    private String locationLink;

    @Column(name = "CREATED_DATETIME")
    private Date createdDateTime;
}
