package com.spring.rest.vmtask.dto;

import com.spring.rest.vmtask.model.Network;

public class NetworkDto {

    private Long id;
    private String name;
    private String data;

    public static NetworkDto from(Network network) {

        NetworkDto networkDto = new NetworkDto();
        networkDto.setId(network.getId());
        networkDto.setName(network.getName());
        networkDto.setData(network.getData());
        return networkDto;
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

    public String getData() {
        return data;
    }

    private void setData(String data) {
        this.data = data;
    }
}
