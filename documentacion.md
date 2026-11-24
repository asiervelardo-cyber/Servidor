# Recomendar Canciones — U4P3

Aplicación web hecha con Spring Boot para guardar canciones que me gustan y recomendarlas.
En una sola página se ve la lista de canciones guardadas y, debajo, un formulario para añadir otras nuevas.
Las canciones se guardan en una base de datos MariaDB, así que siguen ahí aunque se cierre la aplicación.

---

## 1. Dominio y atributos

El tema elegido es la **música**: cada registro es una **canción**, con los datos básicos para decidir si recomendarla.

| Atributo | Tipo | ¿Obligatorio? | Qué representa | Reglas |
|---|---|---|---|---|
| `id` | Número entero | Automático | Identificador de la canción. Lo genera la base de datos. | — |
| `titulo` | Texto | Sí | Nombre de la canción. | Máximo 120 caracteres |
| `autor` | Texto | Sí | Artista o grupo que la canta. | Máximo 120 caracteres |
| `tipo` | Texto | Sí | Si salió como sencillo, en un EP o en un álbum. | Solo `Sencillo`, `EP` o `Album` |
| `genero` | Texto | No | Uno o varios géneros musicales (Rock, Pop, Jazz…). | Solo géneros de la lista; se guardan separados por comas |
| `duracion` | Número entero | Sí | Lo que dura la canción. Se escribe como `3:35` y se guarda en segundos (`215`). | Formato minutos:segundos; no puede ser `0:00` |
| `valoracion` | Número decimal | Sí | Nota que le doy a la canción. | Entre 0 y 10 |
| `fecha` | Fecha | Sí | Fecha de lanzamiento. | Fecha válida |
| `recomendada` | Sí / No | Sí | Si la recomiendo o no. | Casilla marcada = sí |

---

## 2. Base de datos

- **Nombre de la base de datos:** `recomendar_canciones`
- **Programa usado:** MariaDB (incluido en XAMPP)

**Cómo prepararla:**

1. Abrir el panel de XAMPP y pulsar **Start** en *MySQL*.
2. Entrar en http://localhost/phpmyadmin y, en la pestaña **SQL**, crear la base de datos vacía:
   ```sql
   CREATE DATABASE recomendar_canciones CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```
3. **No hace falta crear la tabla.** La crea Hibernate solo, la primera vez que se arranca la aplicación.

**Cómo se configura la conexión** (archivo `src/main/resources/application.properties`):

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/recomendar_canciones
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:}
spring.jpa.hibernate.ddl-auto=update
```

- Por defecto se entra con el usuario `root` sin contraseña, que es como viene XAMPP.
- Si tu MariaDB tiene otro usuario o contraseña, no hay que cambiar el código. Basta con crear las variables de entorno `DB_USERNAME` y `DB_PASSWORD`, por ejemplo en IntelliJ: *Run → Edit Configurations → Environment variables*. Así ninguna contraseña real queda guardada en el proyecto ni en Git.
- `ddl-auto=update` crea la tabla si no existe y la mantiene si ya existe, **sin borrar los datos** al reiniciar.

---

## 3. Cómo iniciar la aplicación

1. Comprobar que **MySQL está en verde** en el panel de XAMPP.
2. Abrir el proyecto en IntelliJ y ejecutar la clase `U4EjercicioP3Application`, con el botón ▶.
3. Esperar a que la consola diga que la aplicación ha arrancado (`Started U4EjercicioP3Application`).
4. Abrir el navegador en **http://localhost:8080**

Para comprobar que los datos se guardan: añadir una canción, parar la aplicación, volver a arrancarla y recargar la página. La canción sigue en la lista.

---

## 4. Cómo está organizada la aplicación (capas)

El código está separado en partes, y cada una se encarga de una sola cosa:

| Capa | Clase | De qué se encarga |
|---|---|---|
| **Entidad** | `Modelo/Cancion` | Representa una canción tal como se guarda en la base de datos. Cada atributo es una columna de la tabla `canciones`. |
| **Repositorio** | `Repositorio/CancionRepositorio` | Habla con la base de datos: guardar canciones y sacar la lista ordenada por título. |
| **Servicio** | `Servicio/CancionServicio` | Tiene la lógica de la aplicación. Por ejemplo, convierte la duración `3:35` en segundos y junta los géneros elegidos antes de guardar. |
| **Controlador** | `Controlador/CancionController` | Recibe lo que pide el navegador: abrir la página o enviar el formulario. Pide los datos al servicio y elige qué página mostrar. Nunca usa el repositorio directamente. |
| **Formulario (DTO)** | `Dto/NuevaCancionFormulario` | Recoge lo que el usuario escribe en el formulario y comprueba que sea correcto: campos obligatorios, límites, formatos. |
| **Vista** | `templates/index.html` | La página que ve el usuario. Muestra la lista y el formulario. Solo se encarga de enseñar los datos, no los busca ni los guarda. |

**Recorrido de una petición:**
navegador → controlador → servicio → repositorio → base de datos, y vuelta con los datos hasta la vista.

### ¿Por qué separar la presentación de la lógica?

- **Orden:** cada cosa está en su sitio. Si falla el guardado, se mira el servicio o el repositorio. Si algo se ve mal, se mira el HTML.
- **Cambios más fáciles:** se puede cambiar el diseño de la página sin tocar la lógica, y al revés.
- **Reutilizar:** la misma lógica del servicio podría usarse desde otra página o desde una app distinta sin reescribirla.
- **Menos errores:** la página no puede tocar la base de datos por su cuenta. Todo pasa por el servicio.

### Papel de Spring MVC, el servicio y Thymeleaf

- **Spring MVC** recibe las peticiones del navegador y las manda al método correcto del controlador: `GET /` para ver la página y `POST /cancion` para guardar.
- **El servicio** es el punto intermedio donde se decide qué hacer con los datos.
- **Thymeleaf** construye la página HTML **en el servidor**, rellenándola con las canciones y los errores del formulario antes de enviarla al navegador.

### Tecnologías usadas para acceder a los datos

| Tecnología | Para qué sirve aquí |
|---|---|
| **MariaDB** | La base de datos donde se guardan las canciones. |
| **Driver de MariaDB (JDBC)** | El "traductor" que permite que Java se conecte a MariaDB. |
| **JPA / Hibernate** | Convierte la clase `Cancion` en una tabla y cada objeto en una fila, sin escribir SQL a mano. También crea y actualiza la tabla sola. |
| **Spring Data JPA** | Nos da el repositorio ya hecho, con métodos como `save()`. Solo hay que escribir la consulta para ordenar por título. |
| **HikariCP** | Mantiene abiertas unas cuantas conexiones con la base de datos para no tener que abrir una nueva cada vez. Viene incluido en Spring Boot. |

---

## 5. Principio o patrón de diseño aplicado

**Inyección de dependencias por constructor**

Las clases no crean ellas mismas las piezas que necesitan: se las da Spring.

- Dónde se ve:
  - `CancionController` recibe un `CancionServicio` en su constructor.
  - `CancionServicio` recibe un `CancionRepositorio` en su constructor.
- Ventaja: cada clase depende de la pieza que usa, pero no tiene que saber cómo se construye. Por eso es fácil cambiarla o probarla por separado.

También se ven otros dos patrones:

- **Repositorio** (`CancionRepositorio`): todo el acceso a la base de datos está en un solo sitio.
- **DTO** (`NuevaCancionFormulario`): el formulario usa su propia clase, distinta de la entidad. Así el usuario no puede enviar campos que no debe, como el `id`.