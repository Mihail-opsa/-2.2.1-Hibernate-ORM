package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//// основные правки и статья https://refactoring.guru/ru/design-patterns/java
//1.разделение логики сушностей userDaoImp-carDaoImp и service
//2.соответственно интерфейсы и т д
//3.убрал дубликаты при создании user (использовал "merge" в методе update )
// 4. добавил для себя класс для проверки соединения TestDB





public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);


//Сохраняем пользователей
        List<User> users = new ArrayList<>();
        users.add(new User("User1", "LastName1", "users1@mail.com"));
        users.add(new User("User2", "LastName2", "users2@mail.com"));

        for (User user : users) {
            userService.add(user);
        }
// Сохраняем машины
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("bmw", 520));
        cars.add(new Car("Mercedes", 1200));

        for (Car car : cars) {
            carService.addCar(car);
        }
//Получаем из БД
        List<User> userFromDb = userService.listUsers();
        List<Car> carFromBd = carService.listCars();

        for (int i = 0; i < userFromDb.size(); i++) {
            userFromDb.get(i).setCar(carFromBd.get(i));
        }

        for (User user : userFromDb) {
            userService.update(user);

        }

        context.close();


    }
}

