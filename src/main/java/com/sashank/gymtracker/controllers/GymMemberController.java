package com.sashank.gymtracker.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sashank.gymtracker.dto.AuthResponse;
import com.sashank.gymtracker.model.GymMember;
import com.sashank.gymtracker.model.MachineData;
import com.sashank.gymtracker.repos.GymMemberRepository;
import com.sashank.gymtracker.model.UserAuthentication;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class GymMemberController {

	private GymMemberRepository repository;
	Map<String,String> filters = new HashMap<>();

	@Autowired
	GymMemberController(GymMemberRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(value = "/gymmembers", method = RequestMethod.GET)
	public List<GymMember> getGymMembers() {
		return repository.findAll();
	}
	
	@RequestMapping(value="/gymmembers/{id}")
	public GymMember getGymMember(@PathVariable("id") int id) {
		return repository.findById(id).get();
	}
	
	@RequestMapping(value="/gymmembers", method = RequestMethod.POST)
	public GymMember saveGymMember(@RequestBody GymMember gymMember) {
		return repository.save(gymMember);
	}
	
	@RequestMapping(value="/gymmembers/analyze/{id}")
	public GymMember analyze(@PathVariable("id")int id) {
		GymMember gymMember = repository.findById(id).get();
		List<MachineData> machineData = gymMember.getMachineData();
		ArrayList<MachineData> duplicateMachineData = new ArrayList<>(machineData);
		// Filtering out everything but the latest data for each exercise 
		for(int i = duplicateMachineData.size() - 1; i >= 0; i --) {
			MachineData eachEntry = duplicateMachineData.get(i);
			if(filters.containsKey(eachEntry.getExercise())) {
				machineData.remove(eachEntry);
				continue;
			}else {
				filters.put(eachEntry.getExercise(), null);
			}
		}
		
		filters.clear();
		return gymMember;
	}
	
	/*
	 * Custom defined authentication method where this method receives a POST
	 * request from React given a username and password, and this method will 
	 * verify with the database and give 1 of 3 responses:
	 * - User name isn't in the gym member database thus user doesn't exist
	 * - User name is in the gym member database, but password given doesn't match
	 *   one in database i.e invalid password
	 * - User name is in the database and passwords match, which means the 
	 *   the credential are correct and so the method returns an object 
	 *   confirming the credentials and passing the gym member's id to 
	 *   retrieve the user's machine data for the next screen*/
	@RequestMapping(value="/gymmembers/authenticate", method = RequestMethod.POST)
	public AuthResponse authenticate(@RequestBody UserAuthentication auth) {
		GymMember member = repository.findByUsername(auth.getUsername());
		AuthResponse authentication = new AuthResponse();
		if(member == null) {
			authentication.setAuthenticated(false);
			authentication.setMessage("This user does not exist in the database");
		}else if(!(auth.getPassword().equals(member.getPassword()))) {
			authentication.setAuthenticated(false);
			authentication.setMessage("Sorry, that was the incorrect password");
		}
		else {
			authentication.setAuthenticated(true);
			authentication.setMessage("");
			authentication.setId(member.getId());
			authentication.setRole(member.getRole());
		}
		
		return authentication;
	}
	
	
}
