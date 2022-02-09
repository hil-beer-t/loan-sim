package com.hilbert.loansimapi.data;

import com.hilbert.loansimapi.models.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

// Basic config
// Client Data in Spring Security pattern
public class ClientDetailData implements UserDetails {

    private final Optional<Client> client;

    public ClientDetailData(Optional<Client> client) {
        this.client = client;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {

        return client.orElse(new Client()).getPassword();
    }

    @Override
    public String getUsername() {
        return client.orElse(new Client()).getEmail();
    }

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
        return true;
    }
}
