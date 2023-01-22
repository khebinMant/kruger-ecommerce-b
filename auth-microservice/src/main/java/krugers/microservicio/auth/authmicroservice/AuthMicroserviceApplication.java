package krugers.microservicio.auth.authmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import krugers.microservicio.auth.authmicroservice.seeders.admin.AdminSeeder;
import krugers.microservicio.auth.authmicroservice.seeders.customer.CustomerSeeder;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AuthMicroserviceApplication implements CommandLineRunner{

	 @Autowired
	 AdminSeeder adminSeeder;

	 @Autowired
	 CustomerSeeder customerSeeder;

	public static void main(String[] args) {
		SpringApplication.run(AuthMicroserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Crear 3 users Kevin, Kenan, José
		customerSeeder.fillCustomersToDB();
		//Crear 3 admins Kevin, Kenan, José
		adminSeeder.fillAdminsToDB();
	}

}
