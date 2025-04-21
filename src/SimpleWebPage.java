import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleWebPage {
    public static void main(String[] args) throws IOException {
        // Get port from environment variables (required for Render)
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8000"));

        // Create HTTP server
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Define the response for the root URL "/"
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "<html>" +
                        "<head><title>Welcome</title></head>" +
                        "<body>" +
                        "<h1 style='text-align:center;'>Welcome to My Website</h1>" +
                        "<p style='text-align:center;'>This site is hosted on Render and built with pure Java!</p>" +
                        "</body>" +
                        "</html>";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                exchange.getResponseBody().write(response.getBytes());
                exchange.getResponseBody().close();
            }
        });

        // Start the server
        server.start();
        System.out.println("Server is running on port " + port);
    }
}