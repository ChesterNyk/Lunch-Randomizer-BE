package com.challenge.lunch_randomizer.repository;

import com.challenge.lunch_randomizer.model.Choices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoicesRepository extends JpaRepository<Choices, Long> {
}
