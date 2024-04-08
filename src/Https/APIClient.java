/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Https;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author admin
 */
public class APIClient {
     public static void main(String[] args) throws Exception {
       
      
        // URL của API bạn muốn gọi
        String apiUrl = "http://localhost:3000/test";

        // Gọi phương thức GET và in ra đoạn text nhận được
        String responseText = sendHttpGetRequest(apiUrl);
        System.out.println("Response Text: " + responseText);
        
         String cardNumber = "012314324";
         Double amount = 23.3;
        
         String jsonData = "{\"cardNumber\": \"" + cardNumber + "\", \"amount\": " + amount + "}";
         
         String responsePostText = sendHttpPostRequest(apiUrl, jsonData);
         System.out.println("responsePostText: " + responsePostText);
        
    }
     
  
       
    
     

    // Phương thức thực hiện HTTP GET request và trả về dữ liệu nhận được
    private static String sendHttpGetRequest(String apiUrl) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }
    }

    // Phương thức thực hiện HTTP PUT request để cập nhật dữ liệu
    private static void sendHttpPutRequest(String apiUrl, String data) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(data.getBytes());
        }

        // Xử lý kết quả nếu cần
        int responseCode = connection.getResponseCode();
        System.out.println("PUT Response Code: " + responseCode);
    }

    // Phương thức thực hiện HTTP POST request và trả về ID của bài post mới tạo
    private static String sendHttpPostRequest(String apiUrl, String data) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(data.getBytes());
        }

        // Xử lý kết quả và trích xuất ID của bài post mới
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }
    }
}
