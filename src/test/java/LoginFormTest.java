import dao.UserDAO;
import dao.UserDAOImp;
import model.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginFormTest {
    @Test
    public void testAuthenticateExample(){
        String username = "admin";
        String password = "1234";
        UserDAO userDao = new UserDAOImp();
        String user;
        String pass;
        try {
            User dbUser = userDao.readUser(1);
            user = dbUser.getUser();
            pass = dbUser.getPassword();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(username);
        System.out.println(user);
        System.out.println(password);
        System.out.println(pass);
        assertEquals(true,user.equals(username) && pass.equals(password), "No coinciden usario" +
                " y contrasena dados por el usuario");
    }
}
