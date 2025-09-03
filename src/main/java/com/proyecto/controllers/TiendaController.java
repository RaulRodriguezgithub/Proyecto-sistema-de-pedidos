package com.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.proyecto.models.CarritoItem;
import com.proyecto.models.Cliente;
import com.proyecto.models.Pedido;
import com.proyecto.models.PedidoDetalle;
import com.proyecto.models.Producto;
import com.proyecto.services.ClienteService;
import com.proyecto.services.PedidoDetalleService;
import com.proyecto.services.PedidoService;
import com.proyecto.services.ProductoService;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
@Controller
@RequestMapping("/admin/tienda")
@SessionAttributes("carrito")
public class TiendaController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private PedidoDetalleService pedidoDetalleService;

    @ModelAttribute("carrito")
    public List<CarritoItem> inicializarCarrito() {
        return new ArrayList<>();
    }

    @ModelAttribute("cantidadCarrito")
    public int cantidadCarrito(@ModelAttribute("carrito") List<CarritoItem> carrito) {
        return carrito.stream().mapToInt(CarritoItem::getCantidad).sum();
    }

    @GetMapping("/productos")
    public String verProductos(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        List<Producto> productos = (keyword != null && !keyword.isEmpty())
                ? productoService.buscar(keyword)
                : productoService.listarDisponibles();

        model.addAttribute("productos", productos);
        model.addAttribute("keyword", keyword);

        
        return "admin/tienda/productos";
    }

    @PostMapping("/agregar")
    public String agregarAlCarrito(@RequestParam("productoId") Integer id,
                                   @ModelAttribute("carrito") List<CarritoItem> carrito) {
        productoService.buscarPorId(id)
                .ifPresent(prod -> carrito.add(new CarritoItem(prod, 1)));
        return "redirect:/admin/tienda/productos";
    }

    @GetMapping("/carrito")
    public String verCarrito(@ModelAttribute("carrito") List<CarritoItem> carrito, Model model) {
        double total = carrito.stream().mapToDouble(CarritoItem::getSubtotal).sum();
        int cantidadTotal = carrito.stream().mapToInt(CarritoItem::getCantidad).sum();

        model.addAttribute("total", total);
        model.addAttribute("cantidadCarrito", cantidadTotal);
        return "admin/tienda/carrito";
    }

    @GetMapping("/compra")
    public String checkout(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "admin/tienda/compra";
    }

    @PostMapping("/confirmar")
    public String confirmarPedido(@ModelAttribute Cliente cliente,
                                  @ModelAttribute("carrito") List<CarritoItem> carrito) {

        Cliente clienteGuardado = clienteService.guardar(cliente);

        Pedido pedido = new Pedido();
        pedido.setCliente(clienteGuardado);
        pedido.setTotal(carrito.stream().mapToDouble(CarritoItem::getSubtotal).sum());
        Pedido pedidoGuardado = pedidoService.guardar(pedido);
        List<PedidoDetalle> detalles = new ArrayList<>();
        for (CarritoItem ci : carrito) {
			PedidoDetalle detalle = new PedidoDetalle();
			detalle.setPedido(pedidoGuardado);
			detalle.setProductos(ci.getProducto());
			detalle.setCantidad(ci.getCantidad());
			detalle.setSubtotal(ci.getSubtotal());
			detalles.add(detalle);

            Producto p = ci.getProducto();
            p.setStock(p.getStock() - ci.getCantidad());
            productoService.guardar(p);
        }
        pedidoDetalleService.guardarTodos(detalles);

        carrito.clear();

        return "redirect:/admin/pedidos/listar"; // Ojo: ahora va al controller de pedidos
    }
    
    @GetMapping("/remover/{id}")
    public String removerDelCarrito(@PathVariable Integer id,
                                    @ModelAttribute("carrito") List<CarritoItem> carrito) {
        carrito.removeIf(item -> item.getProducto().getId().equals(id));
        return "redirect:/admin/tienda/carrito";
    }
}