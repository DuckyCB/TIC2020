# PROYECTO TIC2020	

## Universidad de Montevideo
	
## Laboratorio TIC 1 - 2do. Semestre 2020

Prof.: 
- D. Pereda

Grupo.: 
- P. Carrau
- R. D'Ambrosio
- G. González

# Instrucciones de uso

## Requisitos

- MySQL 8
- Java RE 11

## Iniciar el servidor

- Dentro de la consola de MySQL, correr el script dBInit.sql (copiando todo su contenido).
> Ese script inicializa la base
- Ejecutar el binario ServerG10.jar, se recomienda hacerlo desde la consola para terminar el proceso facilmente

#### Genera:

- UsuarioMySQL (Usuario: "grupo10_tic_2020", Contraseña: "grupo10_tic_2020")
> Acceso solamente a la base de datos proyecto_tic1v2
- Admin (Usuario: "admin1", Contraseña: "admin1")
> Puede crear nuevos usuarios de tienda y marca (Administración paralela a la aplicación, usando herramientas de desarrollo)
- Marca 1 [Levis] (Usuario: "levis", Contraseña: "levis")
> Asociada a la marca Levis (Gestiona productos)
- Marca 2 [Polo] (Usuario: "polo", Contraseña: "polo")
> Asociada a la marca Polo (Gestiona productos)
- Marca 3 [Tommy Hilfiger] (Usuario: "tommy", Contraseña: "tommy")
> Asociada a la marca Tommy Hilfiger (Gestiona productos)
- Tienda 1 (Usuario: "tienda1", Contraseña: "tienda1")
> Asociada a la marca Levis y Polo (gestiona stock)
- Tienda 2 (Usuario: "tienda2", Contraseña: "tienda2")
> Asociada a la marca Levis y Tommy Hilfiger (gestiona stock)


## Iniciar aplicación

- Ejecutar el binario TiendaG10.jar
> Es posible ingresar con los usuarios generados con el script (menos el de Admin) o  registrar usuarios
