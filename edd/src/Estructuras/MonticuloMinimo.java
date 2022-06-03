package edd.src.Estructuras;

import java.time.Year;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** 
 * 
 * Clase para monticulos minimos (Minheaps)
*/
public class MonticuloMinimo<T extends ComparableIndexable<T>> implements Collection<T>{
    
    
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

    public MonticuloMinimo(){
        elementos = 0;
        arbol = nuevoArreglo(100);
    }



    public MonticuloMinimo(Iterable<T> iterable, int n ){
        elementos = n;
        arbol = nuevoArreglo(n);
        int i = 0;
        for (T e : iterable) {
           arbol[i] = e;
           arbol[i].setIndice(i);
           i ++;
        }
        for(int j = (elementos-1) /2; j >= 0; j--){
            monticuloMin(j);
            
        }
    }

    private void monticuloMin(int i){
        int izq = i * 2 +1 ;
        int der = i * 2 + 2;
        int minimo = i;

        if (elementos <= i) {
            return;
        }
        if(izq < elementos && arbol[izq].compareTo(arbol[i]) < 0){
            minimo = izq;
        }
        if(der < elementos && arbol[der].compareTo(arbol[minimo]) < 0){
            minimo = der;
        }
        if(minimo == i){
            return;
        }

        else{
            swap(arbol[minimo],arbol[i]);
        }
    }

    @Override public void add(T elemento){
        if (elementos == arbol.length) {
            duplicaSize();
        }
        elemento.setIndice(elementos);
        arbol[elementos] = elemento;
        elementos++;
        recorreArriba(elementos - 1);
    }

    private void duplicaSize(){
        T[] arr = nuevoArreglo(arbol.length * 2);
        elementos = 0;
        for(T e: arbol){
            arr[elementos++] = e;
        }
        this.arbol = arr;
    }

    private void recorreArriba(int i){
        int padre = (i-1) / 2;
        int m = i;
        if(padre >= 0 && arbol[padre].compareTo(arbol[i]) > 0){
            m = padre;
        }
        if (m!= i) {
            this.swap(arbol[i],arbol[m]);
            recorreArriba(m);
        }
    }
    
    /**
     * Elimina el elemento minimo del monticulo
     * 
     */
    public T delete(){
        if(elementos == 0){
            throw new IllegalStateException("Monticulo vacio");
        }
        T e = arbol[0];
        boolean bool = delete(e);
        if(bool){
            return e;
        }
        else{
            return null;
        }

    }

    /**
     * Elimina un elmento del monticulo
     * 
     */

    @Override public boolean delete(T elemento){
        if(elemento ==null || isEmpty() ){
            return false;
        }
        if(!contains(elemento)){
            return false;
        }
        int i = elemento.getIndice();
        if(i <0 || elementos <=i )
            return false;
        swap(arbol[i], arbol[elementos -1]);
        arbol[elementos -1] = null;
        elementos --;
        recorreAbajo(i);
        return true;
    }
    
    private void swap(T i, T j) {
        int aux = j.getIndice();
        arbol[i.getIndice()] = j;
        arbol[j.getIndice()] = i;
        j.setIndice(i.getIndice());
        i.setIndice(aux);
    }

    private void recorreAbajo(int i){
        if(i < 0){
            return;
        }
        int izq = 2*i +1;
        int der = 2*i +2;
        int min = der;
        //No existen
        //  0, 1
        // [],[]
        if(izq >= elementos && der >= elementos){
            return;
        }
        if(izq < elementos){
            if (der < elementos) {
                if (arbol[izq].compareTo(arbol[der]) <0 ) {
                    min = izq;
                }
            }
            else{
                min = izq;
            }
        }
        if(arbol[min].compareTo(arbol[i])<0){
            //Este swap ya esta 
            swap(arbol[i], arbol[min]);
            
            recorreAbajo(i);
            
        }
        
        

    }


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
    public int size(){
        return elementos;
    }

    public T get(int i){
        if (i< 0 || i>= elementos) {
            throw new NoSuchElementException("Indice no valido");
        }
        return arbol[i];
    }


    @Override public String toString(){
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
        @SuppressWarnings("unchecked") MonticuloMinimo<T> monticulo = (MonticuloMinimo<T>)obj;
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
     * Metodo que nos dice si el parametro es un monticulo minimo.
     * @param X
     * @return true si el arreglo en el parámetro es un montículo mínimo.
     */
    public static boolean esMontMin(int[] X){
        if (X.length <= 1) {
            
            return true;
        }
        for (int i = 0; i <= (X.length - 2) / 2; i++){

            if (X[i] > X[2*i + 1] || (2*i + 2 != X.length && X[i] > X[2*i + 2])) {

                return false;
            }
        }

        return true;
    }


    public void heapAux(int a[], int n, int sz){
        int men = n; 
        int derecho = 2 * n + 2;
        int izquierdo = 2 * n + 1;
 
        //Caso en el que el hijo derecho será menor que el padre
        if (sz > derecho && a[men] > a[derecho])
            men = derecho;
        
        //Caso en el que el hijo izquierdo será menor que el padre
        if (sz > izquierdo && a[n] > a[izquierdo])
            men = izquierdo;
 
        // Caso en el que el menor resulta ser diferente al padre
        if (men != n) {
            swapB(a, n, men);
            heapAux(a, men, sz);
        }
    }

    public void swapB (int N[] , int i, int j){

        int temp = N[i];
        N[i] = N[j];
        N[j] = temp;
    }

    
    public void heapSort(int a[], int x){
        // Va a construir el heap
        for (int i = x / 2 - 1; i >= 0; i--)
            heapAux(a, x, i);
 
        for (int i = x - 1; i >= 0; i--) {            
            // Se moverá la raiz actual hacia el final 
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            heapAux(a, x, 0);
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
     * Método que convierte un arreglo que representa un montículo máximo a uno minimo en O(n)
     */
    public void MontMax_MontMin(int[] X){
        int i = (X.length - 2) / 2;

         while (i >= 0) {
        
            heapAux(X, i--, X.length);
        }
    }
    
}