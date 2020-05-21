package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface Service {
    public boolean register(User user);

    boolean active(String code);

    User findByNameAndPassword(User user);
}
