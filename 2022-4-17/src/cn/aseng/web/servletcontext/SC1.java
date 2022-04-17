package cn.aseng.web.servletcontext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/sC1")
public class SC1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext con1 = request.getServletContext();
        ServletContext s2 = this.getServletContext();
        System.out.println(con1);
        System.out.println(s2);
        System.out.println(s2==con1);;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }


}
