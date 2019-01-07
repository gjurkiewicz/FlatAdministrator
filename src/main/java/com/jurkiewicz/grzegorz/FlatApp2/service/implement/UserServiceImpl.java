//package com.jurkiewicz.grzegorz.FlatApp2.service.implement;
//
//import com.jurkiewicz.grzegorz.FlatApp2.model.Role;
//import com.jurkiewicz.grzegorz.FlatApp2.model.User;
//import com.jurkiewicz.grzegorz.FlatApp2.repository.RoleRepository;
//import com.jurkiewicz.grzegorz.FlatApp2.repository.UserRepository;
//import com.jurkiewicz.grzegorz.FlatApp2.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//
//@Service
//
//public class UserServiceImpl implements UserService {
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
//    @Autowired
//    RoleRepository roleRepository;
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public User findUserByEmail(String email) {
//        return userRepository.findByEmail(email);
//
//    }
//
//    @Override
//    public void saveUser(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setStatus("VERIFIED");
//        Role userRole = roleRepository.findByRole("SITE_USER");
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//        userRepository.save(user);
//    }
//
//    @Override
//    public User getActiveUser(String name) {
//        List<User> getUsers = userRepository.findAll();
//        for (User u : getUsers) {
//            if (u.getEmail().equals(name)) {
//                return u;
//            }
//        }   return null;
//        }
//
//    @Override
//    public boolean isUserAlreadyPresent(User user) {
//        return false;
//    }
//}