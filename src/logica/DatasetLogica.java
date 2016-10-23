/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mersennetwister.MersenneTwisterFast;
import modelo.Dataset;
import modelo.Diagnostico;

/**
 *
 * @author crisd
 */
public class DatasetLogica {
    
    Dataset ds = new Dataset();
    Map<Long, Diagnostico> hcs = ds.getHistoriasClinicas();
    ArrayList<String> diseasesHC = ds.getEnfermedadesHC();
    ArrayList<String> diseasesSet = ds.getConjEnfermedades();
    ArrayList<String> symptomsHC = ds.getSintomasHC();
    ArrayList<String> symptomsSet = ds.getConjSintomas();
    
    Map<Long, Diagnostico> trainingHC = new HashMap<>();
    Map<Long, Diagnostico> testingHC = new HashMap<>();
        
    private int [][] matrizTF;                      // Matriz TF (Frecuencia de término) que a su vez es la matriz de la cantidad de HC's de 
                                                    // una enfermedad que contiene el síntoma, pues el síntoma se escribe una sóla vez por cada HC.
    private double [][] matrizIDF;                  // Matriz IDF (Frencuencia inversa de documento)
    private int [] cantidadHCSintoma;               // Arreglo de cantidad de HC's que contengan el síntoma
    private double [][] matrizTFIDF;                // Matriz TF*IDF
    
    private double [][] matrizNormalizada;
    private int [] cadenaPrueba;
    
    private double [] cadenaSimilaridad;

    private int cantidadAciertos = 0;
    private int cantidadDesaciertos = 0;
    private double porcentajePrecision = 0.0;
    
    public DatasetLogica(){

    }

    public double getPorcentajePrecision() {
        return porcentajePrecision;
    }

    public void setPorcentajePrecision(double porcentajePrecision) {
        this.porcentajePrecision = porcentajePrecision;
    }
    
