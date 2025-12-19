package com.ecommerce.user.service;



import com.ecommerce.user.dto.AddressDTO;
import com.ecommerce.user.repository.UserRepository;
import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.model.User;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.model.Address;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<UserResponse> fetchAllUsers(){

        return userRepository.findAll().stream()
                .map(x->mapToUserResponse(x))
                .collect(Collectors.toList());
    }
    public void addUser(UserRequest userRequest){
        User user= new User();
        updateUserFromRequest(user,userRequest);
        userRepository.save(user);
    }

    public Optional<UserResponse> fetchUser(Long id){
       return userRepository.findById(id)
               .map(this::mapToUserResponse);
    }
    public boolean updateUser(Long id, UserRequest updatedUserRequest){
        return userRepository.findById(id)
        .map(existingUser-> {
            updateUserFromRequest(existingUser, updatedUserRequest);
            userRepository.save(existingUser);
            return true;
        }).orElse( false);
    }

    public boolean deleteUser(Long id) {
        Optional<User> delUser = userRepository.findById(id);
        if (delUser.isPresent()){
            userRepository.delete(delUser.get());
            return true;
        }
        return false;
    }
    public UserResponse mapToUserResponse(User user){
        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));

        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());

        if (user.getAddress() != null){
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            response.setAddress(addressDTO);
        }
        return response;
    }



    private void updateUserFromRequest(User user,
                                       UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if (userRequest.getAddress() != null) {
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZipcode(userRequest.getAddress().getZipcode());
            user.setAddress(address);
        }
    }

}
/*
public boolean updateUser(Long id, UserRequest updatedUser){
        return userRepository.findById(id)
                .map(existingUser->{
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setPhone(updatedUser.getPhone());

                    if (existingUser.getAddress() != null) {
                        System.out.println("ddress is not null");
                        Address address = new Address();
                        address.setStreet(updatedUser.getAddress().getStreet());
                        address.setCity(updatedUser.getAddress().getCity());
                        address.setState(updatedUser.getAddress().getState());
                        address.setCountry(updatedUser.getAddress().getCountry());
                        address.setZipcode(updatedUser.getAddress().getZipcode());
                        existingUser.setAddress(address);
                    }


                        userRepository.save(existingUser);
                        return true;
                }).orElse(false);
    }

 */