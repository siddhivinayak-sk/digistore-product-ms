package com.sk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sk.model.ProductDTO;
import com.sk.model.ResponseDTO;
import com.sk.service.ProductService;

/**
 * 
 * @author kumar-sand
 *
 */
@RestController
@RequestMapping("/v1/products")
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseDTO<ProductDTO> create(ProductDTO product) {
		return productService.create(product);
	}
	
	@PutMapping
	public ResponseDTO<ProductDTO> update(ProductDTO product) {
		return productService.update(product);
	}

	@DeleteMapping
	public ResponseDTO<ProductDTO> delete(@RequestParam Long productId) {
		return productService.delete(productId);
	}
	
	@GetMapping("/byname")
	public ResponseDTO<ProductDTO> getByName(@RequestParam String productName) {
		return productService.getByName(productName);
	}

	@GetMapping("/byid")
	public ResponseDTO<ProductDTO> getById(@RequestParam Long productId) {
		return productService.getById(productId);
	}
	
	@GetMapping
	public ResponseDTO<List<ProductDTO>> getPorducts() {
		return productService.getPorducts();
	}
}
