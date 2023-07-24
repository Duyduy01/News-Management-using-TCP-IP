/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;

/**
 *
 * @author ADMIN
 */


public class CategoryDAO {
    private Connection connection;
    private static CategoryDAO instance;

    public static CategoryDAO getInstance(Connection connection) throws IOException {
        if (instance == null) {
            instance = new CategoryDAO(connection);
        }
        return instance;
    }
    
    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Category> getAllCategories() throws SQLException, IOException {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Category category = new Category(result.getInt("MaLoai"), result.getString("TenLoai"), result.getString("MoTa"));
            categories.add(category);
        }
        return categories;
    }

    public Category getCategoryById(int MaLoai) throws SQLException {
        String sql = "SELECT * FROM categories WHERE MaLoai = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, MaLoai);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            Category category = new Category(result.getInt("MaLoai"), result.getString("TenLoai"), result.getString("MoTa"));
            return category;
        } else {
            return null;
        }
    }

    public void addCategory(Category category) throws SQLException {
        String sql = "INSERT INTO categories (MaLoai, TenLoai, MoTa) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, category.getMaLoai());
        statement.setString(2, category.getTenLoai());
        statement.setString(3, category.getMoTa());
        statement.executeUpdate();
    }

    public void updateCategory(Category category) throws SQLException {
        String sql = "UPDATE categories SET TenLoai = ?, MoTa = ? WHERE MaLoai = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, category.getTenLoai());
        statement.setString(2, category.getMoTa());
        statement.setInt(3, category.getMaLoai());
        statement.executeUpdate();
    }

    public void deleteCategory(int MaLoai) throws SQLException {
        String sql = "DELETE FROM categories WHERE MaLoai = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, MaLoai);
        statement.executeUpdate();
    }
}

