package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",  // Ruta a tus archivos de características
        glue = "steps",      // Paquete de tus step definitions
        plugin = {"pretty", "html:target/cucumber-reports.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        //tags = "@Success"                      // Etiqueta opcional
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
