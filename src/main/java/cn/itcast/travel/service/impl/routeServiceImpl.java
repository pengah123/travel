package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.routeDaoImpl;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.routeService;

import java.util.List;

public class routeServiceImpl implements routeService {
    routeDaoImpl dao=new routeDaoImpl();
    @Override
    public PageBean<Route> pageList(int cid, int currPage, int pageSize, String rname) {
        PageBean<Route> pb=new PageBean<>();
        pb.setCurrPage(currPage);
        pb.setPageSize(pageSize);
//        totalCount
        int totalCount=dao.totalCount(cid,rname);
        pb.setTotalCount(totalCount);
//        totalPage
        int totalPage=0;
        if(totalCount%pageSize==0){
           totalPage=totalCount/pageSize;
        }else {
            totalPage=totalCount/pageSize+1;
        }
        pb.setTotalPage(totalPage);
//        list
        int start=(currPage-1)*pageSize;
        List<Route> list=dao.routeList(cid,start,pageSize,rname);
        pb.setList(list);
        return pb;
    }

    @Override
    public Route findOne(int rid) {
//        查询基本信息
        Route route=dao.findRouteByRid(rid);
//        设置图片信息
        List<RouteImg> routeImgList=dao.findImgByRid(rid);
        route.setRouteImgList(routeImgList);
//        设置类别信息
        Category category=dao.findCategoryByCid(route.getCid());
        route.setCategory(category);
//        设置所属商家
        Seller seller=dao.findSellerBySid(route.getSid());
        route.setSeller(seller);
        return route;
    }

    @Override
    public Boolean findFavorite(int rid, int uid) {
        Favorite favorite=dao.finFavorite(rid,uid);
        return favorite!=null;
    }
}
