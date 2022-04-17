package cn.aseng.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/cC")
public class CC extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width=200;
        int height=100;

        //创建对象，在内存中的图片
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //美化图片
        //2.1填充背景色
        Graphics g = image.getGraphics();
        g.setColor(Color.pink);
        g.fillRect(0,0,width,height);

        //画边框
        g.setColor(Color.blue);
        g.drawRect(0,0,width-1,height-1);

        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrestuvwxyz0123456789";

        Random ran=new Random();
        for(int i=1;i<=4;i++){
            int index=ran.nextInt(str.length());
            char ch=str.charAt(index);
            g.drawString(ch+"",width/5*i,height/2);

        }

        g.setColor(Color.green);

        for(int i=0;i<10;i++){
            int x1=ran.nextInt(width);
            int x2=ran.nextInt(width);

            int y1=ran.nextInt(height);
            int y2=ran.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }

        ImageIO.write(image,"jpg",response.getOutputStream());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }


}
