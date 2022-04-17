package cn.aseng.web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/responseDemo4")
public class ResponseDemo4 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setCharacterEncoding("utf-8");
//        response.setHeader("context-type","text/html;charset=utf-8");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter pw=response.getWriter();

        pw.write("<h1>hello 杨小芬</h1>");
        pw.write("");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }


}
