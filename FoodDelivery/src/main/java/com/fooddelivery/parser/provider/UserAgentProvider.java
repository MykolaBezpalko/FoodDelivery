package com.fooddelivery.parser.provider;

/**
 * Provider for random UserAgent string that using in connection to site
 * should be to reduce the suspicion of a dudos attack
 */
public class UserAgentProvider {

    /**
     * Method that provide random user agent (etc. "Chrome/6.5.263.6 Safari/687.2")
     *
     * @return String of user agent
     */
    public static String provideRandomAgent() {
        String chromeVersion = "Chrome/" + provideRandomDoubleString(1, 10) + "." + provideRandomDoubleString(100, 500);
        String safariVersion = "Safari/" + provideRandomDoubleString(100, 800);
        return chromeVersion + " " + safariVersion;
    }

    /**
     * Method that used to provide random double value between min and max in String format
     *
     * @param min minimum for random
     * @param max maximum for random
     * @return String of double number
     */
    private static String provideRandomDoubleString(int min, int max) {
        return String.format("%.1f", Math.random() * (max - min) + min);
    }
}
