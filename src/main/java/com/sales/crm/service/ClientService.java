package com.sales.crm.service;

import com.sales.crm.dtos.ClientRequestDTO;
import com.sales.crm.exceptions.ResourceNotFoundException;
import com.sales.crm.exceptions.ResourceNotValidException;
import com.sales.crm.model.Client;
import com.sales.crm.model.User;
import com.sales.crm.repository.ClientRepository;
import com.sales.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClientService {
    private ClientRepository clientRepository;
    private UserRepository userRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
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

        if(clientRequestDTO.getName() == null){
            client.setName(client.getName());
        }else{
            client.setName(clientRequestDTO.getName());
        }
        if(clientRequestDTO.getPhoneNumber() == null){
            client.setPhoneNumber(client.getPhoneNumber());
        }else{
            client.setPhoneNumber(clientRequestDTO.getPhoneNumber());
        }
        if(clientRequestDTO.getEmail()== null){
            client.setEmail(client.getEmail());
        }else{
            client.setEmail(clientRequestDTO.getEmail());
        }
        if(clientRequestDTO.getAddress() == null){
            client.setAddress(client.getAddress());
        }else{
            client.setAddress(clientRequestDTO.getAddress());
        }

        return client;

    }

}
