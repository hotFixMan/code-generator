package org.own.generator.codegen.utils;

import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Interface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author passionateLittleBaby
 * @createDateTime 2019-01-09 11:11
 * @description Add a java document to the Java head
 */
public class JavaDocUtils {

    private static String docContent;

    private final static String NEXT_ROW_SYMBOL = "\n";

    private static DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public JavaDocUtils() {
        StringBuilder tempContent = new StringBuilder();
        tempContent.append("/**").append(NEXT_ROW_SYMBOL)
                   .append(" * Created by code generator on ")
                   .append(LocalDateTime.now().format(defaultFormatter)).append(NEXT_ROW_SYMBOL)
                   .append("*/");
        docContent = String.valueOf(tempContent);
    }

    public static void writeJavaHeader(Interface anInterface) {
        anInterface.addJavaDocLine(docContent);
    }

    public static void writeJavaHeader(InnerClass aClass) {
        aClass.addJavaDocLine(docContent);
    }

}
