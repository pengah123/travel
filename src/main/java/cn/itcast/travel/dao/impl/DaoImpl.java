package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.Dao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class DaoImpl implements Dao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findUserByUsename(String username) {
        User user=null;
        String sql="select* from tab_user where username=?";
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void register(User user) {
        String sql="insert into tab_user (username,password,name,birthday,sex,telephone,email,status,code) values (?,?,?,?,?,?,?,?,?)";
        template.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),user.getStatus(),user.getCode());

    }

    @Override
    public User findUserByCode(String code) {
        User user=null;
        String sql="select* from tab_user where code=?";
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateUserState(User user) {
        String sql="update tab_user set status='Y' where uid=?";
        template.update(sql,user.getUid());
    }

    @Override
    public User findByNameAndPassword(User user) {
        User u=null;
        String sql="select* from tab_user where username=? and password=?";
        try {
            u = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),user.getUsername(),user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
}
