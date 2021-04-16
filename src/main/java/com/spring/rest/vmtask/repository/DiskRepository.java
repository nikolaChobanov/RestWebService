package com.spring.rest.vmtask.repository;

import com.spring.rest.vmtask.model.Disk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiskRepository extends JpaRepository<Disk,Long> {
}
