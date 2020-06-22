package com.potensas.zartacms;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.potensas.zartacms");

        noClasses()
            .that()
            .resideInAnyPackage("com.potensas.zartacms.service..")
            .or()
            .resideInAnyPackage("com.potensas.zartacms.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.potensas.zartacms.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
