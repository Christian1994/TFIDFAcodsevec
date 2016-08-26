/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfidfacodsevec;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author crisd
 */
public class Dataset {

    private final Map<Long, Diagnostico> historiasClinicas = new HashMap<>();
    private final Map<Long, Diagnostico> trainingHC = new HashMap<>();
    private final Map<Long, Diagnostico> testingHC = new HashMap<>();
    
    private final ArrayList<String> enfermedadesHC = new ArrayList<>();
    private final ArrayList<String> conjEnfermedades = new ArrayList<>();   // Atributo para el conjunto de enfermedades sin repetir
    
    private final ArrayList<String> sintomasHC = new ArrayList<>();
    private final ArrayList<String> conjSintomas = new ArrayList<>();   // Atributo para el conjunto de síntomas sin repetir
    
    private int [][] matrizTF;                      // Matriz TF (Frecuencia de término) que a su vez es la matriz de la cantidad de HC's de 
                                                    // una enfermedad que contiene el síntoma, pues el síntoma se escribe una sóla vez por cada HC.
    private double [][] matrizIDF;                  // Matriz IDF (Frencuencia inversa de documento)
    private int [] cantidadHCEnfermedad;            // Arreglo de cantidad de HC's que contengan la enfermedad
    private double [][] matrizTFIDF;                // Matriz TF*IDF
    
    private double [][] matrizNormalizada;
    private int [] cadenaPrueba;
    
    private double [] cadenaSimilaridad;
    
    public Dataset(){                         

    }
    
