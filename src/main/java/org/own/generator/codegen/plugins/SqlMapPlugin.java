package org.own.generator.codegen.plugins;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.ColumnOverride;

import java.util.List;

/**
 * @author passionateLittleBaby
 * @createDateTime 2019-01-09 15:03
 * @description // append java type to mybatis resultMap
 */
@Log4j2
public class SqlMapPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean sqlMapResultMapWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        List<Element> columnElementList = element.getElements();
        List<ColumnOverride> columnOverrideList = introspectedTable.getTableConfiguration().getColumnOverrides();

        if (CollectionUtils.isEmpty(columnOverrideList)) {
            return true;
        }

        for (int i = 0; i < columnOverrideList.size(); i++) {

            ColumnOverride columnOverride = columnOverrideList.get(i);
            String overrideColumnName = columnOverride.getColumnName();

            for (int j = 0; j < columnElementList.size(); j++) {

                XmlElement columnElement = (XmlElement) columnElementList.get(j);
                Attribute targetAttribute = findAttributeFromXmlElementByAttributeName(columnElement, "column");
                if (!columnElement.getName().equals("result") || targetAttribute == null) {
                    continue;
                }

                String targetAttributeValue = targetAttribute.getValue();
                String columnOverrideJavaType = columnOverride.getJavaType();
                if (targetAttributeValue.equals(overrideColumnName)) {
                    columnElement.addAttribute(new Attribute("javaType", columnOverrideJavaType));
                    log.info("Found replace target! The {} field is appended java type [{}] in mybatis resultMap", targetAttributeValue, columnOverrideJavaType);
                }
            }
        }

        return true;
    }

    private Attribute findAttributeFromXmlElementByAttributeName(XmlElement columnElement, String attributeName) {
        List<Attribute> attributeList = columnElement.getAttributes();
        for (int k = 0; k < attributeList.size(); k++) {
            Attribute attribute = attributeList.get(k);
            if (attribute.getName().equals(attributeName)) {
                return attribute;
            }
        }

        return null;
    }
}
