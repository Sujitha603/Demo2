package com.jdbc.repository.impl;

import com.jdbc.dto.OwnerDTO;
import com.jdbc.exception.InternalServiceException;
import com.jdbc.repository.OwnerRepository;
import com.jdbc.util.DBUtil;
import com.jdbc.util.MapperUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OwnerRepositoryImpl implements OwnerRepository {

    @Override
    public void saveOwner(OwnerDTO owner) {
        String sql = "insert into owner_table(id,first_name,last_name,gender,city,state,mobile_number,email_id,pet_id,pet_name,pet_date_of_birth,pet_gender,pet_type) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, owner.getId());
            ps.setString(2, owner.getFirstName());
            ps.setString(3, owner.getLastName());
            ps.setString(4, owner.getGender().toString());
            ps.setString(5, owner.getCity());
            ps.setString(6, owner.getState());
            ps.setString(7, owner.getMobileNumber());
            ps.setString(8, owner.getEmailId());
            ps.setInt(9, owner.getPetId());
            ps.setString(10, owner.getPetName());
            ps.setDate(11, Date.valueOf(owner.getPetBirthDate()));
            ps.setString(12, owner.getPetGender().toString());
            ps.setString(13, owner.getPetType().toString());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new InternalServiceException(e.getMessage());
        }
    }

    @Override
    public OwnerDTO findOwner(int ownerId) {

        String sql = "select * from owner_table where id = ?";
        OwnerDTO ownerDto = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ownerId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ownerDto = MapperUtil.convertOwnerResultSetToDto(rs);
                }
            }

        } catch (SQLException e) {
            throw new InternalServiceException(e.getMessage());
        }

        return ownerDto;
    }

    @Override
    public void updatePetDetails(int ownerId, String petName) {

        String sql = "update owner_table set pet_name = ? where id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, petName);
            ps.setInt(2, ownerId);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new InternalServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteOwner(int ownerId) {

        String sql = "delete from owner_table where id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ownerId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new InternalServiceException(e.getMessage());
        }
    }

    @Override
    public List<OwnerDTO> findAllOwners() {

        String sql = "select * from owner_table";
        List<OwnerDTO> ownerList = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ownerList.add(MapperUtil.convertOwnerResultSetToDto(rs));
            }

        } catch (SQLException e) {
            throw new InternalServiceException(e.getMessage());
        }

        return ownerList;
    }

    @Override
    public List<OwnerDTO> findOwner(String ownerEmailId, LocalDate petBirthDate) {

        String sql = "select * from owner_table where email_id = ? and pet_date_of_birth = ?";
        List<OwnerDTO> ownerList = new ArrayList<>();

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ownerEmailId);
            ps.setDate(2, Date.valueOf(petBirthDate));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ownerList.add(MapperUtil.convertOwnerResultSetToDto(rs));
            }

        } catch (SQLException e) {
            throw new InternalServiceException(e.getMessage());
        }

        return ownerList;
    }
}