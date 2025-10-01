//package hiber.service;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class TestDB {
//    public static void main(String[] args) {
//        try {
//            Connection conn = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/spring_hiber",
//                    "root",
//                    "root"
//            );
//            System.out.println(" База данных подключена успешно!");
//            conn.close();
//        } catch (Exception e) {
//            System.out.println(" Ошибка: " + e.getMessage());
//        }
//    }
//}