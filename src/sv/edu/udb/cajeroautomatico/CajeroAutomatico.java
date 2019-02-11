/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.cajeroautomatico;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo
 */
public class CajeroAutomatico {
     static String cuentaTexto;
    static Integer numeroCuenta;
    static Integer numeroCuentaD;
    static String dineroTexto;
    static Double dinero;

    public void cajero() { //Funcion principal
        AccionesData data = new AccionesData();
        try {
            data.importarData("archivo.txt"); // Importamos la informacion del txt
        
        
        String opcion;
        boolean salir = false;
        
        JOptionPane.showMessageDialog(null, "Inserta tu tarjeta","Cajero",JOptionPane.INFORMATION_MESSAGE);
        
        do { //bucle para ingresar correctamente un numero de cuenta
            cuentaTexto = JOptionPane.showInputDialog(null, "Número de cuenta:","Cajero",JOptionPane.QUESTION_MESSAGE);
        
        } while (cuentaTexto == null || !validarCuenta(cuentaTexto) || data.verificarCuenta(cuentaTexto).contains("null"));                
        
        
        numeroCuenta = Integer.parseInt(cuentaTexto); // convertimos el numero de cuenta a entero
        
        do { // bucle para mostrar el menu hasta seleccionar la opcion Salir
            opcion = JOptionPane.showInputDialog("Opciones: "
                                            + "\n1. Consulta de saldo"
                                            + "\n2. Retirar Saldo"
                                            + "\n3. Consignar Saldo"
                                            + "\n4. Transferencia de Saldo"
                                            + "\n5. Salir");
            
            if (opcion != null) {
                switch (opcion) {
                    case "1": //Opcion para mostrar el saldo actual de la cuenta
                        data.mostrarSaldo(numeroCuenta);
                        break;
                        
                    case "2": // Opcion para Retirar Saldo
                    {
                        do { //bucle para ingresar correctamente un valor numerico
                            dineroTexto = JOptionPane.showInputDialog(null,"Cantidad a retirar:","Retiros",JOptionPane.OK_CANCEL_OPTION);      
                            if(dineroTexto == null) // Verificamos si se ha seleccionado la Opcion Cancelar
                                break;
                        }while (!validarCantidad(dineroTexto));
                        try { //Utilizamos el try catch para preveer si se da la opcion Cancel
                            dinero = Double.parseDouble(dineroTexto);
                        }
                        catch(NumberFormatException e) {break;} // convertimos el dinero a tipo Double 
                        data.modificarCuenta("retirar", dinero, numeroCuenta); // Mandamos a llamar el metodo para modificar, con parametros de Retiro
                        data.exportarData(); // Guardamos los cambios realizados en el archivo txt
                    }
                        break;
                        
                    case "3": // Opcion de Consignar Saldo
                        do { //bucle para ingresar correctamente un valor numerico
                            dineroTexto = JOptionPane.showInputDialog(null, "Cantidad a consignar:","Consignaciones",JOptionPane.OK_CANCEL_OPTION);      
                            if(dineroTexto == null) // Verificamos si se ha seleccionado la Opcion Cancelar
                                break;
                        }while (!validarCantidad(dineroTexto));
                        try{ //Utilizamos el try catch para preveer si se da la opcion Cancel
                        dinero = Double.parseDouble(dineroTexto); // Convertimos el valor del dinero a tipo Double
                        
                        data.modificarCuenta("consignar", dinero, numeroCuenta); // Mandamos a llamar el metodo para modificar, con parametros de Consginacion
                        data.exportarData();} // Guardamos los cambios realizados en el archivo txt
                        catch(NumberFormatException e ){ break;}
                        break;
                        
                    case "4": // Opcion de Transferencia de Saldo
                        test:
                        do { //bucle para ingresar correctamente el valor de la cuenta y verificar que exista en el txt
                            cuentaTexto = JOptionPane.showInputDialog(null, "Número de cuenta a Transferir:","Cajero",JOptionPane.OK_CANCEL_OPTION);
                            if(cuentaTexto == null) // Verificamos si se ha seleccionado la Opcion Cancelar
                            {break;}                            
                        
                            do { //bucle para ingresar correctamente un valor numerico
                                dineroTexto = JOptionPane.showInputDialog(null, "Cantidad a transferir:","Retiros",JOptionPane.OK_CANCEL_OPTION); 
                                if(dineroTexto == null) // Verificamos si se ha seleccionado la Opcion Cancelar
                                    break;
                            }while (!validarCantidad(dineroTexto));
                        
                        } while (!validarCuenta(cuentaTexto) || data.verificarCuenta(cuentaTexto).contains("null")); 
                        
                        try{ //Utilizamos el try catch para preveer si se da la opcion Cancel
                        numeroCuentaD = Integer.parseInt(cuentaTexto); // Convertimos el numero de cuenta a  tipo Entero 
                        if(numeroCuentaD.equals(numeroCuenta)) // Verificamos si la cuenta a transferir no sea la misma 
                        { JOptionPane.showMessageDialog(null, "Esta Ingresando el mismo Numero de Cuenta");
                            break;}
                        dinero = Double.parseDouble(dineroTexto); // Convertimos el valor del dinero a tipo Double
                        
                       data.modificarCuenta("transferir", dinero, numeroCuenta, numeroCuentaD); // Mandamos a llamar el metodo para modificar, con parametros de Transferencia
                       data.exportarData(); // Guardamos los cambios realizados en el archivo txt
                        } catch(NumberFormatException e) { break;}
                        break;
                        
                     
                    case "5": // Opcion de Salir
                        int salida = JOptionPane.showConfirmDialog(null, "¿Realmente desea salir?", "Salida", JOptionPane.YES_NO_OPTION);
                        
                        if (salida == JOptionPane.YES_OPTION){
                            salir = true;
                        }
                        
                        break;
                    default: // 
                        JOptionPane.showMessageDialog(null, "Opción no válida","Error",JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
            
        } while ((opcion == null) || !salir);
    

        }  catch(IOException e) // Error si no se encuentra el archivo txt
    {
            JOptionPane.showMessageDialog(null, "Error al abrir archivo, rebisar si el archivo se encuentra en el proyecto","Error",JOptionPane.ERROR_MESSAGE);
    }      
}
    //Metodo para validar la cuenta
    public static boolean validarCuenta(String cuenta){
        
        try {
            int cuentaValida = Integer.parseInt(cuenta); // creamos una conversion que solo se podra cumplir si es un numero
            //eliminamos numeros negativos
            if (cuentaValida <= 0)
                return false;
            else
                return true;
        } catch (NumberFormatException e) { // en caso de no ser un entero se genera un mensaje de error
            JOptionPane.showMessageDialog(null, "Número de cuenta inválido","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public static boolean validarCantidad(String cantidad){
        
        try {
            double cantidadValida = Double.parseDouble(cantidad); // creamos una conversion que solo se podra cumplir si es un numero
            if (cantidadValida <= 0) // eliminamos numeros negativos
            {JOptionPane.showMessageDialog(null, "Cantidad inválida","Error",JOptionPane.ERROR_MESSAGE);
            return false;}
            else
                return true;
        } catch (NumberFormatException e) { // en caso de no ser un entero se genera un mensaje de error
            JOptionPane.showMessageDialog(null, "Cantidad inválida","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    } 
}
