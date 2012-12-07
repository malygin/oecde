package org.sgu.oecde.web;

import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.users.AbstractUser;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: malygin
 * Date: 27.11.12
 * Time: 15:53
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(value = "/ImageServlet")
public class ImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
       // PrintWriter out = response.getWriter();
        String username=request.getParameter("user") ;
        String url= "";
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        IBasicDao<AbstractUser>  userDao = (IBasicDao<AbstractUser>)  wac.getBean("userDao");
        AbstractUser user =  new AbstractUser();
        user.setUsername(username);
        try{
            AbstractUser result = userDao.getByExample(user).get(0);
            if (result.getSmallPhoto()!=null) url = "http://oec-static.main.sgu.ru/storage/oec-j2ee6/ROOT/userFiles/avatars/"+result.getSmallPhoto();
            else url = "http://oec-static.main.sgu.ru/storage/oec-j2ee6/ROOT/userFiles/avatars/defaultAvatarSmall.jpg";

        }catch (Exception e){
            url = "http://oec-static.main.sgu.ru/storage/oec-j2ee6/ROOT/userFiles/avatars/defaultAvatarSmall.jpg";

        }

        URL url2 = new URL(url);
        BufferedImage image = ImageIO.read(url2);
        ImageIO.write(image, "jpeg", response.getOutputStream());


    }
}
