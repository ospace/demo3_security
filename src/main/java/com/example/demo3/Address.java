package com.example.demo3;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	public String zipCode;
	public String address;
	
	public String getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String toString() {
		return "zipCode["+zipCode+"] address["+address+"]";
	}
}
