package krugers.microservicio.company.companymicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import krugers.microservicio.company.companymicroservice.seeders.CompanyClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CompanyMicroserviceApplication implements CommandLineRunner{

	@Autowired
	CompanyClient companyClient;

	public static void main(String[] args) {
		SpringApplication.run(CompanyMicroserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Creo la única compañia KrugerCell
		companyClient.fillCompanyToDB();
	}

}
