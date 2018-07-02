package core.projectdata;

import java.io.File;

public class ProjectData {
    public static String baseURL = "https://www.ebay.in/";
    public static String projectPath = new File("").getAbsolutePath();
    public static String testDataFile = new File("").getAbsolutePath() + "//src//test//resources//TestData//TestData.properties";
    public static String screenshotFolderPath = new File("").getAbsolutePath() + "//test-screenshots";
    public static String ebayApkFile = new File("").getAbsolutePath() + "//src//test//resources//ebay-apk//ebay.apk";
}
