package pagos;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
/** Demostración de la selección y confirmación idempotente; sin red ni persistencia real. */
public final class ServicioPagos {
    private final Map<String, ProveedorPago> proveedores;
    private final Set<String> confirmadas = new HashSet<>();
    public ServicioPagos(Map<String, ProveedorPago> proveedores) { this.proveedores = Map.copyOf(proveedores); }
    public String iniciar(String proveedor, String reservaId, String referencia) {
        if (!proveedores.containsKey(proveedor) || referencia.isBlank()) throw new IllegalArgumentException("Proveedor o referencia inválida");
        return proveedores.get(proveedor).iniciar(reservaId, referencia);
    }
    /** El aviso debe haber sido autenticado externamente; el estado se reconcilia por consulta. */
    public boolean conciliar(String proveedor, String reservaId, String referencia, boolean avisoAutenticado) {
        if (!avisoAutenticado || !proveedores.containsKey(proveedor)) return false;
        try {
            if (proveedores.get(proveedor).consultar(referencia) != EstadoPago.CONFIRMADO) return false;
        } catch (RuntimeException falloExterno) { return false; }
        return confirmadas.add(reservaId + ":" + referencia);
    }
}
