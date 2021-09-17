package com.sashank.gymtracker.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
// Mapping the model class to exact name in Postgres
@Table(name = "gymmember")
public class GymMember {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	// Mapping Postgres exact column name to Model class variable
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "first_name")
	private String firstName;
	private int age;
	private int experience;
	private String username;
	@Column(name = "pword")
	private String password;
	@Column (name = "tabrole")
	private String role;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="gymmember")
	private List<MachineData> machineData;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public List<MachineData> getMachineData() {
		return machineData;
	}

	public void setMachineData(List<MachineData> machineData) {
		this.machineData = machineData;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
