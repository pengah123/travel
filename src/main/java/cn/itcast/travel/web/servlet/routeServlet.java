package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.impl.routeServiceImpl;
import cn.itcast.travel.service.routeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/route/*")
public class routeServlet extends BeanServlet {
    routeServiceImpl service = new routeServiceImpl();

    public void routeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cidstr = request.getParameter("cid");
        String currPagestr = request.getParameter("currPage");
        String pageSizestr = request.getParameter("pageSize");
        String rname = request.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        int cid = 0;
        if (cidstr != null && cidstr.length() != 0 && !cidstr.equals("null")) {
            cid = Integer.parseInt(cidstr);
        }
        int currPage = 1;
        if (currPagestr != null && currPagestr.length() != 0) {
            currPage = Integer.parseInt(currPagestr);
        }
        int pageSize = 5;
        if (pageSizestr != null && pageSizestr.length() != 0) {
            pageSize = Integer.parseInt(pageSizestr);
        }
//        调用service方法返回pageBean
        PageBean<Route> pb = service.pageList(cid, currPage, pageSize, rname);
//        写回到前端
        writeValue(pb, response);
    }

    public void routeDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ridstr = request.getParameter("rid");
        int rid = Integer.parseInt(ridstr);
        Route route = service.findOne(rid);
        writeValue(route, response);
    }

    public void favorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ridstr = request.getParameter("rid");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        int uid = 0;
        if (user != null) {
            //        如果用户登陆了
             uid = user.getUid();
        }
        int rid=0;
        if (ridstr != null && ridstr.length() != 0) {
            //        如果用户登陆了
            rid=Integer.parseInt(ridstr);
        }
        boolean flag=service.findFavorite(rid,uid);
        writeValue(flag,response);

    }

}
