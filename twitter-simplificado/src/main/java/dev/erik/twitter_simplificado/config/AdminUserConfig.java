package dev.erik.twitter_simplificado.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import dev.erik.twitter_simplificado.models.Role;
import dev.erik.twitter_simplificado.models.User;
import dev.erik.twitter_simplificado.repositories.RoleRepository;
import dev.erik.twitter_simplificado.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AdminUserConfig implements CommandLineRunner {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  @Transactional
  public void run(String... args) throws Exception {

    var roleAdmin = this.roleRepository.findByName(Role.Values.ADMIN.name()).orElseThrow(
        () -> new RuntimeException("Admin role not found"));

    this.userRepository.findByUsername("admin").ifPresentOrElse(
        user -> {
          System.out.println("Admin user already exists");
        },
        () -> {
          var user = new User();
          user.setUsername("admin");
          user.setPassword(this.bCryptPasswordEncoder.encode("admin"));
          user.setRoles(Set.of(roleAdmin));
          this.userRepository.save(user);
        });
  }
}
