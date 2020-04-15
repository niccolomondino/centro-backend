package com.tecnositaf.centrobackend.dto;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.tecnositaf.centrobackend.model.User;

public class DTOUser {

	private Integer idUser;
	private String firstname;
	private String lastname;
	private LocalDate birthDate;
	private String username;
	private Integer age;	//campo in più (richiesto dal front-end) rispetto al model User, calcolabile dal campo 'birthday'


	public DTOUser() {
	}

	public DTOUser(Integer idUser, String firstname, String lastname, LocalDate birthday, String username, Integer age) {
		this.idUser = idUser;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthDate = birthday;
		this.username = username;
		this.age = age;
	}

	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public LocalDate getBirthday() {
		return birthDate;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthDate = birthday;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "DTOUser{" +
				"idUser=" + idUser +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				", birthday=" + birthDate +
				", username='" + username + '\'' +
				", age=" + age +
				'}';
	}


	/*************************************************************************************************/
	/*************************************************************************************************/
	/*************************************************************************************************/

	
	//metodo per il passaggio da DTO a model
	public User toUser() {
		User output = new User(); // 'this' => DTOUser	 'output'	=> User
		BeanUtils.copyProperties(this, output);	//vengono settate in 'output' tutti campi che hanno lo stesso nome tra la classe User e DTOUser
		return output;
	}
}
