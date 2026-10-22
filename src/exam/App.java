package exam;

import java.util.Scanner;

public class App {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[]args) {
	
		GestionProductos gp = new GestionProductos();
		GestionContactos gc = new GestionContactos();
		int op;
		do {
		System.out.println("Menu de opciones");
		System.out.println("1: Escribir producto: ");
		System.out.println("2: Leer producto");
		System.out.println("3: Actualizar precio");
		System.out.println("4: Mostrar todos los productos");
		System.out.println("5: Agregar Contactos");
		System.out.println("6: Listar contactos");
		System.out.println("7: Eliminar contacto");
		System.out.println("0: salir");
		System.out.println("Elige una opcion: ");
		op = sc.nextInt();
		
		switch (op) {
		
		case 1:
			System.out.println("Codigo del producto: ");
				int codigo = sc.nextInt();	
				sc.nextLine();
			System.out.println("nombre del producto: ");
				String nom = sc.nextLine();
			System.out.println("Precio del producto: ");
				double precio = sc.nextDouble();
					Productos p = new Productos(codigo, nom, precio);
						gp.nProducto(p);
			break;
		case 2:
			System.out.println("Codigo del producto: ");
				int cod = sc.nextInt();
					gp.lProducto(cod);
			break;
		case 3:
			System.out.println("Codigo del producto a actualizar");
				int codi = sc.nextInt();
				sc.nextLine();
			System.out.println("Nuevo precio del producto: ");
				int preci = sc.nextInt();
					gp.aProducto(codi, preci);
			break;
		case 4:
			gp.mCProductos();
			break;
		case 5:
			System.out.println("Id del contacto: ");
				int id = sc.nextInt();
				sc.nextLine();
				if(gc.bId(id) != null) {
					System.out.println("EL id "+ id+(" ya esta en uso"));
					break;
				}
			System.out.println("Nombre del contacto: ");
				String nombre = sc.nextLine();
			System.out.println("Nº de telefono: ");
				long tlf = sc.nextLong();
					Contactos c = new Contactos(id, nombre, tlf);
					gc.nContacto(c);
			break;
		case 6:
			gc.mContactos();
			break;
		case 7:
			System.out.println("id del contacto a eliminar: ");
			int idE = sc.nextInt();
			gc.eContactos(idE);
			break;
		case 0:
			System.out.println("Adios");
		}
		
		}while (op != 0);
			
	}

}

