package com.order.ordermicroservice.Controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.order.ordermicroservice.entity.Order;
import com.order.ordermicroservice.entity.OrderItem;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
public class OrderControllerTest {

    // private final HttpHeaders headers = new HttpHeaders();
    // private final TestRestTemplate restTemplate = new TestRestTemplate();

    // @LocalServerPort
    // private int port = 9091;

    // private String createURLWithPort(String uri) {
    //     return "http://localhost:" + port + uri;
    // }

    // @Test
    // public void test_ListAllOrders() throws Exception {

    //     HttpEntity<String> entity = new HttpEntity<>(null, headers);
    //     ResponseEntity<String> response = restTemplate.exchange(
    //             createURLWithPort("/api/orders"),
    //             HttpMethod.GET, entity, String.class);
    //     if (response.getStatusCode() != HttpStatus.OK) {
    //         assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    //     } else {
    //         assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    //     }
    // }

    // @Test
    // public void test_FindById() throws Exception {

    //     HttpEntity<String> entity = new HttpEntity<>(null, headers);
    //     ResponseEntity<String> response = restTemplate.exchange(
    //             createURLWithPort("/api/orders/1"),
    //             HttpMethod.GET, entity, String.class);
    //     if (response.getStatusCode() != HttpStatus.OK) {
    //         assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    //     } else {
    //         assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    //     }
    // }

    // @Test
    // public void test_createOrder() throws Exception {
    //     OrderItem newList = new OrderItem(200L, 5.30, 5.30, 300L, 5.30, null, new Date());
    //     List<OrderItem> extraList = new ArrayList<>(Arrays.asList(newList));
    //     Order customOrder = new Order(100L, 8.50,5.2, new Date(), "SUCCES", 1L,  null, new Date(), extraList);

    //     HttpEntity<Order> entity = new HttpEntity<>(customOrder, headers);
    //     ResponseEntity<String> response = restTemplate.exchange(
    //             createURLWithPort("/api/orders/user/1"),
    //             HttpMethod.POST, entity, String.class);
    //     if (response.getStatusCode() != HttpStatus.CREATED) {
    //         assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    //     } else {
    //         assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    //     }
    // }

    // @Test
    // public void test_deleteOrder() throws Exception {

    //     ResponseEntity<String> responseDelete = restTemplate.exchange(
    //             createURLWithPort(
    //                     "/api/orders/1"),
    //             HttpMethod.DELETE, null, String.class);

    //     if (responseDelete.getStatusCode() != HttpStatus.OK) {
    //         assertThat(responseDelete.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    //     } else {
    //         assertThat(responseDelete.getStatusCode()).isEqualTo(HttpStatus.OK);
    //     }
    // }
}