package com.spring.rest.vmtask.dto;


import com.spring.rest.vmtask.model.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MachineDto {

    private Long id;
    private String name;
    private int CPU;
    private int memory;

    private List<DiskDto> disks = new ArrayList<>();
    private List<NetworkDto> networks = new ArrayList<>();


    public static MachineDto from(Machine machine) {
        MachineDto machineDto = new MachineDto();
        machineDto.setId(machine.getId());
        machineDto.setName(machine.getName());
        machineDto.setCPU(machine.getCPU());
        machineDto.setMemory(machine.getMemory());
        machineDto.setDisks(machine.getDisks().stream().map(DiskDto::from).collect(Collectors.toList()));
        machineDto.setNetworks(machine.getNetworks().stream().map(NetworkDto::from).collect(Collectors.toList()));
        return machineDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCPU() {
        return CPU;
    }

    private void setCPU(int CPU) {
        this.CPU = CPU;
    }

    public int getMemory() {
        return memory;
    }

    private void setMemory(int memory) {
        this.memory = memory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DiskDto> getDisks() {
        return disks;
    }

    private void setDisks(List<DiskDto> disks) {
        this.disks = disks;
    }

    public List<NetworkDto> getNetworks() {
        return networks;
    }

    private void setNetworks(List<NetworkDto> networks) {
        this.networks = networks;
    }
}
