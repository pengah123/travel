package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.ServiceImple;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        错误信息对象
        ResultInfo resultInfo = new ResultInfo();
//        获取验证码
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkcode_server!=null&&check.equalsIgnoreCase(checkcode_server)){
//            验证码输入正确
            //        获取传递的用户信息参数
            Map<String, String[]> map = request.getParameterMap();
            User user=new User();
            try {
//            封装参数到user
                BeanUtils.populate(user,map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
//        调用service查找user
            ServiceImple service=new ServiceImple();
            boolean exist=service.register(user);
//        返回的结果为true,用户名不存在，跳转页面

            if(exist){
                resultInfo.setFlag(true);
            }else {
                //        返回的结果为false，用户名已存在
//        设置错误信息
                resultInfo.setErrorMsg("用户信息已存在");
                resultInfo.setFlag(false);

            }
        }else{
//        设置错误信息
            resultInfo.setErrorMsg("验证码输入有误");
            resultInfo.setFlag(false);
        }

        //        序列化json对象
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(resultInfo);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(s);
//        返回数据


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
