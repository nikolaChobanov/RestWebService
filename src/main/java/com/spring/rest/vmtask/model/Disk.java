package com.spring.rest.vmtask.model;

import com.spring.rest.vmtask.model.dto.DiskDto;

import javax.persistence.*;

@Entity
@Table(name = "disks")
public class Disk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(
            name="disk_name",
            columnDefinition = "TEXT",

    unique = true)
    private String name;

    @Column(name="disk_size")
    private int size;

    @ManyToOne
    private Machine machine;

    public Disk() {
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public static Disk from(DiskDto diskDto){
        Disk disk=new Disk();
        disk.setName(diskDto.getName());
        disk.setSize(diskDto.getSize());
        return disk;
    }
}
