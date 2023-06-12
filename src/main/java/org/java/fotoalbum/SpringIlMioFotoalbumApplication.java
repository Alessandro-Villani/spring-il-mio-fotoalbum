package org.java.fotoalbum;

import org.java.fotoalbum.pojo.Category;
import org.java.fotoalbum.pojo.Message;
import org.java.fotoalbum.pojo.Photo;
import org.java.fotoalbum.pojo.auth.Role;
import org.java.fotoalbum.pojo.auth.User;
import org.java.fotoalbum.services.CategoryService;
import org.java.fotoalbum.services.MessageService;
import org.java.fotoalbum.services.PhotoService;
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
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MessageService messageService;

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
		
		Photo p1 = new Photo("foto 1", "descrizione", "https://picsum.photos/id/180/150", true, u3, c1, c2);
		Photo p2 = new Photo("foto 2", "descrizione", "https://picsum.photos/id/275/150", true, u3, c3, c2, c4);
		Photo p3 = new Photo("foto 3", "descrizione", "https://picsum.photos/id/355/150", true, u2, c1, c5);
		Photo p4 = new Photo("foto 4", "descrizione", "https://picsum.photos/id/401/150", true, u2);
		Photo p5 = new Photo("foto 5", "descrizione", "https://picsum.photos/id/506/150", false, u3, c3, c2);
		
		photoService.save(p1);
		photoService.save(p2);
		photoService.save(p3);
		photoService.save(p4);
		photoService.save(p5);
		
		Message m1 = new Message("gino@gino.it", "ciao", p1);
		
		messageService.save(m1);
		
		
	}

}
