package com.project.services.interfaces;

import java.util.Optional;

import com.project.models.entity.Producto;

public interface ProductoService {
	
	public Iterable<Producto> findAll();
	
	public Optional<Producto> findById(Long id);
	
	public Producto save(Producto producto);
	
	public void delete(Producto producto);
}
