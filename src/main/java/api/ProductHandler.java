package api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataBaseAccess;
import model.Product;
import service.ProductService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ProductHandler implements HttpHandler {

    DataBaseAccess dao = new DataBaseAccess();
    ProductService service = new ProductService(dao);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";
        int statusCode = 200;
        String path = exchange.getRequestURI().getPath();

        if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            if ("/products".equals(path)) {
                List<Product> productList = service.getAllProducts();
                response = listToString(productList);

            } else if (path.startsWith("/products/")) {
                String[] parts = path.split("/");
                if (parts.length == 3) {
                    String idPart = parts[2];
                    try {
                        int id = Integer.parseInt(idPart);
                        Product product = service.findById(id);
                        if (product != null) {
                            response = product.getName();
                        } else {
                            response = "Product not found";
                            statusCode = 404;
                        }
                    } catch (NumberFormatException e) {
                        response = "Invalid ID format";
                        statusCode = 400;
                    }
                } else {
                    response = "Invalid request path";
                    statusCode = 400;
                }
            } else {
                response = "Not Found";
                statusCode = 404;
            }
        } else {
            response = "Method Not Allowed";
            statusCode = 405;
        }

        exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public static String listToString(List<Product> productList) {
        String response = "";
        for (Product p : productList) {
            response += p.getName() + "\n";
        }
        return response;
    }
}

