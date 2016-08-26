/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfidfacodsevec;

/**
 *
 * @author crisd
 */
public class Acodsevec {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Dataset ds = new Dataset();
        ds.crearDataset();
        ds.imprimirDiagnosticos();
        ds.imprimirEnfermedades();
        ds.imprimirSintomas();
        ds.seleccionAleatoria();
        ds.imprimirDiagnosticosSeleccionados();
        ds.entrenamiento();
        ds.crearConjuntoPrueba();
        ds.imprimirDiagnosticosPrueba();
        ds.pruebas();
        ds.estadisticas();
    }
    
}
