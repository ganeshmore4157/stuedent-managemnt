package com.ganesh.student_managment.service.impl;

import com.ganesh.student_managment.bo.UserBO;
import com.ganesh.student_managment.constants.ErrorConstants;
import com.ganesh.student_managment.entity.Roles;
import com.ganesh.student_managment.entity.Users;
import com.ganesh.student_managment.exception.ResourceNotFoundException;
import com.ganesh.student_managment.exception.EventException;
import com.ganesh.student_managment.repository.RoleRepository;
import com.ganesh.student_managment.repository.UsersRepository;
import com.ganesh.student_managment.security.JwtTokenHelper;
import com.ganesh.student_managment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UserService {

    @Autowired
    UsersRepository userRepo;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenHelper jwtTokenHelper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Users userLogin(UserBO userBO) throws Exception {
        Users checkuser=userRepo.findByEmail(userBO.getEmail());
        if (checkuser==null) {
            throw new ResourceNotFoundException(ErrorConstants.NOT_FOUND.toString(), "Username or password incorrect");
        }
        if (!passwordEncoder.matches(userBO.getPassword(),checkuser.getPassword())){
        throw new EventException(ErrorConstants.INVALID.toString(), "username or password incorrect");
        }
        if (!checkuser.isStatus()){
           throw new EventException(ErrorConstants.INVALID.toString(), "Accoun is not active");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userBO.getEmail(),userBO.getPassword()));
        UserDetails userDetails=userDetailsService.loadUserByUsername(checkuser.getMobile());
        String generateToken=jwtTokenHelper.generateToken(userDetails);
        checkuser.setSessionToken(generateToken);
        if (null !=userBO.getDeviceId() && !userBO.getDeviceId().isEmpty()){
         checkuser.setDeviceId(userBO.getDeviceId());
         checkuser.setDeviceInfo(userBO.getDeviceInfo());
         userRepo.save(checkuser);
        }
        return checkuser;
    }

    @Override
    public void registerUser(UserBO userBO) throws Exception {
        Users checkEmail=userRepo.findByEmail(userBO.getEmail());
        if (null != checkEmail){
          throw new EventException(ErrorConstants.UNIQUE_KEY_EXISTS.toString(), "Email id is all ready exists");
        }
        Users checkMobile=userRepo.findByMobile(userBO.getMobile());
        if (null != checkMobile){
            throw new EventException(ErrorConstants.UNIQUE_KEY_EXISTS.toString(), "Mobile number is all ready exists") ;
        }

        Users user =new Users();
        user.setUserName(userBO.getFullName());
        user.setPassword(userBO.getPassword());
        user.setMobile(userBO.getMobile());
        user.setEmail(userBO.getEmail());

       Roles role=roleRepository.findById(userBO.getRole().getRoleId()).orElseThrow(
               () -> new ResourceNotFoundException(ErrorConstants.NOT_FOUND.toString(),"Role not found"));
       user.setRole(userBO.getRole());
        userRepo.save(user);
    }

    @Override
    public Users getLoggedUser() throws Exception {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        return (Users) auth.getPrincipal();
    }
}
