package mx.com.tp.smc.util;

import org.aeonbits.owner.ConfigFactory;

import mx.com.tp.smc.properties.TemplateProperties;

public class TemplateBuilder {

	private static TemplateProperties props = ConfigFactory.create(TemplateProperties.class);	

	public static String buildCategory(String arg0, String arg1) {
		String template = props.category();
		template = template.replace("$arg0", arg0);
		template = template.replace("$arg1", arg1);
		return template;
	}
		
}
