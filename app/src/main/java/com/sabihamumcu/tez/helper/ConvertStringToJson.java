package com.sabihamumcu.tez.helper;

/**
 * Created by sabis on 3/10/2018.
 */

public class ConvertStringToJson {
    public static String convertToJson(String data){
        String reg= data.replaceAll("[^\\{\\},]+", "\"$0\"");
        String value=reg.replace("\"[\"{", "[{").replace("=","\"=\"").replace("\" ","\"").replace("}\"]\"","}]").replace("\"true\"", "true").replace("\"false\"", "false");
        System.out.println("value :: "+value);
        return value;
    }
}
