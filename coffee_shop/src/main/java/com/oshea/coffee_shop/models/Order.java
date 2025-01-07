package com.oshea.coffee_shop.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	//	one user can have many orders
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="orderedBy_id")
	private User orderedBy;
	
	@Temporal(TemporalType.DATE)
	private Date dateOrdered;
	
	//one order contains many beverages; one beverage can be part of many orders
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "orders_beverages",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "beverage_id")
    )
    private List<Beverage> beverages;

	
	@Column(updatable=false)
	private Date createdAt; 
	
	private Date updatedAt;
	
	public Order() {}
	
	public Long getId() {
		return this.id;
	}
	public User getOrderedBy() {
		return this.orderedBy;
	}
	
	public Date getDateOrdered() {
		return this.dateOrdered;
	}
	
	public List<Beverage> getBeverages() {
		return this.beverages;
	}
	
	 public Date getCreatedAt() {
			return this.createdAt;
		}
		
		public Date getUpdatedAt() {
			if(!this.updatedAt.equals(null)) {
				return this.updatedAt;
			}
			else {
				return this.createdAt;
			}
		}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setOrderedBy(User orderedBy) {
		this.orderedBy=orderedBy;
	}
	
	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered=dateOrdered;
	}
	
	public void setBeverages(List<Beverage> beverages) {
		this.beverages=beverages;
	}
	
	 @PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
		
		@PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	    }
}

