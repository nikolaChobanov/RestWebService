package com.spring.rest.vmtask.repository;

import com.spring.rest.vmtask.model.Network;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkRepository extends JpaRepository<Network,Long> {
}
