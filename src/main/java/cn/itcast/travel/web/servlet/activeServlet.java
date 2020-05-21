package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.impl.ServiceImple;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeServlet")
public class activeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if(code==null||code.length()==0){
//            验证码不存在
        }else{
//            验证码存在
            ServiceImple serviceImple = new ServiceImple();
            boolean flag= serviceImple.active(code);
//            是否更新成功
            String msg;
            if(flag){
                msg="验证成功，请<a href='localhost/travel/login.html'>登陆</a>";
            }else{
                msg="验证失败";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
