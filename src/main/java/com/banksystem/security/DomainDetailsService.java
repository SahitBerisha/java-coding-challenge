package com.banksystem.security;

import static java.util.stream.Collectors.toUnmodifiableList;

import com.banksystem.core.RoleName;
import com.banksystem.users.Role;
import com.banksystem.users.User;
import com.banksystem.users.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DomainDetailsService implements UserDetailsService {

  private static final String ROLE = "ROLE_";
  private final UserRepository repository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) {
    return repository.findByUsername(username)
        .map(this::toUserDetails)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
  }

  private UserDetails toUserDetails(User user) {
    return new AuthenticatedUser(
        user.getId(),
        user.getUsername(),
        user.getPassword(),
        authorities(user));
  }

  private List<SimpleGrantedAuthority> authorities(User user) {
    return user.getRoles().stream().map(Role::getName).map(this::map).collect(toUnmodifiableList());
  }

  private SimpleGrantedAuthority map(RoleName roleName) {
    return new SimpleGrantedAuthority(ROLE + roleName.toString());
  }
}
