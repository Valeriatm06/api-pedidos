package co.edu.uptc.virtualcoffee.api_pedidos.dto;

import lombok.Data;

/**
 * DTO (POJO) que representa la respuesta de la API de Bebidas (Python).
 * Se usa para deserializar el JSON de la respuesta.
 */
@Data
public class BebidaDTO {
    private Long id;
    private String name;
    private String size;
    private double price;
}