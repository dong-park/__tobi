package dongpark.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HelloApiTest {

    @Test
    void helloApi() {
        // http localhost:8080/hello?name=dongpark
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> response =
                rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "dongpark");

        // status code 확인
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        // header 확인 content-type text/plain
        Assertions.assertThat(response.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        // body 확인
        Assertions.assertThat(response.getBody()).isEqualTo("Hello, dongpark");
    }

}
