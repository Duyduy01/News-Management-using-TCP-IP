package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.DAO.CategoryDAO;
import model.Category;
import model.News;
import server.DAO.NewsDAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class ServerThread implements Runnable {
    private Socket socket;
    private DataInputStream dis;  //nhận string từ client
    private DataOutputStream dos;
    private ObjectInputStream ois; //nhận obj từ client
    private ObjectOutputStream oos;

//	khởi tạo
    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        this.dis = new DataInputStream(socket.getInputStream());
        this.dos = new DataOutputStream(socket.getOutputStream());
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.ois = new ObjectInputStream(socket.getInputStream());
    }
    

    @Override
    public void run() {
        while (true) {
            try {
                String message = dis.readUTF();
                System.out.println("Request: " + message);
                
                if (message.contains("categories/store")) {
                    
                    Category category = (Category) ois.readObject();
                    saveCategory(category);
                } else if (message.contains("categories/update")) {
                    
                    Category category = (Category) ois.readObject();
                    updateCategory(category);
                } else if (message.contains("categories/delete")) {
                    
                    String ML = dis.readUTF();
                    int MaLoai = Integer.parseInt(ML);
                    deleteCategory(MaLoai);
                } else if (message.contains("categories/")) {
                    
                    String[] strs = message.split("/");
                    String ML = strs.length > 1 ? strs[1] : "";
                    int MaLoai = Integer.parseInt(ML);
                    getCategory(MaLoai);
                } else if (message.contains("categories")) {
                    getListCategories();
                } 
                else 
                if (message.contains("newss/store")) {
                    News news = (News) ois.readObject();
                    saveNews(news);
                } else if (message.contains("newss/update")) {
                    News news = (News) ois.readObject();
                    updateNews(news);
                } else if (message.contains("newss/delete")) {
                    String MTT = dis.readUTF();
                    int MaTinTuc = Integer.parseInt(MTT);
                    deleteNews(MaTinTuc);
                } else if (message.contains("newss/")) {
                    String[] strs = message.split("/");
                    String MTT = strs.length > 1 ? strs[1] : "";
                    int MaTinTuc = Integer.parseInt(MTT);
                    getNews(MaTinTuc);
                } else if (message.contains("newss")) {
                    getListNews();
                }
                
            } catch (Exception e) {
                System.err.println(e);
                closeConnection();
                return;
            }
        }
    }
    
    private void closeConnection() {
        if (socket != null) {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Connection is closed!");
    }
    
    
    private void getListCategories() throws IOException {
        List<Category> list = null;
        try {
            list = CategoryDAO.getInstance(Controller.getInstance().getDBConnection()).getAllCategories();
        } catch (SQLException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("111 " + ex);
        }
        Category[] categories = null;
        if (list != null) {
            categories = list.toArray(new Category[list.size()]);
        }
        oos.writeObject(categories);
        oos.flush();
    }
    
    private void getCategory(int MaLoai) throws IOException {
        try {
            Category category = CategoryDAO.getInstance(Controller.getInstance().getDBConnection()).getCategoryById(MaLoai);
            oos.writeObject(category);
            oos.flush();
        } catch (Exception e) {
            System.err.println(e);
            oos.writeObject(null);
            oos.flush();
        }
    }
    
    private void saveCategory(Category category) throws IOException, SQLException {
        try {
            CategoryDAO.getInstance(Controller.getInstance().getDBConnection()).addCategory(category);
            dos.writeUTF("success!");
            dos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            dos.writeUTF(ex.toString());
            dos.flush();
        }
    }
    
    private void updateCategory(Category category) throws IOException, SQLException {
        try {
            CategoryDAO.getInstance(Controller.getInstance().getDBConnection()).updateCategory(category);
            dos.writeUTF("success!");
            dos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            dos.writeUTF(ex.toString());
            dos.flush();
        }
    }
    
    private void deleteCategory(int MaLoai) throws IOException, SQLException {
        try {
            CategoryDAO.getInstance(Controller.getInstance().getDBConnection()).deleteCategory(MaLoai);
            dos.writeUTF("success!");
            dos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            dos.writeUTF(ex.toString());
            dos.flush();
        }
    }
    
    
    private void getListNews() throws IOException {
        try {
            List<News> list = NewsDAO.getInstance(Controller.getInstance().getDBConnection()).getAllNews();
            News[] newss = list.toArray(new News[list.size()]);
            oos.writeObject(newss);
            oos.flush();
        } catch (Exception e) {
            System.err.println(e);
            oos.writeObject(null);
            oos.flush();
        }
    }

    private void getNews(int MaTinTuc) throws IOException {
        try {
            News news = NewsDAO.getInstance(Controller.getInstance().getDBConnection()).getNewsById(MaTinTuc);
            oos.writeObject(news);
            oos.flush();
        } catch (Exception e) {
            System.err.println(e);
            oos.writeObject(null);
            oos.flush();
        }
    }

    private void saveNews(News news) throws IOException, SQLException {
        try {
            NewsDAO.getInstance(Controller.getInstance().getDBConnection()).addNews(news);
            dos.writeUTF("success!");
            dos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            dos.writeUTF(ex.toString());
            dos.flush();
        }
    }

    private void updateNews(News news) throws IOException, SQLException {
        try {
            NewsDAO.getInstance(Controller.getInstance().getDBConnection()).updateNews(news);
            dos.writeUTF("success!");
            dos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            dos.writeUTF(ex.toString());
            dos.flush();
        }
    }

    private void deleteNews(int MaTinTuc) throws IOException, SQLException {
        try {
            NewsDAO.getInstance(Controller.getInstance().getDBConnection()).deleteNews(MaTinTuc);
            dos.writeUTF("success!");
            dos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            dos.writeUTF(ex.toString());
            dos.flush();
        }
    }
    
    private String getStringBetween(String str, String start, String end) {
        String[] strs = str.split(start);
        if (strs.length == 1) {
            return "";
        }
        if ("".equals(end)) {
            return strs[1];
        }
        return strs[1].split(end)[0];
    }
}
