package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        // создаем и сохраняем юзеров

        List<User> users = new ArrayList<>();
        users.add(new User("User1", "LastName1", "users1@mail.com"));
        users.add(new User("User2", "LastName2", "users2@mail.com"));

        for (User user : users) {// сохраняем по отдельности
            userService.add(user);
        }

        List<Car> cars = new ArrayList<>();
        cars.add(new Car("bmw",520));
        cars.add(new Car("Mercedes",1200));

        for (Car car : cars) {
            userService.addCar(car);
        }

        List<User> userFromDb = userService.listUsers();
        List<Car> carFromBd = userService.listCars();

       for (int i = 0; i < userFromDb.size(); i++) {
           userFromDb.get(i).setCar(carFromBd.get(i));
       }

       for (User user : userFromDb) {
           userService.add(user); // add косяк!!! понял принял  надо не добавлять а обновлять во избежания дуболикатов 

       }

        context.close();


    }
}
