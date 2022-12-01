# Sistema de manejo de inventarios

El sistema permite tener control de las cantidades del inventario, de cualquier establecimiento. Proporciona restricciones en los cambios de precios,
ya que solo permite su modificación por el personal autorizado para esto.

## Implementacion de la solucion 🚀

Para la solución del problema planteado se nos ocurrió implementar un programa enlazado con una base de datos de MySQL, donde el manejo de información de los productos pudiera ser fácil e intuitiva, dicho programa se divide en dos tipos de usuarios, un administrador cuyas funciones en el programa sería la de poder crear nuevos usuarios o eliminarlos, como también modificar o crear en la base de datos nuevos productos y también poder desarrollar las funciones del otro tipo de usuario al cual llamaremos cajero, tiene este nombre porque su funcionalidad en el programa es la de registrar las compras hechas, realizar cuentas  para cobrar los productos que llevara el cliente, esta cuenta se actualizara cada vez que modifique, agregue o elimine un producto de la lista de compra, otra función de este usuario es el poder agregar a la base de datos unidades nuevas de productos que ya existan en dicha base, al confirmar la compra de los productos el programa se encargara de descontar las cantidades correspondientes de la compra realizada.
Las funciones de agregar unidades de un producto existente serán las mismas para los dos usuarios, aunque el administrador tendrá algunas funciones de más como poder cambiar de nombre el producto, valga aclarar que ambos usuarios solo podrán aumentar el número de unidades existentes y no el descontarlas, la única forma de descontar unidades es vendiendo, por lo cual solo al confirmar una compra las unidades de productos descenderán. La última funcionalidad de la ventana de cajero es el botón de búsqueda el cual también podrá utilizar el administrador en su ventana correspondiente, dicha funcionalidad le ayudará a los usuarios a buscar los datos de un producto específico, ingresando el nombre o el código, el programa realizará la búsqueda en la base de datos y devolverá los datos de existir el producto.
Ya explicado las funciones de cajero nos deja las funciones del administrador dentro del programa, como ya se dijo anteriormente, sus funciones dentro del programa son las de crear un usuario o eliminarlo, la de crear un usuario, agregara a la base un nuevo usuario con los datos ingresados por el administrador como lo son nick, documento y rol, este último ayudara a darle los permisos pertinentes al nuevo usuario.  Las funciones de modificar base de datos se componen de métodos para crear nuevo producto, modificar existencias y eliminar un producto, de igual forma podrá utilizar la función búsqueda para encontrar los datos de un producto específico. Para terminar la explicación de este usuario dentro del programa recordaremos que el administrador también podrá ejecutar las funciones del usuario cajero y el cajero solo tendrá acceso de algunas funciones de administrador y estas estarán un poco limitadas.

### Pre-requisitos 📋

Tener instalado MySQL en tu equipo y crear una base de datos, en ella tener dos tablas, una para usuarios y otra para productos.
para la vinculacion de MySQL y java re recomiendo ver el siguiente video  https://www.youtube.com/watch?v=e8g9eNnFpHQ.
Si deseas modificar las ventanas de interfaz que se desarrollaron con el sofware SceneBuilder es una herramienta muy util.

### Instalación 🔧

Siguendo el video de enlace MySQL y java, ademas clonando este repositorio tendras lo necesario para que el programa te funcione, sin embargo revisa ubucaciones de carpetas del proyecto.

### Hablando un poco del codigo

En todas las clases encontraras comentarios sobre lo que realiza cada funcion o metodo.
sin embargo te recomiendo documentarte sobre MySQL y las librerias de esta misma en java ya que es lo que mas se utilizara durante todo el proyecto.
El metodo Conectar es originario de la clase Hellocontroller y esta clase es padre de muchas otras clases, ya que la funcion conectar es usada constantemente para realizar requirimientos a MySQL desde java.
La Clase Crear_Usuario utiliza algunos requerimientos a la hora de crear un nuevo usuario, para esta ocasion no puede existir nick repetidos aunque la verdadera regla es que MySQl no nos deja crear dos items con un numero de identicacion igual, por lo tanto esta regla antecede a cualquier otra.
tambien se revisa si el nick ingresado comienza con numero lo cual decidimos que no fuera valido.
Como te dijimos anteriormente todo el codigo esta comentado y especifica la funcion de cada metodo.

## Construido con 🛠️

MySQL
Java Fx
SceneBuilder
Intellij ide

## Autores ✒️



* **Yefferson Luna** - *Programacion - diseño Interfaz* 
* **Fulanito Detal** - *Programacion - Documentacion* 

## Realizado Para

* **proyecto de la asignatura: Programacion en Java
* **Universidad Nacional de Colombia
* **Profesor:Juan Bernardo Gomez Mendoza
