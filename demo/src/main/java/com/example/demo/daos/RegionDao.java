package com.example.demo.daos;

import java.sql.*;
import java.util.*;

import com.example.demo.models.Region;

public class RegionDao {
    private Connection con;

    public RegionDao(Connection connection){
        this.con = connection;
    }

    public List<Region> getAll(){
        List<Region> regions = new ArrayList<>();
        String query = "Select Id, Name from tb_m_region";
        try{
            ResultSet resultSet = con.prepareStatement(query).executeQuery();
            while (resultSet.next()){
                Region region = new Region();
                region.setId(resultSet.getInt(1));
                region.setName(resultSet.getString(2));
                regions.add(region);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regions;
    }
    
    public Region getById(int id) {
        Region region = new Region();
        String query = "SELECT * FROM tb_m_region WHERE Id = ?";
        try {
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                region.setId(resultSet.getInt(1));
                region.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }
    

    public boolean insertData(Region region){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("Insert INTO tb_m_region(Name) values(?)");
            preparedStatement.setString(1, region.getName());
            preparedStatement.executeUpdate();
            //int temp = preparedStatement.executeUpdate();
            //return temp > 0;
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateData(Region region){
        try {
            String query = "Update tb_m_region SET Id = ?, Name = ? WHERE Id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, region.getId());
            preparedStatement.setString(2, region.getName());
            preparedStatement.setInt(3, region.getId());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public Boolean delete(Integer id){
        try {
            String query = "Delete from tb_m_region where Id = ?";
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
