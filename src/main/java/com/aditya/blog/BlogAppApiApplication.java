package com.aditya.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aditya.blog.config.AppConstants;
import com.aditya.blog.entities.Role;
import com.aditya.blog.repository.RoleRepository;


@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner{
	
	@Autowired
	private RoleRepository roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Override
	public void run(String... args) throws Exception {
		 try {
			 Role role = new Role();
			 role.setId(AppConstants.ADMIN_ROLE);
			 role.setName("ROLE_ADMIN");
			 
			 Role role1 = new Role();
			 role1.setId(AppConstants.NORMAL_ROLE);
			 role1.setName("ROLE_NORMAL");
			 
			 List<Role> roles = List.of(role, role1);
			 
			 List<Role> result = roleRepo.saveAll(roles);
			 
			 result.forEach(r->System.out.println(r.getName()));
		 }catch(Exception e) {
			 
		 }
	}

}
