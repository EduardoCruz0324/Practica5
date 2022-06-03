package edd.src.Estructuras;

//import java.util.Comparator;
import java.util.Arrays;

public class Practica5 {
    public static void main(String[] args) {
        //Puedes hacer tus pruebas aqui

        System.out.println("······················PRACITCA 5······················");
        
        System.out.println("----------------------Pokemon-------------------------");
        
        Pokemon poke1 = new Pokemon("a", "Planta", 1, 110);
        Pokemon poke2 = new Pokemon("b", "Fuego", 2, 100);
        Pokemon poke3 = new Pokemon("c", "Electrico", 3, 100);
        Pokemon poke4 = new Pokemon("d", "Agua", 4, 100);
        Pokemon poke5 = new Pokemon("e", "Volador", 5, 100);
        
        MonticuloMinimo<Pokemon> monticulo = new MonticuloMinimo<Pokemon>();
        // Agregamos elementos
        monticulo.add(poke1);
        monticulo.add(poke2);
        monticulo.add(poke3);
        monticulo.add(poke4);
        monticulo.add(poke5);
    
        // Mostramos el monticulo
        System.out.println(monticulo);
        System.out.println(monticulo.size());
        System.out.println("########");
        // Eliminamos el elemento con el mínimo valor
        monticulo.delete();
        // Mostramos el monticulo
        System.out.println(monticulo);
        System.out.println(monticulo.size());
        System.out.println("########");
        // Eliminamos el elemento con el mínimo valor
        monticulo.delete();
        // Mostramos el monticulo
        System.out.println(monticulo);
        System.out.println(monticulo.size());
        System.out.println("########");
        System.out.println(monticulo.contains(poke5));
        
        monticulo.empty();
        System.out.println("########");

        System.out.println(monticulo);
        System.out.println(monticulo.size());
    

        System.out.println("-------------------------Test------------------------");

        int[] array = {2,4,6,8,10,12,14,16,18,20}; //ARREGLO DE ENTEROS

        System.out.println("------------Metodo esMontMin------------");
        boolean esMontMin = monticulo.esMontMin(array);

        System.out.println("El arreglo "+Arrays.toString(array)+" es monticulo min?"+"\n"+ String.valueOf(esMontMin));

        System.out.println("------------Metodo heapsort para MontMin------------");
        System.out.println("Vamos a probar al metodo heapsort para el Monticulo Minimo.");
        System.out.println("¿Cómo es el arreglo antes de heapsort?" + Arrays.toString(array));
        monticulo.heapSort(array, array.length);
        System.out.println("Arreglo aplicando nuestro meteodo heapsort para Monticulo Mínimo" + Arrays.toString(array));
       
        System.out.println("------------Metodo esMontMin------------");
        
        int [] M = {1,2,3,4};
        boolean esMontMin2 = monticulo.esMontMin(M);
        
        System.out.println("El arreglo "+Arrays.toString(M)+" es monticulo min? "+"\n"+ String.valueOf(esMontMin2));

        System.out.println("------------Metodo MontMax_MontMin------------");
        System.out.println("Probemos el metodo convertir Monticulo Maximo a Mininimo");
        System.out.println("Arreglo Monticulo maximo: "+ Arrays.toString(array));
        monticulo.MontMax_MontMin(array);
        System.out.println("A Monticulo minimo va a quedar: "+ Arrays.toString(array));

        System.out.println("------------Metodo MontMin_MontMax------------");
        
        MonticuloMaximo<Pokemon> monticuloMax = new MonticuloMaximo<Pokemon>();
        int a[] = {3, 5, 9, 6, 8, 20, 10, 12, 18, 9};
        int x = a.length;
        
        System.out.println("Arreglo Monticulo minimo : " + Arrays.toString(a));
        monticuloMax.MontMin_MontMax(a, x);
        System.out.println("Arreglo convertido a Monticulo maximo: " + Arrays.toString(a));
       
        System.out.println("------------Metodo esMontMax------------");
        System.out.println("Probemos el metodo ¿es Monticulo maximo?");
        
        int b[] = { 76, 23, 16, 6, 11, 3, 6, 2};
        int y = b.length - 1;
        
        if(monticuloMax.esMontMax(b, 0, y)){

            System.out.println("El arreglo "+Arrays.toString(b)+" es Monticulo maximo");
        }else{

            System.out.println("El arreglo "+Arrays.toString(b)+" no es monticulo maximo");
        }
    }

}
