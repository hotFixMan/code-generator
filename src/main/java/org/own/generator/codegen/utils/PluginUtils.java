package org.own.generator.codegen.utils;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.dom.java.InnerClass;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author passionateLittleBaby
 * @createDateTime 2019-01-09 13:24
 * @description Provide some methods for customizing plugins.
 * @see
 */
public class PluginUtils {


    public static void addAnnotations(InnerClass innerClass, List<String> annotationList){
        List<String> removeDuplicateList = new ArrayList<>(new LinkedHashSet<>(annotationList));
        for (String annotation : removeDuplicateList) {
            innerClass.addAnnotation(StringUtils.prependIfMissing(annotation, "@", "@"));
        }
    }


}
