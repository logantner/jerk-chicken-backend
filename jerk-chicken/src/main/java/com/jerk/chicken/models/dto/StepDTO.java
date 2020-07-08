package com.jerk.chicken.models.dto;

import java.io.Serializable;

public class StepDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private int step_id;
	
	private int position;
	
	private String instruction;

	public StepDTO() {
		super();
	}

	public StepDTO(int step_id) {
		super();
		this.step_id = step_id;
	}

	public int getStep_id() {
		return step_id;
	}

	public void setStep_id(int step_id) {
		this.step_id = step_id;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((instruction == null) ? 0 : instruction.hashCode());
		result = prime * result + position;
		result = prime * result + step_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StepDTO other = (StepDTO) obj;
		if (instruction == null) {
			if (other.instruction != null)
				return false;
		} else if (!instruction.equals(other.instruction))
			return false;
		if (position != other.position)
			return false;
		if (step_id != other.step_id)
			return false;
		return true;
	}
	
	

}
