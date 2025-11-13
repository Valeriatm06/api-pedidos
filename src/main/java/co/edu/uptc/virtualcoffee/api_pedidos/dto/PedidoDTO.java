package co.edu.uptc.virtualcoffee.api_pedidos.dto;

import lombok.Data;
import java.util.List;

/**
 * DTO (Data Transfer Object) para recibir la solicitud de un nuevo pedido.
 * Usamos una clase POJO estándar con Lombok para la brevedad.
 */
@Data
public class PedidoDTO {
    private String customerName;
    private List<String> items;
}