package com.project.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.models.entity.Producto;


public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
