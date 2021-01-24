package com.sudhir.APIRestTestNG.TestUtils;

//Imported from OWNER API
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ 
	"file:src/test/resources/propertyFiles/config.properties" 
	})

//extended config is from Owner API
public interface ConfigProperty extends Config {
	
	@Key("baseURI")
	String getBaseURI();
	
	@Key("basePath")
	String getBasePath();
	
	@Key("secreatKey")
	String getSecreatKey();
	
	@Key("testReportName")
	String getTestReportName();
	
	@Key("testReportPath")
	String getTestReportPath();
	
}
