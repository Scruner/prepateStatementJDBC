package com.desantnic.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Calendar;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?user=root&password=" +
            "Ltcfynehf77&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Ltcfynehf77";

    private static final String INSERT_NEW = "INSERT INTO vdv VALUES(?,?,?,?,?,?,?)";
    private static final String GET_ALL = "SELECT * FROM vdv";
    private static final String DELETE = "DELETE FROM vdv WHERE id = ?";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, 2);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(GET_ALL);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String desc = resultSet.getString("description");
                float rating = resultSet.getFloat("rating");
                boolean published = resultSet.getBoolean("published");
                Date date = resultSet.getDate("created");
                byte[] icon = resultSet.getBytes("icon");

                System.out.println("id: " + id + ", title: " + title + ", desc: " + desc + ", rating: " + rating + "" +
                        ", published: " + published + ", date: " + date + ", icon length: " + icon.length);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
        }
//            preparedStatement = connection.prepareStatement(INSERT_NEW);
//
//            //выставляем параметры запроса
//            preparedStatement.setInt(1, 2);
//            preparedStatement.setString(2, "Inserted title");
//            preparedStatement.setString(3, "Inserted desc");
//            preparedStatement.setFloat(4, 0.2f);
//            preparedStatement.setBoolean(5, true);
//            preparedStatement.setDate(6, new Date(Calendar.getInstance().getTimeInMillis()));
//            preparedStatement.setBlob(7, new FileInputStream("VDV.png"));
//
//            //запускаем наш запрос
//            preparedStatement.execute();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
