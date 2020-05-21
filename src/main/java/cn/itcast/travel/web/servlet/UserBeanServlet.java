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

@WebServlet("/user/*")
public class UserBeanServlet extends BeanServlet {
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        创建错误信息对象
        ResultInfo resultInfo = new ResultInfo();
//        获取数据
        Map<String, String[]> map = request.getParameterMap();
//        封装数据到User
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        根据用户名和密码查找用户是否存在
        ServiceImple service = new ServiceImple();
        User uresult = service.findByNameAndPassword(user);
        if (uresult != null) {
//            用户存在
            //        验证该用户是否被激活
            if (uresult.getStatus().equals("Y")) {
//                用户被激活
                resultInfo.setFlag(true);
                //        登陆成功则存入session
                HttpSession session = request.getSession();
                session.setAttribute("user",uresult);
            } else {
//           用户没激活
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("尚未激活邮箱，请激活");
            }
        } else {
//            用户不存在
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名密码错误，请检查");
        }
//        返回信息
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(),resultInfo);
    }

}
