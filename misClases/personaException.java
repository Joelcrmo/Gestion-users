//Excepcion creado por Joel Acoran Cruz Morales 1ºDAW
package misClases;

public class personaException extends Exception {
    private String errorMessage;
//------------------------------------------------------------------------------------------------------------------------------------
//Se crea el constructor con el mensaje de error
    public personaException(){
        this.errorMessage="Error indefinido";
    }
//------------------------------------------------------------------------------------------------------------------------------------
//El constructor con parametros de string devuelve el mensaje
    public personaException(String message){
        this.errorMessage=message;
    }
//------------------------------------------------------------------------------------------------------------------------------------
//Recibir mensajes de error desde persona 
    public String getMessage(){
        return this.errorMessage;
    }
}
