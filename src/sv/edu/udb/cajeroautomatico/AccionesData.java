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
public class AccionesData {
    public static final HashMap<Integer, Double> cuentas = new HashMap<>();   //Definimos el HashMap con su llave Entera y valores Double
    //Generamos la clase que contendra los operadores .
    public  void importarData(String archivo) throws FileNotFoundException, IOException 
        {
            String cadena;
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            br.readLine(); //Ignoramos el Header para evitar problemas de conversiones.
            cadena = null;
            while((cadena = br.readLine())!=null) {
                String [] rowfields = cadena.split(","); // Separamos los valores
                cuentas.put(Integer.parseInt(rowfields[0]),Double.parseDouble(rowfields[1])); // Ingresamos los datos en HashMap
            }
            br.close(); // cerramos el archivo para ahorrar recursos
        }
        
        // metodo para mostrar el Saldo actual de la cuenta
        public void mostrarSaldo(Integer nCuenta)
        {   
            //Verificamos si existe la cuenta en nuestro HashMap
            if(cuentas.containsKey(nCuenta) == true)
            {
                Double saldo = cuentas.get(nCuenta); // obtenemos el saldo actual de la cuenta
                JOptionPane.showMessageDialog(null, "El saldo de la cuenta " + nCuenta + " es\n$" + saldo);
            }
            else // en caso de no encontrarse esa cuenta se genera un error
            {
                JOptionPane.showMessageDialog(null, "Cuenta no se encuentra en la Base de Datos","Error Cuenta",JOptionPane.ERROR_MESSAGE); 
            }
        }
        
        // Metodo para verificar si existe la cuenta en el txt
        public String verificarCuenta(String nCuenta)
        {   
            String cuenta;
            //Verificamos si existe la cuenta en nuestro HashMap
            if(cuentas.containsKey(Integer.parseInt(nCuenta)) == true){
                cuenta = nCuenta;
                return cuenta;
            }else
            {JOptionPane.showMessageDialog(null, "Cuenta no se encuentra en la Base de Datos","Error Cuenta",JOptionPane.ERROR_MESSAGE); }
            return "null";
        }
        
        //Metodo para modificar los valores de las cuentas utilizando parametros para filtrar
        public void modificarCuenta(String opr,Double valor,Integer nCuenta){
            //Verificamos si la opcion a realizar es retirar
            if (opr.contains("retirar"))
            {
                //Verificamos si existe la cuenta en nuestro HashMap
                if(cuentas.containsKey(nCuenta) == true)
                    {
                        //Guardamos el saldo actual de la cuenta en variable temporal
                        Double saldo = cuentas.get(nCuenta);
                        //Comparamos si el valor es mayor a la saldo de la cuenta
                        if(saldo >= valor)
                        {
                            saldo = saldo - valor; // Realizamos el retiro de saldo de la cuenta
                            cuentas.put(nCuenta,saldo); // Ingresamos el nuevo saldo de la cuenta
                            JOptionPane.showMessageDialog(null, "Retiro exitoso","Retiros",JOptionPane.INFORMATION_MESSAGE);
                            JOptionPane.showMessageDialog(null, "El nuevo saldo de la cuenta " + nCuenta + " es\n$" + saldo);
                        }
                        //
                        else // En caso de ser mayor se genera un error
                        {
                           JOptionPane.showMessageDialog(null, "Saldo insuficiente","Retiros",JOptionPane.ERROR_MESSAGE); 
                        }
                    }
            }
            //Verificamos si la opcion a realizar es Consignar
            if (opr.contains("consignar"))
            {
                //Verificamos si existe la cuenta en nuestro HashMap
                if(cuentas.containsKey(nCuenta) == true)
                {
                    Double saldo = cuentas.get(nCuenta); // obtenemos el saldo actual de la cuenta
                    saldo = saldo + valor; // realizamos el abono de saldo a la cuenta
                    cuentas.put(nCuenta,saldo); //asignamos el nuevo saldo a la cuenta 
                    JOptionPane.showMessageDialog(null, "Consignación exitosa","Consignaciones",JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "El nuevo saldo de la cuenta " + nCuenta + " es\n$" + saldo);
                }
            }
        }
        //Metodo para modificar los valores de las cuentas utilizando parametros para filtrar
        public void modificarCuenta(String opr,Double valor,Integer nCuenta,Integer nCuentaD){
            if (opr.contains("transferir"))
            {
                //Verificamos si existen la cuenta en nuestro HashMap (Base de Datos)
                if(cuentas.containsKey(nCuenta) == true && cuentas.containsKey(nCuentaD))
                {   
                    //Asignamos en variables temporales los saldos de las dos cuentas 
                    Double saldoC1 = cuentas.get(nCuenta);
                    Double saldoC2 = cuentas.get(nCuentaD);
                    if(saldoC1 >= valor) // comparamos que el saldo de la cuenta 1 no sea menor al monto a transferir
                        {
                            saldoC1 = saldoC1 - valor; // realizamos el retiro de la cuenta 1
                            cuentas.put(nCuenta,saldoC1); // asignamos el nuevo saldo de la cuenta 1
                            saldoC2 = saldoC2 + valor; // realizamos el abono a la cuenta 2 
                            cuentas.put(nCuentaD,saldoC2); // asignamos el nuevo saldo de  la cuenta 2
                            JOptionPane.showMessageDialog(null, "Transferencia exitosa","Transferencia",JOptionPane.INFORMATION_MESSAGE);
                            JOptionPane.showMessageDialog(null, "El nuevo saldo de la cuenta " + nCuenta + " es\n$" + saldoC1);
                        }
                    else // En caso de ser mayor se genera un error
                        {
                           JOptionPane.showMessageDialog(null, "Saldo insuficiente","Retiros",JOptionPane.ERROR_MESSAGE); 
                        }
                }
            }
        }
        //Metodo para guardar los datos en el txt
        public void exportarData()
        {
            try{ 
                File guardar = new File("Archivo.txt"); // asignamos el mismo archivo destino para sobreescribir
                FileOutputStream fos = new FileOutputStream(guardar); 
                PrintWriter pw = new PrintWriter(fos); 
                pw.println("#Cuenta,Saldo en Dolares"); //Colocamos el Header nuevamente;
                for(Map.Entry<Integer,Double> cuenta :cuentas.entrySet()) // asignamos los nuevos saldos a las cuentas modificadas
                { 
                  pw.println(cuenta.getKey()+","+cuenta.getValue()); 
                }
                pw.flush(); 
                pw.close(); 
                fos.close(); 
                }catch(Exception e)
                {
                    System.out.println("Hubo un error al momento de guardar el archivo");
                }
        }
}
