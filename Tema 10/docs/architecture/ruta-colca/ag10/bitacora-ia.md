# E7 · Bitácora de IA (RutaColca, grupo 04)

**Transparencia:** los textos P1–P5 siguientes son **reconstrucciones adaptadas de las plantillas del Anexo B y de las tareas de AG-10** para que el equipo pueda repetir la consulta. No son transcripciones literales de mensajes enviados por el estudiante. Las correcciones reales recibidas en esta conversación figuran al final. La revisión humana se registra en la tabla de verificación del equipo.

## P1 · Cuestionar la priorización

> Actúa como evaluador de arquitectura con experiencia en ATAM. RutaColca opera en temporada alta con 2000 consultas simultáneas, caídas de pasarela y AUTOCOLCA, operador de otra agencia y pedido de PayPal. Con API Spring Boot monolítica, PostgreSQL y dos réplicas, revisa nuestra tabla I/D E1–E5 sin cambiar calificaciones. Señala datos faltantes y contradicciones posibles.

**Respuesta aprovechada:** E1, E2 y E5 pueden ser (A,A); pedir perfil de carga, duración/contratos de fallos y operación de cola. **Corregido/rechazado:** no presentar p95, 30 min ni 2 h como mediciones obtenidas; son metas de la guía. **Verificación:** se contrastaron literalmente los cinco escenarios con la tabla 4 de AG-10; faltan pruebas de carga y contratos reales. **Evidencia:** `utilidad.md`, secciones 2–3 del PDF.

## P2 · Comparar diseños con ADD

> Actúa como arquitecto y aplica los pasos 2–5 de ADD 3.0 al escenario E4 del grupo 04: añadir PayPal en ≤ 3 días-persona y 0 cambios en el dominio. Compara (A) puerto con adaptadores dentro del monolito y (B) condicional y cliente directo en servicio de pagos. Explica tácticas, patrón, costos, restricciones, riesgos y cambios C4; no agregues microservicios sin justificación.

**Respuesta aprovechada:** alternativa A, interfaz `ProveedorPago` y adaptadores por proveedor; alternativa B mantenida para comparar costo inicial. **Corregido/rechazado:** no garantizar el plazo sin código real ni contrato PayPal; evitar afirmaciones de webhook o integración ejecutada. **Verificación:** la tabla de alternativas indica relación con la medida y las restricciones AG-10. **Evidencia:** `alternativas.md`, sección 4 del PDF.

## P3 · Revisar el ADR

> Actúa como revisor de decisiones arquitectónicas. Examina ADR-002 para RutaColca/E4; exige contexto, alternativas, decisión, consecuencias negativas, criterio de medición, idempotencia y manejo de respuestas ambiguas. No cambies la decisión sin justificarlo.

**Respuesta aprovechada:** separar proveedores, verificar aviso y referencia antes de confirmar, registrar desventajas y costo de operación. **Corregido/rechazado:** no marcar ADR como aprobado, ni afirmar que PayPal se usa en producción o que 3 días-persona están cumplidos. **Verificación:** se revisó la plantilla de ADR del Anexo B; numeración por confirmar contra AG-08 editable. **Evidencia:** `docs/architecture/adr/ADR-002-proveedores-de-pago.md`.

## P4 · Evaluar el diseño E5 de referencia

> Actúa como evaluador ATAM. Dado el ADR-E5-REF de validación diferida de AUTOCOLCA y el árbol E1–E5, identifica riesgo, punto de sensibilidad y trade-off, marca incertidumbres y plantea cómo comprobarlas.

**Respuesta aprovechada:** riesgo de cobrar boleto inválido, sensibilidad del plazo de reintentos, trade-off entre esperar AUTOCOLCA y confirmar venta. **Corregido/rechazado:** contrastar cada hallazgo con los cinco escenarios y no limitarlo a E5. **Verificación:** se comprobó que las tres categorías de la tabla 10 de AG-10 aparecen en la evaluación. **Evidencia:** `docs/architecture/adr/ADR-E5-REF-validacion-autocolca.md`, `evaluacion-cruzada.md`.

## P5 · Cambios del modelo C4

> Actúa como revisor de Structurizr DSL. Desde la arquitectura RutaColca descrita en AG-10 y el ADR E4, señala elementos/relaciones mínimos: PayPal externo, puerto y adaptadores dentro de API, dinámica con relaciones declaradas. No inventes cambios en el despliegue y advierte si falta el DSL editable AG-08.

**Respuesta aprovechada:** vistas `ContextoE4`, `ContenedoresE4`, `ComponentesPagosE4`, `DinamicoPagoE4`. **Corregido/rechazado:** una vista dinámica de softwareSystem con componentes era inválida; se acotó a `rutacolca.api` y se separó la relación Web → API en la vista de contenedores. Los PNG de Graphviz no se describen como exportaciones de Structurizr. **Verificación técnica real:** Structurizr CLI v2025.11.09 validó el DSL sin errores y exportó cuatro `.puml`; `./run-tests.sh` pasó siete comprobaciones Java. **Pendiente:** cotejo con DSL AG-08 original y contratos externos.

## Correcciones reales del usuario registradas en esta conversación

1. Se pidió aplicar la guía al caso **RutaColca** y dejar de usar SGTD Moquegua; se creó un repositorio nuevo y se retiró el material AG-10 de Moquegua.
2. Se pidió que el informe tuviera estilo cercano a la guía, nombres de integrantes visibles, fondos y bordes de tablas legibles; se ajustó el PDF.
3. Se pidió incorporar entregables en repositorio, diagramas y explicación del C4; se añadieron rutas E1–E7, modelo, exportaciones y figuras.
4. Se incorporó el ADR-E5-REF y su evaluación cruzada.
5. Se solicitó esta revisión de escenarios, cuestionario, bitácora y estructura. **No se infiere aprobación académica** a partir de estas peticiones.

## Verificación del equipo

| Prompt | Verificador | Rol | Fecha |
|---|---|---|---|
| P1 | Auccacusi Conde Brayan Carlos | Facilitador/a ADD | 28/10/2026 |
| P2 | Anco Aymara Jean Pierre | Arquitecto/a | 28/10/2026 |
| P3 | Llacma Quispe Kevin Andree | Redactor/a | 28/10/2026 |
| P4 | Pari Charrez Dean Diego | Evaluador/a | 28/10/2026 |
| P5 | Anco Aymara Jean Pierre | Arquitecto/a | 28/10/2026 |

**Dato no compartido con IA:** no se proporcionaron claves PayPal, credenciales, datos personales de turistas ni código de producción. No existe enlace GitHub publicado en esta bitácora.
