package org.lhq.orm.builder.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.lhq.orm.binding.MappedStatement;
import org.lhq.orm.session.Configuration;
import org.xml.sax.InputSource;

import java.io.Reader;
import java.util.List;

public class XMLConfigBuilder extends BaseBuilder{
    private Element root;

    public XMLConfigBuilder(Reader reader) {
        // 1. 调用父类初始化Configuration
        super(new Configuration());
        // 2. dom4j 处理 xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public Configuration parse() {
        try {
            // 解析映射器
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }

    private void mapperElement(Element mappers) throws Exception {
        List<Element> mapperList = mappers.elements("mapper");
        for (Element e : mapperList) {
            // 解析处理，具体参照源码
            MappedStatement mappedStatement = new MappedStatement();
            // 添加解析 SQL
            configuration.addMappedStatement(mappedStatement);
        }

        // 注册Mapper映射器
        configuration.addMapper( null);
    }
}

