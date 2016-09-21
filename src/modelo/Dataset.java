/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author crisd
 */
public class Dataset {

    private Map<Long, Diagnostico> historiasClinicas; // = new HashMap<>();
    
    private ArrayList<String> enfermedadesHC; // = new ArrayList<>();
    private ArrayList<String> conjEnfermedades; // = new ArrayList<>();   // Atributo para el conjunto de enfermedades sin repetir
    
    private ArrayList<String> sintomasHC; // = new ArrayList<>();
    private ArrayList<String> conjSintomas; // = new ArrayList<>();   // Atributo para el conjunto de síntomas sin repetir    
    
    public Dataset(){                         
        this.historiasClinicas = new HashMap<>();
        this.enfermedadesHC = new ArrayList<>();
        this.conjEnfermedades = new ArrayList<>();
        this.sintomasHC = new ArrayList<>();
        this.conjSintomas = new ArrayList<>();
        
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
        Diagnostico d84 = new Diagnostico(84, "Dermatitis", new String[]{"Caída de pelo", "Alopecia", "Prurito", "Eritrema"}, false);
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
        Diagnostico d102 = new Diagnostico(102, "Coccidiosis", new String[]{"Diarrea", "Disminución de apetito", "Heces negras con pintas de sangre"}, false);
        Diagnostico d103 = new Diagnostico(103, "Moquillo", new String[]{"Diarrea", "Disminución de apetito", "Mucosas pálidas", "Moco"}, false);
        Diagnostico d104 = new Diagnostico(104, "Coccidiosis", new String[]{"Disminución de apetito", "Diarrea muy líquida", "Mucosas pálidas", "Diarrea con sangre", "Sensibilidad abdominal"}, false);
        Diagnostico d105 = new Diagnostico(105, "Coccidiosis", new String[]{"Anorexia", "Vómito", "Diarrea", "Dolor abdominal"}, false);
        Diagnostico d106 = new Diagnostico(106, "Dermatitis", new String[]{"Prurito", "Lesión en piel"}, false);
        Diagnostico d107 = new Diagnostico(107, "Dermatitis", new String[]{"Comezón en orejas", "Eritrema"}, false);
        Diagnostico d108 = new Diagnostico(108, "Dermatitis", new String[]{"Lesión en piel", "Descamación", "Eritrema", "Mal olor en el oído"}, false);
        Diagnostico d109 = new Diagnostico(109, "Dermatitis", new String[]{"Prurito", "Eritrema", "Pústulas", "Pulgas"}, false);
        Diagnostico d110 = new Diagnostico(110, "Dermatitis", new String[]{"Prurito", "Ronchas", "Pústulas", "Pulgas", "Decaimiento"}, false);
        Diagnostico d111 = new Diagnostico(111, "Dermatitis", new String[]{"Prurito", "Lesión en piel", "Eczema"}, false);
        Diagnostico d112 = new Diagnostico(112, "Dermatitis", new String[]{"Lesión en piel", "Descamación", "Eczema", "Pápulas"}, false);
        Diagnostico d113 = new Diagnostico(113, "Dermatitis", new String[]{"Lesión en piel", "Eritrema", "Pústulas"}, false);
        Diagnostico d114 = new Diagnostico(114, "Dermatitis", new String[]{"Lesión en piel", "Pulgas", "Piel engrosada", "Piel oleosa"}, false);
        Diagnostico d115 = new Diagnostico(115, "Dermatitis", new String[]{"Lesión en piel", "Pulgas", "Caída de pelo"}, false);
        Diagnostico d116 = new Diagnostico(116, "Dermatitis", new String[]{"Prurito", "Descamación", "Pulgas", "Caída de pelo"}, false);
        Diagnostico d117 = new Diagnostico(117, "Dermatitis", new String[]{"Prurito", "Lesión en piel", "Caída de pelo", "Caspa"}, false);
        Diagnostico d118 = new Diagnostico(118, "Dermatitis", new String[]{"Prurito", "Lesión en piel"}, false);
        Diagnostico d119 = new Diagnostico(119, "Dermatitis", new String[]{"Prurito", "Lesión en piel"}, false);
        Diagnostico d120 = new Diagnostico(120, "Dermatitis", new String[]{"Lesión en piel", "Ronchas", "Pústulas"}, false);
        Diagnostico d121 = new Diagnostico(121, "Dermatitis", new String[]{"Prurito", "Lesión en piel", "Ronchas", "Pústulas"}, false);
        Diagnostico d122 = new Diagnostico(122, "Dermatitis", new String[]{"Prurito", "Dolor", "Piel engrosada", "Piel oleosa", "Mal olor"}, false);
        Diagnostico d123 = new Diagnostico(123, "Dermatitis", new String[]{"Prurito", "Lesión en piel", "Caída de pelo"}, false);
        Diagnostico d124 = new Diagnostico(124, "Dermatitis", new String[]{"Prurito", "Lesión en piel", "Ronchas", "Caída de pelo", "Alopecia"}, false);
        Diagnostico d125 = new Diagnostico(125, "Dermatitis", new String[]{"Prurito", "Lesión en piel", "Caída de pelo", "Pelo opaco"}, false);
        Diagnostico d126 = new Diagnostico(126, "Dermatitis", new String[]{"Prurito", "Eritrema", "Mal olor", "Pelo opaco"}, false);
        Diagnostico d127 = new Diagnostico(127, "Dermatitis", new String[]{"Lesión en piel", "Seborrea seca", "Secreción ótica", "Inflamación"}, false);
        Diagnostico d128 = new Diagnostico(128, "Dermatitis", new String[]{"Lesión en piel", "Caída de pelo", "Pelo opaco", "Lesiones húmedas"}, false);
        Diagnostico d129 = new Diagnostico(129, "Dermatitis", new String[]{"Prurito", "Lesión en piel", "Eritrema", "Caída de pelo", "Alopecia"}, false);
        Diagnostico d130 = new Diagnostico(130, "Dermatitis", new String[]{"Prurito", "Lesión en piel", "Descamación", "Pulgas", "Alopecia", "Presencia de garrapatas"}, false);
        Diagnostico d131 = new Diagnostico(131, "Dermatitis", new String[]{"Prurito", "Caída de pelo", "Alopecia"}, false);
        Diagnostico d132 = new Diagnostico(132, "Dermatitis", new String[]{"Prurito", "Ganglios aumentados", "Infección en piel"}, false);
        Diagnostico d133 = new Diagnostico(133, "Gastroenteritis", new String[]{"Vómito", "Diarrea", "Disminución de apetito", "Dolor abdominal"}, false);
        Diagnostico d134 = new Diagnostico(134, "Gastroenteritis", new String[]{"Diarrea", "Diarrea muy líquida"}, false);
        Diagnostico d135 = new Diagnostico(135, "Gastroenteritis", new String[]{"Decaimiento", "Vómito", "Reflujo"}, false);
        Diagnostico d136 = new Diagnostico(136, "Gastroenteritis", new String[]{"Vómito", "Diarrea", "Deshidratación"}, false);
        Diagnostico d137 = new Diagnostico(137, "Gastroenteritis", new String[]{"Vómito", "Diarrea", "Deshidratación", "Mucosas pálidas"}, false);
        Diagnostico d138 = new Diagnostico(138, "Gastroenteritis", new String[]{"Diarrea", "Disminución de apetito", "Mal aliento", "Heces blandas"}, false);
        Diagnostico d139 = new Diagnostico(139, "Gastroenteritis", new String[]{"Vómito", "Diarrea", "Mucosas pálidas", "Desnutrición"}, false);
        Diagnostico d140 = new Diagnostico(140, "Gastroenteritis", new String[]{"Anorexia", "Diarrea muy líquida", "Diarrea con sangre", "Diarrea con moco", "Sensibilidad abdominal"}, false);
        Diagnostico d141 = new Diagnostico(141, "Gastroenteritis", new String[]{"Vómito", "Disminución de apetito", "Diarrea muy líquida", "Agitación", "Diarrea amarilla"}, false);
        Diagnostico d142 = new Diagnostico(142, "Gastroenteritis", new String[]{"Deshidratación", "Mucosas pálidas", "Diarrea con sangre", "Diarrea con moco", "Vómito amarillo"}, false);
        Diagnostico d143 = new Diagnostico(143, "Gastroenteritis", new String[]{"Diarrea", "Dolor abdominal", "Estornudo", "Ano húmedo"}, false);
        Diagnostico d144 = new Diagnostico(144, "Gastroenteritis", new String[]{"Tos", "Decaimiento", "Vómito", "Agitación"}, false);
        Diagnostico d145 = new Diagnostico(145, "Gastroenteritis", new String[]{"Vómito", "Diarrea", "Sensibilidad abdominal"}, false);
        Diagnostico d146 = new Diagnostico(146, "Gastroenteritis", new String[]{"Vómito", "Diarrea", "Dolor abdominal", "Mucosas pálidas"}, false);
        Diagnostico d147 = new Diagnostico(147, "Gastroenteritis", new String[]{"Decaimiento", "Disminución de apetito", "Mucosas pálidas", "Vómito amarillo"}, false);
        Diagnostico d148 = new Diagnostico(148, "Gastroenteritis", new String[]{"Fiebre alta", "Diarrea con sangre"}, false);
        Diagnostico d149 = new Diagnostico(149, "Gastroenteritis", new String[]{"Vómito", "Diarrea con sangre", "Diarrea con moco", "Sensibilidad abdominal"}, false);
        Diagnostico d150 = new Diagnostico(150, "Gastroenteritis", new String[]{"Vómito", "Dolor abdominal", "Deshidratación"}, false);
        Diagnostico d151 = new Diagnostico(151, "Gastroenteritis", new String[]{"Dolor", "Vómito", "Diarrea", "Dolor abdominal", "Deshidratación", "Inflamación perianal"}, false);
        Diagnostico d152 = new Diagnostico(152, "Gastroenteritis", new String[]{"Decaimiento", "Vómito", "Diarrea", "Sensibilidad abdominal", "Inflamación perianal"}, false);
        Diagnostico d153 = new Diagnostico(153, "Gastroenteritis", new String[]{"Decaimiento", "Vómito", "Dolor abdominal"}, false);
        Diagnostico d154 = new Diagnostico(154, "Gastroenteritis", new String[]{"Vómito", "Diarrea", "Disminución de apetito", "Mucosas pálidas", "Sensibilidad abdominal"}, false);
        Diagnostico d155 = new Diagnostico(155, "Gastroenteritis", new String[]{"Decaimiento", "Diarrea", "Sensibilidad abdominal"}, false);
        Diagnostico d156 = new Diagnostico(156, "Gastroenteritis", new String[]{"Anorexia", "Vómito", "Dolor abdominal"}, false);
        Diagnostico d157 = new Diagnostico(157, "Gastroenteritis", new String[]{"Vómito", "Dolor abdominal", "Heces con moco"}, false);
        Diagnostico d158 = new Diagnostico(158, "Gastroenteritis", new String[]{"Decaimiento", "Vómito", "Diarrea", "Dolor abdominal", "Deshidratación"}, false);
        Diagnostico d159 = new Diagnostico(159, "Gastroenteritis", new String[]{"Decaimiento", "Vómito", "Disminución de apetito", "Diarrea con sangre", "Ano húmedo"}, false);
        Diagnostico d160 = new Diagnostico(160, "Gastroenteritis", new String[]{"Diarrea", "Deshidratación"}, false);
        Diagnostico d161 = new Diagnostico(161, "Gastroenteritis", new String[]{"Vómito", "Diarrea", "Deshidratación"}, false);
        Diagnostico d162 = new Diagnostico(162, "Gastroenteritis", new String[]{"Anorexia", "Decaimiento", "Vómito", "Diarrea"}, false);
        Diagnostico d163 = new Diagnostico(163, "Gastroenteritis", new String[]{"Vómito", "Diarrea", "Deshidratación",}, false);
        Diagnostico d164 = new Diagnostico(164, "Gastroenteritis", new String[]{"Decaimiento", "Diarrea con sangre", "Sensibilidad abdominal"}, false);
        Diagnostico d165 = new Diagnostico(165, "Gastroenteritis", new String[]{"Vómito", "Diarrea", "Deshidratación", "Sensibilidad abdominal"}, false);
        Diagnostico d166 = new Diagnostico(166, "Gastroenteritis", new String[]{"Vómito", "Disminución de apetito", "Deshidratación", "Bebe poca agua", "Diarrea con moco"}, false);
        Diagnostico d167 = new Diagnostico(167, "Gastroenteritis", new String[]{"Vómito", "Mucosas pálidas", "Diarrea con sangre"}, false);
        Diagnostico d168 = new Diagnostico(168, "Gastroenteritis", new String[]{"Vómito", "Dolor abdominal", "Heces blandas"}, false);
        Diagnostico d169 = new Diagnostico(169, "Gastroenteritis", new String[]{"Anorexia", "Vómito", "Diarrea", "Deshidratación"}, false);
        Diagnostico d170 = new Diagnostico(170, "Gastroenteritis", new String[]{"Vómito", "Diarrea", "Disminución de apetito", "Dolor abdominal"}, false);
        Diagnostico d171 = new Diagnostico(171, "Gastroenteritis", new String[]{"Vómito", "Diarrea con sangre"}, false);
        Diagnostico d172 = new Diagnostico(172, "Hemoparásitos", new String[]{"Anorexia", "Ganglios aumentados", "Dolor"}, false);
        Diagnostico d173 = new Diagnostico(173, "Hemoparásitos", new String[]{"Decaimiento", "Dolor renal", "Fiebre alta"}, false);
        Diagnostico d174 = new Diagnostico(174, "Hemoparásitos", new String[]{"Anorexia", "Decaimiento", "Dolor abdominal", "Mucosas pálidas", "Fiebre alta", "Lagañas", "Dolor lumbar"}, false);
        Diagnostico d175 = new Diagnostico(175, "Hemoparásitos", new String[]{"Anorexia", "Decaimiento", "Fiebre", "Nariz seca"}, false);
        Diagnostico d176 = new Diagnostico(176, "Hemoparásitos", new String[]{"Anorexia", "Dolor", "Decaimiento"}, false);
        Diagnostico d177 = new Diagnostico(177, "Hemoparásitos", new String[]{"Anorexia", "Decaimiento", "Sensibilidad renal", "Fiebre alta", "Bebe poca agua"}, false);
        Diagnostico d178 = new Diagnostico(178, "Hemoparásitos", new String[]{"Diarrea", "Mucosas pálidas", "Mal aliento", "Manchas en la piel", "Heces negras"}, false);
        Diagnostico d179 = new Diagnostico(179, "Hemoparásitos", new String[]{"Anorexia", "Decaimiento", "Sensibilidad renal", "Vómito", "Fiebre alta", "Sensibilidad abdominal"}, false);
        Diagnostico d180 = new Diagnostico(180, "Hemoparásitos", new String[]{"Anorexia", "Decaimiento", "Vómito", "Fiebre", "Dolor renal", "Dolor abdominal craneal"}, false);
        Diagnostico d181 = new Diagnostico(181, "Hemoparásitos", new String[]{"Anorexia", "Decaimiento"}, false);
        Diagnostico d182 = new Diagnostico(182, "Hemoparásitos", new String[]{"Anorexia", "Fiebre", "Mucosas pálidas", "Agitación"}, false);
        Diagnostico d183 = new Diagnostico(183, "Hemoparásitos", new String[]{"Anorexia", "Vómito", "Mucosas pálidas", "Debilidad", "Anemia"}, false);
        Diagnostico d184 = new Diagnostico(184, "Hemoparásitos", new String[]{"Anorexia", "Decaimiento", "Sensibilidad renal", "Bebe poca agua"}, false);
        Diagnostico d185 = new Diagnostico(185, "Otitis", new String[]{"Comezón en orejas", "Ronchas"}, false);
        Diagnostico d186 = new Diagnostico(186, "Otitis", new String[]{"Comezón en orejas", "Serumen"}, false);
        Diagnostico d187 = new Diagnostico(187, "Otitis", new String[]{"Prurito", "Comezón en orejas", "Dolor en orejas"}, false);
        Diagnostico d188 = new Diagnostico(188, "Otitis", new String[]{"Pústulas", "Lesión en orejas"}, false);
        Diagnostico d189 = new Diagnostico(189, "Otitis", new String[]{"Mal olor en el oído", "Dolor en orejas", "Cabeceo"}, false);
        Diagnostico d190 = new Diagnostico(190, "Otitis", new String[]{"Comezón en orejas", "Mal olor en el oído"}, false);
        Diagnostico d191 = new Diagnostico(191, "Otitis", new String[]{"Comezón en orejas", "Ácaros"}, false);
        Diagnostico d192 = new Diagnostico(192, "Otitis", new String[]{"Prurito", "Comezón en orejas", "Caída de pelo"}, false);
        Diagnostico d193 = new Diagnostico(193, "Otitis", new String[]{"Prurito", "Cabeceo"}, false);
        Diagnostico d194 = new Diagnostico(194, "Traqueitis", new String[]{"Anorexia", "Dolor", "Tos", "Jadeo", "Sensibilidad renal", "Estridor traqueal"}, false);
        Diagnostico d195 = new Diagnostico(195, "Traqueitis", new String[]{"Estridor traqueal", "Vómito", "Flemas", "Dificultad para respirar"}, false);
        Diagnostico d196 = new Diagnostico(196, "Traqueitis", new String[]{"Tos", "Estridor traqueal", "Flemas"}, false);
        Diagnostico d197 = new Diagnostico(197, "Traqueitis", new String[]{"Tos", "Estridor traqueal", "Flemas", "Sarro leve", "Fiebre"}, false);
        Diagnostico d198 = new Diagnostico(198, "Traqueitis", new String[]{"Tos", "Estridor traqueal", "Flemas"}, false);
        Diagnostico d199 = new Diagnostico(199, "Traqueitis", new String[]{"Tos", "Estridor traqueal", "Bebe poca agua"}, false);
        Diagnostico d200 = new Diagnostico(200, "Traqueitis", new String[]{"Tos", "Sonidos respiratorios", "Depresión"}, false);
        Diagnostico d201 = new Diagnostico(201, "Traqueitis", new String[]{"Tos", "Estridor traqueal", "Ojo irritado"}, false);
        Diagnostico d202 = new Diagnostico(202, "Traqueitis", new String[]{"Tos", "Estridor traqueal", "Flemas"}, false);
        Diagnostico d203 = new Diagnostico(203, "Traqueitis", new String[]{"Tos", "Estridor traqueal", "Disminución de apetito"}, false);
        Diagnostico d204 = new Diagnostico(204, "Traqueitis", new String[]{"Vómito", "Lagañas", "Ronquidos", "Molestia en garganta", "Ojos rojos"}, false);
        
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
        historiasClinicas.put(d102.getReferencia(), d102);
        historiasClinicas.put(d103.getReferencia(), d103);
        historiasClinicas.put(d104.getReferencia(), d104);
        historiasClinicas.put(d105.getReferencia(), d105);
        historiasClinicas.put(d106.getReferencia(), d106);
        historiasClinicas.put(d107.getReferencia(), d107);
        historiasClinicas.put(d108.getReferencia(), d108);
        historiasClinicas.put(d109.getReferencia(), d109);
        historiasClinicas.put(d110.getReferencia(), d110);
        historiasClinicas.put(d111.getReferencia(), d111);
        historiasClinicas.put(d112.getReferencia(), d112);
        historiasClinicas.put(d113.getReferencia(), d113);
        historiasClinicas.put(d114.getReferencia(), d114);
        historiasClinicas.put(d115.getReferencia(), d115);
        historiasClinicas.put(d116.getReferencia(), d116);
        historiasClinicas.put(d117.getReferencia(), d117);
        historiasClinicas.put(d118.getReferencia(), d118);
        historiasClinicas.put(d119.getReferencia(), d119);
        historiasClinicas.put(d120.getReferencia(), d120);
        historiasClinicas.put(d121.getReferencia(), d121);
        historiasClinicas.put(d122.getReferencia(), d122);
        historiasClinicas.put(d123.getReferencia(), d123);
        historiasClinicas.put(d124.getReferencia(), d124);
        historiasClinicas.put(d125.getReferencia(), d125);
        historiasClinicas.put(d126.getReferencia(), d126);
        historiasClinicas.put(d127.getReferencia(), d127);
        historiasClinicas.put(d128.getReferencia(), d128);
        historiasClinicas.put(d129.getReferencia(), d129);
        historiasClinicas.put(d130.getReferencia(), d130);
        historiasClinicas.put(d131.getReferencia(), d131);
        historiasClinicas.put(d132.getReferencia(), d132);
        historiasClinicas.put(d133.getReferencia(), d133);
        historiasClinicas.put(d134.getReferencia(), d134);
        historiasClinicas.put(d135.getReferencia(), d135);
        historiasClinicas.put(d136.getReferencia(), d136);
        historiasClinicas.put(d137.getReferencia(), d137);
        historiasClinicas.put(d138.getReferencia(), d138);
        historiasClinicas.put(d139.getReferencia(), d139);
        historiasClinicas.put(d140.getReferencia(), d140);
        historiasClinicas.put(d141.getReferencia(), d141);
        historiasClinicas.put(d142.getReferencia(), d142);
        historiasClinicas.put(d143.getReferencia(), d143);
        historiasClinicas.put(d144.getReferencia(), d144);
        historiasClinicas.put(d145.getReferencia(), d145);
        historiasClinicas.put(d146.getReferencia(), d146);
        historiasClinicas.put(d147.getReferencia(), d147);
        historiasClinicas.put(d148.getReferencia(), d148);
        historiasClinicas.put(d149.getReferencia(), d149);
        historiasClinicas.put(d150.getReferencia(), d150);
        historiasClinicas.put(d151.getReferencia(), d151);
        historiasClinicas.put(d152.getReferencia(), d152);
        historiasClinicas.put(d153.getReferencia(), d153);
        historiasClinicas.put(d154.getReferencia(), d154);
        historiasClinicas.put(d155.getReferencia(), d155);
        historiasClinicas.put(d156.getReferencia(), d156);
        historiasClinicas.put(d157.getReferencia(), d157);
        historiasClinicas.put(d158.getReferencia(), d158);
        historiasClinicas.put(d159.getReferencia(), d159);
        historiasClinicas.put(d160.getReferencia(), d160);
        historiasClinicas.put(d161.getReferencia(), d161);
        historiasClinicas.put(d162.getReferencia(), d162);
        historiasClinicas.put(d163.getReferencia(), d163);
        historiasClinicas.put(d164.getReferencia(), d164);
        historiasClinicas.put(d165.getReferencia(), d165);
        historiasClinicas.put(d166.getReferencia(), d166);
        historiasClinicas.put(d167.getReferencia(), d167);
        historiasClinicas.put(d168.getReferencia(), d168);
        historiasClinicas.put(d169.getReferencia(), d169);
        historiasClinicas.put(d170.getReferencia(), d170);
        historiasClinicas.put(d171.getReferencia(), d171);
        historiasClinicas.put(d172.getReferencia(), d172);
        historiasClinicas.put(d173.getReferencia(), d173);        
        historiasClinicas.put(d174.getReferencia(), d174);
        historiasClinicas.put(d175.getReferencia(), d175);
        historiasClinicas.put(d176.getReferencia(), d176);
        historiasClinicas.put(d177.getReferencia(), d177);
        historiasClinicas.put(d178.getReferencia(), d178);
        historiasClinicas.put(d179.getReferencia(), d179);
        historiasClinicas.put(d180.getReferencia(), d180);
        historiasClinicas.put(d181.getReferencia(), d181);
        historiasClinicas.put(d182.getReferencia(), d182);
        historiasClinicas.put(d183.getReferencia(), d183);
        historiasClinicas.put(d184.getReferencia(), d184);
        historiasClinicas.put(d185.getReferencia(), d185);
        historiasClinicas.put(d186.getReferencia(), d186);
        historiasClinicas.put(d187.getReferencia(), d187);
        historiasClinicas.put(d188.getReferencia(), d188);
        historiasClinicas.put(d189.getReferencia(), d189);
        historiasClinicas.put(d190.getReferencia(), d190);
        historiasClinicas.put(d191.getReferencia(), d191);
        historiasClinicas.put(d192.getReferencia(), d192);
        historiasClinicas.put(d193.getReferencia(), d193);
        historiasClinicas.put(d194.getReferencia(), d194);
        historiasClinicas.put(d195.getReferencia(), d195);
        historiasClinicas.put(d196.getReferencia(), d196);
        historiasClinicas.put(d197.getReferencia(), d197);
        historiasClinicas.put(d198.getReferencia(), d198);
        historiasClinicas.put(d199.getReferencia(), d199);
        historiasClinicas.put(d200.getReferencia(), d200);
        historiasClinicas.put(d201.getReferencia(), d201);
        historiasClinicas.put(d202.getReferencia(), d202);
        historiasClinicas.put(d203.getReferencia(), d203);
        historiasClinicas.put(d204.getReferencia(), d204);       
    }

    public Map<Long, Diagnostico> getHistoriasClinicas() {
        return historiasClinicas;
    }

    public void setHistoriasClinicas(Map<Long, Diagnostico> historiasClinicas) {
        this.historiasClinicas = historiasClinicas;
    }

    public ArrayList<String> getEnfermedadesHC() {
        return enfermedadesHC;
    }

    public void setEnfermedadesHC(ArrayList<String> enfermedadesHC) {
        this.enfermedadesHC = enfermedadesHC;
    }

    public ArrayList<String> getConjEnfermedades() {
        return conjEnfermedades;
    }

    public void setConjEnfermedades(ArrayList<String> conjEnfermedades) {
        this.conjEnfermedades = conjEnfermedades;
    }

    public ArrayList<String> getSintomasHC() {
        return sintomasHC;
    }

    public void setSintomasHC(ArrayList<String> sintomasHC) {
        this.sintomasHC = sintomasHC;
    }

    public ArrayList<String> getConjSintomas() {
        return conjSintomas;
    }

    public void setConjSintomas(ArrayList<String> conjSintomas) {
        this.conjSintomas = conjSintomas;
    }
                        
}
