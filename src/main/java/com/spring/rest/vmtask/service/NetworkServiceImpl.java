package com.spring.rest.vmtask.service;


import com.spring.rest.vmtask.exception.ResourceNotFoundException;
import com.spring.rest.vmtask.model.DatabaseStoredTypes;
import com.spring.rest.vmtask.model.Network;
import com.spring.rest.vmtask.repository.NetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service("networkService")
public class NetworkServiceImpl implements NetworkService {

    @Autowired
    private NetworkRepository networkRepository;

    @Override
    public Network addNetwork(Network network) {
        return networkRepository.save(network);
    }

    @Override
    public List<Network> getNetworks() {
        return StreamSupport.stream(networkRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Network getNetwork(Long id) {
        return networkRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, DatabaseStoredTypes.NETWORK));
    }

    @Override
    public Network deleteNetwork(Long id) {
        Network network = getNetwork(id);
        networkRepository.delete(network);
        return network;
    }

    @Override
    @Transactional
    public Network editNetwork(Long id, Network network) {
        Network networkToEdit = getNetwork(id);
        networkToEdit.setName(network.getName());
        networkToEdit.setData(network.getData());
        networkToEdit.setMachine(network.getMachine());
        return networkToEdit;
    }
}