    // Impresión de datos de cada Diagnóstico    
    public void imprimirDiagnosticos(FileWriter fichero){
        for (long key : hcs.keySet()) {
            try {
                fichero.write(hcs.get(key).getReferencia() + " | ");
                fichero.write(hcs.get(key).getEnfermedad() + " | ");
                fichero.write(Arrays.toString(hcs.get(key).getSintomas()));
                fichero.write("\r\n");
            } catch (IOException ex) {
                Logger.getLogger(DatasetLogica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
    }
        
    // Impresión de todas las enfermedades sin repetir
    public void imprimirEnfermedades(FileWriter fichero){
        try {
            // Agregación de enfermedades
            fichero.write("\r\n");
            for (long key : hcs.keySet()) {
                diseasesHC.add(hcs.get(key).getEnfermedad());
            }
            
            // Asignación para almacenar los elementos de enfermedades sin repetir
            HashSet<String> diseaseSet = new HashSet<>(diseasesHC);
            diseasesSet.addAll(diseaseSet);
            
            // Impresión de las enfermedades sin repetir
            fichero.write("\r\n");
            for(String disease : diseasesSet){
                fichero.write(disease + " ");
                fichero.write("\r\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(DatasetLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Impresión de todos los síntomas sin repetir
    public void imprimirSintomas(FileWriter fichero){
        try {
            // Agregación de síntomas
            for (long key : hcs.keySet()) {
                String [] conjuntoSintomas = hcs.get(key).getSintomas();         
                symptomsHC.addAll(Arrays.asList(conjuntoSintomas));
            }
            
            // Asignación para almacenamiento de elementos de síntomas sin repetir
            HashSet<String> symptomSet = new HashSet<>(symptomsHC);
            symptomsSet.addAll(symptomSet);
            
            // Impresión de los síntomas sin repetir
            fichero.write("\r\n");
            for(String symptoms : symptomsSet){
                fichero.write(symptoms + " ");
                fichero.write("\r\n");
            }
            fichero.write("\r\n");   
            
        } catch (IOException ex) {
            Logger.getLogger(DatasetLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    // Entrena al sistema mediante el algoritmo de clustering TF-IDF
    public void entrenamiento(FileWriter fichero){
        
        this.seleccionAleatoria();
        this.imprimirDiagnosticosSeleccionados(fichero);
        
        matrizTF = new int [diseasesSet.size()][symptomsSet.size()];
        matrizIDF = new double [diseasesSet.size()][symptomsSet.size()];
        matrizTFIDF = new double [diseasesSet.size()][symptomsSet.size()];
        cantidadHCSintoma = new int [symptomsSet.size()];

        // Genera el arreglo de la cantidad de HC's que contiene el síntoma
        for(int i = 0; i < symptomsSet.size(); i++){
            String sintoma = symptomsSet.get(i);                
            for(long key: trainingHC.keySet()){
                String [] symptomSet = trainingHC.get(key).getSintomas();
                for(String symptom : symptomSet){
                    if(sintoma.equals(symptom)){
                        cantidadHCSintoma[i]++;
                    }
                }                
                /*if(enfermedad.equals(trainingHC.get(key).getEnfermedad())){
                    cantidadHCEnfermedad[i]++;
                }*/
            }                            
        }

        // Imprime el arreglo de la cantidad de HC's por síntoma
        for(int i = 0; i < cantidadHCSintoma.length; i++){
            System.out.println(symptomsSet.get(i) + ": " + cantidadHCSintoma[i] + " ");
        }
        System.out.println();
        System.out.println();
        
        this.generarMatrizTF(matrizTF);                 // Genera la matriz TF        
        this.generarMatrizIDF(matrizIDF);                // Genera la matriz IDF
        this.generarMatrizTFIDF(matrizTFIDF);              // Genera la matriz TF*IDF        
        
        // Imprime la matriz TF
        System.out.println("Matriz TF (Term Frequency):");
        for(int i = 0; i < matrizTF.length; i++){
            for(int j = 0; j < matrizTF[i].length; j++){
                System.out.print(matrizTF[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        // Imprime la matriz IDF
        DecimalFormat formateador = new DecimalFormat("0.0000");
        System.out.println("Matriz IDF (Inverse Document Frequency):");        
        for(int i = 0; i < matrizIDF.length; i++){
            for(int j = 0; j < matrizIDF[i].length; j++){
                System.out.print(formateador.format(matrizIDF[i][j]) + " ");
                // System.out.print(matrizIDF[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        // Imprime la matriz TFIDF
        System.out.println("Matriz TF-IDF (TF * IDF):");        
        for(int i = 0; i < matrizTFIDF.length; i++){
            for(int j = 0; j < matrizTFIDF[i].length; j++){
                System.out.print(formateador.format(matrizTFIDF[i][j]) + " ");
                // System.out.print(matrizIDF[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();        
        
        // Normaliza la tabla
        this.normalizacion(matrizTFIDF);
        
        // Imprime la matriz de conocimiento normalizada entrenada para aplicar las técnicas de IA
        System.out.println("Matriz Normalizada:");        
        for(int i = 0; i < matrizNormalizada.length; i++){
            for(int j = 0; j < matrizNormalizada[i].length; j++){
                System.out.print(formateador.format(matrizNormalizada[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println();        
    }
            
    // Prueba el sistema
    public void pruebas(FileWriter fichero){
        
        this.crearConjuntoPrueba();
        this.imprimirDiagnosticosPrueba(fichero);
        
        for(long key: testingHC.keySet()){
            
            try {
                cadenaPrueba = new int [symptomsSet.size()];
                cadenaSimilaridad = new double [diseasesSet.size()];
                String [] symptomSet = testingHC.get(key).getSintomas();
                
                // Crea la cadena de prueba
                this.crearCadenaPrueba(cadenaPrueba, symptomSet, fichero);
                
                // Medida de similaridad de HC de prueba con cada enfermedad (cluster)
                for(int i = 0; i < cadenaSimilaridad.length; i++){
                    cadenaSimilaridad[i] = this.similaridad(matrizNormalizada[i], cadenaPrueba);
                    fichero.write(cadenaSimilaridad[i] + " ");
                }
                
                // Mayor similaridad determina el diagnóstico que arroje el sistema
                int indice = 0;
                double mayor = cadenaSimilaridad[indice];
                for(int i = 0; i < cadenaSimilaridad.length; i++){
                    if(cadenaSimilaridad[i] > mayor){
                        mayor = cadenaSimilaridad[i];
                        indice = i;
                    }
                }
                
                fichero.write("\r\n");
                fichero.write("Diagnóstico: " + diseasesSet.get(indice));
                fichero.write("\r\n");
                fichero.write("Mayor similaridad: " + mayor);
                fichero.write("\r\n");
                fichero.write("Enfermedad esperada: " + testingHC.get(key).getEnfermedad() + " -> Enfermedad diagnosticada: " + diseasesSet.get(indice));
                fichero.write("\r\n");
                fichero.write("\r\n");
                
                if(testingHC.get(key).getEnfermedad().equals(diseasesSet.get(indice))){
                    cantidadAciertos++;
                }
                else{
                    cantidadDesaciertos++;
                }
            } catch (IOException ex) {
                Logger.getLogger(DatasetLogica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Realiza las pruebas automatizadas de precisión realizando las fases de entrenamiento, pruebas y recolección de resultados en forma cíclica
    public void pruebasCiclicas(File directorio) {
        FileWriter archivoReporte = null;
        ArrayList<Long> listaTiempos = new ArrayList<>();
        ArrayList<Double> listaPrecisiones = new ArrayList<>();
        try {
            directorio.mkdir();
            File reporte = new File(directorio, "Reporte.txt");
            reporte.createNewFile();
            archivoReporte = new FileWriter(reporte);
            DecimalFormat formateador = new DecimalFormat("0.00");
            int cantidadPruebas = 100;
            double sumatoria = 0.0;
            long totales = 0;
            for(int i = 0; i < cantidadPruebas; i++){
                try {
                    long tInicio, tFin, tTotal;
                    tInicio = System.currentTimeMillis();
                    File prueba = new File(directorio, "Prueba " + (i + 1) + ".txt");
                    try (FileWriter archivoPruebaCrear = new FileWriter(prueba)) {
                        DatasetLogica dslogic = new DatasetLogica();
                        
                        dslogic.imprimirDiagnosticos(archivoPruebaCrear);
                        dslogic.imprimirEnfermedades(archivoPruebaCrear);
                        dslogic.imprimirSintomas(archivoPruebaCrear);
                        // ds.seleccionAleatoria();
                        // ds.imprimirDiagnosticosSeleccionados();
                        dslogic.entrenamiento(archivoPruebaCrear);
                        // ds.crearConjuntoPrueba();
                        // ds.imprimirDiagnosticosPrueba();
                        dslogic.pruebas(archivoPruebaCrear);
                        dslogic.estadisticas(archivoPruebaCrear);
                        listaPrecisiones.add(dslogic.getPorcentajePrecision());
                        sumatoria += dslogic.getPorcentajePrecision();
                    }
                    tFin = System.currentTimeMillis();
                    tTotal = tFin - tInicio;
                    totales += tTotal;
                    listaTiempos.add(tTotal);
                } catch (IOException ex) {
                    Logger.getLogger(DatasetLogica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
            double promedioPrecision = (sumatoria / cantidadPruebas);
            double promedioTiempos = totales / listaTiempos.size();
            double mayor = listaPrecisiones.get(0);
            double menor = listaPrecisiones.get(0);
            
            // Mayor precisión
            for(int i = 1; i < listaPrecisiones.size(); i++){
                if(listaPrecisiones.get(i) >= mayor){
                    mayor = listaPrecisiones.get(i);
                }
            }
            
            // Menor precisión
            for(int i = 1; i < listaPrecisiones.size(); i++){
                if(listaPrecisiones.get(i) <= menor){
                    menor = listaPrecisiones.get(i);
                }
            }            
            
            archivoReporte.write("Reporte Pruebas Cíclicas: ");
            archivoReporte.write("\r\n");
            archivoReporte.write("Tiempo de Ejecución Promedio: " + promedioTiempos / 1000 + " seg.");
            archivoReporte.write("\r\n");
            archivoReporte.write("Mejor porcentaje de Precisión: " + formateador.format(mayor) + "%");
            archivoReporte.write("\r\n");
            archivoReporte.write("Peor porcentaje de Precisión: " + formateador.format(menor) + "%");
            archivoReporte.write("\r\n");              
            archivoReporte.write("Porcentaje Promedio Precisión: " + formateador.format(promedioPrecision) + "%");
        } catch (IOException ex) {
            Logger.getLogger(DatasetLogica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                archivoReporte.close();
            } catch (IOException ex) {
                Logger.getLogger(DatasetLogica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Muestra las estadísticas
    public void estadisticas(FileWriter fichero){
        try {
            DecimalFormat formateador = new DecimalFormat("0.00");
            porcentajePrecision = ((double)cantidadAciertos / testingHC.size()) * 100;
            
            fichero.write("Cantidad de Historias clínicas: " + hcs.size());
            fichero.write("\r\n");
            fichero.write("Cantidad de Enfermedades: " + diseasesSet.size());
            fichero.write("\r\n");
            fichero.write("Cantidad de Síntomas: " + symptomsSet.size());
            fichero.write("\r\n");
            fichero.write("\r\n");
            fichero.write("Cantidad de Historias clínicas seleccionadas para entrenar: " + trainingHC.size());
            fichero.write("\r\n");
            fichero.write("Cantidad de Historias clínicas seleccionadas para probar: " + testingHC.size());
            fichero.write("\r\n");
            fichero.write("\r\n");
            fichero.write("Cantidad de aciertos: " + cantidadAciertos);
            fichero.write("\r\n");
            fichero.write("Cantidad de desaciertos: " + cantidadDesaciertos);
            fichero.write("\r\n");
            fichero.write("Procentaje de precisión: " + formateador.format(porcentajePrecision) + "%");
            fichero.write("\r\n");
            fichero.write("\r\n");
        } catch (IOException ex) {
            Logger.getLogger(DatasetLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
//----------------------- Funciones auxiliares para las operaciones del Dataset --------------------------------

    // Selecciona aleatoriamente las historias clínicas para entrenamiento
    public void seleccionAleatoria(){
        int i = 0;
        MersenneTwisterFast mt = new MersenneTwisterFast();                 // Generador de números pseudoaleatorios MersenneTwister
        mt.setSeed((long)(hcs.size() * Math.random() + 1));   // Semilla     
        do{           
            long claveAux = (long)(mt.nextDouble() * hcs.size() + 1); 
            // System.out.println(claveAux);            
            // long claveAux = (long)(hcs.size() * Math.random() + 1);
            if(!hcs.get(claveAux).isSeleccionado()){
                trainingHC.put(claveAux, hcs.get(claveAux));
                trainingHC.get(claveAux).setSeleccionado(true);
                i++;
                mt.setSeed((long)(hcs.size() * Math.random() + 1));   // Semilla 
            }
        }
        while(i < hcs.size() * 0.8);
    }
    
    // Impresión de historias clínicas seleccionadas aleatoriamente    
    public void imprimirDiagnosticosSeleccionados(FileWriter fichero){
        try {
            for (long key : trainingHC.keySet()) {
                fichero.write(trainingHC.get(key).getReferencia() + " | ");
                fichero.write(trainingHC.get(key).getEnfermedad() + " | ");
                fichero.write(Arrays.toString(trainingHC.get(key).getSintomas()));
                fichero.write("\r\n");
            }
            fichero.write("\r\n");
        } catch (IOException ex) {
            Logger.getLogger(DatasetLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    // Genera la matriz TF
    public void generarMatrizTF(int [][] TFMatrix){
        for(int i = 0; i < diseasesSet.size(); i++){
            String enfermedad = diseasesSet.get(i);
            
            for(int j = 0; j < symptomsSet.size(); j++){
                String sintoma = symptomsSet.get(j);
                
                for(long key: trainingHC.keySet()){
                    if(enfermedad.equals(trainingHC.get(key).getEnfermedad())){
                        String [] symptomSet = trainingHC.get(key).getSintomas();
                        for(String symptom : symptomSet){
                            if(sintoma.equals(symptom)){
                                TFMatrix[i][j]++;
                            }
                        }
                    }
                }
                
            }            
        }        
    }
    
    // Genera la matriz IDF -> IDF = log((cantidadHC)/(1 + cantidadHCsintoma))
    public void generarMatrizIDF(double [][] IDFMatrix){
        for(int i = 0; i < diseasesSet.size(); i++){
            String enfermedad = diseasesSet.get(i);

            for(int j = 0; j < symptomsSet.size(); j++){
                String sintoma = symptomsSet.get(j);

                for(long key: trainingHC.keySet()){
                    if(enfermedad.equals(trainingHC.get(key).getEnfermedad())){
                        String [] symptomSet = trainingHC.get(key).getSintomas();
                        for(String symptom : symptomSet){
                            if(sintoma.equals(symptom)){
                                IDFMatrix[i][j] = Math.log(trainingHC.size() / (double)(1 + cantidadHCSintoma[j]));
                            }
                        }
                    }
                }                
            }            
        }            
    }    

    // Genera la matriz TF-IDF
    public void generarMatrizTFIDF(double [][] TFIDFMatrix){
        for(int i = 0; i < diseasesSet.size(); i++){            
            for(int j = 0; j < symptomsSet.size(); j++){                
                TFIDFMatrix[i][j] = matrizTF[i][j] * matrizIDF[i][j];                
            }            
        }        
    }    
    
    // Función auxiliar para normalizar la tabla (Valor elemento sobre la norma vectorial)
    public double [][] normalizacion(double [][] tabla){
        matrizNormalizada = new double[diseasesSet.size()][symptomsSet.size()];
        for(int i = 0; i < tabla.length; i++){
            for(int j = 0; j < tabla[i].length; j++){
                matrizNormalizada[i][j] = tabla[i][j] / this.normaVectorial(tabla[i]);
            }
        }
        
        return matrizNormalizada;
    }
    
    // Función auxiliar para calcular la norma vectorial
    public double normaVectorial(double [] arreglo){
        double norma = 0.0;
        
        for(int i = 0; i < arreglo.length; i++){
            norma += Math.pow(arreglo[i], 2);
        }
        
        return Math.pow(norma, 0.5);
    }

    // Selecciona el conjunto de HC's de prueba
    public void crearConjuntoPrueba(){
        for(long key : hcs.keySet()){
            if(!hcs.get(key).isSeleccionado()){
                testingHC.put(key, hcs.get(key));
                testingHC.get(key).setSeleccionado(true);
            }            
        }        
    }

    // Impresión de historias clínicas seleccionadas aleatoriamente    
    public void imprimirDiagnosticosPrueba(FileWriter fichero){
        try {
            for (long key : testingHC.keySet()) {
                fichero.write(testingHC.get(key).getReferencia() + " | ");
                fichero.write(testingHC.get(key).getEnfermedad() + " | ");
                fichero.write(Arrays.toString(testingHC.get(key).getSintomas()));
                fichero.write("\r\n");
            }
            fichero.write("\r\n");
        } catch (IOException ex) {
            Logger.getLogger(DatasetLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    // Crea la cadena de prueba (Rrepresentación de HC de prueba)
    public void crearCadenaPrueba(int [] testString, String [] symptomSet, FileWriter fichero){
        for(String symptom : symptomSet){
            for(int i = 0; i < symptomsSet.size(); i++){
                String sintoma = symptomsSet.get(i);
                if(sintoma.equals(symptom)){
                    cadenaPrueba[i] = 1;
                }
            }
        }

        for(int i = 0; i < cadenaPrueba.length; i++){
            try {
                fichero.write(cadenaPrueba[i] + " ");
            } catch (IOException ex) {
                Logger.getLogger(DatasetLogica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println();        
    }
    
    // Función auxiliar para calcular la medida de similaridad
    public double similaridad(double [] normCadena, int [] testCadena){
        double valorSimilaridad = 0.0;
        
        for(int i = 0; i < normCadena.length; i++){
            valorSimilaridad += normCadena[i] * testCadena[i];
        }
        
        return valorSimilaridad;
    }
    
    // Función auxiliar para retornar el mayor valor de similaridad
    /* public double mayorSimilaridad(double [] similarCadena){
        int indice = 0;
        double mayor = cadenaSimilaridad[indice];
        for(int i = 0; i < cadenaSimilaridad.length; i++){
            if(cadenaSimilaridad[i] > mayor){
                mayor = cadenaSimilaridad[i];
                indice = i;
            }          
        }
        System.out.println("Diagnóstico: " + diseasesSet.get(indice));        
        return mayor;
    }*/    
    
}
