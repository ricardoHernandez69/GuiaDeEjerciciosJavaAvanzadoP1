/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.inicio;

import javax.swing.JOptionPane;
import sv.edu.udb.cajeroautomatico.*;
import sv.edu.udb.mediateca.*;
import sv.edu.udb.tomadedatos.*;
/**
 *
 * @author Ricardo
 */
public class Inicio {
    public static void main(String[] args) {
        // TODO code application logic here
        String strOpcion="";
        int opcion=0;
        do{
             if(opcion==JOptionPane.CANCEL_OPTION){
                    System.out.println("A apretado el boton Cancelar, escriba 4 para salir");
                }
                if(opcion==JOptionPane.CLOSED_OPTION){
                    System.out.println("A apretado el boton CERRAR, escriba 4 para salir");
                }
            try{
                strOpcion=(String) JOptionPane.showInputDialog(null,
                        "***************************************"+
                        "\n*      1-  Ejercicio No 1             *"+
                        "\n***************************************"+
                        "\n*      2-  Ejercicio No 2             *"+
                        "\n***************************************"+
                        "\n*      3-  Ejercicio No 3             *"+
                        "\n***************************************"+
                        "\n*      4- Salir                       *"+
                        "\n***************************************"+
                        "\n\tSeleccione una opcion",
                         "Menu Principa",
                        JOptionPane.QUESTION_MESSAGE,
                        null, // null para icono defecto
                        new Object[] { "Opcion 1", "Opcion 2","Opcion 3","Opcion 4"},
                        "Opcion 1");
                switch(strOpcion){
                    case "Opcion 1":
                        TomaDeDatos dato=new TomaDeDatos();
                        dato.Datos();
                        System.out.println("Opcion 1");
                        break;
                    case "Opcion 2":
                        CajeroAutomatico cajero=new CajeroAutomatico();
                        cajero.cajero();
                        break;
                    case "Opcion 3":
                        Mediateca mediateca=new Mediateca();
                        mediateca.mediateca();
                        break;
                    case "Opcion 4":
                        System.out.println("Opcion 4");
                        break;
                    default:
                        System.out.println("Seleccione la opcion 4 para salir");
                        break;
                }
                
            }catch( Exception e){
                if(strOpcion==null){
                    System.out.println("Seleccione la opcion 4 para salir");
                    JOptionPane.showMessageDialog(null,"Seleccione la opcion 4 para salir","Error",JOptionPane.ERROR_MESSAGE);
                }else{
    
                    JOptionPane.showMessageDialog(null,"Ha ingresado alguna letra, o no a "+
                            "ingresado nada, porfavor ingrese el numero de "+
                            "la opcion que desea" +e,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
            
      
        }while(strOpcion!="Opcion 4");
    }
}
