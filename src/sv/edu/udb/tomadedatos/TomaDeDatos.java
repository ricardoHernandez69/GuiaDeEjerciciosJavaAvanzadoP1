/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.tomadedatos;

import javax.swing.JOptionPane;

import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;
import java.util.regex.*;
import java.time.LocalTime;

/**
 *
 * @author Ricardo
 */
public class TomaDeDatos {
    private static String nombre;
    private static String sexo;
    private static String fecha;
    private static int edad;
    
    
//    public static void main(String[] args) {
        
    
    public void ingresoDatos(){//Comentarear para trabajar esta seccion
        int fechaCorrecta=0;
        ProcesamientoDeDatos process=new ProcesamientoDeDatos();
        SignoSodiacal signo=new SignoSodiacal();
        JOptionPane.showMessageDialog(null,"Toma de Datos, Porfavor ingrese "
                +"los datos que se le piden a continuacion",
                "Resultado",JOptionPane.INFORMATION_MESSAGE);
        nombre=JOptionPane.showInputDialog("Ingrese Nombre y Apellido: ");
        sexo=(String) JOptionPane.showInputDialog(
                null,
            "¿Sexo?",
            "Seleccione su sexo: ",
            JOptionPane.QUESTION_MESSAGE,
            null, // null para icono defecto
            new Object[] { "Masculino", "Femenino"},
            "Masculino");
        do{
            
            fecha=JOptionPane.showInputDialog("Ingresar Fecha");
            fechaCorrecta=process.evaluacionFecha(fecha);
            
            if(fechaCorrecta==1){
                edad=process.obtenerAño(fecha);
                if(edad>=0){
                    JOptionPane.showMessageDialog(null, "Fecha Correcta, Usted tiene:"+edad);
                }else{
                    process.errorAño();
                    fechaCorrecta=0;
                }
            }else{
                fechaCorrecta=0;
                JOptionPane.showMessageDialog(null, "Fecha Incorrecta");
            }
                
            
        }while(fechaCorrecta!=1);
        GregorianCalendar calendar = new GregorianCalendar();
        int año=process.getAño();
        int mes=process.getMes();
        int dia=process.getDia();
        String sodiacal=signo.signo(dia, mes);
        
        String diaSemana=process.diaSemana();
        String bisiesto=process.bisiesto();
        if(edad>30){
            if(sexo.equals("Masculino")){
                JOptionPane.showMessageDialog(null,
                        "Buenos Dias Mr. "+nombre+
                                "\nActualmente su edad es de: "+edad+" años"+
                                "\nNacio el Dia: "+diaSemana+
                                "\n"+bisiesto+
                                "\nSu Signo Sodiacal es: "+sodiacal,
                        "Informacion",
                        JOptionPane.INFORMATION_MESSAGE);
            }else if(sexo.equals("Femenino")){
                JOptionPane.showMessageDialog(null,
                        "Buenos Dias Ms. "+nombre+
                                "\nActualmente su edad es de: "+edad+" años"+
                                "\nNacio el Dia: "+diaSemana+
                                "\n"+bisiesto+
                                "\nSu Signo Sodiacal es: "+sodiacal,
                        "Informacion",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            if(sexo.equals("Masculino")){
                JOptionPane.showMessageDialog(null,
                        "Hola amigo "+nombre+
                                "\nActualmente su edad es de: "+edad+" años"+
                                "\nNacio el Dia: "+diaSemana+
                                "\n"+bisiesto+
                                "\nSu Signo Sodiacal es: "+sodiacal,
                        "Informacion",
                        JOptionPane.INFORMATION_MESSAGE);
            }else if(sexo.equals("Femenino")){
                JOptionPane.showMessageDialog(null,
                        "Hola Amiga "+nombre+
                                "\nActualmente su edad es de: "+edad+" años"+
                                "\nNacio el Dia: "+diaSemana+
                                "\n"+bisiesto+
                                "\nSu Signo Sodiacal es: "+sodiacal,
                        "Informacion",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
