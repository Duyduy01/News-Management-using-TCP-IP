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
import model.News;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

/**
 *
 * @author ADMIN
 */

public class NewsController {
    private static NewsController instance;

    private Socket client;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    private final String IP = "localhost";
    private final int PORT_NUMBER = 7890;

    public static NewsController getInstance() throws IOException {
        if (instance == null) {
            instance = new NewsController();
        }
        return instance;
    }

    public NewsController() throws IOException {
        client = new Socket(IP, PORT_NUMBER);
        System.out.println(client + " client " + client.getLocalPort());
        dis = new DataInputStream(client.getInputStream());
        dos = new DataOutputStream(client.getOutputStream());
        oos = new ObjectOutputStream(client.getOutputStream());
        ois = new ObjectInputStream(client.getInputStream());
    }

    public static String getList(Request req, Response res) throws IOException {
        NewsController rc = getInstance();
        rc.dos.writeUTF("newss?");
        rc.dos.flush();

        News[] newss = null;
        try {
            newss = (News[]) rc.ois.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("newss", newss);
        Gson gson = new Gson();
        String json = gson.toJson(response);

        // Set the response type to "application/json"
        res.type("application/json");

        // Return the JSON string as the response body
        return json;
    }

    public static String getNews(Request req, Response res) throws IOException {
        NewsController rc = getInstance();
        String MaTinTuc = req.params("news");
        rc.dos.writeUTF("newss/" + MaTinTuc);
        rc.dos.flush();

        News news = null;
        try {
            news = (News) rc.ois.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("news", news);
        Gson gson = new Gson();
        String json = gson.toJson(response);

        // Set the response type to "application/json"
        res.type("application/json");

        // Return the JSON string as the response body
        return json;
    }

    public static String saveNews(Request req, Response res) throws IOException {
        NewsController rc = getInstance();
        String body = req.body();
        News news = new Gson().fromJson(body, News.class);

        rc.dos.writeUTF("newss/store");
        rc.dos.flush();
        rc.oos.writeObject(news);
        rc.oos.flush();

        String status = (String) rc.dis.readUTF();

        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        Gson gson = new Gson();
        String json = gson.toJson(response);

        // Set the response type to "application/json"
        res.type("application/json");

        // Return the JSON string as the response body
        return json;
    }
 
    public static String updateNews(spark.Request req, spark.Response res) throws IOException {
        NewsController fc = getInstance();
        String MTT = req.params("news");
        int MaTinTuc = Integer.parseInt(MTT);
        String body = req.body();
        News news = new Gson().fromJson(body, News.class);
        news.setMaTinTuc(MaTinTuc);

        fc.dos.writeUTF("newss/update");
        fc.dos.flush();
        fc.oos.writeObject(news);
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

    public static String deleteNews(spark.Request req, spark.Response res) throws IOException {
        NewsController fc = getInstance();
        String MaTinTuc = req.params("news");

        fc.dos.writeUTF("newss/delete");
        fc.dos.flush();
        fc.dos.writeUTF(MaTinTuc);
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