    // Creación de dataset
    public void crearDataset(){
        // Creación de historias clínicas
        Diagnostico d1 = new Diagnostico(1, "Hemoparásitos", new String[]{"Dolor en la parte posterior", "Anorexia", "Decaimiento", "Dolor renal"}, false);
        Diagnostico d2 = new Diagnostico(2, "Hemoparásitos", new String[]{"Disminución de apetito", "Duerme mucho", "Brote abdominal", "Caída de pelo", "Cambio de color de pelo"}, false);
        Diagnostico d3 = new Diagnostico(3, "Hemoparásitos", new String[]{"Decaimiento", "Agitación", "Fiebre alta"}, false);
        Diagnostico d4 = new Diagnostico(4, "Hemoparásitos", new String[]{"Disminución de apetito", "Decaimiento", "Dolor renal"}, false);
        Diagnostico d5 = new Diagnostico(5, "Hemoparásitos", new String[]{"Fiebre alta", "Decaimiento", "Duerme mucho", "Anorexia", "Tos"}, false);
        Diagnostico d6 = new Diagnostico(6, "Hemoparásitos", new String[]{"Decaimiento", "Anorexia", "Tos", "Lagañas", "Dolor lumbar"}, false);
        Diagnostico d7 = new Diagnostico(7, "Hemoparásitos", new String[]{"Letargo", "Fiebre alta", "Ganglios aumentados", "Dolor renal"}, false);
        Diagnostico d8 = new Diagnostico(8, "Hemoparásitos", new String[]{"Disminución de apetito", "Baba espesa", "Duerme mucho", "Mucosas pálidas", "Queratitis"}, false);
        Diagnostico d9 = new Diagnostico(9, "Hemoparásitos", new String[]{"Anorexia", "Ojos rojos", "Lagañas", "Duerme mucho", "Decaimiento", "Mucosas pálidas", "Fiebre"}, false);
        Diagnostico d10 = new Diagnostico(10, "Hemoparásitos", new String[]{"Agitación", "Decaimiento", "Disminución de apetito", "Fiebre alta"}, false);
        Diagnostico d11 = new Diagnostico(11, "Hemoparásitos", new String[]{"Ataque canino", "Decaimiento", "Anorexia", "Bebe poca agua", "Vómito amarillo", "Sarro leve", "Mucosas pálidas", "Fiebre"}, false);
        Diagnostico d12 = new Diagnostico(12, "Hemoparásitos", new String[]{"Anorexia", "No camina", "Jadeo", "Moco"}, false);        
        Diagnostico d13 = new Diagnostico(13, "Hemoparásitos", new String[]{"Depresión", "Decaimiento", "Dolor abdominal craneal", "Dolor renal"}, false);
        Diagnostico d14 = new Diagnostico(14, "Hemoparásitos", new String[]{"Decaimiento", "Dolor abdominal", "Fiebre alta"}, false);
        Diagnostico d15 = new Diagnostico(15, "Hemoparásitos", new String[]{"Jadeo", "No bebe agua", "Fiebre", "Sensibilidad renal"}, false);
        Diagnostico d16 = new Diagnostico(16, "Hemoparásitos", new String[]{"Anorexia", "Fiebre alta", "Orina turbia"}, false);
        Diagnostico d17 = new Diagnostico(17, "Hemoparásitos", new String[]{"Fiebre alta", "Orina con sangre", "Heces con sangre", "Mal aliento", "Palidez", "Diarrea con sangre", "Dolor lumbar", "Hiperactividad"}, false);
        Diagnostico d18 = new Diagnostico(18, "Hemoparásitos", new String[]{"Anorexia", "Fiebre"}, false);
        Diagnostico d19 = new Diagnostico(19, "Hemoparásitos", new String[]{"Decaimiento", "Vómito amarillo"}, false);
        Diagnostico d20 = new Diagnostico(20, "Coccidiosis", new String[]{"Vómito", "Diarrea", "Lagañas", "Nariz seca", "Diarrea muy líquida", "Mucosas pálidas"}, false);
        Diagnostico d21 = new Diagnostico(21, "Coccidiosis", new String[]{"Diarrea con sangre", "Borborigmos aumentados"}, false);
        Diagnostico d22 = new Diagnostico(22, "Coccidiosis", new String[]{"Diarrea muy líquida", "Diarrea con sangre", "Diarrea mal oliente", "Vómito"}, false);
        Diagnostico d23 = new Diagnostico(23, "Coccidiosis", new String[]{"Vómito amarillo con pintas de sangre", "Diarrea con sangre"}, false);
        Diagnostico d24 = new Diagnostico(24, "Coccidiosis", new String[]{"Decaimiento", "Diarrea muy líquida"}, false);
        Diagnostico d25 = new Diagnostico(25, "Coccidiosis", new String[]{"Diarrea", "Dolor abdominal"}, false);
        Diagnostico d26 = new Diagnostico(26, "Coccidiosis", new String[]{"Diarrea", "Vómito", "Disminución de apetito", "Fiebre"}, false);
        Diagnostico d27 = new Diagnostico(27, "Coccidiosis", new String[]{"Vómito amarillo", "Anorexia"}, false);
        Diagnostico d28 = new Diagnostico(28, "Coccidiosis", new String[]{"Vómito", "Decaimiento"}, false);
        Diagnostico d29 = new Diagnostico(29, "Coccidiosis", new String[]{"Diarrea con sangre", "Diarrea muy líquida", "Anorexia"}, false);
        Diagnostico d30 = new Diagnostico(30, "Coccidiosis", new String[]{"Anorexia", "Vómito", "Diarrea"}, false);
        
        // Añadiendo cada historia clínica al Hash Map
        historiasClinicas.put(d1.getReferencia(), d1);
        historiasClinicas.put(d2.getReferencia(), d2);
        historiasClinicas.put(d3.getReferencia(), d3);
        historiasClinicas.put(d4.getReferencia(), d4);
        historiasClinicas.put(d5.getReferencia(), d5);
        historiasClinicas.put(d6.getReferencia(), d6);
        historiasClinicas.put(d7.getReferencia(), d7);
        historiasClinicas.put(d8.getReferencia(), d8);
        historiasClinicas.put(d9.getReferencia(), d9);
        historiasClinicas.put(d10.getReferencia(), d10);
        historiasClinicas.put(d11.getReferencia(), d11);
        historiasClinicas.put(d12.getReferencia(), d12);
        historiasClinicas.put(d13.getReferencia(), d13);
        historiasClinicas.put(d14.getReferencia(), d14);
        historiasClinicas.put(d15.getReferencia(), d15);        
        historiasClinicas.put(d16.getReferencia(), d16);
        historiasClinicas.put(d17.getReferencia(), d17);
        historiasClinicas.put(d18.getReferencia(), d18);
        historiasClinicas.put(d19.getReferencia(), d19);
        historiasClinicas.put(d20.getReferencia(), d20);
        historiasClinicas.put(d21.getReferencia(), d21);
        historiasClinicas.put(d22.getReferencia(), d22);
        historiasClinicas.put(d23.getReferencia(), d23);
        historiasClinicas.put(d24.getReferencia(), d24);
        historiasClinicas.put(d25.getReferencia(), d25);
        historiasClinicas.put(d26.getReferencia(), d26);
        historiasClinicas.put(d27.getReferencia(), d27);
        historiasClinicas.put(d28.getReferencia(), d28);
        historiasClinicas.put(d29.getReferencia(), d29);
        historiasClinicas.put(d30.getReferencia(), d30);        
    }
    
