package com.project.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.entity.Producto;
import com.project.models.repository.ProductoRepository;
import com.project.services.interfaces.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	private ProductoRepository reporsitory;
	
	@Override
	public Iterable<Producto> findAll() {
		return this.reporsitory.findAll();
	}

	@Override
	public Optional<Producto> findById(Long id) {
		return this.reporsitory.findById(id);
	}

	@Override
	public Producto save(Producto producto) {
		return this.reporsitory.save(producto);
	}

	@Override
	public void delete(Producto producto) {
		this.reporsitory.delete(producto);
	}
}
