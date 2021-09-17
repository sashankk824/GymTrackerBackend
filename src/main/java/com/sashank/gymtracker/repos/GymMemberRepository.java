package com.sashank.gymtracker.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sashank.gymtracker.model.GymMember;

public interface GymMemberRepository extends JpaRepository<GymMember, Integer> {
	GymMember findByUsername(String username);
}
