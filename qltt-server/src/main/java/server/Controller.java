/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class Controller {
    private static Controller instance;
    private Connection dbConnection;
    private List<ServerThread> clients;
    
    private final int PORT_NUMBER = 7890;

    
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    public Controller() {
        this.clients = new ArrayList<>();
        createDatabaseConnection();
    }
    
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
        System.out.printf("ServerSocket is running on port: %d\n", PORT_NUMBER);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Accept a connection: " + clientSocket.getPort());
            ServerThread serverThread = new ServerThread(clientSocket);
            clients.add(serverThread);
            new Thread(serverThread).start();
        }
    }

    
    private void createDatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ltm2";
            String username = "root";
            String password = "123456";
            this.dbConnection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.err.println("Could not find database driver class: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Could not connect to database: " + e.getMessage());
        }
        System.out.println("Database connected");
    }
    
    public Connection getDBConnection() {
        return dbConnection;
    }
}
