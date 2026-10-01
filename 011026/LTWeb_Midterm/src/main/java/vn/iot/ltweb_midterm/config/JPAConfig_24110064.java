package vn.iot.ltweb_midterm.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class JPAConfig_24110064 {
    private static EntityManagerFactory factory;

    static {
        try {
            Properties props = new Properties();
            InputStream in = JPAConfig_24110064.class.getClassLoader().getResourceAsStream("application.properties");
            if (in != null) {
                props.load(in);
            }
            
            Map<String, String> properties = new HashMap<>();
            properties.put("jakarta.persistence.jdbc.url", props.getProperty("db.url"));
            properties.put("jakarta.persistence.jdbc.user", props.getProperty("db.username"));
            properties.put("jakarta.persistence.jdbc.password", props.getProperty("db.password"));
            properties.put("hibernate.hbm2ddl.auto", "none");
            
            factory = Persistence.createEntityManagerFactory("LTWeb_MidtermPU", properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
