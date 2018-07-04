package core.commonExtensions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormattor {

    public static String getCurrentTime()
    {
        return new SimpleDateFormat("mm-HH-ss").format(new Date());
    }

    public static  String getCurrentDate()
    {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }
}
