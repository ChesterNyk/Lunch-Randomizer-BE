package com.challenge.lunch_randomizer.repository;

import com.challenge.lunch_randomizer.model.Choices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoicesRepository extends JpaRepository<Choices, Long> {
    List<Choices> findAllByLunchRecordsLunchRecordId(Long lunchRecordId);

    void deleteByLunchRecordsLunchRecordId(Long lunchRecordId);
}
