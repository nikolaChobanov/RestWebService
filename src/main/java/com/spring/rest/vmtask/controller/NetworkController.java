package com.spring.rest.vmtask.controller;


import com.spring.rest.vmtask.model.Machine;
import com.spring.rest.vmtask.model.Network;
import com.spring.rest.vmtask.model.dto.MachineDto;
import com.spring.rest.vmtask.model.dto.NetworkDto;
import com.spring.rest.vmtask.service.NetworkServiceImpl;
import com.spring.rest.vmtask.service.VirtualMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/network")
public class NetworkController {

    @Autowired
    private NetworkServiceImpl networkService;
    @Autowired
    private VirtualMachineService virtualMachineService;

    @GetMapping
    public ResponseEntity<List<NetworkDto>> getNetworks() {
        List<Network> networkList = networkService.getNetworks();
        List<NetworkDto> networkDtos = networkList.stream().map(NetworkDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(networkDtos, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<NetworkDto> getNetwork(@PathVariable final Long id) {
        Network network = networkService.getNetwork(id);
        return new ResponseEntity<>(NetworkDto.from(network), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NetworkDto> addNetwork(@RequestBody final NetworkDto networkDto) {
        Network network = networkService.addNetwork(Network.from(networkDto));
        return new ResponseEntity<>(NetworkDto.from(network), HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity<NetworkDto> deleteNetwork(@PathVariable final Long id) {
        Network network = networkService.deleteNetwork(id);
        return new ResponseEntity<>(NetworkDto.from(network), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<NetworkDto> editNetwork(@PathVariable final Long id,
                                            @RequestBody final NetworkDto networkDto) {
        Network network = networkService.editNetwork(id, Network.from(networkDto));
        return new ResponseEntity<>(NetworkDto.from(network), HttpStatus.OK);
    }

    @PostMapping(value = "{vmId}/networks/{networkId}/add")
    public ResponseEntity<MachineDto> addNetworkToVirtualMachine(@PathVariable final Long vmId,
                                                              @PathVariable final Long netoworkId) {
        Machine machine = virtualMachineService.addNetworkToVM(vmId, netoworkId);
        return new ResponseEntity<>(MachineDto.from(machine), HttpStatus.OK);
    }

    @DeleteMapping(value = "{vmId}/networks/{networkId}/remove")
    public ResponseEntity<MachineDto> removeNetworkFromVirtualMachine(@PathVariable final Long vmId,
                                                                   @PathVariable final Long networkId) {
        Machine machine = virtualMachineService.removeNetworkFromVM(vmId, networkId);
        return new ResponseEntity<>(MachineDto.from(machine), HttpStatus.OK);
    }

}
