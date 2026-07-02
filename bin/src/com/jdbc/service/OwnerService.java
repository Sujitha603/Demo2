package com.jdbc.service;

import com.jdbc.dto.OwnerDTO;
import com.jdbc.exception.DuplicateOwnerException;
import com.jdbc.exception.OwnerNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface OwnerService {

	
	void saveOwner(OwnerDTO ownerDTO) throws DuplicateOwnerException;

	OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException;

	void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException;

	void deleteOwner(int ownerId) throws OwnerNotFoundException;

	List<OwnerDTO> findAllOwners();
	
	List<OwnerDTO> findOwner(String ownerEmailId, LocalDate petBirthDate);
}
