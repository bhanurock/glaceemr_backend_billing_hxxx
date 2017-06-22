package com.glenwood.glaceemr.server.application.repositories;

/**
 * H222Repository.java is used to save the data for followupAction
 * 
 * */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.glenwood.glaceemr.server.application.models.H222;

@Repository
public interface H222Repository extends JpaRepository<H222, Integer>,JpaSpecificationExecutor<H222>{

}