    // Impresión de datos de cada Diagnóstico    
    public void imprimirDiagnosticos(){
        for (long key : historiasClinicas.keySet()) {
            System.out.print(historiasClinicas.get(key).getReferencia() + " | ");
            System.out.print(historiasClinicas.get(key).getEnfermedad() + " | ");
            System.out.print(Arrays.toString(historiasClinicas.get(key).getSintomas()));
            System.out.print("\n");
        }      
    }
    
    // Impresión de todas las enfermedades sin repetir
    public void imprimirEnfermedades(){
        // Agregación de enfermedades
        System.out.print("\n");
        for (long key : historiasClinicas.keySet()) {
            enfermedadesHC.add(historiasClinicas.get(key).getEnfermedad());         
        }
        
        // Asignación para almacenar los elementos de enfermedades sin repetir
        HashSet<String> diseaseSet = new HashSet<>(enfermedadesHC);
        conjEnfermedades.addAll(diseaseSet);
        
        // Impresión de las enfermedades sin repetir
        System.out.print("\n");        
        for(String disease : conjEnfermedades){
            System.out.println(disease + " ");
        }
    }
    
    // Impresión de todos los síntomas sin repetir
    public void imprimirSintomas(){
        // Agregación de síntomas
        for (long key : historiasClinicas.keySet()) {
            String [] conjuntoSintomas = historiasClinicas.get(key).getSintomas();
            sintomasHC.addAll(Arrays.asList(conjuntoSintomas));         
        }
        
        // Asignación para almacenamiento de elementos de síntomas sin repetir
        HashSet<String> symptomSet = new HashSet<>(sintomasHC);
        conjSintomas.addAll(symptomSet);
        
        // Impresión de los síntomas sin repetir
        System.out.print("\n");        
        for(String symptoms : conjSintomas){
            System.out.println(symptoms + " ");
        }
        System.out.print("\n");        
    }
    
