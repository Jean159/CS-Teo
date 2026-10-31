# E4 · Evaluación de arquitectura

## 6. Evaluación de arquitectura (ATAM ligero)

**Procedencia:** la guía pide intercambiar el ADR con el grupo del escenario siguiente (E5). Para la evaluación cruzada se analiza el [ADR-E5-REF de validación con AUTOCOLCA](../../adr/ADR-E5-REF-validacion-autocolca.md). Propone persistir la validación pendiente, reintentar con límites y no confirmar el cobro antes de comprobar la validez del boleto.

| Tipo | Hallazgo E5-REF | Escenario afectado | Verificación / mitigación |
|---|---|---|---|
| Riesgo | Confirmar o capturar el pago mientras AUTOCOLCA está caído puede cobrar boletos inválidos. | E5, E2 | Mantener reserva sin cobro final hasta validación; probar caída, recuperación y estado ambiguo. |
| Punto de sensibilidad | Intervalo, límite y concurrencia de reintentos determinan si los pendientes se resuelven ≤ 2 h. | E5, E1 | Medir cola y p95 bajo carga ×10; backoff y cuota por proveedor. |
| Trade-off | Retener cobro protege E5 pero retrasa ventas y confirmación (E2); validar en línea limita rendimiento (E1). | E5 frente a E1/E2 | Definir expiración de reserva y métricas de tiempos, sin prometer disponibilidad no medida. |
| Riesgo adicional (?) | Si un callback de pago llega antes de la validación, puede quedar un estado inconsistente. | E5, E2 (?) | Modelar transición de estados y reordenamiento de eventos; confirmar políticas comerciales pendientes. |

**Revisión del ADR propio:** separar adaptadores favorece E4, pero llamadas externas y callbacks pueden añadir latencia a E1 o pérdida de confirmaciones a E2. Se requieren timeouts, idempotencia, trazabilidad sin datos sensibles y pruebas de contratos reales.
