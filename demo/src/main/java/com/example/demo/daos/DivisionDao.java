package com.example.demo.daos;

import java.sql.*;
import java.util.*;
import com.example.demo.models.Division;
import com.example.demo.models.Region;

public class DivisionDao {
    private Connection con;

    public DivisionDao(Connection connection){
        this.con = connection;
    }

    public List<Division> getAll(){
        List<Division> divisions = new ArrayList<>();
        String query = "Select d.Id, d.Name, r.Name from tb_m_division d JOIN tb_m_region r ON d.regionId = r.Id";
        try{
            ResultSet resultSet = con.prepareStatement(query).executeQuery();
            while (resultSet.next()){
                Division division = new Division();
                Region regions = new Region();
                division.setId(resultSet.getInt(1));
                division.setName(resultSet.getString(2));
                division.setRegion(regions);
                regions.setName(resultSet.getString(3));
                divisions.add(division);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return divisions;
    }

    public Division getNameRegion() {
        Division division = new Division();
        String query = "SELECT DISTINCT r.Id, r.Name FROM tb_m_division d RIGHT JOIN tb_m_region r on d.regionId = r.Id;";
        try {
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Region region = new Region();
                division.setRegion(region);
                region.setId(resultSet.getInt(1));
                division.setRegion(region);
                region.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return division;
    }

    public Division getById(Integer id) {
        Division division = new Division();
        String query = "SELECT d.Id, d.Name, r.Name, r.Id FROM tb_m_division d Join tb_m_region r On d.regionId = r.Id WHERE d.Id = ?";
        try {
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Region region = new Region();
                division.setId(resultSet.getInt(1));
                division.setName(resultSet.getString(2));
                division.setRegion(region);
                region.setName(resultSet.getString(3));
                division.setRegion(region);
                region.setId(resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return division;
    }

    public boolean insertData(Division division){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("Insert INTO tb_m_division(Name, RegionId) values(?,?)");
            preparedStatement.setString(1, division.getName());
            preparedStatement.setInt(2, division.getRegion().getId());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateData(Division division){
        try {
            String query = "Update tb_m_division SET Id = ?, Name = ?, regionId = ? WHERE Id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, division.getId());
            preparedStatement.setString(2, division.getName());
            preparedStatement.setInt(3, division.getRegion().getId());
            preparedStatement.setInt(4, division.getId());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteData(Integer id){
        try {
            String query = "Delete from tb_m_division where Id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


}

