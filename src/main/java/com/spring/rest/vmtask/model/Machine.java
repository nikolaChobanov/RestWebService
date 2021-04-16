package com.spring.rest.vmtask.model;


import com.spring.rest.vmtask.model.dto.MachineDto;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "vms")
//@AllArgsConstructor
//@Getter
//@Setter
public class Machine {


    @Id
    @SequenceGenerator(
            name = "vm_sequence",
            sequenceName = "vm_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vm_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private long id;

    @Column(
            name = "virtual_machine_name",
            nullable = false,
            unique = true
    )
    private String name;

    @Column(
            name = "virtual_machine_cpu",
            nullable = false
    )
    private int CPU;

    @Column(
            name = "virtual_machine_memory",
            nullable = false
    )
    private int memory;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "machine_id")
    private List<Disk> disks;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "machine_id")
    private List<Network> networks;

    public Machine() {
    }

    public long getId() {
        return id;
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

    public void setCPU(int CPU) {
        this.CPU = CPU;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public List<Disk> getDisks() {
        return disks;
    }

    public void setDisks(List<Disk> disks) {
        this.disks = disks;
    }

    public void addDisk(Disk disk) {
        disks.add(disk);
    }

    public void removeDisk(Disk disk) {
        disks.remove(disk);
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    public void addNetwork(Network network) {
        networks.add(network);
    }

    public void removeNetwork(Network network) {
        networks.remove(network);
    }

    public static Machine from(MachineDto machineDto) {
        Machine machine = new Machine();
        machine.setName(machineDto.getName());
        machine.setCPU(machineDto.getCPU());
        machine.setMemory(machineDto.getMemory());
        machine.setDisks(machineDto.getDisks().stream().map(Disk::from).collect(Collectors.toList()));
        machine.setNetworks(machineDto.getNetworks().stream().map(Network::from).collect(Collectors.toList()));
        return machine;
    }
}
