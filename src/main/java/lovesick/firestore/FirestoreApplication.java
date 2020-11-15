package lovesick.firestore;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@SpringBootApplication
@RestController
public class FirestoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirestoreApplication.class, args);
	}

  @GetMapping("/hello")
  public String hello(@RequestParam(value = "name", defaultValue = "there") String name) {
    return String.format("Hello %s", name);
  }

  @GetMapping("/helloWorld")
  ResponseEntity<String> hello() {
    return ResponseEntity.ok("Hello World!");
  }

  @PostMapping("/login")
  ResponseEntity login (@RequestBody LoginData loginData) {
    System.out.println(String.format("loginData: %s/%s %tT", loginData.username, loginData.password, System.currentTimeMillis()));
    return ResponseEntity.ok(HttpStatus.OK);
  }
  

  @PostMapping("/postData")
  public ResponseEntity postData(@RequestBody RequestData req) {
    System.out.println(String.format("postData: %s/%s %tT", req.username, req.email, System.currentTimeMillis()));
    return ResponseEntity.ok(HttpStatus.OK);
  }
  
  @PatchMapping("/patchData")
  public ResponseEntity patchData(@RequestBody RequestData req) {
    System.out.println(String.format("patchData: %s/%s %tT", req.username, req.email, System.currentTimeMillis()));
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @DeleteMapping("/deleteData")
  public ResponseEntity deleteData(@RequestBody RequestData req) {
    System.out.println(String.format("deleteData: %s/%s %tT", req.username, req.email, System.currentTimeMillis()));
    return ResponseEntity.ok(HttpStatus.OK);
  }


/* 
  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {
      System.out.println("Let's inspect the beans provided by Spring Boot:");
      String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);
      for (String beanName : beanNames) {
        System.out.println(beanName);
      }
    };
  }
*/


}
