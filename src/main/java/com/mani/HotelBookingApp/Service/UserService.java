package com.mani.HotelBookingApp.Service;

import com.mani.HotelBookingApp.DTO.SignupRequest;
import com.mani.HotelBookingApp.DTO.UserDTO;
import com.mani.HotelBookingApp.Entity.User;
import com.mani.HotelBookingApp.Enum.UserRole;
import com.mani.HotelBookingApp.Repository.UserRepo;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo repo;

    @PostConstruct
    public void createAdminAcc() {
        Optional<User> adminAcc = repo.findByUserRole(UserRole.ADMIN);
        if (adminAcc.isEmpty()) {
            User user = new User();
            user.setEmail("mani@123");
            user.setUsername("manik");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder(12).encode("mani123"));
            repo.save(user);
        } else {
            System.out.println("acc already exist");
        }
    }

    public UserDTO createUser(SignupRequest request) {
        if (repo.findByEmail(request.getEmail()).isPresent()) {
            throw new EntityExistsException("user Already Present with username" + request.getEmail());
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setUserRole(UserRole.CUSTOMER);
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        User create = repo.save(user);
        return create.getUserdto();
    }

    public Optional<User> findByEmail(String email) {
        System.out.println(email);
        System.out.println(repo.findByEmail(email));
        return repo.findByEmail(email);
    }
}
