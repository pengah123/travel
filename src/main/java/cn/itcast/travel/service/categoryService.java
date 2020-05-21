package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

public interface categoryService {
    /**
     * 查找所有类别名
     * @return
     */
    List<Category> findAll();
}
