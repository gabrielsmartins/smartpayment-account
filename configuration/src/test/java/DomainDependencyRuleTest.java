import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class DomainDependencyRuleTest {

    private JavaClasses javaClasses;

    @BeforeEach
    public void setup(){
        this.javaClasses = new ClassFileImporter()
                .importPackages("br.gabrielsmartins.smartpayment..");
    }


    @Test
    @DisplayName("Domain Layer Does Not Depend On Others Layers")
    public void domainLayerDoesNotDependOnOthersLayer(){
        noClasses()
                .that()
                .resideInAPackage("br.gabrielsmartins.smartpayment.application..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("br.gabrielsmartins.smartpayment.application.adapters.persistence..")
                .andShould()
                .resideInAPackage("br.gabrielsmartins.smartpayment.application.adapters.web..")
                .check(javaClasses);

    }

    @Test
    @DisplayName("Domain Layer Does Not Depend On Application Layer")
    public void domainLayerDoesNotDependOnApplicationLayer(){
       noClasses()
                .that()
               .resideInAPackage("br.gabrielsmartins.smartpayment.application.domain..")
               .should()
               .dependOnClassesThat()
               .resideInAPackage("br.gabrielsmartins.smartpayment.application.service..")
               .andShould()
               .resideInAPackage("br.gabrielsmartins.smartpayment.application.ports..")
               .check(javaClasses);

    }

}
