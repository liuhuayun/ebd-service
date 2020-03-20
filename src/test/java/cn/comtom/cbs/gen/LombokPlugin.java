package cn.comtom.cbs.gen;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import tk.mybatis.mapper.generator.MapperPlugin;

/**
 * 在生成model时使用lombok注解
 * @author william
 */
public class LombokPlugin extends MapperPlugin {

	@Override
	public boolean validate(List<String> list) {
		return true;
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		// 添加domain的import
		topLevelClass.addImportedType("lombok.Data");
		topLevelClass.addImportedType("lombok.Builder");
		topLevelClass.addImportedType("lombok.NoArgsConstructor");
		topLevelClass.addImportedType("lombok.AllArgsConstructor");
		topLevelClass.addImportedType("javax.persistence.*");

		// 添加domain的注解
		topLevelClass.addAnnotation("@Data");
		topLevelClass.addAnnotation("@Builder");
		topLevelClass.addAnnotation("@NoArgsConstructor");
		topLevelClass.addAnnotation("@AllArgsConstructor");

		// 添加domain的注释
		topLevelClass.addJavaDocLine("/**");
		topLevelClass.addJavaDocLine("* Created by Mybatis Generator on " + date2Str(new Date()));
		topLevelClass.addJavaDocLine("* @author William ");
		topLevelClass.addJavaDocLine("*/");

		return true;
	}


	@Override
	public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		// 不生成getter
		return false;
	}

	@Override
	public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		// 不生成setter
		return false;
	}

	private String date2Str(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);
	}

}