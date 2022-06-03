package edd.src.Estructuras;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.time.Year;

/**
 * Clase para monticulos MAXIMO (Max heaps)
 * @author López Diego Gabriela
 * @author Trujillo Beltrán Zianya Nenetzi
 * version 1
 */

public class MonticuloMaximo<T extends ComparableIndexable<T>> implements Collection<T>{
    

    private class Iterador implements Iterator<T>{
        private int indice;

        @Override public boolean hasNext(){
            return indice < elementos;
        }

        @Override public T next(){
            if (hasNext()) {
                return arbol[indice++];
            }
            throw new NoSuchElementException("No hay, no existe");
        }
    }



    private static class Adaptador<T extends Comparable<T>>
    implements ComparableIndexable<Adaptador<T>>{
        /* El elemento. */
        private T elemento;
        /* El índice. */
        private int indice;

        /* Crea un nuevo comparable indexable. */
        public Adaptador(T elemento) {
            this.elemento = elemento;
            this.indice = -1;
        }

        /* Regresa el índice. */
        @Override
        public int getIndice() {
            return this.indice;
        }

        /* Define el índice. */
        @Override
        public void setIndice(int indice) {
            this.indice = indice;
        }

        /* Compara un indexable con otro. */
        @Override
        public int compareTo(Adaptador<T> adaptador) {
            return this.elemento.compareTo(adaptador.elemento);
        }
    }


    /* numero de elementos en el arreglo */
    private int elementos;
    /* Nuestro arbol representado como arreglo */
    private T[] arbol;

    /* Con esto podemos crear arreglos genericos sin que el compilador marque error */
    @SuppressWarnings("unchecked") private T[] nuevoArreglo(int n){
        return (T[])(new ComparableIndexable[n]);
    }

    //Contructor
      public MonticuloMaximo(){
       elementos = 0;
       arbol = nuevoArreglo(100);
      }


    //Agregamos un elemento a nuestro Maxheap 
    @Override public void add(T elemento){
        if (elementos == arbol.length) {
            T[] nuevoArreglo = nuevoArreglo(elementos * 2);
            for (int i = 0; i < elementos; i++) {
                nuevoArreglo[i] = arbol[i];
            }
            arbol = nuevoArreglo;
        }
        arbol[elementos++] = elemento;
        int i = elementos - 1;
        while (i > 0 && arbol[i].compareTo(arbol[(i - 1) / 2]) > 0) {
            T temp = arbol[i];
            arbol[i] = arbol[(i - 1) / 2];
            arbol[(i - 1) / 2] = temp;
            arbol[i].setIndice(i);
            arbol[(i - 1) / 2].setIndice((i - 1) / 2);
            i = (i - 1) / 2;
        }
    }
    
    /**private void duplicaSize(){
        T[] arr = nuevoArreglo(arbol.length * 2);
        elementos = 0;
        for(T e: arbol){
            arr[elementos++] = e;
        }
        this.arbol = arr;
    }
    */
    
    @Override public boolean contains(T elemento){
        for(T e: arbol){
            if(elemento.equals(e))
                return true;
        }
        return false;
    }

    @Override public boolean isEmpty(){
        return elementos == 0;
    }
    
    @Override
    public void empty() {
        for (int i = 0; i < elementos; i++) {
            arbol[i] = null;
        }
        elementos = 0;
    }

    @Override
        public boolean delete(T elemento) {
            
            return false;
        }

    @Override
    public int size(){
        return elementos;
    }

    public T get(int i){
        if (i< 0 || i>= elementos) {
            throw new NoSuchElementException("Indice no valido");
        }
        return arbol[i];
    }


     public String toString(){
        String resultado ="";
        for (int i = 0; i <elementos; i++) {
            resultado += arbol[i].toString() + ",";
        }
        return resultado;
     }

    @Override
    public boolean equals(Object obj) {
        if(obj==null || getClass() != obj.getClass()){
            return false;
        }
        @SuppressWarnings("unchecked") MonticuloMaximo<T> monticulo = (MonticuloMaximo<T>)obj;
        if (elementos != monticulo.elementos) {
            return false;
        }
        for (int i = 0; i < elementos; i++) {
            if(!arbol[i].equals(monticulo.arbol[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * Regresa un iterador para iterar el montículo mínimo. El montículo se
     * itera en orden BFS.
     * 
     * @return un iterador para iterar el montículo mínimo.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }

        /**
     * Este metodo nos va a decir si es monticulo maximo dado un arreglo
     * @param a areglo  
     * @param i 
     * @param x
     * @return true or false.
     */
    public boolean esMontMax(int a[],int i, int x){
        if (i >= (x - 1) / 2){
        
            return true;
        }
        if (a[i] >= a[2 * i + 1] && a[i] >= a[2 * i + 2]
            && esMontMax(a, 2 * i + 1, x) && esMontMax(a, 2 * i + 2, x)){

            return true;
        }

        return false;
    }

    public void heapAux (int a[],int i, int x){
        int derecho = 2*i + 2;
        int izquierdo = 2*i + 1;
        int maxim = i;

        if (derecho < x && a[maxim] < a[derecho]){
            maxim = derecho;
        }
        
        if (izquierdo < x && a[maxim] < a[izquierdo]){
            maxim = izquierdo;
        }

        if (maxim != i){
            int temp = a[i];
            a[i] = a[maxim];
            a[maxim] = temp;
            heapAux(a, maxim, x);
        }
    }

    /**
    * Ordena la colección usando HeapSort.
    * @param <T> tipo del que puede ser el arreglo.
    * @param coleccion la colección a ordenar.
    * @return una lista ordenada con los elementos de la colección.
    */
    public static <T extends Comparable<T>> Lista<T> heapSort(Collection<T> coleccion) {
        Lista<Adaptador<T>> lAdaptador = new Lista<Adaptador<T>>();
        Lista<T> l = new Lista<T>();      
        return l;  
    }
    
    /**
     * Método que convierte un arreglo que representa un montículo mínimo a uno máximo en O(n)
     */
    public void MontMin_MontMax(int arreglo [], int n){

        for (int i = (n - 2) / 2; i >= 0; --i){

            heapAux(arreglo, i, n);
        }
    }

}