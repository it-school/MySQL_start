package com.itschool;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
   public static DataBase db;

   public static void main(String[] args) throws SQLException {
      db = new DataBase("test");
      System.out.println(db.Connect() ? "Connected successfully" : "Connection failed");

      useDB();
   }

   public static void useDB() throws SQLException {
      db.executeQuery("use " + db.getDBname() + ";");

      System.out.println(db.executeQueryWithResult("SELECT * FROM users ORDER BY 2;"));

      db.insert("INSERT INTO users(login, password, name) VALUES ('viktor" + (int) (Math.random() * 100) + "', 'Passw', 'Viktor')");
      System.out.println();
      System.out.println(getStringFromResultSet(db.executeQueryWithResult("SELECT * FROM users ORDER BY id;")));

      System.out.println();
      System.out.println(getStringFromResultSet(db.search("us")));
   }

   private static String getStringFromResultSet(ArrayList result) {
      String str = "";

      for (Object row : result) {
         for (String column : (String[]) row) {
            str += column + "\t";
         }
         str += "\n";
      }

      return str;
   }
}