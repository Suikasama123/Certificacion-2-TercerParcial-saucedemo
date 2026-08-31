import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class EjecucionTotalTest {

    @Test
    @Order(1)
    public void test1CheckoutSinProductos() throws Exception {
        CheckoutWithoutProductsTest t = new CheckoutWithoutProductsTest();
        t.setup();
        try {
            t.checkoutWithoutAddingProductsToCart();
            System.out.println("test 1: Permite hacer checkout con carrito vacio sin validacion, avanza a checkout-step-one y muestra formulario sin productos ni error.-");
        } finally {
            t.tearDown();
        }
    }

    @Test
    @Order(2)
    public void test2CartResetSinRefresh() throws Exception {
        CartResetWithoutRefreshTest t = new CartResetWithoutRefreshTest();
        t.setup();
        try {
            t.verifyProductsRemovedAfterResetWithoutRefresh();
            System.out.println("test 2: Reset App State limpia el badge a vacio de inmediato pero los productos siguen visibles en el DOM sin refresh, estado inconsistente que solo se corrige al recargar.-");
        } finally {
            t.tearDown();
        }
    }

    @Test
    @Order(3)
    public void test3LogoutTresBacks() throws Exception {
        LogoutBackNavigationTest t = new LogoutBackNavigationTest();
        t.setup();
        try {
            t.verifyEpicSadfaceErrorsAfterLogoutAndBackNavigation();
            System.out.println("test 3: Tras agregar producto e ir al carrito y hacer logout, 3 backs consecutivos exponen Epic sadface para /cart.html, /checkout-step-one.html e /inventory.html, falla control de sesion en cliente.-");
        } finally {
            t.tearDown();
        }
    }

    @Test
    @Order(4)
    public void test4BotonResetCambiaEstado() throws Exception {
        ResetButtonStateTest t = new ResetButtonStateTest();
        t.setup();
        try {
            t.verifyButtonStateChangesAfterResetAppState();
            System.out.println("test 4: Reset App State cambia el estado del boton de Remove a Add to cart y limpia el badge, estado visual cambia sin refresh y se confirma tras recargar.-");
        } finally {
            t.tearDown();
        }
    }

    @Test
    @Order(5)
    public void test5CheckoutDatosAleatorios() throws Exception {
        CheckoutRandomDataTest t = new CheckoutRandomDataTest();
        t.setup();
        try {
            t.checkoutWithRandomUnlimitedDataAndNoPostalValidation();
            System.out.println("test 5: Checkout acepta firstName y lastName de mas de 200 caracteres y postal alfanumerico con simbolos sin limite ni validacion y avanza a checkout-step-two sin error.-");
        } finally {
            t.tearDown();
        }
    }
}
