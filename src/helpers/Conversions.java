package helpers;


public class Conversions {
    public static Integer booleanToInteger(boolean b){
        if(b == false)
            return 0;
        return 1;
    }

    public static boolean integerToBoolean(int i){
        if(i == 0)
            return false;
        return true;
    }

    public static int stringToInt(String s){
        switch (s){
            case "Hudson":
                return 0;
            case "Thalia":
                return 1;
        }

        return 0;
    }

    public static String seeBanner(boolean b){
        if(b == true)
            return "Banner; ";
        return "";
    }

    public static String seeSite(boolean b){
        if(b == true)
            return "Site; ";
        return "";
    }

    public static String seeSeo(boolean b){
        if(b == true)
            return "Seo; ";
        return "";
    }

    public static String seeGrap(boolean b){
        if(b == true)
            return "Peça Gráfica; ";
        return "";
    }
}
