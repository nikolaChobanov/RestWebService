package com.spring.rest.vmtask.service;

import com.spring.rest.vmtask.model.Disk;
import com.spring.rest.vmtask.model.Machine;
import com.spring.rest.vmtask.model.Network;

import java.util.List;

public interface VirtualMachineService {

    Machine insertVirtualMachine(Machine vmEntity);
    Machine getVirtualMachineById(Long id);
    Machine getVirtualMachineByName(String name);
    Machine deleteVirtualMachine(Long vmEntity);
    List<Machine> getAllVmMachines();
    Machine editVirtualMachine(Long id, Machine machine);

    Machine addDiskToVM(Long machineId, Long diskId);
    Machine addNetworkToVM(Long machineId, Long networkId);

    Machine removeDiskFromVM(Long machineId, Long diskID);
    Machine removeNetworkFromVM(Long machineId, Long networkID);
}
