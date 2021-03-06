package com.tsk.ecommerce.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Orders implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrder;
	private Double total;
	private Date createdAt;
	private Double costDelivery;
	private Boolean payed;
	private Boolean delivered;
	
	@ManyToOne
	@JoinColumn(name = "customer")
	private Customer customer;
	
	@OneToMany(mappedBy = "order")
	private Collection<OrderLine> orderLines;
	
	
	public Orders() {
		super();
	}

	public Orders(Customer customer, Collection<OrderLine> orderLines) {
		super();
		this.customer = customer;
		this.orderLines = orderLines;
	}


	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}


	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Boolean getPayed() {
		return payed;
	}


	public void setPayed(Boolean payed) {
		this.payed = payed;
	}


	public Boolean getDelivered() {
		return delivered;
	}


	public void setDelivered(Boolean delivered) {
		this.delivered = delivered;
	}


	public Double getCostDelivery() {
		return costDelivery;
	}


	public void setCostDelivery(Double costDelivery) {
		this.costDelivery = costDelivery;
	}
	

	public Collection<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(Collection<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	@Override
	public String toString() {
		return "Orders [idOrder=" + idOrder + ", total=" + total + ", createdAt=" + createdAt + ", costDelivery="
				+ costDelivery + ", payed=" + payed + ", delivered=" + delivered + ", customer=" + customer + "]";
	}



	
	
	
	

	

}
