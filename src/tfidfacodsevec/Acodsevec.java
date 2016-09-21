/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfidfacodsevec;

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
    }
    
}
