import java.util.ArrayList;

public class Vertice<T> {
    private T valor;
    
    private ArrayList<Aresta<T>> destinos = new ArrayList<>();

    public Vertice(T valor) {
        this.valor = valor;
    }
  
    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public void setDestinos(ArrayList<Aresta<T>> destinos) {
        this.destinos = destinos;
    }
    
    public ArrayList<Aresta<T>> getDestinos() {
        return destinos;
    }

    public void AddDestino(Aresta<T> destino){
        AdicionaDestino(destino);
    }

    private ArrayList<Aresta<T>> AdicionaDestino(Aresta<T> destino){
        destinos.add(destino);
        return destinos;
    }

    @Override
    public String toString(){
        return "Código: "+ this.valor;

    }

   
    
}
