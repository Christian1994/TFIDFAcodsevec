/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
    public void imprimirDiagnosticos(){
        for (long key : hcs.keySet()) {
            System.out.print(hcs.get(key).getReferencia() + " | ");
            System.out.print(hcs.get(key).getEnfermedad() + " | ");
            System.out.print(Arrays.toString(hcs.get(key).getSintomas()));
            System.out.print("\n");
        }      
    }
        
    // Impresión de todas las enfermedades sin repetir
    public void imprimirEnfermedades(){
        // Agregación de enfermedades
        System.out.print("\n");
        for (long key : hcs.keySet()) {
            diseasesHC.add(hcs.get(key).getEnfermedad());         
        }
        
        // Asignación para almacenar los elementos de enfermedades sin repetir
        HashSet<String> diseaseSet = new HashSet<>(diseasesHC);
        diseasesSet.addAll(diseaseSet);
        
        // Impresión de las enfermedades sin repetir
        System.out.print("\n");        
        for(String disease : diseasesSet){
            System.out.println(disease + " ");
        }
    }
    
    // Impresión de todos los síntomas sin repetir
    public void imprimirSintomas(){
        // Agregación de síntomas
        for (long key : hcs.keySet()) {
            String [] conjuntoSintomas = hcs.get(key).getSintomas();
            symptomsHC.addAll(Arrays.asList(conjuntoSintomas));         
        }
        
        // Asignación para almacenamiento de elementos de síntomas sin repetir
        HashSet<String> symptomSet = new HashSet<>(symptomsHC);
        symptomsSet.addAll(symptomSet);
        
        // Impresión de los síntomas sin repetir
        System.out.print("\n");        
        for(String symptoms : symptomsSet){
            System.out.println(symptoms + " ");
        }
        System.out.print("\n");        
    }    
    
    // Entrena al sistema mediante el algoritmo de clustering TF-IDF
    public void entrenamiento(){
        
        this.seleccionAleatoria();
        this.imprimirDiagnosticosSeleccionados();
        
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
    public void pruebas(){
        
        this.crearConjuntoPrueba();
        this.imprimirDiagnosticosPrueba();
        
        for(long key: testingHC.keySet()){
            
            cadenaPrueba = new int [symptomsSet.size()];
            cadenaSimilaridad = new double [diseasesSet.size()];            
            String [] symptomSet = testingHC.get(key).getSintomas(); 
            
            // Crea la cadena de prueba
            this.crearCadenaPrueba(cadenaPrueba, symptomSet);
                        
            // Medida de similaridad de HC de prueba con cada enfermedad (cluster)
            for(int i = 0; i < cadenaSimilaridad.length; i++){
                cadenaSimilaridad[i] = this.similaridad(matrizNormalizada[i], cadenaPrueba);
                System.out.print(cadenaSimilaridad[i] + " ");                
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
            
            System.out.println();            
            System.out.println("Diagnóstico: " + diseasesSet.get(indice));            
            System.out.println("Mayor similaridad: " + mayor);
            System.out.println(testingHC.get(key).getEnfermedad() + " -> " + diseasesSet.get(indice));            
            System.out.println();
            
            if(testingHC.get(key).getEnfermedad().equals(diseasesSet.get(indice))){
                cantidadAciertos++;
            }
            else{
                cantidadDesaciertos++;
            }
        }
    }

    // Muestra las estadísticas
    public void estadisticas(){
        DecimalFormat formateador = new DecimalFormat("0.00");
        porcentajePrecision = ((double)cantidadAciertos / testingHC.size()) * 100;
        
        System.out.println("Cantidad de Historias clínicas: " + hcs.size());
        System.out.println("Cantidad de Enfermedades: " + diseasesSet.size());
        System.out.println("Cantidad de Síntomas: " + symptomsSet.size());
        System.out.println("");
        System.out.println("Cantidad de Historias clínicas seleccionadas para entrenar: " + trainingHC.size());
        System.out.println("Cantidad de Historias clínicas seleccionadas para probar: " + testingHC.size());
        System.out.println("Cantidad de aciertos: " + cantidadAciertos);
        System.out.println("Cantidad de desaciertos: " + cantidadDesaciertos);
        System.out.println("Procentaje de precisión: " + formateador.format(porcentajePrecision) + "%");
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
    public void imprimirDiagnosticosSeleccionados(){
        for (long key : trainingHC.keySet()) {
            System.out.print(trainingHC.get(key).getReferencia() + " | ");
            System.out.print(trainingHC.get(key).getEnfermedad() + " | ");
            System.out.print(Arrays.toString(trainingHC.get(key).getSintomas()));
            System.out.print("\n");
        }
        System.out.print("\n");
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
    public void imprimirDiagnosticosPrueba(){
        for (long key : testingHC.keySet()) {
            System.out.print(testingHC.get(key).getReferencia() + " | ");
            System.out.print(testingHC.get(key).getEnfermedad() + " | ");
            System.out.print(Arrays.toString(testingHC.get(key).getSintomas()));
            System.out.print("\n");
        }
        System.out.print("\n");
    }    

    // Crea la cadena de prueba (Rrepresentación de HC de prueba)
    public void crearCadenaPrueba(int [] testString, String [] symptomSet){
        for(String symptom : symptomSet){
            for(int i = 0; i < symptomsSet.size(); i++){
                String sintoma = symptomsSet.get(i);
                if(sintoma.equals(symptom)){
                    cadenaPrueba[i] = 1;
                }
            }
        }

        for(int i = 0; i < cadenaPrueba.length; i++){
            System.out.print(cadenaPrueba[i] + " ");
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
