package cn.itcast.travel.dao;

import cn.itcast.travel.domain.*;

import java.util.List;

public interface routeDao {
    /**
     * 计算总条目数
     * @param cid
     * @return
     */
    int totalCount(int cid,String rname);

    /**
     * 获取记录
     * @param cid
     * @param start
     * @param pageSize
     * @return
     */
    List<Route> routeList(int cid, int start, int pageSize,String rname);

    Route findRouteByRid(int rid);

    List<RouteImg> findImgByRid(int rid);

    Category findCategoryByCid(int cid);

    Seller findSellerBySid(int sid);

    Favorite finFavorite(int rid, int uid);
}
