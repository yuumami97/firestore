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
  private Token token;

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

  public Spring5WebClient(String apiKey, Token tokenIn) {
    API_KEY = apiKey;
    token = tokenIn;
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

  public Token signIn(SignData sd) {

    String endPoint = "https://identitytoolkit.googleapis.com";
    WebClient wc = WebClient.builder()
                    .baseUrl(endPoint)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();
    /*
    
       StringBuilder sb = new StringBuilder();
    Token resToken = wc.method(HttpMethod.POST).uri("/v1/accounts:signInWithPassword?key=" + API_KEY)
                  .body(Mono.just(sd), SignData.class)
                  .exchange()
                  .doOnSuccess(clientResponse -> System.out.println("clientResponse.headers() = " + clientResponse.headers()))
                  // Cannot assign variable status inside lambda but we can mutate it //https://stackoverflow.com/questions/33518589/java-assign-a-variable-within-lambda
                  .doOnSuccess(clientResponse -> sb.append(clientResponse.statusCode())) 
                  //.doOnSuccess(clientResponse -> System.out.println("clientResponse.statusCode() = " + clientResponse.statusCode()))
                  .flatMap(clientResponse -> clientResponse.bodyToMono(String.class))
                  //.flatMap(clientResponse -> clientResponse.bodyToMono(Token.class))
                  .block();
    String status = sb.toString(); 
    System.out.println("clientResponse.statusCode() = " + status); 
    
    */

    token = wc.method(HttpMethod.POST).uri("/v1/accounts:signInWithPassword?key=" + API_KEY)
                  .body(Mono.just(sd), SignData.class)
                  .retrieve()
                  .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new Http4xxException("4xx")))
                  .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new Http5xxException("5xx")))
                  .bodyToMono(Token.class)
                  .block();

// TODO: handle error
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

    return token;
  }

  public Pill get(String docId) {
    String endPoint = "https://firestore.googleapis.com";
    WebClient wc = WebClient.builder()
                    .baseUrl(endPoint)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultHeaders(httpHeaders -> httpHeaders.setBearerAuth(token.idToken))
                    .build();

    Pill pill = wc.method(HttpMethod.GET).uri("/v1/projects/lovesick-firestore/databases/(default)/documents/pills/" + docId + "?key=" + API_KEY)
                  .retrieve()
                  .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new Http4xxException("4xx")))
                  .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new Http5xxException("5xx")))
                  .bodyToMono(Pill.class)
                  .block();
    return pill;
  }

  public Pill post(Pill pill) {
/*
    String endPoint = "http://localhost:5000";
    String res = WebClient.builder()
                  .baseUrl(endPoint)
                  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                  .defaultHeaders(httpHeaders -> httpHeaders.setBearerAuth(token.idToken))
                  .build()
                  .method(HttpMethod.POST).uri("/testData?key=" + API_KEY)
                  .body(Mono.just(pill), Pill.class)
                  .retrieve()
                  .bodyToMono(String.class)
                  .block();
    Pill resPill = new Pill();
*/    
    
    String endPoint = "https://firestore.googleapis.com";
    WebClient wc = WebClient.builder()
                    .baseUrl(endPoint)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultHeaders(httpHeaders -> httpHeaders.setBearerAuth(token.idToken))
                    .build();
    Pill resPill = wc.method(HttpMethod.POST).uri("/v1/projects/lovesick-firestore/databases/(default)/documents/pills?key=" + API_KEY)
                  .body(Mono.just(pill), Pill.class)
                  .retrieve()
                  .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new Http4xxException("4xx")))
                  .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new Http5xxException("5xx")))
                  .bodyToMono(Pill.class)
                  .block();
    
    
    return resPill;
  }

  public Pill patch(Pill pill) {
    
    String endPoint = "https://firestore.googleapis.com";
    WebClient wc = WebClient.builder()
                    .baseUrl(endPoint)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultHeaders(httpHeaders -> httpHeaders.setBearerAuth(token.idToken))
                    .build();
    Pill resPill = wc.method(HttpMethod.PATCH).uri("/v1/projects/lovesick-firestore/databases/(default)/documents/pills/kceEs0Ui1eUcWLP2BcVi?updateMask.fieldPaths=name&updateMask.fieldPaths=taste&key=" + API_KEY)
                  .body(Mono.just(pill), Pill.class)
                  .retrieve()
                  .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new Http4xxException("4xx")))
                  .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new Http5xxException("5xx")))
                  .bodyToMono(Pill.class)
                  .block();
    
    
    return resPill;
  }

  public void delete(String docId) {
    String endPoint = "https://firestore.googleapis.com";
    WebClient wc = WebClient.builder()
                    .baseUrl(endPoint)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultHeaders(httpHeaders -> httpHeaders.setBearerAuth(token.idToken))
                    .build();

    Pill pill = wc.method(HttpMethod.DELETE).uri("/v1/projects/lovesick-firestore/databases/(default)/documents/pills/" + docId + "?key=" + API_KEY)
                  .retrieve()
                  .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new Http4xxException("4xx")))
                  .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new Http5xxException("5xx")))
                  .bodyToMono(Pill.class)
                  .block();
  }

}
