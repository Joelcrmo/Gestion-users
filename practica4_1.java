// Trabajo realizado por Joel Acoran Cruz Morales 1ºDAW

import misClases.persona; 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

public class practica4_1 {
//------------------------------------------------------------------------------------------------------------------------------------
//Creamos un cls para limpiar la pantalla
    private static void cls(){
        System.out.print("\033[H\033[2J");
        System.out.flush(); 
    }
//------------------------------------------------------------------------------------------------------------------------------------  
//creamos un system pause para que tengamos que darle enter y poder ver de forma mas pausada los resultados.
    public static void pause(){
        Scanner teclado=new Scanner(System.in);
        System.out.print("\n\n Presione ENTER para continuar");
        teclado.nextLine();
    }
//------------------------------------------------------------------------------------------------------------------------------------
//Realizamos busqueda dentro del arraylist
    private static int buscarid(ArrayList<persona> listaPersonas) {
//Le añadimos una posicion de partida
        int posicion=-1;
        try{
        Scanner a = new Scanner(System.in);
        String DNI = a.nextLine();
        for (int i = 0; i < listaPersonas.size(); i++) {
            if(DNI.equalsIgnoreCase(listaPersonas.get(i).getID())){
                posicion=i; 
//Si esta bien realizado nos muestra los datos correctos, sino nos enseña el error
                break;
            };
        }if (posicion != -1 ) {
            System.out.println(posicion);
            System.out.print("Se ha encontrado una persona con el DNI siguiente: " + listaPersonas.get(posicion).getID() + "\n");
        } else {
            System.out.print("No se ha encontrado el DNI en la lista de personas\n");
        }
        }catch(Exception e){
            System.out.println("Error "+e);
        }return posicion;
        }
//------------------------------------------------------------------------------------------------------------------------------------
//Este es el main del codigo
    public static void main(String args[]) {
        int opcion=0;
        
       switch(args.length){
/* verificar si hay un fichero o no.
Case 0= para detectar el error.
Case 1=  Inicia el programa sin errores y con el menu que hemos dado */
               case 0:
                   System.out.println("No se ha detectado un fichero de entrada.");
                   break;
//------------------------------------------------------------------------------------------------------------------------------------
               case 1:
//Para leer el fichero
                    System.out.printf("Fichero de entrada: %s ",args[0]);
                    Scanner menu =null;
                    Scanner scan= new Scanner(System.in);     
                    ArrayList<persona> listapersonas =new ArrayList<persona>();
// Cargamos el fichero dentro del arrayList de persona. Estos se añadiran despues de la ejecución
                try {
//Con esto hacemos que las lineas que lea las asigne a una posicion de un array para que pasen a ser objeto persona.
                    File archivo=new File ("usuarios.txt");
                    try (Scanner sc =new Scanner(archivo)){
                         while(sc.hasNextLine()){
                             String [] datos=sc.nextLine().split(",");
                             persona persona=new persona();
                             persona.setID(datos[0]);
                             persona.setNombre(datos[1]);
                             persona.setApellidos(datos[2]);
                             persona.setCorreo(datos[3]);
                             persona.setañoNac(Integer.parseInt(datos[4]));
                             listapersonas.add(persona);
                         }
                     } catch (Exception e) {
                         System.out.println("Error "+e);
                     }                   
                //Leemos el menu
                    File fichero = new File("menu.txt");
                    System.out.println("Leemos el contenido del fichero");
                    menu =new Scanner(fichero);

                    do{
                        cls();
                        menu = new Scanner(fichero);
                        while (menu.hasNextLine()) {
                            // Guardamos la linea en un String.
                               String linea = menu.nextLine(); 
                            // Imprimimos la linea.
                               System.out.println(linea);      
                        }
//Cerramos el lector para volver a verlo despues.
                        menu.close(); 
                        System.out.print("Elige una opcion: ");
                        opcion = scan.nextInt();
                    switch (opcion) {
//------------------------------------------------------------------------------------------------------------------------------------
//Caso donde se crearan los datos
                        case 1:
                            cls();
                            persona per =new persona();
                            per.recogerDatos();
                            cls();
                            listapersonas.add(per);
                            System.out.println("Se ha creado persona: " + per.getNombre()+" con los siguientes datos: ");
                            per.mostrarDatos();
                            pause();
                            break;
//------------------------------------------------------------------------------------------------------------------------------------
//Mostrar los datos de los datos creados
                        case 2:
                            cls();
                            listapersonas.forEach((n)->{System.out.println(n.getPersona());});
                            pause();
                            break;
//------------------------------------------------------------------------------------------------------------------------------------
                        case 3:
//Mostrar las personas mediante DNI 
                            cls();
                            System.out.println("Busca el DNI de una persona y muestrala en pantalla: ");
                            int idbusq = buscarid(listapersonas);
                            if(idbusq != -1){
                                listapersonas.get(idbusq).mostrarDatos();;
                            }else{
                               System.out.println("No hay coincidencias con el DNI introducido. ");
                            }
                            pause(); 
                            break;
//------------------------------------------------------------------------------------------------------------------------------------
                        case 4:
//Creacion del fichero 
                            cls();
//Creacion de salida y nombre de fichero
                            String ruta = "usuarios.txt";
                            Writer bw = null;
                            try {
                                FileOutputStream fw = new FileOutputStream(ruta);
                                bw = new BufferedWriter(new OutputStreamWriter(fw, "UTF-8"));
// Para escribir linea a linea por persona
                                for (persona per2 : listapersonas) {
                                    try {
                                        bw.write(per2.escribefichero() + "\n");
                                    } catch (IOException ex) {
                                        System.out.println("Mensaje excepcion escritura: " + ex.getMessage());
                                    }
                                }
//Definimos los errores por si no se puede escribir o codificar
                            } catch (UnsupportedEncodingException | FileNotFoundException ex2) {
                                System.out.println("Mensaje error 2: " + ex2.getMessage());
                            } finally {
//Creamos fallo por si hubo error al cerrar el fichero
                                try {
                                    bw.close();
                                } catch (IOException ex3) {
                                    System.out.println("Mensaje error cierre fichero: " + ex3.getMessage());
                                }
                            }
                            System.out.println("Se ha generado el fichero");
                            pause();
                            break;
//------------------------------------------------------------------------------------------------------------------------------------
                        case 5:
//Creamos salida del ejercicio
                            cls();
                            System.out.println("Ha elegido salir");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Por favor, introduzca un numero correcto comprendido del 1 al 5.");
                            break;
                    }
                    }while(opcion !=5);
                    } catch (Exception ex) {
                    System.out.println("Mensaje: " + ex.getMessage());
                     }
                   break;
//------------------------------------------------------------------------------------------------------------------------------------
               default:
                   System.out.println("Debe escribir correctamente el nombre del fichero y su extencion ");
       }            
    }
}
