# ADR-E5-REF · Validación diferida con AUTOCOLCA

**Estado:** Propuesto. **Escenario:** E5 de la guía AG-10. **Uso:** referencia para la evaluación cruzada de E4.

## Contexto

La guía describe llamadas síncronas a AUTOCOLCA. Durante temporada alta, AUTOCOLCA puede no responder cuando se intenta validar un boleto: E5 exige resolver reservas pendientes en ≤ 2 h y evitar cobros por boletos inválidos. Sin contrato de API ni políticas comerciales no es posible asegurar cómo se recuperan reservas durante la caída.

## Alternativas consideradas

- **A (elegida):** persistir una solicitud de validación `PENDIENTE`, procesarla mediante reintentos con backoff y no capturar ni confirmar el cobro hasta validar el boleto.
- **B (descartada):** reintentar la llamada síncrona indefinidamente en el request del usuario o cobrar antes de conocer la respuesta. B bloquea recursos y podría cobrar boletos inválidos.

## Decisión de referencia

Registrar un identificador estable por reserva/validación; después de un timeout dejar la reserva pendiente sin afirmar validez. Reintentar con límites, backoff y seguimiento de antigüedad; una respuesta válida permite continuar con el pago, una inválida cancela el intento. Una respuesta ambigua requiere revisión y no confirma automáticamente el boleto. Configurar expiración y límites solo después de medir carga y acordar política comercial.

## Consecuencias y preguntas abiertas

- **Beneficio:** reduce el riesgo de cobrar boletos inválidos y desacopla temporalmente el proceso de una caída externa.
- **Costos:** almacenamiento de estado, reintentos, monitoreo, alertas, conciliación e idempotencia. Una reserva puede permanecer sin confirmar y perjudicar la experiencia del turista o la medida E2.
- **Sensibilidades:** tasa de llegada en julio, tiempo de recuperación de AUTOCOLCA, tamaño/concurrencia de la cola, plazo de expiración.
- **Preguntas no resueltas:** ¿hay mecanismo de consulta posterior y límites de la API? ¿Se puede reservar plaza sin cobrar? ¿El pago tiene autorización separada de captura? ¿Quién resuelve solicitudes todavía ambiguas después de 2 h?

**Nota:** las tácticas se evalúan en el diseño; su implementación y medición requieren el contrato y las políticas reales de AUTOCOLCA.
