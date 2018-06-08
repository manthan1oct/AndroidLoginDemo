package com.icarus.androidtest.util;

public class ConfigBuildType {

    public static ConfigBuildType INSTANCE;
    // 1 - Server Url
    // 2 - Client Server URL

    private final int APP_BUILD_TYPE = 1;
    private final String APP_VERSION = "1.0";
    private final String OS = "android";

    public static ConfigBuildType getInstance() {

        if (INSTANCE == null)
            INSTANCE = new ConfigBuildType();

        return INSTANCE;
    }

    /**
     * Returns the permissions that would be needed by facebook
     *
     * @return
     */

    public String getAppVersion() {
        return APP_VERSION;
    }

    public String getOS() {
        return OS;
    }

    public String GetBaseAPIURL() {
        if (APP_BUILD_TYPE == 1)
            return "http://private-222d3-homework5.apiary-mock.com/api/";
        else
            return "";
    }
}