/*
 * =====================================================================
 *  EJERCICIO DE REFUERZO · UT1 TRABAJO CON FICHEROS · 2º DAM
 *  Acceso a Datos · Colegio Santa Gema Galgani        (autoevaluación: 5 puntos)
 * =====================================================================
 *  Este ejercicio se plantea igual que la prueba práctica, pero con otros
 *  datos:
 *    - Parte 1. Inventario de productos con RandomAccessFile (como el
 *      Registro de estudiantes).
 *    - Parte 2. Agenda de contactos con serialización (como la Gestión de
 *      biblioteca).
 *    - Parte 3. Programa completo con menú.
 *
 *  INSTRUCCIONES
 *  - Crea un proyecto Java en Eclipse. Tú decides cómo organizar el código:
 *    qué clases crear, qué métodos, con qué nombres y parámetros.
 *  - Respeta los REQUISITOS FIJOS de abajo: son los mismos tipos de requisitos
 *    que tendrás en el examen.
 *  - Al final tienes una BATERÍA DE PRUEBAS con los resultados que debes
 *    obtener. Úsala para comprobar tu solución.
 *  - Consejo: intenta hacerlo sin mirar los proyectos de clase. Si te atascas,
 *    repasa el informe de indagación (streams, buffer, serialización).
 *
 * =====================================================================
 *  REQUISITOS FIJOS (obligatorios)
 * =====================================================================
 *  R1. Productos: fichero "productos.dat", gestionado con RandomAccessFile.
 *      Registro de longitud fija con estos campos, en este orden:
 *        - codigo -> int
 *        - nombre -> exactamente 25 caracteres, escritos con writeChar()
 *        - precio -> double  (¡OJO! un double ocupa 8 bytes, no 4)
 *      El registro de cada producto empieza en la posición
 *      codigo * TAM_REGISTRO, donde TAM_REGISTRO es el tamaño del registro en
 *      bytes (calcúlalo tú).
 *  R2. Contactos: fichero "contactos.dat". La lista COMPLETA de contactos se
 *      guarda como un único objeto con serialización y se carga entera al
 *      arrancar.
 *  R3. Cada contacto tiene: id (int), nombre, teléfono y un booleano eliminado.
 *  R4. Menú por consola que se repite hasta elegir 0, con estas opciones y
 *      este mismo número:
 *        1. Escribir producto
 *        2. Leer producto
 *        3. Actualizar precio
 *        4. Mostrar todos los productos
 *        5. Agregar contacto
 *        6. Listar contactos
 *        7. Eliminar contacto
 *        0. Salir
 *  R5. Los mensajes entre comillas de este enunciado deben aparecer tal cual.
 *  R6. Ni un error de fichero ni una letra tecleada donde se espera un número
 *      deben cerrar el programa.
 *
 * =====================================================================
 *  PARTE 1. INVENTARIO DE PRODUCTOS (ACCESO ALEATORIO) ......... 2,25 puntos
 * =====================================================================
 *  1.1 Escribir un producto ...................................... 0,75 puntos
 *      - Se piden código, nombre y precio, y se escribe el registro en su
 *        posición.
 *      - El nombre debe ocupar exactamente 25 caracteres: si es más largo se
 *        recorta y si es más corto se rellena con espacios (usa StringBuilder).
 *      - Después de escribir, muestra:
 *        "Producto guardado. Longitud del fichero: N bytes"
 *
 *  1.2 Leer un producto .......................................... 0,5 puntos
 *      - Se pide el código. Si el registro queda fuera del fichero, o es un
 *        hueco (el código leído es 0), muestra "Producto no encontrado."
 *      - Si existe, muestra: "Producto 2: Ratón inalámbrico · 12.99 €"
 *        (el nombre sin los espacios del final).
 *
 *  1.3 Actualizar el precio ...................................... 0,5 puntos
 *      - Se pide el código y el nuevo precio, y se sobrescribe SOLO el precio
 *        (¿cuántos bytes hay delante del precio dentro del registro?).
 *      - Si el registro queda fuera del fichero o es un hueco:
 *        "Producto no encontrado."
 *      - Si se actualiza: "Precio actualizado con éxito"
 *
 *  1.4 Mostrar todos los productos ............................... 0,5 puntos
 *      - Recorre el fichero completo de principio a fin y muestra cada producto
 *        con el formato de 1.2. Los huecos NO se muestran.
 *
 * =====================================================================
 *  PARTE 2. AGENDA DE CONTACTOS (SERIALIZACIÓN) ................ 2,25 puntos
 * =====================================================================
 *  2.1 Contacto serializable ..................................... 0,5 puntos
 *      - El contacto se puede serializar de forma controlada entre versiones.
 *      - Un contacto nuevo nunca está eliminado.
 *      - Se muestra así: "Contacto 1: Ana López · 600111222"
 *
 *  2.2 Cargar los contactos al arrancar .......................... 0,5 puntos
 *      - Si contactos.dat todavía no existe, el programa arranca sin error y
 *        con la agenda vacía.
 *      - Los recursos se cierran siempre y se tratan TODAS las excepciones
 *        comprobadas de la lectura (¡son dos!).
 *
 *  2.3 Guardar los contactos ..................................... 0,5 puntos
 *      - Tras cada cambio, la lista completa se vuelve a guardar en el fichero.
 *      - Al cerrar el programa y volver a abrirlo, los contactos siguen ahí.
 *
 *  2.4 Agregar un contacto ....................................... 0,35 puntos
 *      - Se piden id, nombre y teléfono.
 *      - Si se agrega: "Contacto agregado"
 *      - Si ya existe un contacto activo con ese id:
 *        "El contacto con el ID 1 ya existe"
 *
 *  2.5 Listar contactos .......................................... 0,2 puntos
 *      - Muestra los contactos que no estén eliminados.
 *      - Si no hay ninguno: "La agenda está vacía"
 *
 *  2.6 Eliminar un contacto (borrado lógico) ..................... 0,2 puntos
 *      - El contacto NO se quita de la lista: se marca como eliminado y se
 *        guarda el cambio.
 *      - Si se elimina: "Contacto eliminado"
 *      - Si no existe: "No se puede eliminar el contacto"
 *
 * =====================================================================
 *  PARTE 3. PROGRAMA COMPLETO .................................. 0,5 puntos
 * =====================================================================
 *  - Menú del requisito R4 funcionando ................................ 0,25
 *  - Excepciones tratadas y requisito R6 ............................... 0,15
 *  - Código organizado en clases y métodos con sentido, y comentado .... 0,10
 *
 * =====================================================================
 *  BATERÍA DE PRUEBAS (borra productos.dat y contactos.dat antes de empezar)
 * =====================================================================
 *   #  Opción y datos                                   Resultado esperado
 *   1  1 → 1, "Teclado", 25,5                           Longitud del fichero: 124 bytes
 *   2  1 → 2, "Ratón inalámbrico", 12,99                Longitud del fichero: 186 bytes
 *   3  1 → 5, "Monitor 27 pulgadas con altavoces", 199  Longitud del fichero: 372 bytes
 *   4  2 → 5                                            Producto 5: Monitor 27 pulgadas con a · 199.0 €
 *   5  2 → 3                                            Producto no encontrado.   (hueco)
 *   6  2 → 9                                            Producto no encontrado.   (fuera)
 *   7  3 → 2, 10,5                                      Precio actualizado con éxito
 *   8  3 → 4, 50                                        Producto no encontrado.   (hueco)
 *   9  4                                                Productos 1, 2 (10.5 €) y 5, sin huecos
 *  10  6                                                La agenda está vacía
 *  11  5 → 1, "Ana López", 600111222
 *      5 → 2, "Luis Pérez", 600333444                   Contacto agregado (dos veces)
 *  12  5 → 1, "Otro", 000                               El contacto con el ID 1 ya existe
 *  13  7 → 2   y después   7 → 8                       Contacto eliminado · No se puede eliminar el contacto
 *  14  0, vuelve a ejecutar y elige 6                   Contacto 1: Ana López · 600111222
 *  15  Teclear "x" en el menú                           Aviso, y el programa sigue
 *
 * =====================================================================
 *  PREGUNTAS PARA PENSAR (no puntúan, pero te preparan para el examen)
 * =====================================================================
 *  a) ¿Por qué tras escribir el producto 1 el fichero mide 124 bytes y no 62?
 *  b) ¿Qué hay guardado en las zonas de los productos 3 y 4?
 *  c) Si al actualizar el precio solo comprobaras la longitud del fichero,
 *     ¿qué pasaría en la prueba 8?
 *  d) ¿Qué ocurriría al guardar si la clase de los contactos no fuera
 *     serializable? ¿Y si le añades un atributo sin haber declarado
 *     serialVersionUID y luego cargas un contactos.dat antiguo?
 * =====================================================================
 */