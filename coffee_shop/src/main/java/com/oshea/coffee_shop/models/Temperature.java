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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="temperatures")
public class Temperature {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Name must not be blank.")
	@Size(min=2, max=15, message="Name must be between 2 and 15 characters.")
	private String name;
	
	private String description;
	
	@OneToMany(mappedBy="temperature", fetch=FetchType.LAZY)
    private List<TemperatureDrinkSize> possibleDrinkSizes;
	

	

	@Column(updatable=false)
	private Date createdAt; 
	
	private Date updatedAt;
	
	public Temperature() {
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public List<TemperatureDrinkSize> getPossibleDrinkSizes() {
		return this.possibleDrinkSizes;
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
	
	public void setDescription(String description) {
		this.description=description;
	}
	
	public void setPossibleDrinkSizes(List<TemperatureDrinkSize> possibleDrinkSizes) {
		this.possibleDrinkSizes=possibleDrinkSizes;
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
