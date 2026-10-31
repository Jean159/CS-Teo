package pagos;
/** Puerto independiente de SDK de proveedores. */
public interface ProveedorPago {
    String iniciar(String reservaId, String referencia);
    EstadoPago consultar(String referencia);
}
