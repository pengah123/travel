package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.routeDao;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class routeDaoImpl implements routeDao {
    JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int totalCount(int cid,String rname) {
//        String sql="select count(*) from tab_route where cid=?";
        String sql="select count(*) from tab_route where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        List params=new ArrayList();//条件
        if(cid!=0){
            sb.append(" and cid=? ");
            params.add(cid);
        }
        if(rname!=null&&rname.length()!=0){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }

        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<Route> routeList(int cid, int start, int pageSize,String rname) {
//        String sql="select * from tab_route where cid=? and rname like %?% limit ?, ?";
        String sql="select * from tab_route where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        List params=new ArrayList();//条件
        if(cid!=0){
            sb.append(" and cid=? ");
            params.add(cid);
        }
        if(rname!=null&&rname.length()!=0){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sb.append(" limit ?, ? ");
        params.add(start);
        params.add(pageSize);
        return template.query(sb.toString(),new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }

    @Override
    public Route findRouteByRid(int rid) {
        String sql="select * from tab_route where rid=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }

    @Override
    public List<RouteImg> findImgByRid(int rid) {
        String sql="select * from tab_route_img where rid=?";
        return template.query(sql,new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid);
    }

    @Override
    public Category findCategoryByCid(int cid) {
        String sql="select * from tab_category where cid=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Category>(Category.class),cid);
    }

    @Override
    public Seller findSellerBySid(int sid) {
        String sql="select * from tab_seller where sid=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),sid);
    }

    @Override
    public Favorite finFavorite(int rid, int uid) {
        Favorite favorite= null;
        try {
            String sql = "select * from tab_favorite where rid=? and uid= ?";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return favorite;
    }
}
