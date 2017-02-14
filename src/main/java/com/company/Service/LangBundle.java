package com.company.Service;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by tyuly on 14.02.2017.
 */
public class LangBundle {
    public static Locale locale = new Locale("ru");
    public static ResourceBundle bundle = ResourceBundle.getBundle("message", locale);
}
