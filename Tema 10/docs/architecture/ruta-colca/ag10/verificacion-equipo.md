# Verificación del equipo

La guía exige que la IA proponga y el equipo decida/verifique. Este documento registra la verificación técnica y la revisión humana del equipo.

| Comprobación | Evidencia | Estado |
|---|---|---|
| Estructura E1–E7 y rutas de la tabla 11 | `README.md` y archivos del repositorio | Comprobado |
| Escenarios E1–E5 completos y E4 en seis partes | `utilidad.md`, informe §2–3 | Comprobado contra guía |
| Dos alternativas, restricciones y costos | `alternativas.md`, informe §4 | Redactado; costos no medidos |
| ADR con alternativa descartada y consecuencias | `ADR-002-proveedores-de-pago.md` | Propuesto; numeración por confirmar |
| Evaluación de riesgos, sensibilidad y trade-off | `evaluacion-cruzada.md` | ADR E5 de referencia y evaluación cruzada |
| DSL C4 válido y exportado | `workspace.dsl` + cuatro `.puml` y `.png` en la misma carpeta | Validado/exportado con CLI; cotejo AG-08 pendiente |
| Prueba de concepto Java | `src/`, `run-tests.sh` | 7 comprobaciones; sin API real |
| Bitácora IA con prompts adaptados | `bitacora-ia.md` | Revisada por el equipo; verificación registrada |
| Cuestionario de socialización | `cuestionario.md` | Respuestas de diseño, sin mediciones |

**Registro de revisión del equipo:**

| Integrante | Rol | Revisó | Fecha |
|---|---|---|---|
| Auccacusi Conde Brayan Carlos | Facilitador/a ADD | Sí | 28/10/2026 |
| Anco Aymara Jean Pierre | Arquitecto/a | Sí | 28/10/2026 |
| Llacma Quispe Kevin Andree | Redactor/a | Sí | 28/10/2026 |
| Pari Charrez Dean Diego | Evaluador/a | Sí | 28/10/2026 |

La revisión se registró el 28/10/2026. Las observaciones técnicas pendientes se indican en la tabla anterior.
