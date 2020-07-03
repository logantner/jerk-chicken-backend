package com.jerk.chicken.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jerk.chicken.models.Step;

@Repository
public interface StepRepository extends JpaRepository<Step,Integer> {

}
