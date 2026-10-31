package pagos;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ServicioPagosTest {
    private static void verificar(boolean condicion, String mensaje) {
        if (!condicion) throw new AssertionError(mensaje);
    }
    public static void main(String[] args) throws Exception {
        var pagos = new ServicioPagos(Map.of(
            "PAYPAL", new AdaptadorPayPalMemoria(Map.of("OK", EstadoPago.CONFIRMADO)),
            "ACTUAL", new AdaptadorPasarelaMemoria(Map.of("A", EstadoPago.CONFIRMADO))));
        verificar(pagos.iniciar("PAYPAL", "R1", "OK").equals("PP-OK"), "selección PayPal");
        verificar(pagos.iniciar("ACTUAL", "R2", "A").equals("ACT-A"), "pasarela vigente");
        verificar(!pagos.conciliar("PAYPAL", "R1", "OK", false), "aviso no autenticado");
        verificar(!pagos.conciliar("PAYPAL", "R1", "desconocido", true), "no confirmar respuesta ambigua");
        verificar(pagos.conciliar("PAYPAL", "R1", "OK", true), "primera confirmación");
        verificar(!pagos.conciliar("PAYPAL", "R1", "OK", true), "aviso repetido no confirma dos veces");
        var dominio = Files.readString(Path.of("src/pagos/EstadoPago.java"));
        verificar(!dominio.contains("PayPal") && !dominio.contains("SDK"), "dominio independiente");
        System.out.println("7 comprobaciones superadas (dobles en memoria, no API real)");
    }
}
