package com.order.ordermicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.order.ordermicroservice.seeders.CouponSeeder;

/**
 * This microservice was created by Kevin and David
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderMicroserviceApplication  implements CommandLineRunner{

	@Autowired
	CouponSeeder couponSeeder;
	public static void main(String[] args) {
		SpringApplication.run(OrderMicroserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Creo 50 cupones de descuento aleatorios
		couponSeeder.fillCouponsDB();
	}
}
