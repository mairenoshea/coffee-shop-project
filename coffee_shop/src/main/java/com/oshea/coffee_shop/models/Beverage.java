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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="beverages")
public class Beverage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
	
	@Column(updatable=false)
	private Date createdAt;
	
	private Date updatedAt;
	
	private Boolean isTemplate;

//	@ManyToMany(fetch=FetchType.LAZY)
//	@JoinTable(name="beverages_temperatures_drinkSizes", joinColumns=@JoinColumn(name="beverage_id"), inverseJoinColumns=@JoinColumn(name="temperature_drinkSize_id"))
//	private List<TemperatureDrinkSize> possibleTemperaturesDrinkSizes;
	
	//create a join table - beveragestemperaturesdrinksizes, then link to that for prices
	@OneToMany(mappedBy="beverage", fetch=FetchType.LAZY)
	private List<BeverageTemperatureDrinkSize> possibleTemperaturesDrinkSizes;
	
	private Long parentBeverageId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="beverage_temperature_drinkSize_id")
	private BeverageTemperatureDrinkSize temperatureDrinkSize; 
	
	public Beverage() {}
	
	public Beverage(Beverage beverage) {
		this.id=beverage.getId();
		this.name=beverage.getName();
		this.description=beverage.getDescription();
		this.possibleTemperaturesDrinkSizes=beverage.getPossibleTemperaturesDrinkSizes();
		
	}
	
	
	
	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	
	public Boolean getIsTemplate() {
		return this.isTemplate;
	}
	public String getDescription() {
		return this.description;
	}
	
	public List<BeverageTemperatureDrinkSize> getPossibleTemperaturesDrinkSizes() {
		return this.possibleTemperaturesDrinkSizes;
	}
	
	
	public Long getParentBeverageId() {
		return this.parentBeverageId;
	}


	public BeverageTemperatureDrinkSize getTemperatureDrinkSize() {
		return this.temperatureDrinkSize;
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
		
		public void setName(String name) {
			this.name=name;
		}
		
		public void setIsTemplate(Boolean isTemplate) {
			this.isTemplate=isTemplate;
		}
		
		public void setDescription(String description) {
			this.description=description;
		}
		
		public void setPossibleTemperaturesDrinkSizes(List<BeverageTemperatureDrinkSize> possibleTemperaturesDrinkSizes) {
			this.possibleTemperaturesDrinkSizes=possibleTemperaturesDrinkSizes;
		}
		
		
	
		
		public void setParentBeverageId(Long id) {
			this.parentBeverageId=id;
		}
		
	
		
		public void setTemperatureDrinkSize(BeverageTemperatureDrinkSize tempDS) {
			this.temperatureDrinkSize=tempDS;
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
