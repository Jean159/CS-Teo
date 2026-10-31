# ADR-002: encapsular proveedores de pago

**Estado:** Propuesto · **Fecha:** 28/10/2026 · **Escenario:** E4. Numeración local posterior a ADR-001 (monolito modular en la guía); confirmar con el repositorio real antes de integrar.

### Contexto

La guía RutaColca usa una pasarela de pagos síncrona y propone añadir PayPal para turistas extranjeros sin cambiar el dominio en ≤ 3 días-persona. Hay que conservar el monolito modular y evitar que fallos de red o avisos repetidos confirmen pagos falsos.

### Alternativas consideradas

- **A:** puerto `ProveedorPago`, adaptador por pasarela y estados normalizados dentro de API Core.
- **B descartada:** incorporar llamadas PayPal y condicionales al servicio actual de Pagos.

### Decisión

Elegir A. El caso de uso selecciona un adaptador por una opción permitida y asigna una referencia estable a la reserva. El adaptador maneja solicitudes, credenciales, respuestas y avisos del proveedor; solo comunica estados normalizados a la aplicación. La confirmación exige verificar procedencia, monto, moneda, referencia y estado con el proveedor (conforme a su contrato) y aplicar una transición idempotente. Timeout y respuesta ambigua permanecen pendientes/indeterminados, nunca pagados. No se almacena número de tarjeta ni credenciales en el dominio.

### Consecuencias

- **Beneficios:** contrato del dominio estable, pruebas con dobles en memoria, sustitución localizada de clientes HTTPS.
- **Costo:** interfaces, mapeos, gestión de webhooks/secretos, conciliación y pruebas de contrato por proveedor; mayor esfuerzo inicial.
- **Riesgos:** diferencias entre pagos con redirección, captura y avisos; duplicados/reordenamiento; mitigación: referencias únicas, verificación de firmas, transacciones/idempotencia y revisión manual de estados ambiguos. Las API/credenciales reales y el esfuerzo de tres días-persona siguen por verificar.

