package com.tecnositaf.centrobackend.model;

public class Customer {

	private int idCustomer;

	private String name;

	private int age;

	private boolean active;

	public Customer() {
	}


	public Customer(String name, int age) {
		this.name = name;
		this.age = age;
		this.active = false;
	}

	
	public int setId(int idCustomer) {
		return this.idCustomer = idCustomer;
	}
	public int getId() {
		return idCustomer;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Customer [id=" + ", name=" + name + ", age=" + age + ", active=" + active + "]";
	}
}
