/*
 author: @LuizSampaio-cpu
 Luiz Sampaio Horta- 20202bsi0390
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
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
                arvore.adicionaAresta(destino.getValor(), atual.getValor(), aresta.getPeso()); // adiciona o caminho de volta

                for (Aresta<T> novaAresta : destino.getDestinos()) { // Adiciona as arestas do vértice de destino na fila
                    if (!marcados.contains(novaAresta.getDestino())) {
                        fila.add(novaAresta);
                    }
                }

                atual = destino; // Atualiza o vértice atual para o destino da aresta
            }
        }
        float pesoFinal = arvore.PesoFinal() / 2;
        System.out.println("Peso final: " + pesoFinal + "\n\n");
        for(int i = 0; i < arvore.getArestas().size()  / 2; i++){
            System.out.println("Vertice: " + arvore.getVertices().get(i).getValor() + "\n");
            System.out.println("Para: " + arvore.getArestas().get(i).getDestino());
            System.out.println("Peso: "+ arvore.getArestas().get(i).getPeso());
        }

        

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


    public void CaminhoDijkstra(T origem, T destino){
        //Lista de distâncias 
        ArrayList<Double> distancias = new ArrayList<>();
        //Lista de antecessores de um vértice
        ArrayList<Integer> antecessores = new ArrayList<>();
        //Lista de visitados
        ArrayList<Vertice<T>> marcados = new ArrayList<>();

        //primeiro, preenche a lista de distancias com o maior valos possível(infinito)
        for(int i = 0; i <= vertices.size(); i++){
            distancias.add(Double.POSITIVE_INFINITY);
            antecessores.add(null);
        }

        //pega o vértice de origem passado como parâmetro
        Vertice<T> atual = obtemVertice(origem);
        Integer verOrigem = vertices.indexOf(atual);
        distancias.set(verOrigem, 0.0);

        while(marcados.size() < vertices.size() - 1){
            marcados.add(atual);
        

            ArrayList<Aresta<T>> listaArestas = atual.getDestinos();
            for(Aresta<T> aresta: listaArestas){
                int IndiceAresta = vertices.indexOf(aresta.getDestino());
                Double somaArestas = distancias.get(verOrigem) + aresta.getPeso();

                if(!marcados.contains(verOrigem) && distancias.get(IndiceAresta) > somaArestas){
                    distancias.set(IndiceAresta, somaArestas);
                    antecessores.set(IndiceAresta, verOrigem);
                }
            }

            Double menoresDistancias = Double.POSITIVE_INFINITY;
            for(int i = 0; i < distancias.size() - 1; i++){
                if(!marcados.contains(vertices.get(i))){
                    if(menoresDistancias >= distancias.get(i)){
                        menoresDistancias = distancias.get(i);
                        verOrigem = i;
                    }
                }
            }

            atual = vertices.get(distancias.indexOf(menoresDistancias));

        }
        for(int i = 0; i < vertices.size(); i++){
            Cidade cidade = ((Cidade)vertices.get(i).getValor());
            System.out.println("\n\tIndice: " + i + " | código-cidade: (" + cidade.getCodigo() + "," + cidade.getNome() + 
                ") | distância: " + distancias.get(i) + " | predecessor: " + antecessores.get(i));
        }

    }
    public float fluxoMaximo(Vertice<T> origem, Vertice<T> destino) {
        float fluxoMax = 0;

        // Criar uma cópia dos grafos residuais para realizar as alterações sem afetar o grafo original
        ArrayList<Vertice<T>> grafosResiduais = criarGrafosResiduais();

        // Enquanto existir um caminho entre a origem e o destino no grafo residual
        ArrayList<Vertice<T>> caminho = encontrarCaminho(grafosResiduais, origem, destino);
        while (caminho != null) {
            // Encontrar a capacidade mínima ao longo do caminho
            float capacidadeMinima = encontrarCapacidadeMinima(caminho);

            // Atualizar as capacidades das arestas ao longo do caminho
            atualizarCapacidades(caminho, capacidadeMinima);

            // Adicionar o fluxo ao fluxo máximo
            fluxoMax += capacidadeMinima;

            // Encontrar um novo caminho no grafo residual
            caminho = encontrarCaminho(grafosResiduais, origem, destino);
        }

        return fluxoMax;
    }

    private ArrayList<Vertice<T>> criarGrafosResiduais() {
        ArrayList<Vertice<T>> grafosResiduais = new ArrayList<>();

        // Criar uma cópia dos vértices para os grafos residuais
        for (Vertice<T> vertice : vertices) {
            Vertice<T> novoVertice = new Vertice<>(vertice.getValor());
            grafosResiduais.add(novoVertice);
        }

        // Criar as arestas nos grafos residuais
        for (Vertice<T> vertice : vertices) {
            Vertice<T> verticeResidual = grafosResiduais.get(vertices.indexOf(vertice));
            for (Aresta<T> aresta : vertice.getDestinos()) {
                Vertice<T> destino = grafosResiduais.get(vertices.indexOf(aresta.getDestino()));
                float capacidade = aresta.getPeso();
                Aresta<T> arestaResidual = new Aresta<>(destino, capacidade);
                verticeResidual.AddDestino(arestaResidual);
            }
        }

        return grafosResiduais;
    }

    private ArrayList<Vertice<T>> encontrarCaminho(ArrayList<Vertice<T>> grafosResiduais, Vertice<T> origem, Vertice<T> destino) {
        Queue<Vertice<T>> fila = new LinkedList<>();
        ArrayList<Vertice<T>> visitados = new ArrayList<>();
        ArrayList<Vertice<T>> antecessores = new ArrayList<>();

        // Inicialização
        fila.add(origem);
        visitados.add(origem);
        antecessores.add(null);

        while (!fila.isEmpty()) {
            Vertice<T> verticeAtual = fila.poll();

            // Percorrer os destinos do vértice atual
            for (Aresta<T> aresta : verticeAtual.getDestinos()) {
                Vertice<T> vizinho = aresta.getDestino();

                // Verificar se o vizinho não foi visitado e a capacidade da aresta é maior que zero
                if (!visitados.contains(vizinho) && aresta.getPeso() > 0) {
                    fila.add(vizinho);
                    visitados.add(vizinho);
                    antecessores.add(verticeAtual);

                    // Verificar se chegou ao destino
                    if (vizinho.equals(destino)) {
                        // Construir o caminho a partir dos antecessores
                        ArrayList<Vertice<T>> caminho = new ArrayList<>();
                        int indice = visitados.indexOf(destino);
                        while (indice != 0) {
                            caminho.add(visitados.get(indice));
                            indice = visitados.indexOf(antecessores.get(indice));
                        }
                        caminho.add(origem);

                        // Inverter o caminho para ter a ordem correta
                        ArrayList<Vertice<T>> caminhoInvertido = new ArrayList<>();
                        for (int i = caminho.size() - 1; i >= 0; i--) {
                            caminhoInvertido.add(caminho.get(i));
                        }

                        return caminhoInvertido;
                    }
                }
            }
        }

        return null;
    }

    private float encontrarCapacidadeMinima(ArrayList<Vertice<T>> caminho) {
        float capacidadeMinima = Float.MAX_VALUE;

        for (int i = 0; i < caminho.size() - 1; i++) {
            Vertice<T> verticeAtual = caminho.get(i);
            for (Aresta<T> aresta : verticeAtual.getDestinos()) {
                if (aresta.getDestino().equals(caminho.get(i + 1)) && aresta.getPeso() < capacidadeMinima) {
                    capacidadeMinima = aresta.getPeso();
                    break;
                }
            }
        }

        return capacidadeMinima;
    }

    private void atualizarCapacidades(ArrayList<Vertice<T>> caminho, float capacidadeMinima) {
        for (int i = 0; i < caminho.size() - 1; i++) {
            Vertice<T> verticeAtual = caminho.get(i);
            for (Aresta<T> aresta : verticeAtual.getDestinos()) {
                if (aresta.getDestino().equals(caminho.get(i + 1))) {
                    aresta.setPeso(aresta.getPeso() - capacidadeMinima);
                    break;
                }
            }
        }
    }
    
    

}



