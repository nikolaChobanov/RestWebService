package com.spring.rest.vmtask.service;

import com.spring.rest.vmtask.model.Network;

import java.util.List;

public interface NetworkService {

    Network addNetwork(Network network);
    List<Network> getNetworks();
    Network getNetwork(Long id);
    Network deleteNetwork(Long id);
    Network editNetwork(Long id, Network network);

}
