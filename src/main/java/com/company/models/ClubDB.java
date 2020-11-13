package com.company.models;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClubDB {
    Connection connection = null;
    private static ClubDB instance = null;

    public static ClubDB getInstance(){
        if(instance == null){
            instance = new ClubDB();
        }
        return instance;
    }
    private ClubDB(){
        Context initialContext;
        try
        {
            initialContext = new InitialContext();
            Context envCtx = (Context)initialContext.lookup("java:comp/env");
            DataSource ds = (DataSource)envCtx.lookup("jdbc/final");
            connection = ds.getConnection();
        }
        catch (NamingException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Club> getAllClubs()
    {
        try
        {
            String sql = "SELECT * FROM clubs";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            ArrayList<Club> clubArrayList = new ArrayList();
            while (resultSet.next()) {
                Club clubs = new Club(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("img"),
                        resultSet.getString("description")
                );
                clubArrayList.add(clubs);
            }
            return clubArrayList;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

    public boolean addClubs(Club club) {
        try {
            String sql = "INSERT INTO clubs(name, img, description) " +
                    "VALUES(?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, club.getName());
            stmt.setString(2, club.getImg());
            stmt.setString(3, club.getDescription());
            stmt.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    public void update(String name, String img, String description)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("update clubs set img=?, description=? where name=?");
            preparedStatement.setString(1, img);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, name);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public void remove(String name){
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from clubs where name = ?");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
