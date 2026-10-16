# Sección 6: Análisis y Depuración de Código

## Caso de Estudio 1: NullPointerException

1. El compilador marca el error `variable estudiante1 might not have been initialized`, porque la variable local se declaró pero nunca se le asignó un valor. Si se inicializara con `null` (`Estudiante estudiante1 = null;`), compilaría y en ejecución lanzaría `NullPointerException`.
2. Porque `estudiante1` solo es una referencia y todavía no apunta a ningún objeto. Un método de instancia necesita un objeto real (con sus atributos en memoria) sobre el cual ejecutarse.
3. `estudiante1 = new Estudiante("E001", "Laura Martínez", 4.35, true);` (o `new Estudiante()` con el constructor por defecto).

## Caso de Estudio 2: Contexto estático vs. instancia

1. `non-static method encenderMotor() cannot be referenced from a static context`.
2. Un método `static` pertenece a la clase y se puede ejecutar sin crear ningún objeto, por lo que no existe un objeto actual (`this`). Un método de instancia necesita ese objeto y su estado, así que `main` no puede llamarlo directamente.
3. Alternativa 1: crear un objeto y llamarlo desde él:
   ```java
   GestorVehiculos gestor = new GestorVehiculos();
   gestor.encenderMotor();
   ```
   Alternativa 2: declarar el método como estático: `public static void encenderMotor()`.

## Caso de Estudio 3: Firma incorrecta del constructor

1. Un constructor no tiene tipo de retorno. Al agregar `void`, Java lo interpreta como un método normal que casualmente se llama igual que la clase, y no como un constructor.
2. Como no hay ningún constructor declarado, Java crea el constructor por defecto sin parámetros. Al llamar `new CuentaBancaria("A-101", 500.0)` no existe un constructor que reciba `(String, double)`, y el compilador indica `constructor CuentaBancaria in class CuentaBancaria cannot be applied to given types`.
3. ```java
   public CuentaBancaria(String numeroCuenta, double saldoInicial) {
       this.numeroCuenta = numeroCuenta;
       this.saldo = saldoInicial;
   }
   ```

## Caso de Estudio 4: Variable Shadowing

1. Se mostrará `Velocidad Máxima: 0 | Encendido: false`. En el constructor, `velocidadMaxima = velocidadMaxima;` asigna el parámetro a sí mismo y los atributos nunca se modifican, así que conservan sus valores por defecto (0 y false).
2. El sombreado ocurre cuando una variable local o parámetro tiene el mismo nombre que un atributo: dentro del método, el nombre se refiere al parámetro y "oculta" al atributo. Por eso la asignación afecta solo al parámetro y el atributo no cambia.
3. ```java
   public Vehiculo(int velocidadMaxima, boolean encendido) {
       this.velocidadMaxima = velocidadMaxima;
       this.encendido = encendido;
   }
   ```
