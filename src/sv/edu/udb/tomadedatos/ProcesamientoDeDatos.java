/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.tomadedatos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo
 */
public class ProcesamientoDeDatos extends ValidacionDeDatos{
    private static Date fechaNacimiento;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Calendar today = Calendar.getInstance();
    Calendar c = new GregorianCalendar();
    private int año,mes,dias;
    public int calcularEdad(Calendar fechaNac) {
        this.año=fechaNac.get(Calendar.YEAR);
        this.mes=fechaNac.get(Calendar.MONTH);
        this.dias=fechaNac.get(Calendar.DAY_OF_MONTH);
        int año = today.get(Calendar.YEAR) - this.año;
        int mes = today.get(Calendar.MONTH) - this.mes;
        int dias = today.get(Calendar.DAY_OF_MONTH) - this.dias;
        // Si está en ese año pero todavía no los ha cumplido
        if (mes < 0 || (mes == 0 && dias < 0)) {
            año = año - 1;
        }
        return año;
    }
    
    public int obtenerAño(String fecha){
        
        try {
                    fechaNacimiento = sdf.parse(fecha);
                    
                    c.setTime(fechaNacimiento);
                    StringBuilder result = new StringBuilder();                   
                    return (calcularEdad(c));                     
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
    }
    public String bisiesto(){
        String esBiesiesto="";
        GregorianCalendar calendar = new GregorianCalendar();
        if (calendar.isLeapYear(año)){
            esBiesiesto="El año en que nacio es bisiesto";
        }
        else{
            esBiesiesto="El año no es bisiesto";
        }
        return esBiesiesto;
    }
    public String diaSemana(){
        String valorDia="";
        int diaSemana = c.get(Calendar.DAY_OF_WEEK);
        if (diaSemana == 1) {
            valorDia = "Domingo";
        } else if (diaSemana == 2) {
            valorDia = "Lunes";
        } else if (diaSemana == 3) {
            valorDia = "Martes";
        } else if (diaSemana == 4) {
            valorDia = "Miercoles";
        } else if (diaSemana == 5) {
            valorDia = "Jueves";
        } else if (diaSemana == 6) {
            valorDia = "Viernes";
        } else if (diaSemana == 7) {
            valorDia = "Sabado";
        }
        return valorDia;
        
    }
    public int getAño(){
        return this.año;
    }
    public int getMes(){
        return this.mes;
    }
    public int getDia(){
        return this.dias;
    }
}
class ValidacionDeDatos extends MostrarErrores{
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");//Da un formato a la fecha y ayuda a la validacion

    public int evaluacionFecha(String fecha){
        int resultado;
        String regex = "^[0-3][0-9]-[0-3][0-9]-(?:[0-9][0-9])?[0-9][0-9]$";//Expresion regular formato dd-mm-yyyy
        Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(fecha);
                if(matcher.find()){//Determina que sea del formato. dd-mm-yy
                    try {
                        formatoFecha.setLenient(false);//determina que la fecha este dentro de los parametros establecidos
                        //ejemplo que no sean mas de 12 meses,
                        formatoFecha.parse(fecha);//Si es valido la fecha, deveelve 1                        
                        resultado=1;
                        
                    } catch (ParseException e) {//Si es falso entonces devuelve cero
                        resultado=0;
                        errorTamañoFecha();//Muestra el error
                    }                   
                }
                else{
                    resultado=0;
                }
        return resultado;
    }
    public void verificarFecha(){
        
    }
}
class MostrarErrores{
    public void errorFormatoFecha(){
        JOptionPane.showMessageDialog(null, "Formato de Fecha Erroneo, Formato Correcto: dd-MM-yyyy"+
                "\nPorfavor vuelva a escribir la fecha","Error",JOptionPane.ERROR_MESSAGE);
    }
    public void errorTamañoFecha(){
        JOptionPane.showMessageDialog(null, "La fecha que usted ingreso no Existe"+
                "\nPorfavor vuelva a escribir la fecha","Error",JOptionPane.ERROR_MESSAGE);
    }
    public void errorAño(){
        JOptionPane.showMessageDialog(null, "La fecha ingresado supera la fecha actual"+
                "\nPorfavor vuelva a escribir la fecha","Error",JOptionPane.ERROR_MESSAGE);
    }
    public void errorNombre(){
        JOptionPane.showMessageDialog(null, "Porfavor ingrese el nombre para continuar",
                "Error",JOptionPane.ERROR_MESSAGE);
    }
    public void errorSexo(){
        JOptionPane.showMessageDialog(null, "Seleccione el sexo para continuar",
                "Error",JOptionPane.ERROR_MESSAGE);
    }
    public void errorFechaNulo(){
        JOptionPane.showMessageDialog(null, "Ingrese la fecha para continuar",
                "Error",JOptionPane.ERROR_MESSAGE);
    }
}
