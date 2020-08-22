import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class PersistenceDependencyRuleTest {

    private JavaClasses javaClasses;

    @BeforeEach
    public void setup(){
        this.javaClasses = new ClassFileImporter()
                .importPackages("br.gabrielsmartins.smartpayment..");
    }

    @Test
    @DisplayName("Persistence Layer Only Depends On Application Layer")
    public void persistenceLayerOnlyDependsOnApplicationLayer(){
        classes()
                .that()
                .resideInAPackage("br.gabrielsmartins.smartpayment.application.adapters.persistence..")
                .should()
                .onlyDependOnClassesThat()
                .resideInAPackage("br.gabrielsmartins.smartpayment.application..")
                .check(javaClasses);
    }

    @Test
    @DisplayName("Validate Persistence Classes")
    public void validatePersistenceClasses(){
        classes()
                .that()
                .haveNameMatching(".*PersistenceMapper")
                .and()
                .haveNameMatching(".*PersistenceService")
                .should()
                .onlyBeAccessed()
                .byClassesThat()
                .resideInAPackage(".br.gabrielsmartins.smartpayment.application.adapters.persistence.adapter..")
                .check(javaClasses);
    }


}
