package com.spring.rest.vmtask.repository;


import com.spring.rest.vmtask.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VirtualMachineRepository extends JpaRepository<Machine,Long> {


  //  @Query("Select s from vms where s.Name= ?1")
    Optional<Machine> findByName(String name);
}
