package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber",glue="SnehaBabaAutomation.stepDefinition",
monochrome=true,plugin= {"html:tagrget/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
