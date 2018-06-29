package core.services.screenshot;

import qaselenium.setup.ProjectData;
import qaselenium.extensions.TimeFormattor;
import core.services.logger.Log;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Screenshot {

    private static File newFolder = new File(ProjectData.screenshotFolderPath);

    private static boolean once = true;

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

            if(once) {
                NewFolderForScreenshots();
                once = false;
            }

            ImageIO.write(screenShot, "png", new File(newFolder + "\\"+TimeFormattor.getCurrentTime()+"-"+snapShotName+".png"));
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

    private static File NewFolderForScreenshots()
    {
        newFolder = new File(ProjectData.screenshotFolderPath + "\\" + TimeFormattor.getCurrentDate() + " " + TimeFormattor.getCurrentTime());
        newFolder.mkdirs();
        return newFolder;
    }
}
