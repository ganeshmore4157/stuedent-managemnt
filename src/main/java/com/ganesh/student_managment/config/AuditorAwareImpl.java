package com.ganesh.student_managment.config;

import com.ganesh.student_managment.entity.Users;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class AuditorAwareImpl implements AuditorAware<UUID> {
    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        try {
            if (null != auth && null != auth.getPrincipal()){
                Users users=(Users) auth.getPrincipal();
               return Optional.of(users.getUserId());
            }
        }catch (Exception e){
         return  null;
        }
        return null;
    }
}
