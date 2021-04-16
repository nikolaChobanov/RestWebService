package com.spring.rest.vmtask.controller;


import com.spring.rest.vmtask.model.Disk;
import com.spring.rest.vmtask.model.Machine;
import com.spring.rest.vmtask.model.dto.DiskDto;
import com.spring.rest.vmtask.model.dto.MachineDto;
import com.spring.rest.vmtask.service.DiskServiceImpl;
import com.spring.rest.vmtask.service.VirtualMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/disk")
public class DiskController {

    @Autowired
    private DiskServiceImpl diskService;
    @Autowired
    private VirtualMachineService virtualMachineService;

    @GetMapping
    public ResponseEntity<List<DiskDto>> getDisks() {
        List<Disk> diskList = diskService.getDisks();
        List<DiskDto> diskDtos = diskList.stream().map(DiskDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(diskDtos, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DiskDto> getDisk(@PathVariable final Long id) {
        Disk disk = diskService.getDisk(id);
        return new ResponseEntity<>(DiskDto.from(disk), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DiskDto> addDisk(@RequestBody final DiskDto diskDto) {
        Disk disk = diskService.addDisk(Disk.from(diskDto));
        return new ResponseEntity<>(DiskDto.from(disk), HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity<DiskDto> deleteDisk(@PathVariable final Long id) {
        Disk disk = diskService.deleteDisk(id);
        return new ResponseEntity<>(DiskDto.from(disk), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<DiskDto> editDisk(@PathVariable final Long id,
                                            @RequestBody final DiskDto diskDto) {
        Disk disk = diskService.editDisk(id, Disk.from(diskDto));
        return new ResponseEntity<>(DiskDto.from(disk), HttpStatus.OK);
    }

    @PostMapping(value = "{vmId}/disks/{diskId}/add")
    public ResponseEntity<MachineDto> addDiskToVirtualMachine(@PathVariable final Long vmId,
                                                              @PathVariable final Long diskId) {
        Machine machine = virtualMachineService.addDiskToVM(vmId, diskId);
        return new ResponseEntity<>(MachineDto.from(machine), HttpStatus.OK);
    }

    @DeleteMapping(value = "{vmId}/disks/{diskId}/remove")
    public ResponseEntity<MachineDto> removeDiskFromVirtualMachine(@PathVariable final Long vmId,
                                                                   @PathVariable final Long diskId) {
        Machine machine = virtualMachineService.removeDiskFromVM(vmId, diskId);
        return new ResponseEntity<>(MachineDto.from(machine), HttpStatus.OK);
    }


}
