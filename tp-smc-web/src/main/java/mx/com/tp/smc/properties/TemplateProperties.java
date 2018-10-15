package mx.com.tp.smc.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:template.properties"})
public interface TemplateProperties extends Config {

	@Key("menu.category")
	String category();	
}
	