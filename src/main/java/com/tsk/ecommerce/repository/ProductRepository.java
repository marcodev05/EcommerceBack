package com.tsk.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("select p.pictures from Product p where p.idProduct = ?1 ")
	public List<Picture> findAllPicturesByProduct(Long idProduct);
	
	public Optional<List<Product>> findByNameProductContains(String nameProduct);
	
}
