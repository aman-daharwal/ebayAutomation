package services.screenshot;

import commons.ProjectData;
import services.logger.Log;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Screenshot {

    public static void TakeScreenshot(String snapShotName)
    {
        Log.info("Taking Screenshot for "+snapShotName);
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] screens = ge.getScreenDevices();

            Rectangle allScreenBounds = new Rectangle();
            for (GraphicsDevice screen : screens) {
                Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();

                allScreenBounds.width += screenBounds.width;
                allScreenBounds.height = Math.max(allScreenBounds.height, screenBounds.height);
            }

            Robot robot = new Robot();
            BufferedImage screenShot = robot.createScreenCapture(allScreenBounds);

            String currentTime = new SimpleDateFormat("mm-HH-ss").format(new Date());

            ImageIO.write(screenShot, "png", new File(ProjectData.screenshotFolderPath + "\\"+currentTime+"-"+snapShotName+".png"));
        }
        catch (AWTException awtExp)
        {
            Log.error("Unable to capture screenshot with exception "+awtExp.getLocalizedMessage()+"\nStacktrace : "+awtExp.getStackTrace());
        }
        catch (IOException ioExp)
        {
            Log.error("Unable to save screenshot with exception "+ioExp.getLocalizedMessage()+"\nStacktrace : "+ioExp.getStackTrace());
        }
        catch (Exception exp)
        {
            Log.error("Unable to capture screenshot with exception "+exp.getLocalizedMessage()+"\nStacktrace :"+exp.getStackTrace());
        }
    }
}
