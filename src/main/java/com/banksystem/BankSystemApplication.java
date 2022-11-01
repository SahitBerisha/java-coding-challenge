package com.banksystem;

import static com.banksystem.core.RoleName.ADMIN;

import com.banksystem.users.Role;
import com.banksystem.users.User;
import com.banksystem.users.UserRepository;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJpaAuditing
@SpringBootApplication
public class BankSystemApplication {

  public static void main(String[] args) {
    SpringApplication.run(BankSystemApplication.class, args);
  }

  @Bean
  CommandLineRunner adminInitializer(UserRepository repository,
                                     PasswordEncoder passwordEncoder) {
    return args -> {
      if (repository.findByUsername("admin").isEmpty()) {
        var user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("password"));
        user.setRoles(Set.of(new Role(ADMIN)));
        repository.save(user);
      }
    };
  }
}
