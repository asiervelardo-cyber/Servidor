Aplicación con un catálogo de materiales y un formulario de solicitud de préstamo. Está hecha con Spring Boot y Thymeleaf. No guarda nada: no hay base de datos, sesiones ni envío de correo.

Para ejecutarla: mvn spring-boot:run (o desde el IDE) y abrir http://localhost:8080/

PÁGINAS

/ muestra el catálogo con los tres materiales. /materiales/{codigo} muestra la ficha de un material. Admite el parámetro opcional turno (por ejemplo /materiales/MICRO?turno=tarde); si no se indica, se muestra "general". Si el código no existe, devuelve un error 404. /solicitud/nueva muestra el formulario de préstamo. /solicitudes recibe el formulario. Si hay errores, vuelve a mostrarlo con los mensajes junto a cada campo; si todo es correcto, muestra un resumen.

PARÁMETROS, VARIABLES Y PROPIEDADES

Parámetros de método: solo existen mientras se ejecuta el método. Por ejemplo, codigo y turno en la ficha, o solicitud y resultado al enviar el formulario.

Variables locales: se declaran dentro de un método y desaparecen cuando termina. Por ejemplo, material, calculo, dias y diasUnidad. No se comparten entre peticiones.

Propiedades del DTO: son los campos de SolicitudForm (nombre, correo, material, unidades, fechaRecogida, fechaDevolucion, motivo y aceptaNormas). Se crean con cada envío del formulario y Spring las rellena con los datos escritos por el usuario.

El catálogo (CATALOGO) es una constante de la clase, común a todas las peticiones y solo de lectura.

ATRIBUTOS DEL MODELO EN LA VISTA

El controlador pasa datos a la plantilla mediante el modelo, solo durante esa petición:

materiales: la plantilla lo recorre con th:each para pintar el catálogo y las opciones del selector. material y turno: se muestran en la ficha con th:text. solicitud: es el objeto del formulario (th:object y th:field) y, después, el origen de los datos del resumen. materialNombre, dias y diasUnidad: se muestran en el resumen. Con dias se decide con th:if si el préstamo es de un día o de varios.

Los datos escritos por el usuario se muestran siempre con th:text, nunca con th:utext. Los enlaces se generan con th:href y th:action.

VALIDACIÓN

Las reglas de un solo campo (obligatorio, longitud, correo válido, unidades entre 1 y 3, fechas de hoy o futuras, casilla marcada) están en el DTO con anotaciones, y el controlador las activa con @Valid.

Las reglas que comparan datos están en el controlador y añaden el error al campo con BindingResult: la devolución no puede ser anterior a la recogida, y el material debe existir en el catálogo.

Si se escribe algo que no se puede convertir (unidades que no son un número, fecha mal escrita), el formulario se vuelve a mostrar con un mensaje comprensible, definido en messages.properties.

Los atributos de HTML (required, min, maxlength) solo ayudan en el navegador. El formulario lleva novalidate para que siempre decida el servidor.

ERRORES

Hay tres páginas en templates/error: 404.html, 4xx.html y 5xx.html. Spring Boot elige la más específica, así que un 404 usa 404.html. No se muestran trazas ni detalles internos.