package com.tsk.ecommerce.dto.request;

import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.tsk.ecommerce.entities.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequest {

	@NotBlank(message = "Product name is required")
	private String nameProduct;
	
	@NotBlank(message = "Description is required")
	private String description;

	@NotNull
	private Double price;

	private Integer stock;
	
	private Collection<Picture> pictures;
	
	private Integer idCategory;
}
