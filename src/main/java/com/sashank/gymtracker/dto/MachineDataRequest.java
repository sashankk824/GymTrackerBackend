package com.sashank.gymtracker.dto;

import java.sql.Timestamp;


public class MachineDataRequest {
	private String machineName;
	private String setsByReps;
	private Boolean occupied;
	private int machineWeight;
	private int gymmemberId;
	private String exercise;
	private Timestamp measuredDateTime;

	public Timestamp getMeasuredDateTime() {
		return measuredDateTime;
	}

	public void setMeasuredDateTime(Timestamp measuredDateTime) {
		this.measuredDateTime = measuredDateTime;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getSetsByReps() {
		return setsByReps;
	}

	public void setSetsByReps(String setsByReps) {
		this.setsByReps = setsByReps;
	}

	public int getGymmemberId() {
		return gymmemberId;
	}

	public void setGymmemberId(int gymmemberId) {
		this.gymmemberId = gymmemberId;
	}

	public Boolean getOccupied() {
		return occupied;
	}

	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}

	public int getMachineWeight() {
		return machineWeight;
	}

	public void setMachineWeight(int machineWeight) {
		this.machineWeight = machineWeight;
	}

	public String getExercise() {
		return exercise;
	}

	public void setExercise(String exercise) {
		this.exercise = exercise;
	}
}
