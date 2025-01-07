package com.oshea.coffee_shop.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="First name is required!")
	@Size(min=2, max=30, message="First name must be between 2 and 30 characters!")
	private String firstName;

	@NotBlank(message="Last name is required!")
	@Size(min=2, max=100, message="Last name must be between 2 and 100 characters!")
	private String lastName;
	
	 @NotBlank(message="Email is required!")
	 @Email(message="Please enter a valid email!")
	 private String email;
	    
	 @NotBlank(message="Password is required!")
	 @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
	 private String password;
	    
	    @Transient
	    @NotBlank(message="Confirm Password is required!")
	    @Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
	    private String confirm;

	
	@Column(updatable=false)
	private Date createdAt; 
	
	private Date updatedAt;
	
	@OneToMany(mappedBy="orderedBy", fetch=FetchType.LAZY)
	private List<Order> orders;

	//private Beverage favBeverage;
	 
	 // empty constructor
	 
	 public User() {
		 
	 }
	 
	 
	 // getters 
	 
	 public Long getId() {
		 return this.id;
	 }
	 
	 public String getFirstName() {
		 return this.firstName;
	 }
	 
	 public String getLastName() {
		 return this.lastName;
	 }
	 
	 public String getEmail() {
		 return this.email;
	 }
	 
	 public String getPassword() {
		 return this.password;
	 }
	 
	 public String getConfirm() {
		 return this.confirm;
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
	 
	
	 // setters
	 
	 public void setFirstName(String firstName) {
		 this.firstName=firstName;
	 }
	 
	 public void setLastName(String lastName) {
		 this.lastName=lastName;
	 }
	 
	 public void setEmail(String email) {
		 this.email=email;
	 }
	 
	 public void setPassword(String password) {
		 this.password=password;
	 }
	 
	 public void setConfirm(String confirm) {
		 this.confirm=confirm;
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
