package co.edu.uptc.virtualcoffee.api_pedidos.dto;
import lombok.Data;

@Data
public class PedidoResponseDTO {
    private String message;
    private double total;
    // (En el futuro, podríamos añadir el ID del pedido guardado)
}