package com.banksystem.users;

import java.util.Set;
import javax.validation.constraints.NotBlank;

public record UserRequest(@NotBlank String username, @NotBlank String password, Set<Role> roles) {

}
