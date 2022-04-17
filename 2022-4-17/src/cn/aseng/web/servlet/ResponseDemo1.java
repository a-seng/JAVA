package cn.aseng.web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/responseDemo1")
public class ResponseDemo1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo1..............");
//
//        response.setStatus(302);
//        response.setHeader("location","/responseDemo2");
        String contextPath = request.getContextPath();
        System.out.println(contextPath);
        response.sendRedirect(contextPath+"/responseDemo2");

//        response.sendRedirect("/responseDemo2");


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }


}
