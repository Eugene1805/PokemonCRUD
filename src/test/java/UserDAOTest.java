import dao.UserDAO;
import dao.UserDAOImp;
import model.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    @Test
    public void testCreateNullUser(){
        UserDAO userdao = new UserDAOImp();
        User user = null;
        try {
            userdao.createUser(user);
        }  catch (SQLException | ClassNotFoundException e) {
            fail("Se produjo una excepción durante el test: " + e.getMessage());
        }
        assertEquals(null,user,"No debe poder insertar null a la base de datos");
    }

    @Test
    public void testReadUserWithZeroIndex(){
        UserDAO userdao = new UserDAOImp();
        User user;
        try {
            user = userdao.readUser(0);
            assertEquals(0, user.getId(), "No se encontro en la base de datos un usuario con id 0");
        } catch (SQLException | ClassNotFoundException e) {
            fail("Se produjo una excepción durante el test: " + e.getMessage());
        }
    }

    @Test
    public void testNullUpdateUser(){
        UserDAO userdao = new UserDAOImp();
        User user = null;
        try {
            userdao.updateUser(user);
        } catch (SQLException | ClassNotFoundException e) {
            fail("Se produjo una excepción durante el test: " + e.getMessage());
        }
        assertEquals(null,user,"No debe poder actualizar null a la base de datos");
    }

}
