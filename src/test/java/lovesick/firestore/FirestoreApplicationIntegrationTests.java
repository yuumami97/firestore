package lovesick.firestore;

import static org.assertj.core.api.Assertions.*;

import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FirestoreApplicationIntegrationTests {

  @LocalServerPort
  private int port;

  private URL base;

  @Autowired
  private TestRestTemplate template;

  @BeforeEach
  public void setUp() throws java.net.MalformedURLException {
    this.base = new URL("http://localhost:" + port + "/");
  }

	@Test
	public void getHello() {
	  ResponseEntity<String> res = template.getForEntity(base.toString() + "hello", String.class);
    assertThat(res.getBody()).isEqualTo("Hello there");
  }

}
