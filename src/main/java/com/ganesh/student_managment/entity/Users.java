package com.ganesh.student_managment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ganesh.student_managment.constants.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Users  implements  UserDetails {


    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_mobile")
    private String mobile;

    @Column(name = "user_email")
    private String email;

    @Column(name = "enable_login",columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean enableLogin;

    @JsonIgnore
    @Column(name = "password", length = 255, nullable = true)
    private String password;

    @Lob
    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "device_info", length = 255, nullable = true)
    private String deviceInfo;

    @ManyToOne(targetEntity = Roles.class)
    @JoinColumn(name = "role_id", nullable = false)
    private Roles role;

    @Column(name = "status", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean status;

    @Transient
    private String sessionToken;

    @Transient
    private String refreshToken;

    public Users(){}


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getName());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);
        return authorities;
    }

    public String getUsername() {
     return getRole().getName().equals(Role.ADMIN.toString()) ? mobile : email; }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }

}
