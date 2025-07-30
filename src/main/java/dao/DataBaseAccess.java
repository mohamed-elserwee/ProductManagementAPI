package dao;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseAccess {

    Connection myConnection = null;
    Statement myStatement = null;
    ResultSet myResultSet = null;

    String dbURL = "jdbc:mysql://localhost:3306/products";
    String user = "root";
    String password = "Serwee-3834124";



    public List<Product> listOfAllProducts(){
        List<Product> productList = new ArrayList<>();

        try{
            myConnection = DriverManager.getConnection(dbURL, user, password);

            myStatement = myConnection.createStatement();

            myResultSet = myStatement.executeQuery("SELECT * FROM product_list");

            while(myResultSet.next()){
                Product p = new Product( myResultSet.getString("name"), myResultSet.getInt("weight"), myResultSet.getDouble("price"), myResultSet.getInt("id"));
                productList.add(p);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return productList;
    }

    public void insertProduct(Product p) {
        String query = "INSERT INTO product_list (name, weight, price) VALUES ('"
                + p.getName() + "', " + p.getWeight() + ", " + p.getPrice() + ")";

        try{
            myConnection = DriverManager.getConnection(dbURL, user, password);

            myStatement = myConnection.createStatement();

            myResultSet = myStatement.executeQuery(query);
        }
         catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    public void updateProduct(int id, String name, int weight, double price) {
        String query = "UPDATE product_list SET name = '" + name + "', weight = " + weight + ", price = " + price + " WHERE id = " + id;

        try{
            myConnection = DriverManager.getConnection(dbURL, user, password);

            myStatement = myConnection.createStatement();

            myResultSet = myStatement.executeQuery(query);
        }
        catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    public void deleteProductById(int id) {
        String query = "DELETE FROM product_list WHERE id = " + id;

        try{
            myConnection = DriverManager.getConnection(dbURL, user, password);

            myStatement = myConnection.createStatement();

            myResultSet = myStatement.executeQuery(query);
        }
        catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }
}
