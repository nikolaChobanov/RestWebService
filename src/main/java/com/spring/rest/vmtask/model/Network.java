package com.spring.rest.vmtask.model;

import com.spring.rest.vmtask.model.dto.NetworkDto;

import javax.persistence.*;

@Entity
@Table(name = "networks")
public class Network {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(
            name="network_name",
            columnDefinition = "TEXT",
            nullable=false,
            unique = true)
    private String name;

    @Column(nullable = false)
    private String data;

    @ManyToOne
    private Machine machine;


    public Network() {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public static Network from(NetworkDto networkDto){
        Network network=new Network();
        network.setName(networkDto.getName());
        network.setData(networkDto.getData());
        return network;
    }
}