    // Selecciona aleatoriamente las historias clínicas para entrenamiento
    public void seleccionAleatoria(){
        int i = 0;
        do{
            long claveAux = (long)(historiasClinicas.size() * Math.random() + 1);
            if(!historiasClinicas.get(claveAux).isSeleccionado()){
                trainingHC.put(claveAux, historiasClinicas.get(claveAux));
                trainingHC.get(claveAux).setSeleccionado(true);
                i++;
            }
        }
        while(i < historiasClinicas.size() * 0.8);
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
    
    // Entrena al sistema mediante el algoritmo de clustering TF-IDF
    public void entrenamiento(){
        matrizTF = new int [conjEnfermedades.size()][conjSintomas.size()];
        matrizIDF = new double [conjEnfermedades.size()][conjSintomas.size()];
        matrizTFIDF = new double [conjEnfermedades.size()][conjSintomas.size()];
        cantidadHCEnfermedad = new int [conjEnfermedades.size()];

        // Genera el arreglo de la cantidad de HC's de una enfermedad
        for(int i = 0; i < conjEnfermedades.size(); i++){
            String enfermedad = conjEnfermedades.get(i);                
            for(long key: trainingHC.keySet()){
                if(enfermedad.equals(trainingHC.get(key).getEnfermedad())){
                    cantidadHCEnfermedad[i]++;
                }
            }                            
        }

        // Imprime el arreglo de la cantidad de HC's por enfermedad
        for(int i = 0; i < cantidadHCEnfermedad.length; i++){
            System.out.print(cantidadHCEnfermedad[i] + " ");
        }
        System.out.println();
        System.out.println();
        
        // Genera la matriz TF
        for(int i = 0; i < conjEnfermedades.size(); i++){
            String enfermedad = conjEnfermedades.get(i);
            
            for(int j = 0; j < conjSintomas.size(); j++){
                String sintoma = conjSintomas.get(j);
                
                for(long key: trainingHC.keySet()){
                    if(enfermedad.equals(trainingHC.get(key).getEnfermedad())){
                        String [] symptomSet = trainingHC.get(key).getSintomas();
                        for(String symptom : symptomSet){
                            if(sintoma.equals(symptom)){
                                matrizTF[i][j]++;
                            }
                        }
                    }
                }
                
            }
            
        }
        
        // Genera la matriz IDF
        for(int i = 0; i < conjEnfermedades.size(); i++){
            String enfermedad = conjEnfermedades.get(i);
            
            for(int j = 0; j < conjSintomas.size(); j++){
                String sintoma = conjSintomas.get(j);
                
                for(long key: trainingHC.keySet()){
                    if(enfermedad.equals(trainingHC.get(key).getEnfermedad())){
                        String [] symptomSet = trainingHC.get(key).getSintomas();
                        for(String symptom : symptomSet){
                            if(sintoma.equals(symptom)){
                                matrizIDF[i][j] = Math.log10(cantidadHCEnfermedad[i] / (double)(1 + matrizTF[i][j]));
                            }
                        }
                    }
                }
                
            }
            
        }
        
        // Genera la matriz TF-IDF
        for(int i = 0; i < conjEnfermedades.size(); i++){            
            for(int j = 0; j < conjSintomas.size(); j++){                
                matrizTFIDF[i][j] = matrizTF[i][j] * matrizIDF[i][j];                
            }            
        }
        
        // Imprime la matriz TF
        System.out.println("Matriz TF (Term Frequency)):");
        for(int i = 0; i < matrizTF.length; i++){
            for(int j = 0; j < matrizTF[i].length; j++){
                System.out.print(matrizTF[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        // Imprime la matriz IDF
        DecimalFormat formateador = new DecimalFormat("0.0000");
        System.out.println("Matriz IDF (Inverse Document Frequency)):");        
        for(int i = 0; i < matrizIDF.length; i++){
            for(int j = 0; j < matrizIDF[i].length; j++){
                System.out.print(formateador.format(matrizIDF[i][j]) + " ");
                // System.out.print(matrizIDF[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        // Imprime la matriz TFIDF
        System.out.println("Matriz TF-IDF (TF * IDF)):");        
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
    
    // Selecciona el conjunto de HC's de prueba
    public void crearConjuntoPrueba(){
        for(long key : historiasClinicas.keySet()){
            if(!historiasClinicas.get(key).isSeleccionado()){
                testingHC.put(key, historiasClinicas.get(key));
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
    
    // Prueba el sistema
    public void pruebas(){
        for(long key: testingHC.keySet()){
            
            cadenaPrueba = new int [conjSintomas.size()];
            cadenaSimilaridad = new double [conjEnfermedades.size()];            
            String [] symptomSet = testingHC.get(key).getSintomas(); 
            
            // Crea la cadena de prueba
            for(String symptom : symptomSet){
                for(int i = 0; i < conjSintomas.size(); i++){
                    String sintoma = conjSintomas.get(i);
                    if(sintoma.equals(symptom)){
                        cadenaPrueba[i] = 1;
                    }
                }
            }
            
            for(int i = 0; i < cadenaPrueba.length; i++){
                System.out.print(cadenaPrueba[i] + " ");
            }
            System.out.println();
            
            // Medida de similaridad de HC de prueba con cada enfermedad (cluster)
            for(int i = 0; i < cadenaSimilaridad.length; i++){
                cadenaSimilaridad[i] = this.similaridad(matrizNormalizada[i], cadenaPrueba);
                System.out.print(cadenaSimilaridad[i] + " ");                
            }
            
            // Mayor similaridad determina el diagnóstico que arroje el sistema
            System.out.println();
            System.out.println("Mayor similaridad: " + this.mayorSimilaridad(cadenaSimilaridad));
            System.out.println();           
        }
    }
    
    // Muestra las estadísticas
    public void estadisticas(){
        System.out.println("Cantidad de Historias clínicas: " + historiasClinicas.size());
        System.out.println("Cantidad de Enfermedades: " + conjEnfermedades.size());
        System.out.println("Cantidad de Síntomas: " + conjSintomas.size());
        System.out.println("");
        System.out.println("Cantidad de Historias clínicas seleccionadas para entrenar: " + trainingHC.size());
        System.out.println("Cantidad de Historias clínicas seleccionadas para probar: " + testingHC.size());
    }    
    
//----------------------- Funciones auxiliares para las operaciones del Dataset --------------------------------
    
    // Función auxiliar para normalizar la tabla (Valor elemento sobre la norma vectorial)
    public double [][] normalizacion(double [][] tabla){
        matrizNormalizada = new double[conjEnfermedades.size()][conjSintomas.size()];
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
    
    // Función auxiliar para calcular la medida de similaridad
    public double similaridad(double [] normCadena, int [] testCadena){
        double valorSimilaridad = 0.0;
        
        for(int i = 0; i < normCadena.length; i++){
            valorSimilaridad += normCadena[i] * testCadena[i];
        }
        
        return valorSimilaridad;
    }
    
    // Función auxiliar para retornar el mayor valor de similaridad
    public double mayorSimilaridad(double [] similarCadena){
        int indice = 0;
        double mayor = cadenaSimilaridad[indice];
        for(int i = 0; i < cadenaSimilaridad.length; i++){
            if(cadenaSimilaridad[i] > mayor){
                mayor = cadenaSimilaridad[i];
                indice = i;
            }          
        }
        System.out.println("Diagnóstico: " + conjEnfermedades.get(indice));        
        return mayor;
    }
    
}
