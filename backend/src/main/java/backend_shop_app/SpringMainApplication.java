package backend_shop_app;

import backend_shop_app.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SpringMainApplication {
	

	  /* @Autowired
    private UserRepository repository;
  @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(101, "javatechie", "password", "javatechie@gmail.com"),
                new User(102, "user1", "pwd1", "user1@gmail.com"),
                new User(103, "user2", "pwd2", "user2@gmail.com"),
                new User(104, "user3", "pwd3", "user3@gmail.com")
        ).collect(Collectors.toList());
        repository.saveAll(users);
    }*/

    public static void main(String[] args) {
        SpringApplication.run(SpringMainApplication.class, args);
    }

}
