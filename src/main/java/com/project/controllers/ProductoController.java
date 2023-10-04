package com.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.exceptions.ProductoException;
import com.project.models.entity.Producto;
import com.project.services.interfaces.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("products")
public class ProductoController {

	@Autowired
	private ProductoService service;
	
	@Operation(summary = "Obtener la lista de todos los productos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista retornada con exito", 
				    content = { @Content(mediaType = "application/json", 
				      schema = @Schema(implementation = Producto.class)) })
	})
	@GetMapping("/list")
	public ResponseEntity<?> listProducts() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@Operation(summary = "Obtener un producto por su id")
	@ApiResponses(value = {
			  @ApiResponse(responseCode = "200", description = "Producto encontrado", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = Producto.class)) }),
			  @ApiResponse(responseCode = "400", description = "Id proporcionado no válido", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Producto no encontrado", 
			    content = @Content) })
	@GetMapping("/view/{id}")
	public ResponseEntity<Producto> viewProduct(@PathVariable Long id){
		return ResponseEntity.ok(service.findById(id).orElseThrow(ProductoException::new));
	}
	
	@Operation(summary = "Crear un nuevo producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Producto creado con exito", 
				    content = { @Content(mediaType = "application/json", 
				      schema = @Schema(implementation = Producto.class)) })
	})
	@PostMapping("/add")
	public ResponseEntity<Producto> addProduct(@RequestBody Producto producto){
		Producto response = service.save(producto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@Operation(summary = "Actualizar un producto por su id")
	@ApiResponses(value = {
			  @ApiResponse(responseCode = "201", description = "Producto actualizado", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = Producto.class)) }),
			  @ApiResponse(responseCode = "400", description = "Id proporcionado no válido", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Producto no encontrado", 
			    content = @Content) })
	@PutMapping("/edit/{id}")
	public ResponseEntity<Producto> editProduct(@RequestBody Producto producto, @PathVariable Long id){
		Producto productoDb = service
				.findById(id)
				.orElseThrow(ProductoException::new);
		
		productoDb.setName(producto.getName());
		productoDb.setDescription(producto.getDescription());
		productoDb.setPrice(producto.getPrice());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(productoDb));
	}
	
	@Operation(summary = "Eliminar un producto por su id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Producto eliminado correctamente", 
					content = @Content),
			@ApiResponse(responseCode = "400", description = "Id proporcionado no válido", 
		    		content = @Content), 
			@ApiResponse(responseCode = "404", description = "Producto no encontrado", 
		    		content = @Content) 
	})
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id){
		Producto producto = service
				.findById(id)
				.orElseThrow(ProductoException::new);
		
		service.delete(producto);
		return ResponseEntity.noContent().build();
	}
}
