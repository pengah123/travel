package utilTest;


import cn.itcast.travel.dao.impl.DaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class jdbcUtilTest {
    @Test
    public void jdbcTest() throws IOException {
        InputStream is = jdbcUtilTest.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties pp = new Properties();
        System.out.println(is);

    }

    @Test
    public void DaoUpdataTest(){
        DaoImpl dao = new DaoImpl();
        User user = new User();
        user.setUid(1);
        dao.updateUserState(user);
    }

    @Test
    public void FindByNameAndPassword(){
        DaoImpl dao = new DaoImpl();
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        User u = dao.findUserByUsename("zhangsan");
        System.out.println(u.toString());
    }
}
