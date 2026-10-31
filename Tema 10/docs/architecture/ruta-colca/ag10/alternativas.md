# E2 · Iteración ADD 3.0 y alternativas

## 4. Iteración ADD 3.0: dos alternativas

**Paso 1, entradas:** escenario E4, arquitectura y restricciones de la guía, efectos de E1–E5. **Paso 2, objetivo:** agregar PayPal en la medida de E4 sin cambiar el dominio. **Paso 3, elementos a refinar:** componente Pagos dentro de API Core y conexiones a la pasarela actual; Web del turista conserva el flujo de reserva. **Paso 4, conceptos:** encapsular variación y restringir dependencias con puertos/adaptadores; como contraste, selección condicional en servicio existente. **Paso 5, instanciación:** puerto `ProveedorPago`, caso de uso `IniciarPago`, adaptadores `PasarelaActual` y `PayPal`, selector por proveedor autorizado y referencia idempotente. Ambos devuelven resultado normalizado; el aviso externo debe autenticarse y conciliarse con la reserva antes de confirmar.

| Criterio | A: puerto + adaptadores internos (elegida) | B: cliente PayPal directo en servicio existente |
|---|---|---|
| Tácticas | Encapsular variación y limitar dependencias; confinar errores del proveedor. | Aislar el cambio mediante condicional en el servicio actual. |
| Patrón/tecnología | Puertos y adaptadores dentro del monolito Spring; cliente HTTPS por proveedor. | `if`/`switch` más SDK/cliente PayPal y pasarela vigente dentro del mismo servicio. |
| Relación con medida | Añadir adaptador y pruebas sin cambiar tipos/reglas de dominio; comprobar por diff. | Podría cumplir el plazo inicial si el dominio ya está aislado; cada proveedor vuelve a modificar el servicio. |
| Costo de desarrollo | Presupuesto tentativo: 1 día-persona extracción/puerto, 1 adaptador, 1 pruebas/revisión; **no medido**. | Estimación 1–2 días-persona para el primero; regresión y acoplamiento crecen con cada proveedor. |
| Operación | Sin VM nueva; requieren observabilidad, conciliación, seguridad de secretos y soporte de dos APIs. | Misma infraestructura; mayor riesgo de mezclar estados y errores de contratos distintos. |
| Riesgos | Puerto común puede esconder diferencias; webhook duplicado o falsificado, error de configuración. | Lógica de proveedores y callbacks mezclada; SDK puede filtrarse al dominio. |
| Restricciones | Respeta Java/Spring, PostgreSQL y monolito; depende de contratos reales para integrar. | Respeta topología; cumplir «0 cambios en dominio» depende del código existente. |

**Decisión:** A. B es atractiva por su costo inicial, pero no hace tan verificable el límite de dependencias. Las reservas no se confirman solo por la respuesta del navegador: se verifica el aviso/estado con el proveedor, la firma y la referencia; reintentos no deben producir dos capturas/confirmaciones. La introducción de PayPal no autoriza a afirmar que la pasarela ya está integrada.

