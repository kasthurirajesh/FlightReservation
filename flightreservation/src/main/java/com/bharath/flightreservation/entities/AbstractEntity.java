package com.bharath.flightreservation.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/*
 *mappedsuper class is used for ::
 *		this is not mapped to  a database   instead it acts as parent class for other entities
 *
 *in our application :: 
 *    every entity has id as a command field 
 *    so we are writing all the common variables in one class
 *
 */


@MappedSuperclass
public class AbstractEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
