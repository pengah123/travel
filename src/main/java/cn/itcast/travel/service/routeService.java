package cn.itcast.travel.service;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface routeService {
    PageBean<Route> pageList(int cid, int currPage, int pageSize,String rname);

    Route findOne(int rid);

    Boolean findFavorite(int rid, int uid);
}
