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
        Diagnostico d20 = new Diagnostico(20, "Hemoparásitos", new String[]{"Dolor abdominal", "Mucosas congestionadas", "No hace necesidades fisiológicas", "Camina encorvado"}, false);
        Diagnostico d21 = new Diagnostico(21, "Hemoparásitos", new String[]{"Disminución de apetito", "Heces con sangre", "Alopecia", "Prurito intenso", "Heces con moco"}, false);
        Diagnostico d22 = new Diagnostico(22, "Hemoparásitos", new String[]{"Decaimiento", "Disminución de apetito", "Ectoparásitos"}, false);
        Diagnostico d23 = new Diagnostico(23, "Hemoparásitos", new String[]{"Decaimiento", "Disminución de apetito", "Mucosas pálidas", "Fiebre", "Moco", "Presencia de garrapatas"}, false);
        Diagnostico d24 = new Diagnostico(24, "Hemoparásitos", new String[]{"Decaimiento", "Disminución de apetito", "Fiebre", "Alopecia", "Presencia de garrapatas", "Vómito", "Prurito intenso", "Ectoparásitos", "Obesidad", "Eritrema en región dorso lumbar", "Eritrema en oídos"}, false);
        Diagnostico d25 = new Diagnostico(25, "Hemoparásitos", new String[]{"Disminución de apetito", "Alopecia", "Presencia de garrapatas", "Pulgas", "Vómito", "Prurito intenso", "Ectoparásitos", "Pioderma", "Eritrema"}, false);
        Diagnostico d26 = new Diagnostico(26, "Hemoparásitos", new String[]{"Anorexia", "Decaimiento", "Caída de pelo", "Fiebre", "Moco", "Alopecia", "Presencia de garrapatas", "Ectoparásitos", "Diarrea verdosa", "Ataxia"}, false);
        Diagnostico d27 = new Diagnostico(27, "Hemoparásitos", new String[]{"Anorexia", "Decaimiento", "Fiebre"}, false);
        Diagnostico d28 = new Diagnostico(28, "Hemoparásitos", new String[]{"Vómito amarillo", "Heces con sangre", "Diarrea", "Alopecia", "Prurito"}, false);
        Diagnostico d29 = new Diagnostico(29, "Hemoparásitos", new String[]{"Anorexia", "Decaimiento"}, false);
        Diagnostico d30 = new Diagnostico(30, "Hemoparásitos", new String[]{"Disminución de apetito", "Fiebre", "Diarrea", "Vómito"}, false);
        Diagnostico d31 = new Diagnostico(31, "Hemoparásitos", new String[]{"Anorexia", "Decaimiento", "Fiebre", "Diarrea", "Vómito", "Diarrea mucosa"}, false);
        Diagnostico d32 = new Diagnostico(32, "Hemoparásitos", new String[]{"Anorexia", "Decaimiento", "Presencia de garrapatas", "Vómito"}, false);
        Diagnostico d33 = new Diagnostico(33, "Hemoparásitos", new String[]{"Anorexia", "Decaimiento", "Fiebre", "Vómito"}, false);
        Diagnostico d34 = new Diagnostico(34, "Hemoparásitos", new String[]{"Diarrea", "Vómito", "Mucosas congestionadas"}, false);
        Diagnostico d35 = new Diagnostico(35, "Hemoparásitos", new String[]{"Anorexia", "Decaimiento"}, false);
        Diagnostico d36 = new Diagnostico(36, "Hemoparásitos", new String[]{"Anorexia", "Decaimiento", "Vómito"}, false);        
        Diagnostico d37 = new Diagnostico(37, "Hemoparásitos", new String[]{"Anorexia", "Decaimiento", "Mucosas pálidas", "Presencia de garrapatas", "Vómito"}, false);
        Diagnostico d38 = new Diagnostico(38, "Hemoparásitos", new String[]{"Fiebre alta", "Moco", "Presencia de garrapatas", "Vómito"}, false);
        Diagnostico d39 = new Diagnostico(39, "Hemoparásitos", new String[]{"Decaimiento", "Disminución de apetito", "Fiebre alta", "Dolor abdominal", "Presencia de garrapatas"}, false);        
        Diagnostico d40 = new Diagnostico(40, "Coccidiosis", new String[]{"Vómito", "Diarrea", "Lagañas", "Nariz seca", "Diarrea muy líquida", "Mucosas pálidas"}, false);
        Diagnostico d41 = new Diagnostico(41, "Coccidiosis", new String[]{"Diarrea con sangre", "Borborigmos aumentados"}, false);
        Diagnostico d42 = new Diagnostico(42, "Coccidiosis", new String[]{"Diarrea muy líquida", "Diarrea con sangre", "Diarrea mal oliente", "Vómito"}, false);
        Diagnostico d43 = new Diagnostico(43, "Coccidiosis", new String[]{"Vómito amarillo con pintas de sangre", "Diarrea con sangre"}, false);
        Diagnostico d44 = new Diagnostico(44, "Coccidiosis", new String[]{"Decaimiento", "Diarrea muy líquida"}, false);
        Diagnostico d45 = new Diagnostico(45, "Coccidiosis", new String[]{"Diarrea", "Dolor abdominal"}, false);
        Diagnostico d46 = new Diagnostico(46, "Coccidiosis", new String[]{"Diarrea", "Vómito", "Disminución de apetito", "Fiebre"}, false);
        Diagnostico d47 = new Diagnostico(47, "Coccidiosis", new String[]{"Vómito amarillo", "Anorexia"}, false);
        Diagnostico d48 = new Diagnostico(48, "Coccidiosis", new String[]{"Vómito", "Decaimiento"}, false);
        Diagnostico d49 = new Diagnostico(49, "Coccidiosis", new String[]{"Diarrea con sangre", "Diarrea muy líquida", "Anorexia"}, false);
        Diagnostico d50 = new Diagnostico(50, "Coccidiosis", new String[]{"Anorexia", "Vómito", "Diarrea"}, false);
        Diagnostico d51 = new Diagnostico(51, "Coccidiosis", new String[]{"Anorexia", "Dolor abdominal", "Diarrea con sangre", "Vómito", "Diarrea muy líquida"}, false);
        Diagnostico d52 = new Diagnostico(52, "Coccidiosis", new String[]{"Decaimiento", "Disminución de apetito"}, false);
        Diagnostico d53 = new Diagnostico(53, "Coccidiosis", new String[]{"Anorexia", "Vómito", "Diarrea"}, false);
        Diagnostico d54 = new Diagnostico(54, "Traqueitis", new String[]{"Mucosas pálidas", "Espectoración", "Tos con sangre", "Convulsiones", "Estridor traqueal"}, false);
        Diagnostico d55 = new Diagnostico(55, "Traqueitis", new String[]{"Decaimiento", "Disminución de apetito", "Estridor traqueal", "Estornudos frecuentes"}, false);
        Diagnostico d56 = new Diagnostico(56, "Traqueitis", new String[]{"Tos", "Sensibilidad renal", "Vómito", "Congestión ocular"}, false);
        Diagnostico d57 = new Diagnostico(57, "Traqueitis", new String[]{"Fiebre", "Tos seca", "Ojos irritados", "Secreción salival abundante", "Inflamación de ganglios faringeos", "Dolor zona faringea"}, false);
        Diagnostico d58 = new Diagnostico(58, "Demodicosis", new String[]{"Caída de pelo", "Lesión en piel"}, false);
        Diagnostico d59 = new Diagnostico(59, "Demodicosis", new String[]{"Alopecia", "Prurito"}, false);
        Diagnostico d60 = new Diagnostico(60, "Demodicosis", new String[]{"Ganglios aumentados", "Alopecia", "Presencia de garrapatas", "Seborrea seca", "Pústulas"}, false);
        Diagnostico d61 = new Diagnostico(61, "Demodicosis", new String[]{"Alopecia", "Prurito", "Eritrema", "Descamación"}, false);
        Diagnostico d62 = new Diagnostico(62, "Demodicosis", new String[]{"Alopecia", "Prurito", "Inflamación"}, false);
        Diagnostico d63 = new Diagnostico(63, "Cistitis", new String[]{"Anorexia", "Disminución de apetito", "Depresión", "Dolor abdominal"}, false);
        Diagnostico d64 = new Diagnostico(64, "Cistitis", new String[]{"Orina turbia", "Orina maloliente"}, false);
        Diagnostico d65 = new Diagnostico(65, "Cistitis", new String[]{"Orina turbia", "Orina maloliente", "Dificultad al orinar"}, false);
        Diagnostico d66 = new Diagnostico(66, "Moquillo", new String[]{"Disminución de apetito", "Fiebre", "Diarrea verdosa"}, false);
        Diagnostico d67 = new Diagnostico(67, "Moquillo", new String[]{"Secreción nasal mucosa", "Sonidos respiratorios", "Hiperqueratosis de los pulpejos"}, false);
        Diagnostico d68 = new Diagnostico(68, "Moquillo", new String[]{"Decaimiento", "Fiebre alta", "Jadeo", "Sonidos respiratorios", "Secreción nasal verdosa"}, false);
        Diagnostico d69 = new Diagnostico(69, "Amebiasis", new String[]{"Vómito amarillo", "Heces con moco"}, false);
        Diagnostico d70 = new Diagnostico(70, "Amebiasis", new String[]{"Anorexia", "Mucosas pálidas", "Diarrea", "Retención de líquidos", "Vómito con sangre"}, false);
        Diagnostico d71 = new Diagnostico(71, "Amebiasis", new String[]{"Anorexia", "Diarrea"}, false);
        Diagnostico d72 = new Diagnostico(72, "Gastroenteritis", new String[]{"Heces con parásitos", "Parásitos externos"}, false);
        Diagnostico d73 = new Diagnostico(73, "Gastroenteritis", new String[]{"Dolor abdominal", "Diarrea", "Vómito", "Bradicardia"}, false);
        Diagnostico d74 = new Diagnostico(74, "Gastroenteritis", new String[]{"Anorexia", "Fiebre alta", "Depresión"}, false);
        Diagnostico d75 = new Diagnostico(75, "Otitis", new String[]{"Comezón en orejas", "Serumen", "Cabeceo"}, false);
        Diagnostico d76 = new Diagnostico(76, "Otitis", new String[]{"Prurito", "Serumen"}, false);
        Diagnostico d77 = new Diagnostico(77, "Otitis", new String[]{"Mal aliento", "Cabeceo", "Irritación en las orejas", "Dolor en las orejas"}, false);
        Diagnostico d78 = new Diagnostico(78, "Otitis", new String[]{"Prurito", "Serumen", "Dolor"}, false);
        Diagnostico d79 = new Diagnostico(79, "Otitis", new String[]{"Prurito", "Ácaros"}, false);
        Diagnostico d80 = new Diagnostico(80, "Otitis", new String[]{"Prurito", "Secreción ótica", "Ácaros"}, false);
        Diagnostico d81 = new Diagnostico(81, "Otitis", new String[]{"Cabeceo", "Irritación en las orejas", "Dolor"}, false);
        Diagnostico d82 = new Diagnostico(82, "Otitis", new String[]{"Eritrema", "Comezón en orejas", "Serumen", "Cabeceo", "Dolor en las orejas"}, false);
        Diagnostico d83 = new Diagnostico(83, "Otitis", new String[]{"Comezón en orejas", "Irritación en las orejas", "Dolor en las orejas", "Piel enrojecida"}, false);
        Diagnostico d84 = new Diagnostico(84, "Dermatitis", new String[]{"Caída de pelo", "Alopecia", "Prurito", "Eritrema", ""}, false);
        Diagnostico d85 = new Diagnostico(85, "Dermatitis", new String[]{"Alopecia", "Prurito"}, false);
        Diagnostico d86 = new Diagnostico(86, "Dermatitis", new String[]{"Alopecia", "Prurito", "Piel enrojecida", "Mal olor"}, false);
        Diagnostico d87 = new Diagnostico(87, "Dermatitis", new String[]{"Lesiones por contacto al pasto", "Piel seca"}, false);
        Diagnostico d88 = new Diagnostico(88, "Dermatitis", new String[]{"Fiebre", "Pulgas", "Prurito", "Hongos", "Escamas en la piel", "Granos en el estómago"}, false);
        Diagnostico d89 = new Diagnostico(89, "Dermatitis", new String[]{"Alopecia", "Prurito"}, false);
        Diagnostico d90 = new Diagnostico(90, "Dermatitis", new String[]{"Prurito intenso", "Eritrema", "Comezón en orejas", "Secreción ótica"}, false);
        Diagnostico d91 = new Diagnostico(91, "Dermatitis", new String[]{"Prurito intenso", "Eritrema"}, false);
        Diagnostico d92 = new Diagnostico(92, "Dermatitis", new String[]{"Alopecia", "Eritrema"}, false);
        Diagnostico d93 = new Diagnostico(93, "Dermatitis", new String[]{"Diarrea", "Alopecia", "Prurito", "Eritrema"}, false);
        Diagnostico d94 = new Diagnostico(94, "Dermatitis", new String[]{"Mucosas rojas", "Infección en piel", "Oídos congestionados"}, false);
        Diagnostico d95 = new Diagnostico(95, "Dermatitis", new String[]{"Alopecia", "Lesión en piel", "Descamación"}, false);
        Diagnostico d96 = new Diagnostico(96, "Dermatitis", new String[]{"Alopecia", "Infección en piel"}, false);
        Diagnostico d97 = new Diagnostico(97, "Dermatitis", new String[]{"Decaimiento", "Dolor abdominal", "Alopecia", "Presencia de garrapatas", "Pulgas", "Prurito", "Malestar general"}, false);
        Diagnostico d98 = new Diagnostico(98, "Parvovirosis", new String[]{"Anorexia", "Decaimiento", "Presencia de garrapatas", "Vómito"}, false);
        Diagnostico d99 = new Diagnostico(99, "Parvovirosis", new String[]{"Anorexia", "Decaimiento", "Fiebre alta", "Presencia de garrapatas", "Vómito"}, false);
        Diagnostico d100 = new Diagnostico(100, "Parvovirosis", new String[]{"Lagañas", "Mucosas pálidas", "Presencia de garrapatas", "Pulgas", "Vómito", "Deshidratación"}, false);
        Diagnostico d101 = new Diagnostico(101, "Parvovirosis", new String[]{"Mucosas pálidas", "Depresión", "Diarrea con sangre", "Hiperactividad", "Vómito", "Deshidratación", "Anemia"}, false);
        
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
        historiasClinicas.put(d31.getReferencia(), d31);
        historiasClinicas.put(d32.getReferencia(), d32);
        historiasClinicas.put(d33.getReferencia(), d33);
        historiasClinicas.put(d34.getReferencia(), d34);
        historiasClinicas.put(d35.getReferencia(), d35);
        historiasClinicas.put(d36.getReferencia(), d36);
        historiasClinicas.put(d37.getReferencia(), d37);
        historiasClinicas.put(d38.getReferencia(), d38);
        historiasClinicas.put(d39.getReferencia(), d39);
        historiasClinicas.put(d40.getReferencia(), d40);
        historiasClinicas.put(d41.getReferencia(), d41);
        historiasClinicas.put(d42.getReferencia(), d42);
        historiasClinicas.put(d43.getReferencia(), d43);
        historiasClinicas.put(d44.getReferencia(), d44);
        historiasClinicas.put(d45.getReferencia(), d45);
        historiasClinicas.put(d46.getReferencia(), d46);
        historiasClinicas.put(d47.getReferencia(), d47);
        historiasClinicas.put(d48.getReferencia(), d48);
        historiasClinicas.put(d49.getReferencia(), d49);
        historiasClinicas.put(d50.getReferencia(), d50);
        historiasClinicas.put(d51.getReferencia(), d51);
        historiasClinicas.put(d52.getReferencia(), d52);
        historiasClinicas.put(d53.getReferencia(), d53);
        historiasClinicas.put(d54.getReferencia(), d54);
        historiasClinicas.put(d55.getReferencia(), d55);
        historiasClinicas.put(d56.getReferencia(), d56);
        historiasClinicas.put(d57.getReferencia(), d57);
        historiasClinicas.put(d58.getReferencia(), d58);
        historiasClinicas.put(d59.getReferencia(), d59);
        historiasClinicas.put(d60.getReferencia(), d60);
        historiasClinicas.put(d61.getReferencia(), d61);
        historiasClinicas.put(d62.getReferencia(), d62);
        historiasClinicas.put(d63.getReferencia(), d63);
        historiasClinicas.put(d64.getReferencia(), d64);
        historiasClinicas.put(d65.getReferencia(), d65);
        historiasClinicas.put(d66.getReferencia(), d66);
        historiasClinicas.put(d67.getReferencia(), d67);
        historiasClinicas.put(d68.getReferencia(), d68);
        historiasClinicas.put(d69.getReferencia(), d69);
        historiasClinicas.put(d70.getReferencia(), d70);
        historiasClinicas.put(d71.getReferencia(), d71);
        historiasClinicas.put(d72.getReferencia(), d72);
        historiasClinicas.put(d73.getReferencia(), d73);
        historiasClinicas.put(d74.getReferencia(), d74);
        historiasClinicas.put(d75.getReferencia(), d75);
        historiasClinicas.put(d76.getReferencia(), d76);
        historiasClinicas.put(d77.getReferencia(), d77);
        historiasClinicas.put(d78.getReferencia(), d78);
        historiasClinicas.put(d79.getReferencia(), d79);
        historiasClinicas.put(d80.getReferencia(), d80);
        historiasClinicas.put(d81.getReferencia(), d81);
        historiasClinicas.put(d82.getReferencia(), d82);
        historiasClinicas.put(d83.getReferencia(), d83);
        historiasClinicas.put(d84.getReferencia(), d84);
        historiasClinicas.put(d85.getReferencia(), d85);
        historiasClinicas.put(d86.getReferencia(), d86);
        historiasClinicas.put(d87.getReferencia(), d87);
        historiasClinicas.put(d88.getReferencia(), d88);
        historiasClinicas.put(d89.getReferencia(), d89);
        historiasClinicas.put(d90.getReferencia(), d90);
        historiasClinicas.put(d91.getReferencia(), d91);
        historiasClinicas.put(d92.getReferencia(), d92);
        historiasClinicas.put(d93.getReferencia(), d93);
        historiasClinicas.put(d94.getReferencia(), d94);
        historiasClinicas.put(d95.getReferencia(), d95);
        historiasClinicas.put(d96.getReferencia(), d96);
        historiasClinicas.put(d97.getReferencia(), d97);
        historiasClinicas.put(d98.getReferencia(), d98);
        historiasClinicas.put(d99.getReferencia(), d99);
        historiasClinicas.put(d100.getReferencia(), d100);
        historiasClinicas.put(d101.getReferencia(), d101);
                               
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
        
    // Entrena al sistema mediante el algoritmo de clustering TF-IDF
    public void entrenamiento(){
        
        this.seleccionAleatoria();
        this.imprimirDiagnosticosSeleccionados();
        
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
            
            cadenaPrueba = new int [conjSintomas.size()];
            cadenaSimilaridad = new double [conjEnfermedades.size()];            
            String [] symptomSet = testingHC.get(key).getSintomas(); 
            
            // Crea la cadena de prueba
            this.crearCadenaPrueba(cadenaPrueba, symptomSet);
                        
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

    // Genera la matriz TF
    public void generarMatrizTF(int [][] TFMatrix){
        for(int i = 0; i < conjEnfermedades.size(); i++){
            String enfermedad = conjEnfermedades.get(i);
            
            for(int j = 0; j < conjSintomas.size(); j++){
                String sintoma = conjSintomas.get(j);
                
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
    
    // Genera la matriz IDF
    public void generarMatrizIDF(double [][] IDFMatrix){
        for(int i = 0; i < conjEnfermedades.size(); i++){
            String enfermedad = conjEnfermedades.get(i);

            for(int j = 0; j < conjSintomas.size(); j++){
                String sintoma = conjSintomas.get(j);

                for(long key: trainingHC.keySet()){
                    if(enfermedad.equals(trainingHC.get(key).getEnfermedad())){
                        String [] symptomSet = trainingHC.get(key).getSintomas();
                        for(String symptom : symptomSet){
                            if(sintoma.equals(symptom)){
                                IDFMatrix[i][j] = Math.log10(cantidadHCEnfermedad[i] / (double)(1 + matrizTF[i][j]));
                            }
                        }
                    }
                }                
            }            
        }            
    }    

    // Genera la matriz TF-IDF
    public void generarMatrizTFIDF(double [][] TFIDFMatrix){
        for(int i = 0; i < conjEnfermedades.size(); i++){            
            for(int j = 0; j < conjSintomas.size(); j++){                
                TFIDFMatrix[i][j] = matrizTF[i][j] * matrizIDF[i][j];                
            }            
        }        
    }    
    
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

    // Crea la cadena de prueba (Rrepresentación de HC de prueba)
    public void crearCadenaPrueba(int [] testString, String [] symptomSet){
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
