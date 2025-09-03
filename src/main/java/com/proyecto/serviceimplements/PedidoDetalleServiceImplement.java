package com.proyecto.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.models.PedidoDetalle;
import com.proyecto.repositories.PedidoDetalleRepository;
import com.proyecto.services.PedidoDetalleService;

@Service
public class PedidoDetalleServiceImplement implements PedidoDetalleService {
	
	@Autowired
	private PedidoDetalleRepository repository;
	
	@Override
	public List<PedidoDetalle> guardarTodos(List<PedidoDetalle> detalles) {
		// TODO Auto-generated method stub
		return repository.saveAll(detalles);
	}
	
	@Override
    public List<PedidoDetalle> listarPorPedidoId(int pedidoId) {
        return repository.findByPedidoId(pedidoId);
    }

	@Override
	public List<PedidoDetalle> listarproductos(int id) {
		// TODO Auto-generated method stub
		return repository.findByPedidoId(id);
	}


}
