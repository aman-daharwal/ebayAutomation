package core.commonExtensions;

import core.services.logger.Log;

import java.util.concurrent.TimeUnit;

public class Requirements {

    public static void waitInSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        }
        catch(InterruptedException ieException)
        {
            Log.error("Exception in waiting for "+seconds+" seconds \n"+ieException.getLocalizedMessage());
        }
    }
}
