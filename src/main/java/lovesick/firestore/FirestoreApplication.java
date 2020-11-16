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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


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
  public ResponseEntity login (@RequestBody LoginData loginData) {
    System.out.println(String.format("loginData: %s/%s %tT", loginData.username, loginData.password, System.currentTimeMillis()));
    return ResponseEntity.ok(HttpStatus.OK);
  }
  

  @PostMapping(value = "/postData", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseData postData(@RequestBody RequestData req) {
    System.out.println(String.format("postData: %s/%s %tT", req.username, req.email, System.currentTimeMillis()));
    ResponseData res = new ResponseData();
    res.statusCode = "2000";
    res.message = "";
    res.timeStamp = String.format("%tT", System.currentTimeMillis());
    return res;
  }
  
  @PatchMapping(value = "/patchData", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseData patchData(@RequestBody RequestData req) {
    System.out.println(String.format("patchData: %s/%s %tT", req.username, req.email, System.currentTimeMillis()));
    ResponseData res = new ResponseData();
    res.statusCode = "2000";
    res.message = "";
    res.timeStamp = String.format("%tT", System.currentTimeMillis());
    return res;
  }

  @DeleteMapping(value = "/deleteData", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseData deleteData(@RequestBody RequestData req) {
    System.out.println(String.format("deleteData: %s/%s %tT", req.username, req.email, System.currentTimeMillis()));
    ResponseData res = new ResponseData();
    res.statusCode = "2000";
    res.message = "";
    res.timeStamp = String.format("%tT", System.currentTimeMillis());
    return res;
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
