package com.spring.rest.vmtask.dto;

import com.spring.rest.vmtask.model.Disk;

public class DiskDto {

    private Long id;
    private String name;
    private Integer size;

    public static DiskDto from(Disk disk) {

        DiskDto diskDto = new DiskDto();
        diskDto.setId(disk.getId());
        diskDto.setName(disk.getName());
        diskDto.setSize(disk.getSize());
        return diskDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    private void setSize(Integer size) {
        this.size = size;
    }
}
