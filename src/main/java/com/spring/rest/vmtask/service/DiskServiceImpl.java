package com.spring.rest.vmtask.service;

import com.spring.rest.vmtask.exception.ResourceNotFoundException;
import com.spring.rest.vmtask.model.DatabaseStoredTypes;
import com.spring.rest.vmtask.model.Disk;
import com.spring.rest.vmtask.repository.DiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service("diskService")
public class DiskServiceImpl implements DiskService {

    @Autowired
    private DiskRepository diskRepository;

    @Override
    public Disk addDisk(Disk disk) {
        return diskRepository.save(disk);
    }

    @Override
    public List<Disk> getDisks() {
        return StreamSupport.stream(diskRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Disk getDisk(Long id) {
        return diskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, DatabaseStoredTypes.DISK));
    }

    @Override
    public Disk deleteDisk(Long id) {
        Disk disk = getDisk(id);
        diskRepository.delete(disk);
        return disk;
    }

    @Override
    @Transactional
    public Disk editDisk(Long id, Disk disk) {
        Disk diskToEdit = getDisk(id);
        diskToEdit.setName(disk.getName());
        diskToEdit.setSize(disk.getSize());
        diskToEdit.setMachine(disk.getMachine());
        return diskToEdit;
    }
}
