package com.spring.rest.vmtask.service;

import com.spring.rest.vmtask.exception.ResourceAlreadyAssigned;
import com.spring.rest.vmtask.exception.ResourceNotFoundException;
import com.spring.rest.vmtask.model.DatabaseStoredTypes;
import com.spring.rest.vmtask.model.Disk;
import com.spring.rest.vmtask.model.Machine;
import com.spring.rest.vmtask.model.Network;
import com.spring.rest.vmtask.repository.VirtualMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VirtualMachineServiceImpl implements VirtualMachineService {

    private VirtualMachineRepository vmRepository;
    private DiskServiceImpl diskService;
    private NetworkServiceImpl networkService;


    @Autowired
    public VirtualMachineServiceImpl(VirtualMachineRepository vmRepository, DiskServiceImpl diskService, NetworkServiceImpl networkService) {
        this.vmRepository = vmRepository;
        this.diskService = diskService;
        this.networkService = networkService;
    }

    @Override
    public Machine insertVirtualMachine(Machine vmEntity) {
        return vmRepository.save(vmEntity);
    }

    @Override
    public Machine getVirtualMachineById(Long id) {
        return vmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, DatabaseStoredTypes.MACHINE));
    }

    @Override
    public Machine getVirtualMachineByName(String name) {
        return vmRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(name, DatabaseStoredTypes.MACHINE));
    }

    @Override
    public Machine deleteVirtualMachine(Long vmID) {
        Machine vmEntity = getVirtualMachineById(vmID);
        vmRepository.delete(vmEntity);
        return vmEntity;
    }

    @Override
    public List<Machine> getAllVmMachines() {
        return StreamSupport.stream(vmRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Machine editVirtualMachine(Long id, Machine updatedVM) {
        Machine machineToEdit = getVirtualMachineById(id);

        if (updatedVM.getName() != null && updatedVM.getName().length() > 0 && !Objects.equals(updatedVM.getName(), machineToEdit.getName())) {
            Optional<Machine> vmOptional = vmRepository.findByName(updatedVM.getName());

            if (vmOptional.isPresent()) {
                throw new IllegalStateException("Name already exists in db");
            }

            machineToEdit.setName(updatedVM.getName());
        }
        machineToEdit.setCPU(updatedVM.getCPU());
        machineToEdit.setMemory(updatedVM.getCPU());
        machineToEdit.setDisks(updatedVM.getDisks());
        machineToEdit.setNetworks(updatedVM.getNetworks());
        return machineToEdit;
    }

    @Override
    @Transactional
    public Machine addDiskToVM(Long machineId, Long diskId) {

        Machine machine = getVirtualMachineById(machineId);
        Disk disk = diskService.getDisk(diskId);
        if(Objects.nonNull(disk.getMachine())){
            throw new ResourceAlreadyAssigned(machineId,diskId);
        }
        machine.addDisk(disk);
        return machine;
    }

    @Override
    @Transactional
    public Machine addNetworkToVM(Long machineId, Long networkId) {
        Machine machine = getVirtualMachineById(machineId);
        Network network = networkService.getNetwork(networkId);
        if(Objects.nonNull(network.getMachine())){
            throw new ResourceAlreadyAssigned(machineId,networkId);
        }
        machine.addNetwork(network);
        return machine;
    }

    @Override
    @Transactional
    public Machine removeDiskFromVM(Long machineId, Long diskId) {
        Machine machine = getVirtualMachineById(machineId);
        Disk disk = diskService.getDisk(diskId);
        machine.removeDisk(disk);
        return machine;
    }

    @Override
    @Transactional
    public Machine removeNetworkFromVM(Long machineId, Long networkId) {
        Machine machine = getVirtualMachineById(machineId);
        Network network = networkService.getNetwork(networkId);
        machine.removeNetwork(network);
        return machine;
    }


}
