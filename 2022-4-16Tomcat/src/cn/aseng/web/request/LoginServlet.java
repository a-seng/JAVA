package cn.aseng.web.request;

import cn.aseng.dao.Userdao;
import cn.aseng.domain.User;
import org.springframework.beans.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.attribute.AclEntryPermission;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
//        String username=request.getParameter("username");
//        String password= request.getParameter("password");
//        Map<String, String[]> map = request.getParameterMap();
//
//        User loginUser=new User();
//        //使用BeanUtils封装
//        BeanUtils.populate(loginUser,map);
//
//        loginUser.setUsername(username);
//        loginUser.setPassword(password);
        Map<String,String[]>map=request.getParameterMap();
        User loginUser=new User();
//
//        BeanUtils.populate(loginUser,map);
//

        Userdao dao=new Userdao();
        User user=dao.login(loginUser);
        if(user==null){
            request.getRequestDispatcher("/failServlet").forward(request,response);
        }else {

            request.setAttribute("user",user);
            request.getRequestDispatcher("/successServlet").forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);


    }
}
