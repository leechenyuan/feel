package me.feelwith.common.db.mybatis;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * @See http://xxgblog.com/2016/05/06/mybatis-generator-mysql-pagination/
 * */
public class MapperNameExchanger extends PluginAdapter {

	@Override
	public boolean validate(List<String> list) {
		return true;
	}

    private static final String replaceClassName(String fullyName,String classSimpleName){
        if(fullyName == null ){
            return fullyName;
        }
        int index = fullyName.lastIndexOf(".");
        if(index == -1){
            return fullyName;
        }
        classSimpleName = covertClassName(classSimpleName);
        String packageName = fullyName.substring(0,index);
        return packageName + "." + classSimpleName;
    }
    //将a_b转成aB
    private static String covertClassName(String name){
        char [] namechars = name.toCharArray();
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<namechars.length;i++){
            char ch = namechars[i];
            if(ch == '_'){
                if(i < namechars.length - 1){
                    ch = Character.toUpperCase(namechars[i++]);
                }
            }
            builder.append(ch);
        }
        return builder.toString();
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        super.initialized(introspectedTable);
        introspectedTable.setExampleType(
                replaceClassName(introspectedTable.getExampleType(), introspectedTable.getTableConfiguration().getTableName())+"Example");
        introspectedTable.setBaseRecordType(
                replaceClassName(introspectedTable.getBaseRecordType(), introspectedTable.getTableConfiguration().getTableName())
                );
        introspectedTable.setMyBatis3JavaMapperType(
                replaceClassName(introspectedTable.getMyBatis3JavaMapperType(), introspectedTable.getTableConfiguration().getTableName()+"Mapper")
        );
        String mapperXmlFileName = introspectedTable.getMyBatis3XmlMapperFileName();
        introspectedTable.setMyBatis3XmlMapperFileName(mapperXmlFileName+"");
        introspectedTable.setFullyQualifiedTable(introspectedTable.getFullyQualifiedTable());
    }


   /**
	 * 为每个Example类添加limit和offset属性已经set、get方法
	 */
	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
//        setFileName(topLevelClass, introspectedTable.getTableConfiguration().getTableName());

		PrimitiveTypeWrapper integerWrapper = FullyQualifiedJavaType.getIntInstance().getPrimitiveTypeWrapper();

		Field limit = new Field();
		limit.setName("limit");
		limit.setVisibility(JavaVisibility.PRIVATE);
		limit.setType(integerWrapper);
		topLevelClass.addField(limit);

		Method setLimit = new Method();
		setLimit.setVisibility(JavaVisibility.PUBLIC);
		setLimit.setName("setLimit");
		setLimit.addParameter(new Parameter(integerWrapper, "limit"));
		setLimit.addBodyLine("this.limit = limit;");
		topLevelClass.addMethod(setLimit);

		Method getLimit = new Method();
		getLimit.setVisibility(JavaVisibility.PUBLIC);
		getLimit.setReturnType(integerWrapper);
		getLimit.setName("getLimit");
		getLimit.addBodyLine("return limit;");
		topLevelClass.addMethod(getLimit);

		Field offset = new Field();
		offset.setName("offset");
		offset.setVisibility(JavaVisibility.PRIVATE);
		offset.setType(integerWrapper);
		topLevelClass.addField(offset);

		Method setOffset = new Method();
		setOffset.setVisibility(JavaVisibility.PUBLIC);
		setOffset.setName("setOffset");
		setOffset.addParameter(new Parameter(integerWrapper, "offset"));
		setOffset.addBodyLine("this.offset = offset;");
		topLevelClass.addMethod(setOffset);

		Method getOffset = new Method();
		getOffset.setVisibility(JavaVisibility.PUBLIC);
		getOffset.setReturnType(integerWrapper);
		getOffset.setName("getOffset");
		getOffset.addBodyLine("return offset;");
		topLevelClass.addMethod(getOffset);

		return true;
	}

	/**
	 * 为Mapper.xml的selectByExample添加limit
	 */
	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		XmlElement ifLimitNotNullElement = new XmlElement("if");
		ifLimitNotNullElement.addAttribute(new Attribute("test", "limit != null"));

		XmlElement ifOffsetNotNullElement = new XmlElement("if");
		ifOffsetNotNullElement.addAttribute(new Attribute("test", "offset != null"));
		ifOffsetNotNullElement.addElement(new TextElement("limit ${offset}, ${limit}"));
		ifLimitNotNullElement.addElement(ifOffsetNotNullElement);

		XmlElement ifOffsetNullElement = new XmlElement("if");
		ifOffsetNullElement.addAttribute(new Attribute("test", "offset == null"));
		ifOffsetNullElement.addElement(new TextElement("limit ${limit}"));
		ifLimitNotNullElement.addElement(ifOffsetNullElement);

		element.addElement(ifLimitNotNullElement);

		return true;
	}
}
