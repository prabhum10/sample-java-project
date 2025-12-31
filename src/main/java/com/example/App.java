package com.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App {
    public static void main(String[] args) throws IOException {
        // Create HTTP server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // Create context for root path
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = generateHtmlPage();
                exchange.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });
        
        // Add health check endpoint
        server.createContext("/health", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "OK";
                exchange.getResponseHeaders().set("Content-Type", "text/plain");
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });
        
        // API endpoint
        server.createContext("/api/status", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "{\"status\": \"running\", \"version\": \"1.0.0\"}";
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });
        
        // Start server
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8080");
    }
    
    private static String generateHtmlPage() {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        return "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>IDP - POC</title>\n" +
            "    <style>\n" +
            "        * {\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "            box-sizing: border-box;\n" +
            "        }\n" +
            "        body {\n" +
            "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
            "            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n" +
            "            min-height: 100vh;\n" +
            "            display: flex;\n" +
            "            justify-content: center;\n" +
            "            align-items: center;\n" +
            "            padding: 20px;\n" +
            "        }\n" +
            "        .container {\n" +
            "            background: white;\n" +
            "            border-radius: 15px;\n" +
            "            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);\n" +
            "            padding: 60px 40px;\n" +
            "            text-align: center;\n" +
            "            max-width: 600px;\n" +
            "            animation: slideIn 0.6s ease-out;\n" +
            "        }\n" +
            "        @keyframes slideIn {\n" +
            "            from {\n" +
            "                opacity: 0;\n" +
            "                transform: translateY(-20px);\n" +
            "            }\n" +
            "            to {\n" +
            "                opacity: 1;\n" +
            "                transform: translateY(0);\n" +
            "            }\n" +
            "        }\n" +
            "        .icon {\n" +
            "            font-size: 80px;\n" +
            "            margin-bottom: 20px;\n" +
            "            animation: bounce 2s infinite;\n" +
            "        }\n" +
            "        @keyframes bounce {\n" +
            "            0%, 100% { transform: translateY(0); }\n" +
            "            50% { transform: translateY(-10px); }\n" +
            "        }\n" +
            "        h1 {\n" +
            "            font-size: 48px;\n" +
            "            color: #333;\n" +
            "            margin-bottom: 10px;\n" +
            "            font-weight: 700;\n" +
            "            letter-spacing: 2px;\n" +
            "        }\n" +
            "        .subtitle {\n" +
            "            font-size: 18px;\n" +
            "            color: #666;\n" +
            "            margin-bottom: 30px;\n" +
            "        }\n" +
            "        .status {\n" +
            "            display: inline-block;\n" +
            "            background: #4CAF50;\n" +
            "            color: white;\n" +
            "            padding: 12px 24px;\n" +
            "            border-radius: 25px;\n" +
            "            margin: 20px 0;\n" +
            "            font-weight: 600;\n" +
            "        }\n" +
            "        .info {\n" +
            "            background: #f5f5f5;\n" +
            "            padding: 20px;\n" +
            "            border-radius: 10px;\n" +
            "            margin-top: 30px;\n" +
            "            text-align: left;\n" +
            "        }\n" +
            "        .info-item {\n" +
            "            padding: 10px 0;\n" +
            "            border-bottom: 1px solid #ddd;\n" +
            "        }\n" +
            "        .info-item:last-child {\n" +
            "            border-bottom: none;\n" +
            "        }\n" +
            "        .label {\n" +
            "            font-weight: 600;\n" +
            "            color: #667eea;\n" +
            "        }\n" +
            "        .value {\n" +
            "            color: #666;\n" +
            "            margin-left: 10px;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"container\">\n" +
            "        <div class=\"icon\"></div>\n" +
            "        <h1>IDP - POC</h1>\n" +
            "        <p class=\"subtitle\">Intelligent DevOps Agent - Proof of Concept</p>\n" +
            "        <div class=\"status\"> Running</div>\n" +
            "        <div class=\"info\">\n" +
            "            <div class=\"info-item\">\n" +
            "                <span class=\"label\">Application:</span>\n" +
            "                <span class=\"value\">Java HTTP Server</span>\n" +
            "            </div>\n" +
            "            <div class=\"info-item\">\n" +
            "                <span class=\"label\">Port:</span>\n" +
            "                <span class=\"value\">8080</span>\n" +
            "            </div>\n" +
            "            <div class=\"info-item\">\n" +
            "                <span class=\"label\">Deployment:</span>\n" +
            "                <span class=\"value\">Azure Container App</span>\n" +
            "            </div>\n" +
            "            <div class=\"info-item\">\n" +
            "                <span class=\"label\">Started:</span>\n" +
            "                <span class=\"value\">" + timestamp + "</span>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>";
    }
}