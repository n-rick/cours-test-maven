package com.example.coursunitmaven;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Tester les méthodes de la classe Calcul")
@ExtendWith(MockitoExtension.class)
public class CalculTest {

    Calcul calcul;
    @Mock
    CalculService calculService;
//    CalculService calculService = x -> x * x;
//    CalculService calculService = new CalculService() {
//        @Override
//        public int carre(int x) {
//            return x * x;
//        }
//    };
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
        calcul = new Calcul(calculService);
        System.out.println("@BeforeEach");
    }
    @AfterEach
    void tearDown() throws Exception {
        System.out.println("@AfterEach");
    }

    @Test
    void testSommeCarre() {
        when(calculService.carre(3)).thenReturn(9);
        when(calculService.carre(2)).thenReturn(4);
        assertTrue(calcul.sommeCarre(2, 3) == 13);
//        verify(calculService, times(1)).carre(2);
//        verify(calculService, atLeast(1)).carre(3);
//        verify(calculService, never()).carre(4);
//        verify(calculService, times(2)).carre(Mockito.any(Integer.class));
        verify(calculService, times(2)).carre(anyInt());
        //verifyNoMoreInteractions(calculService);

    }
//    @Test
//    @DisabledOnJre(value= JRE.JAVA_17, disabledReason = "Test désactivé sur Java 17")
//    @DisabledOnOs(value = OS.LINUX, disabledReason = "Test désactivé sur Linux")
//    @DisabledIf("checkNight")
//    @RepeatedTest(5)
    @Order(2)
    @ParameterizedTest()
    @ValueSource(ints = { 2, 3, 7 })
    void testSomme(int i) {
        assertNotEquals(calcul.somme(0, i), 0);
//        if (calcul.somme(0, 0) != 0) {
//            fail("somme de deux nombres nuls");
//        }
//        if (calcul.somme(5, 2) != 7) {
//            fail("somme de deux nombres positifs");
//        }
//        if (calcul.somme(-5, -3) != -8) {
//            fail("somme de deux nombres négatifs");
//        }
//        if (calcul.somme(5, 0) != 5) {
//            fail("somme de deux nombres : un positif et un nul");
//        }
//        if (calcul.somme(-5, 0) != -5) {
//            fail("somme de deux nombres : un négatif et un nul");
//        }
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

    public boolean checkNight() {
        System.out.println(LocalTime.now().getHour() > 22 || LocalTime.now().getHour() < 6);
        return LocalTime.now().getHour() > 22 || LocalTime.now().getHour() < 6;
    }
}
