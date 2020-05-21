package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface Dao {
    User findUserByUsename(String username);

    void register(User user);

    User findUserByCode(String code);

    void updateUserState(User user);

    User findByNameAndPassword(User user);
}
