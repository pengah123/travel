package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.Dao;
import cn.itcast.travel.dao.impl.DaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.Service;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class ServiceImple implements Service {
    Dao dao=new DaoImpl();
    @Override
    public boolean register(User user) {
        User u=dao.findUserByUsename(user.getUsername());
        if(u!=null){
//            用户已存在
            return false;
        }else{
            user.setStatus("N");
            user.setCode(UuidUtil.getUuid());
//            用户不存在,存储用户信息
            dao.register(user);
//            发送邮件
            String content="<a href='localhost/travel/activeServlet?code="+user.getCode()+"'>点击激活邮箱</a>";
            System.out.println(content);
            MailUtils.sendMail(user.getEmail(),content,"激活账户");
            return true;
        }
    }

    @Override
    public boolean active(String code) {
//        根据验证码查找用户
        User user=dao.findUserByCode(code);
        if(user==null){
//            查找失败
            return false;
        }else{
//            查找成功，更新用户
            dao.updateUserState(user);
            return true;
        }

    }

    @Override
    public User findByNameAndPassword(User user) {
        User u = dao.findByNameAndPassword(user);
        return u;
    }
}
