package com.example.b5.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.b5.model.Account;
import com.example.b5.model.Role;
import com.example.b5.repository.AccountRepository;
import com.example.b5.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(AccountRepository accountRepository,
                               RoleRepository roleRepository,
                               PasswordEncoder passwordEncoder) {
        return args -> {

            // ❗ tránh insert trùng
            if (accountRepository.findByLoginName("admin").isPresent()) {
                return;
            }

            // ✅ Tạo role
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");

            Role userRole = new Role();
            userRole.setName("ROLE_USER");

            roleRepository.save(adminRole);
            roleRepository.save(userRole);

            // ✅ Admin
            Account admin = new Account();
            admin.setLogin_name("admin");
            admin.setPassword(passwordEncoder.encode("1"));

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            admin.setRoles(adminRoles);

            accountRepository.save(admin);

            // ✅ User
            Account user = new Account();
            user.setLogin_name("user");
            user.setPassword(passwordEncoder.encode("123456"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);
            user.setRoles(userRoles);

            accountRepository.save(user);

            System.out.println("✅ Insert data thành công!");
        };
    }
}