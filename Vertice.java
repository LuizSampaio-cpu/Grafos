import java.util.ArrayList;

public class Vertice<T> {
    private T valor;
    
    private ArrayList<Aresta<T>> destinos = new ArrayList<Aresta<T>>();
    
    public ArrayList<Aresta<T>> getDestinos() {
        return destinos;
    }

    public void setDestinos(ArrayList<Aresta<T>> destinos) {
        this.destinos = destinos;
    }    

    public Vertice(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public void AddDestino(Aresta<T> destino){
        AdicionaDestino(destino);
    }

    private ArrayList<Aresta<T>> AdicionaDestino(Aresta<T> destino){
        this.destinos.add(destino);
        return destinos;
    }

    public String toString(){
        return "Código: "+ this.valor;

    }
    
}
