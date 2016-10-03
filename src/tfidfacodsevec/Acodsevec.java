/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfidfacodsevec;

import java.text.DecimalFormat;
import logica.DatasetLogica;

/**
 *
 * @author crisd
 */
public class Acodsevec {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DecimalFormat formateador = new DecimalFormat("0.00");
        int cantidadPruebas = 100;
        double sumatoria = 0.0;
        
        for(int i = 0; i < cantidadPruebas; i++){
            DatasetLogica dslogic = new DatasetLogica();
            
            dslogic.imprimirDiagnosticos();
            dslogic.imprimirEnfermedades();
            dslogic.imprimirSintomas();
            // ds.seleccionAleatoria();
            // ds.imprimirDiagnosticosSeleccionados();
            dslogic.entrenamiento();
            // ds.crearConjuntoPrueba();
            // ds.imprimirDiagnosticosPrueba();
            dslogic.pruebas();
            dslogic.estadisticas();
            sumatoria += dslogic.getPorcentajePrecision();
        }
        
        double promedioPrecision = (sumatoria / cantidadPruebas);
        
        System.out.println();
        System.out.println("Porcentaje Promedio Precisión: " + formateador.format(promedioPrecision) + "%");
    }
    
}
