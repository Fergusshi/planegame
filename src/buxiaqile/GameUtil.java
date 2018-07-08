package buxiaqile;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

 

public class GameUtil {
           // 工具类最好将构造器私有化。
           private GameUtil() {         
           }

           public static Image getImage(String path) {
                     BufferedImage bi = null;
                     try {		
//                    	 System.out.println(path);
                    	 		
                    	 		if(GameUtil.class.getClassLoader()==null) {
                    	 			System.out.println("getclass is null");
                    	 		}
                    	 		else {
                    	 			System.out.println("getclass is not null");
                    	 		}
                    	 
                                URL u = GameUtil.class.getClassLoader().getResource(path);
                                
                                if(u==null) {
                                	System.out.println("url is null");
                                	}
                                
                                bi = ImageIO.read(u);
                     } catch (IOException e) {
                                e.printStackTrace();
                     }
                     return bi;
           }
}