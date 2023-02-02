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
        companyServiceImpl.createCompany(new Company(1L,"Kruger Cell", "Nuestra empresa se dedica a ofrecer una amplia variedad de teléfonos móviles de las marcas más reconocidas del mercado. Con un enfoque en la calidad y el servicio al cliente, nos esforzamos por brindar una experiencia de compra satisfactoria y ofrecer soluciones tecnológicas asequibles para todos. ¡Visítanos hoy mismo para encontrar el teléfono perfecto para ti!","Nuestro objetivo es ser líderes en la industria de la venta de teléfonos móviles, ofreciendo una amplia selección de productos de alta calidad y un servicio excepcional a nuestros clientes. Nos esforzamos por mantenernos a la vanguardia de las tendencias tecnológicas y proporcionar soluciones innovadoras a precios competitivos, mientras construimos relaciones duraderas con nuestros clientes y fortalecemos nuestra presencia en el mercado.","Nuestra empresa comenzó como un pequeño negocio familiar en la ciudad hace 10 años. Con el tiempo, crecimos y nos expandimos, ofreciendo una amplia variedad de teléfonos móviles de las marcas más reconocidas del mercado. A medida que nuestra reputación creció, nos convertimos en un destino confiable para los amantes de la tecnología en busca de soluciones innovadoras y asequibles. Hoy en día, nos enorgullece ser uno de los proveedores líderes en la industria y seguir comprometidos con brindar un servicio excepcional a nuestros clientes.","0986261197", "https://krugercorp.com/wp-content/uploads/revslider/xtra_slider_business/Logo-Kruger_banner1.gif","CREATED", new Date(), new Date()));
    }
}
