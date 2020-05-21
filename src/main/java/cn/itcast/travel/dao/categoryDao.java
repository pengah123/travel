package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

public interface categoryDao {
    /**
     * 查找所有类别名
     * @return
     */
    List<Category> findAll();
}
