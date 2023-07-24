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
import model.News;

/**
 *
 * @author ADMIN
 */

public class NewsDAO {
    private Connection connection;
    private static NewsDAO instance;

    public static NewsDAO getInstance(Connection connection) throws IOException {
        if (instance == null) {
            instance = new NewsDAO(connection);
        }
        return instance;
    }

    public NewsDAO(Connection connection) {
        this.connection = connection;
    }

    public List<News> getAllNews() throws SQLException, IOException {
        List<News> newss = new ArrayList<>();
//        System.out.println("getAllNews: " + key + ", " + Maloai);
        String sql = "SELECT * FROM news";
        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            News news = new News(result.getInt("MaTinTuc"), result.getString("TieuDe"), result.getString("ChiTiet"), result.getString("LinkHinhAnh"), result.getInt("MaLoaiTin"), result.getString("NgayDang"));
            newss.add(news);
        }
        return newss;
    }

    public News getNewsById(int MaTinTuc) throws SQLException {
        String sql = "SELECT * FROM news WHERE MaTinTuc = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, MaTinTuc);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            News news = new News(result.getInt("MaTinTuc"), result.getString("TieuDe"), result.getString("ChiTiet"), result.getString("LinkHinhAnh"), result.getInt("MaLoaiTin"), result.getString("NgayDang"));
            return news;
        } else {
            return null;
        }
    }

    public void addNews(News news) throws SQLException {
        String sql = "INSERT INTO news (MaTinTuc, TieuDe, ChiTiet, LinkHinhAnh, MaLoaiTin, NgayDang) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, news.getMaTinTuc());
        statement.setString(2, news.getTieuDe());
        statement.setString(3, news.getChiTiet());
        statement.setString(4, news.getLinkHinhAnh());
        statement.setInt(5, news.getMaLoaiTin());
        statement.setString(6, news.getNgayDang());
        statement.executeUpdate();
    }

    public void updateNews(News news) throws SQLException {
        String sql = "UPDATE news SET TieuDe = ?, ChiTiet = ?, LinkHinhAnh = ?, MaLoaiTin = ?, NgayDang = ? WHERE MaTinTuc = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, news.getTieuDe());
        statement.setString(2, news.getChiTiet());
        statement.setString(3, news.getLinkHinhAnh());
        statement.setInt(4, news.getMaLoaiTin());
        statement.setString(5, news.getNgayDang());
        statement.setInt(6, news.getMaTinTuc());
        statement.executeUpdate();
    }

    public void deleteNews(int MaTinTuc) throws SQLException {
        String sql = "DELETE FROM news WHERE MaTinTuc = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, MaTinTuc);
        statement.executeUpdate();
    }
}

