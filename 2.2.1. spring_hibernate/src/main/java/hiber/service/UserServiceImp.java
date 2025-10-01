package hiber.service;

import hiber.dao.UserDao;
import hiber.dao.UserDaoImp;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired // автоматическое связывание -ищет бин !
   private UserDao userDao; //Dependency Inversion (DIP). предпочтительнее что бы не трогать код в UserDaoImp
                            // private UserDaoImp userDaoImp;




   @Transactional  // только чтение данных
   @Override
   public void add(User user) {
       userDao.add(user);
   }

   @Transactional(readOnly = true) // только чтение данных
   @Override
   public List<User> listUsers() {
       return userDao.listUsers();
   }

   @Transactional(readOnly = true) // только чтение данных
   @Override
    public User getUserById(Long id){
       return userDao.getUserById(id);
    }


@Transactional(readOnly = true) // только чтение данных
@Override
    public User getUserByCar(String model, int series){
       return userDao.getUserByCar(model, series);
    }

}
