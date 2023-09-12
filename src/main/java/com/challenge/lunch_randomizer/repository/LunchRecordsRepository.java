package com.challenge.lunch_randomizer.repository;

import com.challenge.lunch_randomizer.model.LunchRecords;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LunchRecordsRepository extends JpaRepository<LunchRecords, Long> {
    Page<LunchRecords> findAllRecords();

}
