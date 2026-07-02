package com.jdbc.repository;


import com.jdbc.dto.OwnerDTO;

import java.time.LocalDate;
import java.util.List;

public interface OwnerRepository {

    void saveOwner(OwnerDTO owner);

    OwnerDTO findOwner(int ownerId);

    void updatePetDetails(int ownerId, String petName);

    void deleteOwner(int ownerId);

    List<OwnerDTO> findAllOwners();

    List<OwnerDTO> findOwner(String ownerEmailId, LocalDate petBirthDate);
}
