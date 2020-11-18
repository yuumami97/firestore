package lovesick.firestore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Token { 
  
  // Actually, no need any annotations
  @JsonProperty("kind")
  public String kind;
  public String localId;
  public String email;
  public String displayName;
  public String idToken;
  public boolean registered;
  public String refreshToken;
  public String expiresIn;

  @Override
  public String toString() {
    return "Token { email = " + email + ", idToken = " + idToken + ", refreshToken = " + refreshToken + " }";
  }

}

/*
{  
  "kind": "identitytoolkit#VerifyPasswordResponse",  
  "localId": "8tXaZOS4oCQGkTjaUeTKw9DtcSE2",  
  "email": "yuumami97@gmail.com",  
  "displayName": "",  
  "idToken": "eyJhbGciOiJSUzI1NiIsImtpZCI6IjNlNTQyN2NkMzUxMDhiNDc2NjUyMDhlYTA0YjhjYTZjODZkMDljOTMiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vbG92ZXNpY2stZmlyZXN0b3JlIiwiYXVkIjoibG92ZXNpY2stZmlyZXN0b3JlIiwiYXV0aF90aW1lIjoxNjA1Njc5NzM1LCJ1c2VyX2lkIjoiOHRYYVpPUzRvQ1FHa1RqYVVlVEt3OUR0Y1NFMiIsInN1YiI6Ijh0WGFaT1M0b0NRR2tUamFVZVRLdzlEdGNTRTIiLCJpYXQiOjE2MDU2Nzk3MzUsImV4cCI6MTYwNTY4MzMzNSwiZW1haWwiOiJ5dXVtYW1pOTdAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7ImVtYWlsIjpbInl1dW1hbWk5N0BnbWFpbC5jb20iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJwYXNzd29yZCJ9fQ.mZA2r-GjOBr_K29nwmyxmCLqdso4NLV2QQ2kzkry0vCcrH5MrURjGldraY37Udis5fX4YN7jx7bzSWdO8sBq5eAbq-glVLySeYxZwznvRdSTy5JciDXngYPc0_okezUhIbwfKhZa0iywheLql6PKpGxDPg1ahyupCFzZxxdqcEjI3YCHc5FtVQhUf6ElbO9rQGXaPoU9aMfzeC1Gsure9GsoZgT1tE0Cx_8CSGZ5tqrf4pdQpmxqTBuIWWgudEb9Rf4ihzGZufHB8SL2FrI9bOGrTnJrinDnUEEENyItUf2l6p07oV6f9ral4ws_5ovTNs3Xki98WKHyCfQljoxnhA",        
  "registered": true,
  "refreshToken": "AG8BCnenelxsw5bdMrTpVy-HDEek1mIuQBtxkspd7fNyFO-eFu6ujihD8Rl1WpQMfVg9ODHXtQUBLnZYBrpn-4JnSN3bZjAn31YCZBfR5ew4Y1AuzJdPAQduGRvRUmHVbs6j0wkE_OAESEAqv1cnSayeJ7mec1N3vgvWIypNmRpMyQOieUPXyWW514ECuve0jFWmxkJkV0zJwqHNbpBmgQoGbP_4JEYHKA",        
  "expiresIn": "3600"    
}
*/

