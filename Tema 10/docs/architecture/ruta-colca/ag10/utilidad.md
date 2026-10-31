# E1 · Árbol de utilidad y escenario completo E4

## Escenarios de calidad de la guía AG-10 (tabla 4)

| ID | Atributo | Situación | Medida esperada |
|---|---|---|---|
| E1 | Rendimiento | 2000 turistas consultan la disponibilidad de salidas al mismo tiempo. | p95 ≤ 1,5 s. |
| E2 | Disponibilidad | La pasarela de pagos no responde durante 15 min. | 0 reservas pagadas perdidas; confirmación ≤ 30 min tras la recuperación. |
| E3 | Seguridad | Un operador intenta modificar un tour de otra agencia. | 100 % de intentos bloqueados y auditados. |
| E4 | Modificabilidad | El consorcio pide aceptar PayPal. | ≤ 3 días-persona; 0 cambios en el dominio. |
| E5 | Interoperabilidad | AUTOCOLCA no responde al validar boletos. | Reservas pendientes validadas ≤ 2 h; 0 cobros a boletos inválidos. |

Las cifras son metas del enunciado, no resultados obtenidos por el grupo. Solo E4 se desarrolla en seis partes por ser el escenario asignado al grupo 04.

## 2. Parte A: árbol de utilidad (E1–E5)

Raíz: utilidad de RutaColca en temporada alta. **I** = importancia de negocio; **D** = dificultad técnica estimada con la arquitectura de la guía. A = alta, M = media, B = baja. No se confunden estas valoraciones con pruebas de carga.

| Escenario de la guía | I | D | Justificación |
|---|:---:|:---:|---|
| **E1 Rendimiento:** 2000 turistas consultan salidas a la vez; p95 de latencia ≤ 1,5 s. | A | A | Pico ×10 y llamadas síncronas pueden saturar réplicas o base; faltan perfiles de carga. |
| **E2 Disponibilidad:** pasarela no responde 15 min; 0 reservas pagadas perdidas y confirmación ≤ 30 min tras recuperarse. | A | A | Requiere correlación, persistencia e idempotencia; un simple reintento HTTP no basta. |
| **E3 Seguridad:** operador intenta cambiar tour de otra agencia; 100 % de intentos bloqueados y auditados. | A | M | Autenticación no implica autorización por agencia y tour; faltan pruebas de acceso. |
| **E4 Modificabilidad:** consorcio pide aceptar PayPal; ≤ 3 días-persona y 0 cambios en el dominio. | M | M | Depende de si pagos está aislado y de los contratos reales de proveedor/webhooks. |
| **E5 Interoperabilidad:** AUTOCOLCA no responde al validar; pendientes resueltas ≤ 2 h y 0 cobros por boletos inválidos. | A | A | La integración síncrona existente es sensible a la caída externa; exige distinguir validación pendiente de válida. |

**Priorización:** E1, E2 y E5 tienen riesgo estimado (A,A). El ejercicio del grupo 04 desarrolla E4 por asignación, sin empeorar E1–E3/E5. Para confirmar estimaciones faltan datos de concurrencia, contratos de pagos y AUTOCOLCA y perfil de autorización de agencias.

## 3. Escenario E4 completo: aceptar PayPal

| Parte | Definición verificable |
|---|---|
| Fuente | Consorcio responsable de RutaColca. |
| Estímulo | Solicita aceptar PayPal como opción de pago para turistas extranjeros, además de la pasarela vigente. |
| Artefacto | Módulo de pagos en la API Core y conectores HTTPS con proveedores. |
| Entorno | Temporada alta; servicio en operación; Java 21/Spring Boot y PostgreSQL 16, sin nuevos servicios desplegables. |
| Respuesta | La API inicia el pago con el proveedor elegido, recibe su identificador, verifica la confirmación por canal autenticado y actualiza la reserva una sola vez; un timeout o respuesta desconocida **no** equivale a pago exitoso. El dominio usa una operación y estados comunes, no SDK ni modelos PayPal. |
| Medida | Incorporar el proveedor, su configuración y pruebas en **≤ 3 días-persona**, contabilizados con horas de trabajo/revisión, con **0 cambios en paquetes de dominio**, verificados por diff y regla automática de dependencias. Pruebas de contrato y de duplicación de confirmaciones deben pasar antes de aceptar el cambio. |

El límite de tres días-persona es una **meta del enunciado**, no un resultado medido. El acuerdo comercial, credenciales, sandbox, webhooks y contrato del proveedor vigente no están disponibles aquí.
