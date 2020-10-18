package testRunnerPackage;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features = "C:\\Users\\DELL\\eclipse-workspace\\Adidas_Test\\src\\test\\java\\Features" ,
		glue = {"stepDefinitionPackage"})

public class TestRunner_Cucumber {

	
	
	
}
