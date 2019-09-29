
package org.javierbarahona.bean;

public class Persona {
    int codigoPersona;
    String nombres;
    String apellidos;
    String telefono;
    
    public Persona(){}
    
    public Persona(int codigoPersona,String nombres,String apellidos,String telefono){
        this.codigoPersona = codigoPersona;
        this.nombres = nombres;
        this.apellidos= apellidos;
        this.telefono = telefono;
    }

    public int getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(int codigoPersona) {
        this.codigoPersona = codigoPersona;
    }
    

    public String getNombre() {
        return nombres;
    }

    public void setNombre(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido() {
        return apellidos;
    }

    public void setApellido(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
}
