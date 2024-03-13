package com.sales.crm.dtos.mapper;

import com.sales.crm.dtos.ClientRequestDTO;
import com.sales.crm.dtos.ClientResponseDTO;
import com.sales.crm.dtos.ClientSimpleResponseDTO;
import com.sales.crm.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {
    @Mapping(target = "id", ignore=true)
    public abstract Client mapClientRequestDTOtoClient(ClientRequestDTO clientRequestDTO);


    //cand vrem sa mapam de la un obiect la un dto, trebuie sa punem adnotarile prin care
    // ii spunem
    // de la ce proprietate a obiectului mapam catre ce proprietate a dto-ului
    @Mapping(target = "name", expression = "java(client.getName())")
    @Mapping(target = "phoneNumber", expression = "java(client.getPhoneNumber())")
    @Mapping(target = "email", expression = "java(client.getEmail())")
    @Mapping(target = "address", expression = "java(client.getAddress())")
    @Mapping(target = "userId", expression = "java(client.getUser().getId())")
    public abstract ClientResponseDTO mapClientToResponseDTO(Client client);



}
