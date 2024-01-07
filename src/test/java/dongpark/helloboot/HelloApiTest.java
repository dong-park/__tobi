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

    /*
    RestTemplate은 HTTP 서버와 통신할 때 사용하는 클래스이다.
    HTTP 서버에 요청을 보내고 응답을 받는 일을 한다.
    단 위의 코드는 RestTemplate을 직접 사용하는 것이 아니라 TestRestTemplate을 사용한다.
    TestRestTemplate은 RestTemplate을 상속받은 클래스로 테스트에서 사용하기 편리하도록 여러가지 기능을 추가한 클래스이다.
    서버의 변경사항이 있다면 서버를 재시작해야 한다.
     */

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
        Assertions.assertThat(response.getBody()).isEqualTo("*Hello, dongpark*");
    }

    @Test
    void failHelloApi() {
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> response =
                rest.getForEntity("http://localhost:8080/hello", String.class, "");
        // status code 확인
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void failHelloApi2() {
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> response =
                rest.getForEntity("http://localhost:8080/hello?name=", String.class, "");
        // status code 확인
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
