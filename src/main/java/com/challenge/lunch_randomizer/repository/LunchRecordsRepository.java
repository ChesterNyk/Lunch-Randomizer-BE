package com.challenge.lunch_randomizer.repository;

import com.challenge.lunch_randomizer.model.LunchRecords;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LunchRecordsRepository extends JpaRepository<LunchRecords, Long> {
    Optional<LunchRecords> findByLunchRecordId(Long recordId);

    void deleteByLunchRecordId(Long recordId);
}
