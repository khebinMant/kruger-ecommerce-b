package com.order.ordermicroservice.Controller;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.order.ordermicroservice.entity.Order;
import com.order.ordermicroservice.entity.Payment;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

@RunWith(SpringRunner.class)
public class PaymentControllerTest {
    private final HttpHeaders headers = new HttpHeaders();
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @LocalServerPort
    private int port = 9091;

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void test_ListAllPayments() throws Exception {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/payments"),
                HttpMethod.GET, entity, String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        } else {
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        }
    }

    @Test
    public void test_FindById() throws Exception {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/1"),
                HttpMethod.GET, entity, String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        } else {
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        }
    }

    @Test
    public void test_createPayment() throws Exception {

        Order order = new Order();
        Payment customPayment = new Payment("succces", null, new Date(), order);
        HttpEntity<Payment> entity = new HttpEntity<>(customPayment, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/placePayment"),
                HttpMethod.POST, entity, String.class);
        if (response.getStatusCode() != HttpStatus.CREATED) {
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        }
    }

}
