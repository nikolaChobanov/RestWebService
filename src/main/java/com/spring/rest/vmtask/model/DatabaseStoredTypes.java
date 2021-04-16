package com.spring.rest.vmtask.model;

public enum DatabaseStoredTypes {


    DISK("disk"),
    NETWORK("network"),
    MACHINE("machine");


    private String type;


    DatabaseStoredTypes(String type) {
        this.type = type;
    }
}
