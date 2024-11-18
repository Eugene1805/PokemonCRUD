package connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;
    private static String DATABASE_URL;
    private static String DATABASE_USER;
    private static String DATABASE_PASSWORD;

    private DBConnection() {
        try {
            Properties properties = new Properties();
            FileInputStream input = new FileInputStream("config.properties");
            properties.load(input);
            DATABASE_URL = properties.getProperty("DB_URL");
            DATABASE_USER = properties.getProperty("DB_USER");
            DATABASE_PASSWORD = properties.getProperty("DB_PASSWORD");
            input.close();
            this.connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException{
        if( instance == null || instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection(){ return connection;}
}
