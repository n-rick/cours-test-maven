package com.example.coursunitmaven;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tester les méthodes de la classe Calcul")
public class CalculTest {

    Calcul calcul;
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("@BeforeAll");
    }
    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("@AfterAll");
    }
    @BeforeEach
    void setUp() throws Exception {
        calcul = new Calcul();
        System.out.println("@BeforeEach");
    }
    @AfterEach
    void tearDown() throws Exception {
        System.out.println("@AfterEach");
    }
    @DisabledOnJre(value= JRE.JAVA_17, disabledReason = "Test désactivé sur Java 17")
    @DisabledOnOs(value = OS.LINUX, disabledReason = "Test désactivé sur Linux")
    @DisabledIf("checkNight")
    @Test
    void testSomme() {
        if (calcul.somme(0, 0) != 0) {
            fail("somme de deux nombres nuls");
        }
        if (calcul.somme(5, 2) != 7) {
            fail("somme de deux nombres positifs");
        }
        if (calcul.somme(-5, -3) != -8) {
            fail("somme de deux nombres négatifs");
        }
        if (calcul.somme(5, 0) != 5) {
            fail("somme de deux nombres : un positif et un nul");
        }
        if (calcul.somme(-5, 0) != -5) {
            fail("somme de deux nombres : un négatif et un nul");
        }
    }

    @Test
    void testDivision() {
        assertAll("Premier bloc de test",
                () ->  assertFalse(calcul.division(6, 2) == 0, "Cas de 2 entiers +"),
                () -> assertEquals(-3, calcul.division(6, -2))
        );
        Exception exception = null;
        try {
            calcul.division(10, 0);
        } catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof ArithmeticException);
    }

    public boolean checkNight(){
        return LocalTime.now().isAfter(LocalTime.of(22,0));
    }
}
