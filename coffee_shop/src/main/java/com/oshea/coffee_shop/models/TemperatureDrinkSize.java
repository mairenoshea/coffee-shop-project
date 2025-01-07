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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="temperatures_drinkSizes")
public class TemperatureDrinkSize {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="drinkSize_id")
    private DrinkSize drinkSize;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="temperature_id")
    private Temperature temperature;
    
    @OneToMany(mappedBy="temperatureDrinkSize")
    private List<BeverageTemperatureDrinkSize> possibleBeverages;
    
    
    
    
    public TemperatureDrinkSize() {}
    
    public Long getId() {
		return this.id;
	}
    
    public DrinkSize getDrinkSize() {
    	return this.drinkSize;
    }
    
    public Temperature getTemperature() {
    	return this.temperature;
    }
    
    public List<BeverageTemperatureDrinkSize> getPossibleBeverages() {
    	return this.possibleBeverages;
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
	
	public void setDrinkSize(DrinkSize drinkSize) {
		this.drinkSize=drinkSize;
	}
	
	public void setTemperature(Temperature temperature) {
		this.temperature=temperature;
	}
	
	public void setPossibleBeverages(List<BeverageTemperatureDrinkSize> beverages) {
		this.possibleBeverages=beverages;
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
    

