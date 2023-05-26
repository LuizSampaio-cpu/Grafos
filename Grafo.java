import java.util.ArrayList;

public class Grafo<T> {
    private ArrayList<Aresta<T>> arestas = new ArrayList<Aresta<T>>();
    private ArrayList<Vertice<T>> vertices = new ArrayList<Vertice<T>>();


    public ArrayList<Aresta<T>> getArestas() {
        return arestas;
    }
    public void setArestas(ArrayList<Aresta<T>> arestas) {
        this.arestas = arestas;
    }
    public ArrayList<Vertice<T>> getVertices() {
        return vertices;
    }
    public void setVertices(ArrayList<Vertice<T>> vertices) {
        this.vertices = vertices;
    }
    

    public Vertice<T> adicionarVertice(T valor){
        Vertice<T> novo = new Vertice<T>(valor);
        this.vertices.add(novo);
        return novo;
    }


    private Vertice<T> obterVertice(T valor){
        Vertice<T> v;

        for(int i = 0; i < this.vertices.size(); i++){
            v = this.vertices.get(i);
            if(v.getValor().equals(valor)){
                return v;
            }
        }
        
        // chegou até aqui, significa que não achou o vértice
        return null;
    }

    public void AddAresta(T origem, T destino, float peso){
        adicionaAresta(origem, destino, peso);
    }

    private void adicionaAresta(T origem, T destino, float peso){
        Vertice<T> verticeOrigem, verticeDestino;
        Aresta<T> novaAresta;

        //busca o vértice com o valor de origem
        verticeOrigem = obterVertice(origem);

        //caso não exista, cria um vértice com esse valor
        if (verticeOrigem == null){
           verticeOrigem = adicionarVertice(origem);
        }

        //repete o procedimento com o vértice de destino
        verticeDestino = obterVertice(destino);

        if (verticeDestino == null){
           verticeDestino = adicionarVertice(destino);
        }
        
        //cria a aresta que vai conectar os dois
        novaAresta = new Aresta<T>(verticeOrigem, verticeDestino, peso);

        // adiciona a aresta à lista de grafos
        this.arestas.add(novaAresta);

        verticeOrigem.AddDestino(new Aresta<T>(verticeOrigem, verticeDestino, peso));
    }
  

    public Vertice<T> BuscaEmLargura(){
        // cria uma fila de vértices marcados
        ArrayList<Vertice<T>> marcados = new ArrayList<Vertice<T>>();

        // cria uma fila de vértices a serem visitados
        ArrayList<Vertice<T>> fila = new ArrayList<Vertice<T>>();

        Vertice<T> atual = this.vertices.get(0);
        // adiciona o nó atual à fila de nós ainda não visitados
        fila.add(atual);
        
        while(fila.size() > 0){
            // pega o primeiro vértice da fila e o remove, depois o adiciona na lista de marcados
            atual = fila.get(0);
            fila.remove(0);
            marcados.add(atual);
            // retorna o valor daquele vértice
            System.out.println(atual.getValor());
            //obtém os possíveis destinos que se podem chegar por aquele vértice
            ArrayList<Aresta<T>> destinos = this.obterDestinos(atual);
            Vertice<T> proximo;

            for(int i = 0; i < destinos.size(); i++){
                // o proximo vértice a ser visitado será um dos destinos do atual
                proximo = destinos.get(i).getDestino();
                // se ele não tiver sido visitado ainda e não estiver na fila, adiciona-o à fila para acessá-lo
                if(!marcados.contains(proximo) && !fila.contains(proximo)){
                    fila.add(proximo);
                }
            }
        }
        return atual;
    }


    private ArrayList<Aresta<T>> obterDestinos(Vertice<T> vertice){
        // cria a lista de destinos do vértice
        ArrayList<Aresta<T>> destinos =  new ArrayList<Aresta<T>>();
        Aresta<T> atual;
        for(int i = 0; i < this.arestas.size(); i++){
            atual = this.arestas.get(i);
            if(atual.getOrigem().equals(vertice))
                destinos.add(atual);
        }
        return destinos;
    }   
}