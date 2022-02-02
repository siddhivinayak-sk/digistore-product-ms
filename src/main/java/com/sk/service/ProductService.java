package com.sk.service;

import java.util.List;

import com.sk.model.ProductDTO;
import com.sk.model.ResponseDTO;

/**
 * 
 * @author kumar-sand
 *
 */
public interface ProductService {

	ResponseDTO<ProductDTO> create(ProductDTO product);
	
	ResponseDTO<ProductDTO> update(ProductDTO product);
	
	ResponseDTO<ProductDTO> delete(Long productId);
	
	ResponseDTO<ProductDTO> getByName(String productName);
	
	ResponseDTO<ProductDTO> getById(Long productId);
	
	ResponseDTO<List<ProductDTO>> getPorducts();
}
