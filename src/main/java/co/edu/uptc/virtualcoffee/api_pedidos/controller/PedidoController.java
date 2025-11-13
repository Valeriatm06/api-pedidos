package co.edu.uptc.virtualcoffee.api_pedidos.controller;

import co.edu.uptc.virtualcoffee.api_pedidos.dto.PedidoResponseDTO;
import co.edu.uptc.virtualcoffee.api_pedidos.dto.PedidoDTO;
import co.edu.uptc.virtualcoffee.api_pedidos.service.PedidoService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "http://localhost:5173")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // 2. Ahora devuelve el DTO de respuesta
    public PedidoResponseDTO createPedido(@RequestBody PedidoDTO pedidoDTO) {

        // 3. Llama al servicio y captura el total
        double total = pedidoService.savePedido(pedidoDTO);

        // 4. Crea y devuelve la respuesta
        PedidoResponseDTO response = new PedidoResponseDTO();
        response.setMessage("Pedido creado exitosamente");
        response.setTotal(total);
        return response;
    }

    @GetMapping("/{id}")
    public PedidoDTO getPedidoById(@PathVariable Long id) {
        return pedidoService.getPedidoById(id);
    }

    @GetMapping
    public List<PedidoDTO> getAllPedidos() {
        return pedidoService.getAllPedidos();
        /*List<PedidoDTO> pedidos = new ArrayList<>();
        PedidoDTO ejemplo = new PedidoDTO();
        ejemplo.setCustomerName("Valeria"); 
        ejemplo.setItems(List.of("Capuccino"));
        pedidos.add(ejemplo);
        return pedidos;*/
      
    }

}