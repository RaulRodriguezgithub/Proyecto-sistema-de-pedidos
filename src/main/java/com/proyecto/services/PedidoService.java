package com.proyecto.services;

import java.util.List;
import java.util.Optional;

import com.proyecto.models.Pedido;

public interface PedidoService {
	List<Pedido> listar();
	List<Pedido> listarPorEstado(String estado);
    Optional<Pedido> buscarPorId(Integer id);
    Pedido guardar(Pedido pedido);
	public Optional<Pedido>consultarunpedido(int id);
	public int consultarultimo();
	Pedido buscarPedidoPorId(Integer id);
	List<Pedido> buscarPorCliente(String keyword);
}
