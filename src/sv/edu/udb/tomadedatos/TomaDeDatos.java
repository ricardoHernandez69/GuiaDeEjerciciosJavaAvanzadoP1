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
    
    
//    public static void main(String[] args) throws NullPointerException{
        
    
    public void ingresoDatos() throws NullPointerException{//Comentarear para trabajar esta seccion
        int fechaCorrecta=0;//Esta bariable se utiliza para determinar si la fecha ingresada es correcta o no
        ProcesamientoDeDatos process=new ProcesamientoDeDatos();//Creamos un objeto para instanciar a la clase donde se procesan los datos
        SignoSodiacal signo=new SignoSodiacal();//Esta clase se utilisa para obtener el signo sodiacal
        JOptionPane.showMessageDialog(null,"Toma de Datos, Porfavor ingrese "
                +"los datos que se le piden a continuacion",
                "Resultado",JOptionPane.INFORMATION_MESSAGE);
        while(nombre==null){
            nombre=JOptionPane.showInputDialog("Ingrese Nombre y Apellido: ");
            if(nombre==null){
                process.errorNombre();
            }
            
        }
        while(sexo==null)
        {
        sexo=(String) JOptionPane.showInputDialog(
                null,
            "¿Sexo?",
            "Seleccione su sexo: ",
            JOptionPane.QUESTION_MESSAGE,
            null, // null para icono defecto
            new Object[] { "Masculino", "Femenino"},
            "Masculino");
            if(nombre==null){
                    process.errorSexo();
                }
        }
        do{
            while(fecha==null){
                fecha=JOptionPane.showInputDialog("Ingresar Fecha");
                System.out.println(fecha);
                if(fecha==null){
                    process.errorFechaNulo();
                }
            }
            fechaCorrecta=process.evaluacionFecha(fecha);//Se manda a llamar a un metodo donde se determina la validez de la fecha 
            //Ir a la clase PRocesamientoDeDatos para verificar lo que hace
            if(fechaCorrecta==1){
                edad=process.obtenerAño(fecha);//obtiene la edad, para verificar que sea mayor a un año
                if(edad>=0){
                    fechaCorrecta=1;//si es verdad, entonces se le asigna el valor de uno
                }else{
                    process.errorAño();//Si no, manda un mensaje de error
                    fechaCorrecta=0;//Se asigna el valor de cero para que no salga del bucle
                }
            }else{
                fechaCorrecta=0;
                JOptionPane.showMessageDialog(null, "Fecha Incorrecta");
                fecha=null;
            }
                
            
        }while(fechaCorrecta!=1);      
        int mes=process.getMes();
        int dia=process.getDia();
        String sodiacal=signo.signo(dia, mes);//Se obtiene el signo sodiacal por medio del mes y el año     
        String diaSemana=process.diaSemana();//Se obtiene el dia de la semana en que nacio
        String bisiesto=process.bisiesto();//Se determina si el año en que nacio es bisiesto
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
