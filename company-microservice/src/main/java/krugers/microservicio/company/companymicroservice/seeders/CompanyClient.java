package krugers.microservicio.company.companymicroservice.seeders;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import krugers.microservicio.company.companymicroservice.entity.Company;
import krugers.microservicio.company.companymicroservice.service.CompanyServiceImpl;

@Component
public class CompanyClient {
    
    @Autowired
    CompanyServiceImpl companyServiceImpl;

    public void fillCompanyToDB(){
        companyServiceImpl.createCompany(new Company(1L,"Kruger Cell", "Tienda virtual de teléfonos", "https://krugercorp.com/wp-content/uploads/revslider/xtra_slider_business/Logo-Kruger_banner1.gif","CREATED", new Date(), new Date()));
    }
}
