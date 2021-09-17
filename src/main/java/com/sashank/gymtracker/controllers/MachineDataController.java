package com.sashank.gymtracker.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sashank.gymtracker.dto.MachineDataRequest;
import com.sashank.gymtracker.model.GymMember;
import com.sashank.gymtracker.model.MachineData;
import com.sashank.gymtracker.repos.GymMemberRepository;
import com.sashank.gymtracker.repos.MachineDataRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MachineDataController {
	Map<String,String> filters = new HashMap<>();
	private MachineDataRepository machineDataRepository;
	private GymMemberRepository gymMemberRepository;
	
	MachineDataController(MachineDataRepository machineDataRepository,GymMemberRepository gymMemberRepository ){
		this.machineDataRepository = machineDataRepository;
		this.gymMemberRepository = gymMemberRepository;
	}
	
	@RequestMapping(value="/machinedata",method=RequestMethod.POST)
	public MachineData saveMachineData(@RequestBody MachineDataRequest request) {
		GymMember gymMember = gymMemberRepository.findById(request.getGymmemberId()).get();
		MachineData machineData = new MachineData();
		machineData.setMachineName(request.getMachineName());
		machineData.setSetsByReps(request.getSetsByReps());
		machineData.setOccupied(request.getOccupied());
		machineData.setMachineWeight(request.getMachineWeight());
		machineData.setGymmember(gymMember);
		machineData.setExercise(request.getExercise());
		machineData.setMeasuredDateTime(request.getMeasuredDateTime());
		return machineDataRepository.save(machineData);
	}
	
	@RequestMapping(value="/machinedata/{gymmemberId}/{exercise}",method=RequestMethod.GET)
	public List<MachineData> getMachineData(@PathVariable("gymmemberId") int gymmemberId, @PathVariable("exercise")String exercise){
		return machineDataRepository.findByGymmemberIdAndExerciseOrderByMeasuredDateTime(gymmemberId, exercise);
	}
	
	@RequestMapping(value="/machinedata/latestMachineUsage")
	public List<MachineData> getLatestMachineUsage(){
		List<MachineData> latestMachData = machineDataRepository.findAll();
		ArrayList<MachineData> duplicateLatestMachineData = new ArrayList<>(latestMachData);
		// Filtering out everything but the latest data for each exercise 
		for(int i = duplicateLatestMachineData.size()-1; i >= 0; i --) {
			MachineData eachEntry = duplicateLatestMachineData.get(i);
			if(filters.containsKey(eachEntry.getMachineName())) {
				latestMachData.remove(eachEntry);
				continue;
			}else {
				filters.put(eachEntry.getMachineName(), null);
			}
		}
		
		filters.clear();
		return latestMachData;
	}
}
