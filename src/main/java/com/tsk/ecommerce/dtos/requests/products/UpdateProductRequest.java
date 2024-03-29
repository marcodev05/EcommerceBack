package com.tsk.ecommerce.dtos.requests.products;

import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@AllArgsConstructor
public class UpdateProductRequest {

	@NotBlank(message = "Product name is required")
	private String nameProduct;
	
	@NotBlank(message = "Description is required")
	private String description;

	private Collection<Picture> pictures;

	private Collection<Long> pictureToDeletes;

	@NotNull(message = "category is required")
	private Long categoryId;

	public void commitTo(Product product){
		product.setName(nameProduct);
		product.setDescription(description);
	}

}
