# Taller 7 – Interfaces gráficas con JFrame: Sistema de gestión de envíos

Universidad Libre – Facultad de Ingeniería – Programación 1 (Docente: Andrés Castaño)

## Estructura

```
src/main/java/com/gitb/juandaaguilera/Envios
├── Envio.java               interfaz común: calcularCosto() y getters
├── EnvioEstandar.java       $5.000/kg
├── EnvioExpress.java        $5.000/kg + $15.000 de recargo
├── EnvioInternacional.java  $12.000/kg + 10 % impuesto + 5 % seguro
├── Registros.java           ArrayList<Envio> con agregar / consultar
└── IGEnvios.java            JFrame: JComboBox, campos dinámicos, JTable
docs/Informe_Taller7.pdf     integrantes y funcionamiento de la interfaz
```

## Ejecución

- **NetBeans:** abrir la carpeta como proyecto Maven, clic derecho sobre `IGEnvios.java` → *Run File*.
- **Maven:** `mvn compile exec:java -Dexec.mainClass=com.gitb.juandaaguilera.Envios.IGEnvios`
- **Consola (JDK 17+):**
  ```
  javac -encoding UTF-8 -d out $(find src -name "*.java")
  java -cp out com.gitb.juandaaguilera.Envios.IGEnvios
  ```

## Entrega

La entrega está en la rama `entregable`. No deben existir commits posteriores a la fecha de entrega.
