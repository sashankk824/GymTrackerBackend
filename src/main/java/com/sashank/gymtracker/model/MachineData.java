package com.sashank.gymtracker.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//Mapping the model class to exact name in Postgres
@Table(name = "machinedata")
public class MachineData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// Mapping Postgres exact column name to Model class variable
	@Column(name = "machine_name")
	private String machineName;
	@Column(name = "sets_by_reps")
	private String setsByReps;
	private boolean occupied;
	@Column(name = "measured_date_time")
	private Timestamp measuredDateTime;
	@Column(name = "machine_weight")
	private int machineWeight;
	@Column(name = "exercise")
	private String exercise;

	// Note: the variable name has to be the same as what is mapped in gymmember
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gymmember_id", nullable = false)
	@JsonIgnore
	private GymMember gymmember;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public Timestamp getMeasuredDateTime() {
		return measuredDateTime;
	}

	public void setMeasuredDateTime(Timestamp measuredDateTime) {
		this.measuredDateTime = measuredDateTime;
	}

	public GymMember getGymmember() {
		return gymmember;
	}

	public void setGymmember(GymMember gymmember) {
		this.gymmember = gymmember;
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
