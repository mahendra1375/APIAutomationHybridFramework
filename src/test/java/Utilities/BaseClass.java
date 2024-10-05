package Utilities;

import java.util.ResourceBundle;

public class BaseClass {

    public static ResourceBundle getConfigData(){
        ResourceBundle rs=ResourceBundle.getBundle("config");
        return rs;
    }
}
