package CommonSrc;

import java.awt.*;
import java.io.FileInputStream;

/**
 * Created by stefstef on 4/6/2017.
 */
public class FontLoaderBase {
    private static java.awt.Font mainFont=null;
    private static void loadFont(){
        try{
            mainFont= Font.createFont(Font.TRUETYPE_FONT,new FileInputStream("Res/Fonts/Ubuntu-R.ttf"));
            mainFont=mainFont.deriveFont(Font.PLAIN,15);

        }catch (Exception e){
            System.out.println("[ERR]Font File not found...Continue with Default");
            mainFont = new Font(Font.SANS_SERIF,Font.PLAIN,15);
        }
    }
    public static java.awt.Font getFont(){
        if(FontLoaderBase.mainFont==null){
            FontLoaderBase.loadFont();
        }
        return FontLoaderBase.mainFont;
    }
}
