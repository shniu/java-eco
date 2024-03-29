package io.github.shniu.gateway;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"test.uri=http://localhost:${wiremock.server.port}"})
//@AutoConfigureWireMock(port = 0)
@Slf4j
public class ApplicationTest {
//    @Autowired
//    public WebTestClient webClient;

    @Test
    public void contextLoads() throws Exception {
        // Stubs
//        stubFor(get(urlEqualTo("/get"))
//                .willReturn(aResponse()
//                        .withBody("{\"headers\":{\"Hello\":\"World\"}}")
//                        .withHeader("Content-Type", "application/json")));
//        stubFor(get(urlEqualTo("/delay/3"))
//                .willReturn(aResponse()
//                        .withBody("no fallback")
//                        .withFixedDelay(3000)));
//
//        webClient.get().uri("/get")
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody().jsonPath("$.headers.Hello").isEqualTo("World");
//
//        webClient.get().uri("/delay/3")
//                .header("Host", "www.hystrix.com")
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody().consumeWith(
//                        response -> assertThat(response.getResponseBody()).isEqualTo("fallback".getBytes()));
    }
}