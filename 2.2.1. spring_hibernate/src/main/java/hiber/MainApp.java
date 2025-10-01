package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "LastName1", "user1@email.ru");
        user1.setCar(new Car("BMW", 450));


        User user2 = new User("User2", "LastName2", "user2@email.ru");
        user2.setCar(new Car("lada", 2192));
        userService.add(user1);
        userService.add(user2);


        System.out.println("Машина User1: " + userService.getUserById(user1.getId()).getCar().getModel());
        System.out.println("Владелец lada: " + userService.getUserByCar("lada", 2192).getFirstName());
        context.close();
    }
}
