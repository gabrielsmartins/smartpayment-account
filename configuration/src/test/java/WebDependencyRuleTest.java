import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class WebDependencyRuleTest {

    private JavaClasses javaClasses;

    @BeforeEach
    public void setup(){
        this.javaClasses = new ClassFileImporter()
                .importPackages("br.gabrielsmartins.smartpayment..");
    }

    @Test
    @DisplayName("Web Layer Only Depends On Application Layer")
    public void webLayerOnlyDependsOnApplicationLayer(){
        classes()
                .that()
                .resideInAPackage("br.gabrielsmartins.smartpayment.application.adapters.web..")
                .should()
                .onlyDependOnClassesThat()
                .resideInAPackage("br.gabrielsmartins.smartpayment.application..")
                .check(javaClasses);
    }

    @Test
    @DisplayName("Validate Web Classes")
    public void validateWebClasses(){
        classes()
                .that()
                .haveNameMatching(".*WebMapper")
                .and()
                .haveNameMatching(".*WebService")
                .should()
                .onlyBeAccessed()
                .byClassesThat()
                .resideInAPackage(".br.gabrielsmartins.smartpayment.application.adapters.web.adapter..")
                .check(javaClasses);
    }


}
