/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qltt.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

/**
 *
 * @author ADMIN
 */

public class CategoryController {
    private static CategoryController instance;
    
    private final Socket client;
    private final DataInputStream dis; //nhận 1 chuỗi
    private final DataOutputStream dos; //truyền 1 chuỗi
    private final ObjectOutputStream oos; // truyền 1 đối tượng
    private final ObjectInputStream ois; // nhận 1 đối tượng
    
    private final String IP = "localhost"; //địa chỉ server socket
    private final int PORT_NUMBER = 7890; //port server

    
    public static CategoryController getInstance() throws IOException {
        if (instance == null) {
            instance = new CategoryController();
        }
        return instance;
    }
    
    public CategoryController() throws IOException {
        client = new Socket(IP, PORT_NUMBER); //khởi tạo kết nối với server
        System.out.println(client + " client " + client.getLocalPort());
        dis = new DataInputStream(client.getInputStream()); //luồng nhận của client
        dos = new DataOutputStream(client.getOutputStream());// luồng truyền của client
        oos = new ObjectOutputStream(client.getOutputStream());
        ois = new ObjectInputStream(client.getInputStream());
    }
    
    public static String getList(spark.Request req, spark.Response res) throws IOException {
        CategoryController fc = getInstance();
//        String key = req.queryParamOrDefault("key", "");
        fc.dos.writeUTF("categories" );
        fc.dos.flush();
        
        Category[] categories = null;
        try {
            categories = (Category[]) fc.ois.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("categories", categories);
        Gson gson = new Gson();
        String json = gson.toJson(response);

        // Set the response type to "application/json"
        res.type("application/json");

        // Return the JSON string as the response body
        return json;
    }
    
    public static String getCategory(spark.Request req, spark.Response res) throws IOException {
        CategoryController fc = getInstance();
        String MaLoai = req.params("category");
        fc.dos.writeUTF("categories/" + MaLoai);
        fc.dos.flush();
        
        Category category = null;
        try {
            category = (Category) fc.ois.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("category", category);
        Gson gson = new Gson();
        String json = gson.toJson(response);

        // Set the response type to "application/json"
        res.type("application/json");

        // Return the JSON string as the response body
        return json;
    }
    
    public static String saveCategory(spark.Request req, spark.Response res) throws IOException {
        CategoryController fc = getInstance();
        String body = req.body();
        Category category = new Gson().fromJson(body, Category.class);
        
        fc.dos.writeUTF("categories/store");
        fc.dos.flush();
        fc.oos.writeObject(category);
        fc.oos.flush();
        
        String status = (String) fc.dis.readUTF();
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        Gson gson = new Gson();
        String json = gson.toJson(response);

        // Set the response type to "application/json"
        res.type("application/json");

        // Return the JSON string as the response body
        return json;
    }
    
    public static String updateCategory(spark.Request req, spark.Response res) throws IOException {
        CategoryController fc = getInstance();
        String ML = req.params("category");
        int MaLoai = Integer.parseInt(ML);
        String body = req.body();
        Category category = new Gson().fromJson(body, Category.class);
        category.setMaLoai(MaLoai);
        
        fc.dos.writeUTF("categories/update");
        fc.dos.flush();
        fc.oos.writeObject(category);
        fc.oos.flush();
        
        String status = (String) fc.dis.readUTF();
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        Gson gson = new Gson();
        String json = gson.toJson(response);

        // Set the response type to "application/json"
        res.type("application/json");

        // Return the JSON string as the response body
        return json;
    }
    
    public static String deleteCategory(spark.Request req, spark.Response res) throws IOException {
        CategoryController fc = getInstance();
        String MaLoai = req.params("category");
        
        fc.dos.writeUTF("categories/delete");
        fc.dos.flush();
        fc.dos.writeUTF(MaLoai);
        fc.dos.flush();
        
        String status = (String) fc.dis.readUTF();
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        Gson gson = new Gson();
        String json = gson.toJson(response);

        // Set the response type to "application/json"
        res.type("application/json");

        // Return the JSON string as the response body
        return json;
    }
}
