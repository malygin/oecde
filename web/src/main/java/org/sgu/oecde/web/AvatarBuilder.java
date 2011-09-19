package org.sgu.oecde.web;

import java.awt.*;
import java.io.IOException;
import java.awt.image.*;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.journal.EventType;
import org.sgu.oecde.journal.JournalService;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.FacesUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Класс, изменяющий размеры изображения.
 */
@Service
public class AvatarBuilder implements Serializable {

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
    @Resource
    private JournalService journalService;
    private static final long serialVersionUID = 179L;

    public void addAvatar(AbstractUser user) {
        HttpServletRequest req = FacesUtil.getRequest();
        HttpServletRequest multi = req;
        InputStream stream = null;
        try {
            Part p = (Part) req.getAttribute("avatar");
            if(p == null)
                return;
            stream = p.getInputStream();
            BufferedImage im;
            im = ImageIO.read(stream);
            if (im != null) {
                addAvatar(user, im, multi);
            }
        }catch (IOException ex) {
            Logger.getLogger(AvatarBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(stream!=null)
                    stream.close();
            } catch (IOException ex) {
                Logger.getLogger(AvatarBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        userDao.update(user);
        journalService.save(EventType.PHOTO_ADDITION, user);
    }

    private void addAvatar(AbstractUser u, BufferedImage im, HttpServletRequest multi) throws IOException {
        Assert.notNull(u);
        u.setLargePhoto(u.getUsername() + BIG_IMAGE_POSTFIX);
        u.setMediumPhoto(u.getUsername() + MEDIUM_IMAGE_POSTFIX);
        u.setSmallPhoto(u.getUsername() + SMALL_IMAGE_POSTFIX);
        createImage(u.getUsername() + BIG_IMAGE_POSTFIX, BIG_WIDTH, BIG_HEIGHT, im, multi);
        createImage(u.getUsername() + MEDIUM_IMAGE_POSTFIX, MEDIUM_WIDTH, MEDIUM_HEIGHT, im, multi);
        createImage(u.getUsername() + SMALL_IMAGE_POSTFIX, SMALL_WIDTH, SMALL_HEIGHT, im, multi);
    }

    private void createImage(String fname, int width, int height, BufferedImage im, HttpServletRequest multi) throws IOException {
        float k;
        k = (float) (im.getWidth() * 1.0 / width);
        int newHeight = (int) (im.getHeight() / k);
        BufferedImage img = resize(im, width, newHeight);
        //если изображение выше установленного размера - обрезать
        if (newHeight > height) {
            int cr = (newHeight - height) / 2;
            img = img.getSubimage(0, cr, width, newHeight - cr * 2);
        }
        File someFile = new File(multi.getServletContext().getRealPath("/resources/userFiles/avatars/" + fname));
//        if(!someFile.exists())
        someFile.createNewFile();
        ImageIO.write(img, "JPEG", someFile);
    }

    private BufferedImage resize(BufferedImage image, int width, int height) {
            int type = image.getType() == 0? BufferedImage.TYPE_INT_ARGB : image.getType();
            BufferedImage resizedImage = new BufferedImage(width, height, type);
            Graphics2D g = resizedImage.createGraphics();
            g.setComposite(AlphaComposite.Src); 
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(image, 0, 0, width, height, null);
            g.dispose();
            return resizedImage; 
         }
    }
