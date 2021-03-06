package com.tecnositaf.centrobackend.model;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tecnositaf.centrobackend.dto.DTOUser;
import com.tecnositaf.centrobackend.utilities.DateUtility;

public class User {

	private Integer idUser;
	

	private String firstname;
	private String lastname;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	LocalDate birthDate;
	private String username;
		
	private String password;

	
	public User() {
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


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}



	public LocalDate getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
				"idUser=" + idUser +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				", birthday=" + birthDate +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	/*************************************************************************************************/
	/*************************************************************************************************/
	/*************************************************************************************************/

	
	//nuovo metodo che ho aggiunto al model per il passaggo al DTO corrispondente
	public DTOUser toDTOUser() {
		DTOUser output = new DTOUser();	// 'this' => User	 'output'	=> DTOUser
		BeanUtils.copyProperties(this, output);	//vengono settate in 'output' tutti campi che hanno lo stesso nome tra la classe User e DTOUser

		/*** 'age' of DTO class is a value calculated from 'birthday' ***/
		Integer age = DateUtility.calculateAgeOf( this.getBirthDate() );
		output.setAge(age);

		return output;
	}


	
}
