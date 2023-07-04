public class Aresta<T> implements Comparable<Aresta<T>>{
    private Vertice<T> origem, destino;
    private float peso;


    public Aresta(Vertice<T> origem, Vertice<T> destino, float peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public Aresta(float peso) {
        this.peso = peso;
    }
    public Aresta(Vertice<T> destino, float peso){
        this.destino = destino;
        this.peso = peso;
    }

    public Vertice<T> getOrigem() {
        return origem;
    }
    
    public Vertice<T> getDestino() {
        return destino;
    }
    
    public float getPeso() {
        return peso;
    }
    
    
    @Override
    public int compareTo(Aresta<T> aresta){
        return Float.compare(this.peso, aresta.getPeso());
    }

    @Override
    public String toString(){
        return "destino: " + destino + "\nDistância :" + peso;
    }

    public void setOrigem(Vertice<T> origem) {
        this.origem = origem;
    }

    public void setDestino(Vertice<T> destino) {
        this.destino = destino;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }


    
}
