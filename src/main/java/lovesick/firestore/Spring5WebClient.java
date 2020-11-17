package lovesick.firestore;

import java.util.Arrays;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.BodyInserter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.http.client.reactive.ClientHttpRequest;

import reactor.core.publisher.Mono;


public class Spring5WebClient {

  private String API_KEY;

  public Spring5WebClient(String apiKey) {
    API_KEY = apiKey;
  }

  public String signUp(SignData sd) {
    String endPoint = "https://identitytoolkit.googleapis.com";
    
    /*
    WebClient wc = WebClient.builder()
                    .baseUrl(endPoint)
                    .defaultCookie("cookieKey", "cookieValue")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
                    .defaultUriVariables(Collections.singletonMap("url", endPoint))
                    .build();
    */

    WebClient wc = WebClient.builder()
                    .baseUrl(endPoint)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();

    //LinkedMultiValueMap map = new LinkedMultiValueMap();
    //map.add("email", sd.email);
    //map.add("password", sd.password);
    //BodyInserter<MultiValueMap, ClientHttpRequest> inserter = BodyInserters.fromMultipartData(map);
    //BodyInserter<?, ?> inserter = BodyInserters.fromMultipartData(map);

    
    String res = wc.method(HttpMethod.POST).uri("/v1/accounts:signUp?key=" + API_KEY)
                  .body(Mono.just(sd), SignData.class)
                  .retrieve()
                  .bodyToMono(String.class)
                  .block();



    System.out.println(API_KEY);
    return res;
  }

  public String signIn() {
  
    return "";
  }
  public String get() {return "";}
  public String post() {return "";}
  public String patch() {return "";}
  public String delete() {return "";}

}
