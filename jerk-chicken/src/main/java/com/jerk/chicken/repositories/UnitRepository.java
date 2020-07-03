package com.jerk.chicken.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jerk.chicken.models.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit,Integer> {

}
