package pagos;
import java.util.Map;
/** Doble didáctico: no es cliente oficial ni procesa pagos. */
public final class AdaptadorPayPalMemoria implements ProveedorPago {
    private final Map<String, EstadoPago> respuestas;
    public AdaptadorPayPalMemoria(Map<String, EstadoPago> respuestas) { this.respuestas = respuestas; }
    public String iniciar(String reservaId, String referencia) { return "PP-" + referencia; }
    public EstadoPago consultar(String referencia) { return respuestas.getOrDefault(referencia, EstadoPago.INDETERMINADO); }
}
