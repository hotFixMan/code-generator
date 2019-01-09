package org.own.generator.codegen.plugins;

import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.own.generator.codegen.utils.JavaDocUtils;
import org.own.generator.codegen.utils.PluginUtils;

import java.util.List;

/**
 * @author passionateLittleBaby
 * @createDateTime 2019-01-09 10:40
 * @description add lombok annotation to the pojo
 * @see
 */
@Log4j2
public class PojoPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        JavaDocUtils.writeJavaHeader(topLevelClass);
        topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.*"));
        List<String> annotationList = Lists.newArrayList("@Data", "@Builder", "@NoArgsConstructor", "@AllArgsConstructor");
        PluginUtils.addAnnotations(topLevelClass, annotationList);
        log.debug(">>>>>>>>> Pojo added lombok annotations");
        return true;
    }


}
