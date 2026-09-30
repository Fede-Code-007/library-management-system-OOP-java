# 📚 Sistema de Gestión de Biblioteca

Sistema de gestión de biblioteca desarrollado como proyecto académico para aplicar los conceptos fundamentales de **Programación Orientada a Objetos (POO)** utilizando **Java** y **BlueJ**.

El sistema permite administrar libros, socios, préstamos y devoluciones mediante una interfaz gráfica desarrollada con **Java Swing**.

---

## 🛠️ Tecnologías utilizadas

* **Java**
* **Java Swing**
* **BlueJ**
* **Programación Orientada a Objetos (POO)**
* **ArrayList** para la gestión de colecciones

---

## 📋 Funcionalidades

El sistema permite:

* Registrar y eliminar socios.
* Registrar libros.
* Gestionar socios de tipo **Estudiante** y **Docente**.
* Buscar socios mediante su DNI.
* Consultar la cantidad de socios según su tipo.
* Listar socios registrados.
* Listar libros registrados.
* Registrar préstamos y devoluciones.
* Consultar qué socio tiene un determinado libro.
* Consultar préstamos vencidos.
* Identificar docentes responsables por préstamos vencidos.
* Ampliar los días de préstamo de determinados docentes.
* Controlar las restricciones de préstamo según el tipo de socio.

---

## 🧩 Estructura del proyecto

El proyecto se encuentra organizado en diferentes clases que representan las entidades y funcionalidades principales del sistema:

| Clase               | Descripción                                                                    |
| ------------------- | ------------------------------------------------------------------------------ |
| `Biblioteca`        | Gestiona libros, socios y las operaciones principales de la biblioteca.        |
| `Libro`             | Representa los libros disponibles y mantiene el registro de sus préstamos.     |
| `Prestamo`          | Gestiona la información relacionada con un préstamo, sus fechas y vencimiento. |
| `Socio`             | Clase abstracta que representa a los socios de la biblioteca.                  |
| `Estudiante`        | Especialización de `Socio` para los estudiantes.                               |
| `Docente`           | Especialización de `Socio` para los docentes.                                  |
| `GestionBiblioteca` | Implementa la interfaz gráfica y permite interactuar con el sistema.           |

---

## 🧠 Conceptos de Programación Orientada a Objetos

El proyecto fue desarrollado con el objetivo de aplicar distintos conceptos de POO, entre ellos:

* **Clases y objetos**
* **Encapsulamiento**
* **Herencia**
* **Abstracción**
* **Polimorfismo**
* **Asociación entre objetos**
* **Composición**
* **Clases abstractas**
* **Colecciones mediante `ArrayList`**

La relación de herencia se encuentra principalmente en las clases `Estudiante` y `Docente`, que extienden de la clase abstracta `Socio`.

---

## 🖥️ Interfaz gráfica

La interacción con el sistema se realiza mediante una interfaz gráfica desarrollada utilizando **Java Swing**.

Desde la interfaz principal se pueden realizar las operaciones de gestión de socios, libros y préstamos mediante botones y cuadros de diálogo.

La aplicación también utiliza una imagen (`logo.jpeg`) como parte de la interfaz gráfica.

---

## ▶️ Ejecución

### Requisitos

* **Java JDK**
* **BlueJ** (recomendado)
* Sistema operativo compatible con Java

### Ejecución mediante BlueJ

1. Abrir **BlueJ**.
2. Seleccionar **Project → Open Project**.
3. Abrir la carpeta del proyecto.
4. Compilar las clases del proyecto.
5. Ejecutar el método `main` de `GestionBiblioteca`.
6. Ingresar el nombre de la biblioteca cuando sea solicitado.

El archivo `package.bluej` permite conservar la configuración del proyecto para trabajar directamente desde BlueJ.

---

## 📁 Estructura de archivos

```text
.
├── biblioteca/
│   ├── Biblioteca.java
│   ├── Docente.java
│   ├── Estudiante.java
│   ├── Libro.java
│   ├── Prestamo.java
│   └── Socio.java
├── GestionBiblioteca.java
├── logo.jpeg
├── package.bluej
└── README.md
```

---

## 🎓 Contexto académico

Proyecto desarrollado como práctica académica de **Programación Orientada a Objetos**, utilizando Java y el entorno educativo **BlueJ**.

El objetivo principal fue implementar un sistema sencillo de gestión aplicando conceptos de diseño orientado a objetos, herencia, abstracción, polimorfismo y manejo de colecciones.

---

## 🤝 Equipo de desarrollo

Proyecto desarrollado de manera colaborativa como parte de una actividad académica.

* **Federico Pérez Ruiz** — [@Fede-Code-007](https://github.com/Fede-Code-007)
* **Santiago Pérez** — [@SefeeSannt](https://github.com/SefeeSannt)
* **Bruno Pérez** — [@brunoezeq](https://github.com/brunoezeq)
* **Nadia Ramirez** — [@NadiaRamirez2025-art](https://github.com/NadiaRamirez2025-art)
* **Ignacio Solis** 
* **Mariano Stemberg** 

---

## 📌 Estado del proyecto

🟢 **Proyecto académico finalizado**

El proyecto se conserva principalmente como evidencia de aprendizaje y aplicación de conceptos de **Programación Orientada a Objetos con Java**.
