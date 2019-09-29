
package org.javierbarahona.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.javierbarahona.bean.Persona;
import org.javierbarahona.db.Conexion;


/**
 *
 * @author Javier
 */
public class PersonaController implements Initializable {
  
    private ObservableList<Persona> listaPersona;
    @FXML TextField txtNombre;
    @FXML TextField txtApellido;
    @FXML TextField txtTelefono;
    @FXML Button btnAgregar;
    @FXML TableView tblDatos;
    @FXML TableColumn colCodigoPersona;
    @FXML TableColumn colNombre;
    @FXML TableColumn colApellido;
    @FXML TableColumn colTelefono;
    Conexion conn = new Conexion();
    Connection registro = conn.getConexion();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    } 
    
    public void cargarDatos(){
        tblDatos.setItems(getPersona());
        colCodigoPersona.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("codigoPersona"));
        colNombre.setCellValueFactory (new PropertyValueFactory<Persona, String>("nombre"));
        colApellido.setCellValueFactory (new PropertyValueFactory<Persona, String>("apellido"));
        colTelefono.setCellValueFactory (new PropertyValueFactory<Persona, String>("telefono"));
    }
    
    public ObservableList<Persona> getPersona(){
        ArrayList<Persona> lista = new ArrayList<Persona>();
        try{
            PreparedStatement procedimiento = registro.prepareCall("{call sp_ListarPersonas()}");
            ResultSet resultado = procedimiento.executeQuery();
            while(resultado.next()){
                lista.add( new Persona (resultado.getInt("codigoPersona"),
                                        resultado.getString("nombres"),
                                        resultado.getString("apellidos"),
                                        resultado.getString("telefono")));
                                    
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaPersona = FXCollections.observableList(lista);
    }
    
    public void guardar(){
        Persona persona = new Persona();
        persona.setNombre(txtNombre.getText());
        persona.setApellido(txtApellido.getText());
        persona.setTelefono(txtTelefono.getText());
        try{
            PreparedStatement procedimiento = registro.prepareCall("{call sp_AgregarPersona(?,?,?)}");
            procedimiento.setString(1, persona.getNombre());
            procedimiento.setString(2, persona.getApellido());
            procedimiento.setString(3, persona.getTelefono());
            procedimiento.execute();
            listaPersona.add(persona);
        }catch(Exception e){
            e.printStackTrace();
        }
        cargarDatos();
    }
   
}
