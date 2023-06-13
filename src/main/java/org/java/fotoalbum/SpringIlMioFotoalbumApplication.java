package org.java.fotoalbum;

import org.java.fotoalbum.pojo.Category;
import org.java.fotoalbum.pojo.auth.Role;
import org.java.fotoalbum.pojo.auth.User;
import org.java.fotoalbum.services.CategoryService;
import org.java.fotoalbum.services.RoleService;
import org.java.fotoalbum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication  implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	@Override
	public void run(String... args) throws Exception {
		

		Role superadmin = new Role("SUPERADMIN");
		Role admin = new Role("ADMIN");
		
		roleService.save(superadmin);
		roleService.save(admin);
		
		final String adminPsw = new BCryptPasswordEncoder().encode("admin");
		final String userPsw = new BCryptPasswordEncoder().encode("user");
		
		User u1 = new User("admin", adminPsw, superadmin);
		User u2 = new User("user1", userPsw, admin);
		User u3 = new User("user2", userPsw, admin);
		
		userService.save(u1);
		userService.save(u2);
		userService.save(u3);
		
		Category c1 = new Category("Paesaggi");
		Category c2 = new Category("Artistica");
		Category c3 = new Category("Ritratto");
		Category c4 = new Category("Street");
		Category c5 = new Category("Astro");
		
		categoryService.save(c1);
		categoryService.save(c2);
		categoryService.save(c3);
		categoryService.save(c4);
		categoryService.save(c5);
		
	}

}
