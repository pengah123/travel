package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.categoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.categoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class categoryServiceImpl implements categoryService {
    categoryDaoImpl dao = new categoryDaoImpl();

    /**
     * 查找所有类别名
     *
     * @return
     */
    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();
        /*Set<String> category = jedis.zrange("category", 0, -1);*/
        Set<Tuple> category= jedis.zrangeWithScores("category", 0, -1);
        List<Category> list = null;
        if (category == null || category.size() == 0) {
            System.out.println("调用sql数据库");
//            第一次查找,调用dao生成list
            list = dao.findAll();
//            将list数据存入缓存
            for (Category category1 : list) {
                jedis.zadd("category",category1.getCid(),category1.getCname());
            }
        } else {
            System.out.println("调用nosql数据库");
//            不是第一次查找,将查找到的数据存入list
            list=new ArrayList<Category>();
            for (Tuple s : category) {
                Category category1 = new Category();
                category1.setCid((int)s.getScore());
                category1.setCname(s.getElement());
                list.add(category1);
            }
        }
        return list;
    }
}
