/*
 author: @LuizSampaio-cpu
 Luiz Sampaio Horta- 20202bsi0390
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


public class Grafo<T> {
    private ArrayList<Aresta<T>> arestas = new ArrayList<Aresta<T>>();
    private ArrayList<Vertice<T>> vertices = new ArrayList<>();


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
        vertices.add(novo);
        return novo;
    }

    public Vertice<T> obtemVertice(T valor){
        return obterVertice(valor);
    }


    private Vertice<T> obterVertice(T valor){

        for(Vertice<T> v : vertices){
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
  

    public void BuscaEmLargura(T original){
        // cria uma fila de vértices marcados
        ArrayList<Vertice<T>> marcados = new ArrayList<Vertice<T>>();
        // cria uma fila de vértices a serem visitados
        ArrayList<Vertice<T>> fila = new ArrayList<Vertice<T>>();

        Vertice<T> atual = obtemVertice(original);
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
            ArrayList<Aresta<T>> destinos = atual.getDestinos();
            Vertice<T> proximo;

            for(int i = 0; i < destinos.size(); i++){
                // o proximo vértice a ser visitado será um dos destinos do atual
                proximo = destinos.get(i).getDestino();
                // se ele não tiver sido visitado ainda e não estiver na fila, adiciona-o à fila para acessá-lo
                if(!marcados.contains(proximo) && !fila.contains(proximo)){
                    fila.add(proximo);

                    marcados.add(proximo);
                }
            }
        }
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

 
    public Grafo<T> ArvoreMinima() {
        Grafo<T> arvore = new Grafo<>(); // Cria a árvore que será retornada no final
        Set<Vertice<T>> marcados = new HashSet<>(); // Cria o conjunto de vértices visitados

        PriorityQueue<Aresta<T>> fila = new PriorityQueue<>(Comparator.comparingDouble(Aresta::getPeso)); // Cria a fila de prioridade para as arestas

        Vertice<T> atual = this.getVertices().get(0); // O vértice atual começa como o primeiro do grafo

        marcados.add(atual); // Adiciona o vértice atual aos marcados

        arvore.adicionarVertice(atual.getValor()); // Adiciona o vértice atual na árvore

        for (Aresta<T> aresta : atual.getDestinos()) { // Adiciona todas as arestas adjacentes do vértice atual à fila
            fila.add(aresta);
        }

        while (!fila.isEmpty()) {
            Aresta<T> aresta = fila.poll(); // Remove a aresta de menor peso da fila

            Vertice<T> destino = aresta.getDestino();

            if (!marcados.contains(destino)) { // Se o destino ainda não foi marcado, adiciona-o na árvore e marca-o
                marcados.add(destino);

                arvore.adicionarVertice(destino.getValor()); // Adiciona o vértice de destino na árvore
                arvore.adicionaAresta(atual.getValor(), destino.getValor(), aresta.getPeso()); // Adiciona a aresta na árvore

                for (Aresta<T> novaAresta : destino.getDestinos()) { // Adiciona as arestas do vértice de destino na fila
                    if (!marcados.contains(novaAresta.getDestino())) {
                        fila.add(novaAresta);
                    }
                }

                atual = destino; // Atualiza o vértice atual para o destino da aresta
            }
        }

        float pesoFinal = arvore.PesoFinal();
        System.out.println("Peso final: " + pesoFinal);

        return arvore;
    }

    private float PesoFinal() {
        float pesoTotal = 0;

        Set<Aresta<T>> arestasVisitadas = new HashSet<>(); // Usando um conjunto para evitar duplicações

        for (Vertice<T> vertice : this.vertices) {
            ArrayList<Aresta<T>> arestas = vertice.getDestinos();

            for (Aresta<T> aresta : arestas) {
                Vertice<T> destino = aresta.getDestino();
                if (this.vertices.contains(destino) && !arestasVisitadas.contains(aresta)) {
                    pesoTotal += aresta.getPeso();
                    arestasVisitadas.add(aresta);
                }
            }
        }

        return pesoTotal;
    }


}   