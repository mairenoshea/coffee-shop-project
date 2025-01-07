package com.oshea.coffee_shop.models;import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="beverages_temperatures_drinkSizes")
public class BeverageTemperatureDrinkSize {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="beverage_id")
    private Beverage beverage;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="temperatureDrinkSize_id")
    private TemperatureDrinkSize temperatureDrinkSize;
    
    private Float basePrice;
    
    public BeverageTemperatureDrinkSize() {}
    
    public Long getId() {
		return this.id;
	}
    
    public Beverage getBeverage() {
    	return this.beverage;
    }
    
    public TemperatureDrinkSize getTemperatureDrinkSize() {
    	return this.temperatureDrinkSize;
    }
    public Date getCreatedAt() {
		return this.createdAt;
	}
    
    public Float getBasePrice() {
    	return this.basePrice;
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

public void setBeverage(Beverage beverage) {
	this.beverage=beverage;
}

public void setTemperatureDrinkSize(TemperatureDrinkSize tempDS) {
	this.temperatureDrinkSize=tempDS;
}
public void setBasePrice(Float price) {
	this.basePrice=price;
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
