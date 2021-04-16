package com.spring.rest.vmtask.exception;

public class ResourceAlreadyAssigned extends RuntimeException {

    public ResourceAlreadyAssigned(final Long vmId,final Long resourceId) {
    super("Item with id:" + resourceId +
            " is already assigned to Virtual Machine with id: "+ vmId);
    }
}
