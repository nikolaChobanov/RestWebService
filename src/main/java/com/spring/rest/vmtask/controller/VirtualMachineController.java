package com.spring.rest.vmtask.controller;


import com.spring.rest.vmtask.VmtaskApplication;
import com.spring.rest.vmtask.exception.ResourceNotFoundException;
import com.spring.rest.vmtask.model.Machine;
import com.spring.rest.vmtask.model.dto.MachineDto;
import com.spring.rest.vmtask.service.VirtualMachineService;
import com.spring.rest.vmtask.service.VirtualMachineServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vm")
public class VirtualMachineController {

    private static final Logger logger = LoggerFactory.getLogger(VmtaskApplication.class);

    @Autowired
    private VirtualMachineService service;


    @GetMapping("")
    public ResponseEntity<List<MachineDto>> getVirtualMachines() {
        logger.info("get all vms");
        List<Machine> machines=service.getAllVmMachines();
        List<MachineDto> machineDtos=machines.stream().map(MachineDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(machineDtos,HttpStatus.OK);
    }

    @GetMapping(value="{id}")
    public ResponseEntity<MachineDto> getVirtualMachineById(@PathVariable(value = "id") final Long vmId) throws ResourceNotFoundException {
        logger.info("get VM with id: " + vmId);

        Machine vmEntity = service.getVirtualMachineById(vmId);
        return new ResponseEntity<>(MachineDto.from(vmEntity),HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<MachineDto> createVm(@Valid @RequestBody final MachineDto vmEntity) {
        logger.info("Insert VM");
        Machine machine= service.insertVirtualMachine(Machine.from(vmEntity));
        return new ResponseEntity<>(MachineDto.from(machine), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MachineDto> vmEntityById(@PathVariable(value = "id") final Long vmId, @RequestBody final MachineDto updatedVM) throws ResourceNotFoundException {
        logger.info("Update VM with id:" + vmId);
        Machine editMachine=service.editVirtualMachine(vmId, Machine.from(updatedVM));
        return new ResponseEntity<>(MachineDto.from(editMachine),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MachineDto> deleteVirtualMachineEntity(@PathVariable(value = "id")final Long vmId) throws ResourceNotFoundException {
        logger.info("Delete virtual machine with id: " + vmId);
        Machine machine=service.deleteVirtualMachine(vmId);
      return new ResponseEntity<>(MachineDto.from(machine),HttpStatus.OK);
    }

}
