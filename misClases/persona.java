//clase persona creada por Joel Acoran Cruz Morales 1ºDAW

package misClases;
import java.util.Scanner;
import java.time.YearMonth;

//------------------------------------------------------------------------------------------------------------------------------------
//Clase persona para los datos a utilizar
//utilizamos esto para eliminar avisos o "codigos muertos"
@SuppressWarnings("unused")
public class persona {
    private String id; 
    private int añoNac;
    private int edad;
    private int year = YearMonth.now().getYear();
    private String Nombre;
    private String Apellidos;
    private String Correo;
//------------------------------------------------------------------------------------------------------------------------------------
//Constructor para inicializar la clase persona
    public persona(){
    }
//------------------------------------------------------------------------------------------------------------------------------------
//Constructor para la lectura de archivos
    public persona(String id,String Nombre, String Apellidos, String Correo, int añoNac){
        this.id=id;
        this.Nombre=Nombre;
        this.Apellidos=Apellidos;
        this.Correo=Correo;
        this.añoNac=añoNac;
    }
//------------------------------------------------------------------------------------------------------------------------------------
//Constructor de los datos
    public persona(String Nombre, String Apellidos, String Correo, int añoNac, String id){
        this.Nombre=Nombre;
        this.Apellidos=Apellidos;
        this.Correo=Correo;
        this.id=id;
        this.añoNac=añoNac;
    };
//------------------------------------------------------------------------------------------------------------------------------------
//Gets
    public String getID(){ return this.id;}
    public String getNombre(){return this.Nombre;}
    public String getApellidos(){return this.Apellidos ;}
    public String getCorreo(){return this.Correo;}   
    public int getYear(){return this.year ;}
    public int getAñonac(){return this.añoNac ;}
//------------------------------------------------------------------------------------------------------------------------------------
//Sets
    public void setID(String id){this.id =id ;}
    public void setNombre(String Nombre){this.Nombre =Nombre ;}
    public void setApellidos(String Apellidos){this.Apellidos =Apellidos ;}
    public void setCorreo(String Correo){this.Correo =Correo ;}
    public void setañoNac(int añoNac){this.añoNac =añoNac ;}
//------------------------------------------------------------------------------------------------------------------------------------
//Calculamos la edad desde el año de nacimiento
    void edad(int e){edad=year-añoNac;}
//------------------------------------------------------------------------------------------------------------------------------------
//Introducir los datos
    public void recogerDatos(){
        try {
            Scanner teclado = new Scanner(System.in);
            System.out.print("Id: ");  this.id = teclado.next();
            System.out.print("Nombre: "); this.Nombre = teclado.next();
            System.out.print("Apellidos: ");this.Apellidos = teclado.next();
            System.out.print("Correo: "); this.Correo = teclado.next();
            boolean isInt=false;
            do {
                try {
                    System.out.println("Año de nacimiento: "); String a =teclado.next();
                    isInt=a.matches("[0-9]+");
                    if (!isInt) {
                        System.out.printf("Entrada de teclado --> %s ,  Lanzada excepcion "+a);
                        System.out.println("     isinteger = " + isInt);
                        throw  new personaException(" Error de datos, el tipo introducido debe ser numerico (int: [0-9]+ )");
                    } else {

                        this.añoNac=Integer.parseInt(a);
                    }
                    if(!isInt) throw new personaException("Error, tiene que introducir un año de forma correcta.");
                } catch (personaException e) {
                    System.out.println("Mensaje: " + e.getMessage());
                }finally{}
                
            } while (!isInt);
            System.out.println("");
        } catch (Exception ex) {
            System.out.println("Mensaje: " + ex.getMessage());
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------
//Mostramos los datos
    public void mostrarDatos(){
        System.out.println("~~~~~~~~~~~~~~~~~~");
        System.out.println("Id: "+this.id);
        System.out.println("Nombre: "+this.Nombre);
        System.out.println("Apellidos: "+this.Apellidos);
        System.out.println("Correo: "+this.Correo);
        System.out.println("Año de nacimiento: "+this.añoNac); 
        System.out.println("~~~~~~~~~~~~~~~~~~");       
    }
//------------------------------------------------------------------------------------------------------------------------------------
//Mostrar los datos por pantalla
    public String getPersona(){
        return "ID: "+this.id+"\nNombre: "+this.Nombre+"\nApellidos: "+this.Apellidos+"\n------------";
    }
//------------------------------------------------------------------------------------------------------------------------------------  
//Para generar el fichero para cargar los datos en proximas ejecuciones
    public String escribefichero(){
        return this.id+","+this.Nombre+","+this.Apellidos+ ","+this.Correo+","+this.añoNac;   
    }
    
}
