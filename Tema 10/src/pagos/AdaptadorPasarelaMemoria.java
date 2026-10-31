package pagos;
import java.util.Map;
/** Doble de la pasarela vigente; sin conexión externa. */
public final class AdaptadorPasarelaMemoria implements ProveedorPago {
    private final Map<String, EstadoPago> respuestas;
    public AdaptadorPasarelaMemoria(Map<String, EstadoPago> respuestas) { this.respuestas = respuestas; }
    public String iniciar(String reservaId, String referencia) { return "ACT-" + referencia; }
    public EstadoPago consultar(String referencia) { return respuestas.getOrDefault(referencia, EstadoPago.INDETERMINADO); }
}
