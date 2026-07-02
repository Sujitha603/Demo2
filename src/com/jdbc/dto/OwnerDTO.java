package com.jdbc.dto;

import java.time.LocalDate;

import com.jdbc.enums.Gender;
import com.jdbc.enums.PetType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OwnerDTO {
	private int id;
	private String firstName;
	private String lastName;
	private Gender gender;
	private String city;
	private String state;
	private String mobileNumber;
	private String emailId;
	private int petId;
	private String petName;
	private LocalDate petBirthDate;
	private Gender petGender;
	private PetType petType;


}
