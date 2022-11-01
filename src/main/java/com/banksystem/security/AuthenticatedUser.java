package com.banksystem.security;

import java.util.List;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AuthenticatedUser extends User {

  private final String id;

  public AuthenticatedUser(String id, String username, String password, List<SimpleGrantedAuthority> authorities) {
    super(username, password, authorities);
    this.id = id;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof AuthenticatedUser) {
      return id.equals(((AuthenticatedUser) other).getId()) && super.equals(other);
    }
    return super.equals(other);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, getUsername());
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();
    sb.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append("; ");
    sb.append("Id: ").append(this.id).append("; ");
    sb.append("Username: ").append(this.getUsername()).append("; ");
    sb.append("Password: [PROTECTED]; ");
    if (!getAuthorities().isEmpty()) {
      sb.append("Granted Authorities: ");
      boolean first = true;
      for (GrantedAuthority auth : getAuthorities()) {
        if (!first) {
          sb.append(",");
        }
        first = false;
        sb.append(auth);
      }
    } else {
      sb.append("Not granted any authorities");
    }
    return sb.toString();
  }
}
