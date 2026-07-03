package com.foxbank.template;

public class Customers {
	public int ID; //6 digits
	public String Name;
	public int Account_number;
	public int Age;
	public boolean Is_over_18;
	public String Address;
	public String Phone;

	public Customers(int id, String name, int age, boolean is_over_18, String address, String phone) {
		this.ID = id;
		this.Name = name;
		this.Age = age;
		this.Is_over_18 = is_over_18;
		this.Address = address;
		this.Phone = phone;
	}

	public int getID() {return ID;}
	public void setID(int id) {this.ID = id;}

	public String getName() {return Name;}
	public void setName(String name) {this.Name = name;}

	public int getAge() {return Age;}
	public void setAge(int age) {this.Age = age;}

	public boolean get_is_over_18() {return Is_over_18;}
	public void set_Is_over_18(boolean is_over_18) {this.Is_over_18 = is_over_18;}

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
				", Is_over_18=" + Is_over_18 +
				", Address='" + Address + '\'' +
				", Phone='" + Phone + '\'' +
				'}';
	}
}
