package com.sashank.gymtracker.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sashank.gymtracker.model.MachineData;

public interface MachineDataRepository extends JpaRepository<MachineData, Integer> {

	List<MachineData> findByGymmemberIdAndExerciseOrderByMeasuredDateTime(Integer gymmemberId, String exercise);

}
