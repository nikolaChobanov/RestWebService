package com.spring.rest.vmtask.service;

import com.spring.rest.vmtask.model.Disk;

import java.util.List;

public interface DiskService {

    Disk addDisk(Disk disk);
    List<Disk> getDisks();
    Disk getDisk(Long id);
    Disk deleteDisk(Long id);
    Disk editDisk(Long id, Disk disk);
}
