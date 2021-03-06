package nz.ac.auckland.mysql.connector;

import java.sql.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Connector {

    Connection conn = null;

    public Connector(){

//        Authenticator authenticator = null;
//
//        switch (authenticatiionMethod){
//
//            case "Google": authenticator  = new GoogleAuthenticator();
//            break;
//
//        }
//
//        if(authenticator != null){
//            Integer responseCode = authenticator.authenticate(username,password);
//            if(responseCode == 200){

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                    String connectionUrl = "jdbc:mysql://localhost:3306/medical";
                    String connectionUser = "root";
                    String connectionPassword = "";
                    conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//            } else {
//                throw new RuntimeException();
//            }
//
//        } else {
//            throw new RuntimeException();
//        }


    }


    public JSONObject executeQuery( String query ){

        Statement stmt = null;
        ResultSet rs = null;
        try {

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            JSONObject jsonObject = convertToJSON(rs);
            
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return null;
    }
    
    public static JSONObject convertToJSON(ResultSet resultSet)
            throws Exception {
        JSONObject jsonObject = new JSONObject();
        int count = 0;
        while (resultSet.next()) {
            int total_cols = resultSet.getMetaData().getColumnCount();
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < total_cols; i++) {
                JSONObject obj = new JSONObject();
                if (resultSet.getObject(i+1) != null) {
	                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
	                        .toLowerCase(), resultSet.getObject(i + 1));
                } else {
	                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
	                        .toLowerCase(), JSONObject.NULL);
                }
                jsonArray.put(obj);
            }
            jsonObject.put("Record_" + count, jsonArray);
            count++;
        }
        return jsonObject;
    }




/*    public static void main(String[] args) {
        *//*Connector main = new Connector();

        try {
            main.run();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }*//*

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
//			new com.mysql.jdbc.Driver();
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
// conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdatabase?user=testuser&password=testpassword");
            *//*String connectionUrl = "jdbc:mysql://localhost:3306/medical";
            String connectionUser = "root";
            String connectionPassword = "xiaoshuqin";
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);

            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/medical?" + "user=root@password=xiaoshuqin");*//*

            String connectionUrl = "jdbc:mysql://localhost:3306/medical";
            String connectionUser = "root";
            String connectionPassword = "xiaoshuqin";
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM patients");

            int count = 5;
            while (rs.next()) {
                String id = rs.getString("PID");
                String firstName = rs.getString("FIRST");
                String lastName = rs.getString("LAST");
                System.out.println("PID: " + id + ", First Name: " + firstName
                        + ", Last Name: " + lastName);
                count--;
                if(count == 0){
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

    }*/


}
