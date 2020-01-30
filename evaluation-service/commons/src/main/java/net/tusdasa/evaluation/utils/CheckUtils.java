package net.tusdasa.evaluation.utils;

public class CheckUtils {

    public static boolean isString(String string){
        return string!=null && !"".equals(string) &&!string.isEmpty() && string.length()>=1;
    }

    public static boolean isPassword(String string){
        return string!=null && !"".equals(string) && !string.isEmpty() && string.length()<20 && string.length()>=6;
    }
    public static boolean isName(String string){
        return string!=null && !"".equals(string) &&!string.isEmpty() && string.length()<20 && string.length()>=2;
    }

    public static boolean isIntegerNumber(Integer number){
        return number!=null && number>0 && number < Integer.MAX_VALUE;
    }

    public static boolean isLongNumber(Long number){
        return number != null && number>0L && number < Long.MAX_VALUE;
    }

    public static boolean isPage(Integer page, Integer size){
        return page!=null && size!=null && page>=0 && size >=1;
    }

    public static boolean isToken(String token){
        return token!=null && !"".equals(token) && !token.isEmpty() && token.length()>100 && token.length()<250;
    }


}
