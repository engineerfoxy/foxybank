package com.foxbank.template;

public class Customers {
	public int ID; //6 digits
	public String Name;
	public int Account_number;
	public int Age;
	public String Address;
	public String Phone;

	public Customers() {}

	public Customers(String name) {
		this.Name = name;
	}

	public Customers(String name, int age, String address, String phone) {
		this.Name = name;
		this.Age = age;
		this.Address = address;
		this.Phone = phone;
	}

	public int getID() {return ID;}
	public void setID(int id) {this.ID = id;}

	public String getName() {return Name;}
	public void setName(String name) {this.Name = name;}

	public int getAge() {return Age;}
	public void setAge(int age) {this.Age = age;}

	public String getAddress() {return Address;}
	public void setAddress(String address) {this.Address = address;}

	public String getPhone() {return Phone;}
	public void setPhone(String phone) {this.Phone = phone;}

	@Override
	public String toString() {
		return "Customers {" +
				"ID=" + ID +
				", Name='" + Name + '\'' +
				", Account_number=" + Account_number +
				", Age=" + Age +
				", Address='" + Address + '\'' +
				", Phone='" + Phone + '\'' +
				'}';
	}
}
