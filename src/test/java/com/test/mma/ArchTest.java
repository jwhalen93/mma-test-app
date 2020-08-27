package com.test.mma;

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
            .importPackages("com.test.mma");

        noClasses()
            .that()
            .resideInAnyPackage("com.test.mma.service..")
            .or()
            .resideInAnyPackage("com.test.mma.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.test.mma.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
