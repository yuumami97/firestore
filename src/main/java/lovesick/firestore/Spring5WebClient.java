package lovesick.firestore;

import java.util.Arrays;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
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

  ExchangeFilterFunction printlnFilter = new ExchangeFilterFunction() {
    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
      System.out.println("\n\n" + request.method().toString().toUpperCase() + ":\n\nURL:"
                                + request.url().toString() + ":\n\nHeaders:" + request.headers().toString() + "\n\nAttributes:"
                                + request.attributes() + "\n\n");
      return next.exchange(request);
    }
  };

  public Spring5WebClient(String apiKey) {
    API_KEY = apiKey;
  }

  public String signUp(SignData sd) {
    //String endPoint = "http://localhost:5000";
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
                    .filter(printlnFilter)
                    .build();

    //LinkedMultiValueMap map = new LinkedMultiValueMap();
    //map.add("email", sd.email);
    //map.add("password", sd.password);
    //BodyInserter<MultiValueMap, ClientHttpRequest> inserter = BodyInserters.fromMultipartData(map);
    //BodyInserter<?, ?> inserter = BodyInserters.fromMultipartData(map);

    //String res = wc.method(HttpMethod.POST).uri("/testData?key=" + API_KEY)
    String res = wc.method(HttpMethod.POST).uri("/v1/accounts:signUp?key=" + API_KEY)
                  .body(Mono.just(sd), SignData.class)
                  .retrieve()
                  .bodyToMono(String.class)
                  .block();

    return res;
  }

 rpublic String signIn(SignData sd) {
    String endPoint = "https://identitytoolkit.googleapis.com";
    WebClient wc = WebClient.builder()
                    .baseUrl(endPoint)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();
    StringBuilder sb = new StringBuilder();
    String res = wc.method(HttpMethod.POST).uri("/v1/accounts:signInWithPassword?key=" + API_KEY)
                  .body(Mono.just(sd), SignData.class)
                  .exchange()
                  .doOnSuccess(clientResponse -> System.out.println("clientResponse.headers() = " + clientResponse.headers()))
                  // Cannot assign variable status inside lambda but we can mutate it //https://stackoverflow.com/questions/33518589/java-assign-a-variable-within-lambda
                  .doOnSuccess(clientResponse -> sb.append(clientResponse.statusCode())) 
                  //.doOnSuccess(clientResponse -> System.out.println("clientResponse.statusCode() = " + clientResponse.statusCode()))
                  .flatMap(clientResponse -> clientResponse.bodyToMono(String.class))
                  .block();
    String status = sb.toString(); 
    System.out.println("clientResponse.statusCode() = " + status); 

/*
    {  
      "error": {    
        "code": 400,    
          "message": "INVALID_PASSWORD",    
          "errors": [      
            {        
              "message": "INVALID_PASSWORD",        
              "domain": "global",        
              "reason": "invalid"      
            }    
          ]  
      }
    }
*/

    return res;
  }

  public String get() {return "";}
  public String post() {return "";}
  public String patch() {return "";}
  public String delete() {return "";}

}
