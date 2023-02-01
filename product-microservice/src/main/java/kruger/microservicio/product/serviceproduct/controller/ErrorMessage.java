package kruger.microservicio.product.serviceproduct.controller;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Builder
public class ErrorMessage {
    private String code ;
    private List<Map<String,String>> messages;
}
