package com.sales.crm.service;

import com.sales.crm.dtos.ClientRequestDTO;
import com.sales.crm.dtos.ClientResponseDTO;
import com.sales.crm.dtos.ClientSimpleResponseDTO;
import com.sales.crm.exceptions.ResourceNotFoundException;
import com.sales.crm.exceptions.ResourceNotValidException;
import com.sales.crm.model.Category;
import com.sales.crm.model.Client;
import com.sales.crm.model.Quotation;
import com.sales.crm.model.User;
import com.sales.crm.repository.ClientRepository;
import com.sales.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private ClientRepository clientRepository;
    private UserRepository userRepository;
    private QuotationService quotationService;

    @Autowired
    public ClientService(ClientRepository clientRepository, UserRepository userRepository, QuotationService quotationService) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.quotationService = quotationService;
    }
    @Transactional
    public Client addClientToUser (ClientRequestDTO clientRequestDTO){
        User user = userRepository.findUserById(clientRequestDTO.getUserId()).orElseThrow(()->new  ResourceNotFoundException("user not found"));
        Client client = new Client();
        client.setAddress(clientRequestDTO.getAddress());
        client.setName(clientRequestDTO.getName());

        if(!isValidEmail(clientRequestDTO.getEmail())){
            throw new ResourceNotValidException("email not valid");
        }else {
            client.setEmail(clientRequestDTO.getEmail());
        }

        if(!isPhoneNumberValid(clientRequestDTO.getPhoneNumber())) {
            throw new ResourceNotValidException("Number not valid");
        }else{
            client.setPhoneNumber(clientRequestDTO.getPhoneNumber());
        }

        client.setUser(user);
        user.getClients().add(client);
        return client;
    }
    @Transactional
    public boolean isPhoneNumberValid(String phoneNumber){
        if(phoneNumber.length()<10 || phoneNumber.length()>13){
            return false;
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            if(!Character.isDigit(phoneNumber.charAt(i))){
                return false;
            }
        }
        return true;
    }
    @Transactional
    public boolean isValidEmail(String email) {
        // Define the regular expression for a valid email address
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(emailRegex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(email);

        // Return true if the email matches the pattern, otherwise false
        return matcher.matches();
    }
    @Transactional
    public Client updateClient(Long clientId,ClientRequestDTO clientRequestDTO){
            //caut clientul in baza de date dupa id
        Client client = clientRepository.findById(clientId).orElseThrow(()->new ResourceNotFoundException("clientul nu exista"));
//        User user = userRepository.findUserById(client.get).orElseThrow(()->new  ResourceNotFoundException("user not found"));
//        Client clientToBeUpdated = new Client();

        if(clientRequestDTO.getName() != null){
            client.setName(clientRequestDTO.getName());
        }
        if(clientRequestDTO.getPhoneNumber() != null){
            client.setPhoneNumber(clientRequestDTO.getPhoneNumber());
        }
        if(clientRequestDTO.getEmail()!= null){
            client.setEmail(clientRequestDTO.getEmail());
        }
        if(clientRequestDTO.getAddress() != null){
            client.setAddress(clientRequestDTO.getAddress());
        }

        return client;

    }

    @Transactional
    public List<ClientSimpleResponseDTO> findAll(){

        List<Client> clients =clientRepository.findAll();
                return clients.stream()
                        .map(client -> mapFromClientToSimpleResponseDTO(client))
                        .collect(Collectors.toList());


    }

    @Transactional
    public ClientResponseDTO viewClientDetailsByName(String clientName){
        return mapFromClientToResponseDTO(clientRepository.findClientByName(clientName).orElseThrow(()-> new ResourceNotFoundException("Clientul cu acest nume nu exista")));
    }

    //clientii care au cotatii intre doua date calendaristice
    //vizualizare client cu lista de cotatii +toate detaliile
    @Transactional
    public ClientResponseDTO mapFromClientToResponseDTO(Client client){
        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
        clientResponseDTO.setName(client.getName());
        clientResponseDTO.setPhoneNumber(client.getPhoneNumber());
        clientResponseDTO.setEmail(client.getEmail());
        clientResponseDTO.setAddress(client.getAddress());
        clientResponseDTO.setUserId(client.getUser().getId());
        List<Quotation> clientQuotations = client.getQuotations();

        clientResponseDTO.setQuotationResponseDTOs(clientQuotations.stream()
                .map(quotation -> quotationService.mapFromQuotationToDTOResponse(quotation))
                .collect(Collectors.toList()));

        return clientResponseDTO;
    }
    @Transactional
    public Long getNumberOfQuotations(Client client){
        return client.getQuotations().stream()
                .filter(quotation -> quotation!=null)
                .count();
    }
    @Transactional
    public Long getNumberOfOrders(Client client){
        return client.getOrders().stream()
                .filter(order->order!=null)
                .count();
    }
    @Transactional
    public Long getNumbersOfMeetings(Client client){
        return client.getMeetings().stream()
                .filter(meeting -> meeting!=null )
                .count();
    }
    @Transactional
    public ClientSimpleResponseDTO mapFromClientToSimpleResponseDTO(Client client){
        ClientSimpleResponseDTO clientSimpleResponseDTO = new ClientSimpleResponseDTO();
        clientSimpleResponseDTO.setName(client.getName());
        clientSimpleResponseDTO.setUserName(client.getUser().getName());
        clientSimpleResponseDTO.setQuotations(getNumberOfQuotations(client));
        clientSimpleResponseDTO.setOrders(getNumberOfOrders(client));
        clientSimpleResponseDTO.setMeetings(getNumbersOfMeetings(client));
        return clientSimpleResponseDTO;
    }


}
