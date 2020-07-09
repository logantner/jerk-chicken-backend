package com.jerk.chicken.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerk.chicken.models.Unit;
import com.jerk.chicken.repositories.UnitRepository;

@Service
public class UnitService {
	
	@Autowired
	UnitRepository ur;
	
	public List<Unit> getAllUnits() {
		return ur.findAll();
	}
}
