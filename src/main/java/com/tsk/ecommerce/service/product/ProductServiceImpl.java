package com.tsk.ecommerce.service.product;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.print.attribute.standard.DateTimeAtCreation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.dto.request.ProductRequest;
import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.OrderLine;
import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.exception.ResourceNotFoundException;
import com.tsk.ecommerce.repository.ProductRepository;
import com.tsk.ecommerce.service.category.CategoryService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryService categoryService;
	
	@Override
	public Product create(ProductRequest product) {
		Product p = new Product();
		p.setNameProduct(product.getNameProduct());
		p.setDescription(product.getDescription());
		p.setPrice(product.getPrice());
		p.setStock(product.getStock());
		p.setAvailable(true);
		p.setSelected(false);
		p.setCreatedAt(Date.from(Instant.now()));
		
		Category c = categoryService.getCategoryById(product.getIdCategory());
		p.setCategory(c);
		
//		Random r = new Random();
//		Integer rate = r.nextInt(5) + 1; //[1 - 5[
//		p.setRating(rate);
		
		List<Picture> pictures = new ArrayList<Picture>();
		p.setPictures(pictures);

		return productRepository.save(p);
	}

	
	
	
	@Override
	public Product update(Long id, Product product) {
		Product p = this.getProductById(id);
		p.setNameProduct(product.getNameProduct());
		p.setDescription(product.getDescription());
		p.setPrice(product.getPrice());
		p.setStock(product.getStock());
		p.setCategory(product.getCategory());
		
		List<Picture> pictures = new ArrayList<Picture>();
		p.setPictures(pictures);
		
		return productRepository.save(p);
	}

	
	@Override
	public List<Product> findAllProduct() {
		return productRepository.findAll();
	}

	
	
	
	@Override
	public Product getProductById(Long id) {
		Product p = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Le produit est introuvable"));
		return p;
	}
	
	
	
	
	@Override
	public void deleteProduct(Long id) {
		Product p = this.getProductById(id);
		productRepository.delete(p);

	}


	
	
	@Override
	public Product addToStock(Long id, Integer qty) {
		Product p = this.getProductById(id);
		p.setStock(p.getStock() + qty);
		if(p.getStock() > 0){
			p.setAvailable(true);
		}
		return productRepository.save(p);
	}


	
	
	@Override
	public List<Picture> getAllPictureByProduct(Long idProduct) {
		return productRepository.findAllPicturesByProduct(idProduct);
	}


	
	@Override
	public List<Product> findProductByName(String name) {
		return productRepository.findByNameProductContains(name)
				.orElseThrow(() -> new ResourceNotFoundException("Le resultat de la recherche est vide"));
	}


	
	@Override
	public void reduceQtyByOrderLine(OrderLine orderLine) {
		
		Product p = this.getProductById(orderLine.getProduct().getIdProduct());
		Integer restStock = p.getStock() - orderLine.getQuantity();
		p.setStock(restStock);
		
		if (restStock == 0) {
			p.setAvailable(false);
		}
		productRepository.save(p);	
	}



}
