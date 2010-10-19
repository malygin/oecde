package org.sgu.oecde.web;



import java.awt.*;
import java.io.IOException;
import java.awt.image.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.FacesUtil;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.FileUploadUtil;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.MultipartRequestWrapper;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.UploadFile;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Класс, изменяющий размеры изображения.
 */
@Service
public class AvatarBuilder implements Serializable{
    private final int BIG_WIDTH = 150;
    private final int SMALL_WIDTH = 56;
    private final int MEDIUM_WIDTH = 100;
    private final int BIG_HEIGHT = 200;
    private final int SMALL_HEIGHT = 80;
    private final int MEDIUM_HEIGHT = 140;
    private final String BIG_IMAGE_POSTFIX = "_big.jpg";
    private final String SMALL_IMAGE_POSTFIX = "_small.jpg";
    private final String MEDIUM_IMAGE_POSTFIX = "_medium.jpg";
    @Resource
    private IUpdateDao<AbstractUser> userDao;

    private static final long serialVersionUID = 179L;

    public void addAvatar(AbstractUser user){
        HttpServletRequest req = FacesUtil.getRequest();
        if(req instanceof MultipartRequestWrapper){
            MultipartRequestWrapper multi = (MultipartRequestWrapper)req;
            InputStream stream = multi.getStream("avatar");
            BufferedImage im;
            try {
                im = ImageIO.read(stream);
                if(im!=null)
                    addAvatar(user,im,multi);
            } catch (IOException ex) {
                Logger.getLogger(AvatarBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }
            userDao.update(user);
        }
    }

    private void addAvatar(AbstractUser u,BufferedImage im,MultipartRequestWrapper multi) throws IOException{
        Assert.notNull(u);
        u.setLargePhoto(u.getUsername() + BIG_IMAGE_POSTFIX);
        u.setMediumPhoto(u.getUsername() + MEDIUM_IMAGE_POSTFIX);
        u.setSmallPhoto(u.getUsername() + SMALL_IMAGE_POSTFIX);
        createImage(u.getUsername() + BIG_IMAGE_POSTFIX, BIG_WIDTH, BIG_HEIGHT, im, multi);
        createImage(u.getUsername() + MEDIUM_IMAGE_POSTFIX, MEDIUM_WIDTH, MEDIUM_HEIGHT, im, multi);
        createImage(u.getUsername() + SMALL_IMAGE_POSTFIX, SMALL_WIDTH, SMALL_HEIGHT, im, multi);
    }

   private void createImage(String fname, int width, int height,BufferedImage im,MultipartRequestWrapper multi) throws IOException{
        float k;
        k = (float) (im.getWidth() * 1.0 / width);
        int newHeight = (int) (im.getHeight() / k);
        BufferedImage img = resize(im, width, newHeight);
        //если изображение выше установленного размера - обрезать
        if (newHeight > height) {
            int cr = (newHeight - height) / 2;
            img = img.getSubimage(0, cr, width, newHeight - cr * 2);
        }
        File someFile = new File(multi.getRequest().getServletContext().getRealPath("/resources/userFiles/avatars/"+fname));
//        if(!someFile.exists())
            someFile.createNewFile();
        ImageIO.write(img, "JPEG", someFile);
   }

   private BufferedImage resize(BufferedImage img, int newW, int newH) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }
}
