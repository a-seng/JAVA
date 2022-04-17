package cn.aseng.web.servlet;

import cn.aseng.web.utils.DownLoadUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/dlS")
public class dlS extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数，文件名称
        String fn1 = request.getParameter("filename");
        //2.使用直接输入流加载文件进内存
        //2.1找到文件服务器路径
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/img/" + fn1);
        //2.2用字节流关联
        FileInputStream fis = new FileInputStream(realPath);

        //3设置response的响应头
        //3.1设置响应头类型，content-type
        String mimeType = servletContext.getMimeType(fn1);
        response.setHeader("content-type",mimeType);

        //3.2设置响应头打开方式：content-disposition

        //解决中文文件名
        //1.获取user-agent请求头
        String agent = request.getHeader("user-agent");
        fn1 = DownLoadUtils.getFileName(agent, fn1);

        response.setHeader("content-disposition",
                "attachment;filename"+fn1);

        //4.将输入流的数据写出到输出流中
        ServletOutputStream sos = response.getOutputStream();
        byte[] buff=new byte[1024*8];
        int len=0;
        while((len=fis.read(buff))!=-1){
            sos.write(buff,0,len);
        }

        fis.close();


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }


}
