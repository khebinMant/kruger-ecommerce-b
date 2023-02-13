package krugers.microservicio.gateway.gatewaymicroservice.config;

import org.springframework.cloud.gateway.config.HttpClientCustomizer;

import io.netty.resolver.DefaultAddressResolverGroup;
import reactor.netty.http.client.HttpClient;

public class DnsResolutionFixer implements  HttpClientCustomizer{

    @Override
    public HttpClient customize(HttpClient httpClient) {
        return httpClient.resolver(DefaultAddressResolverGroup.INSTANCE);

    }
    
}
