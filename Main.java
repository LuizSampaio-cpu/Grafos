import java.io.BufferedReader;
//import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Grafo<Cidade> mapa = new Grafo<Cidade>();

        String path = "entrada.txt";

        Scanner s = new Scanner(System.in);

        int quantidade = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line = br.readLine();
            // recebe a quantidade de cidades no arquivo
            quantidade = Integer.parseInt(line);

            // lê a primeira cidade
            line = br.readLine();
            for(int i = 0; i < quantidade; i++){
                // lê as próximas
                String data[] = line.split(",");
                Cidade cidade = new Cidade(Integer.parseInt(data[0]), data[1]);

                mapa.adicionarVertice(cidade);
                line = br.readLine();
            }
            // lê a matriz com os pesos das estradas entre cidades
            for (int i = 0; i < quantidade; i++){
                //System.out.println(line);
                String data[] = line.split(",");
                Cidade origem = mapa.getVertices().get(i).getValor();
                for (int j = 0; j < data.length; j++){
                    // verifica se o peso da aresta é maior que 0, porque se for 0, não há aresta
                   if(Float.parseFloat(data[j]) > 0){
                    Cidade destino = mapa.getVertices().get(j).getValor();
                    mapa.AddAresta(origem, destino, Float.parseFloat(data[j]));
                   } 
                }

                line = br.readLine();

            }
            br.close();
        }
        
        catch(IOException e){
            System.out.println("Erro: "+ e.getMessage());
        }



        // Menu para receber as entradas do usuário
        //System.out.println("Quantidade de cidades no grafo: "+ quantidade);
        System.out.println("*****MENU*****\n");
        System.out.println("O QUE DESEJA FAZER?\n");
        System.out.println("1.OBTER CIDADES VIZINHAS\n");
        System.out.println("2.OBTER OS CAMINHOS A PARTIR DE UM PONTO\n");
        System.out.println("3.SAIR\n");
        
        int resposta = s.nextInt();
        s.nextLine();

        while(resposta != 3){
            if(resposta == 1){
                System.out.println("Insira o código da cidade: ");
                resposta = s.nextInt();
                //mostra as cidades vizinhas da cidade
                // esse vétice vai ser usado como comparador para procurar a equivalente dele no grafo Mapa
                Vertice<Cidade> comparador = new Vertice<Cidade>(new Cidade());
                comparador.setValor(mapa.getVertices().get(resposta).getValor());
                comparador = mapa.obtemVertice(comparador.getValor());
                if(comparador == null){ 
                    // se o valor passado pelo comparador for null, significa que não tem uma cidade 
                    // com esse código no mapa
                    System.out.println("Não há uma cidade com este código no mapa");
                }
                else {
                    for (int index = 0; index < comparador.getDestinos().size(); index++) {
                        Aresta<Cidade> i = comparador.getDestinos().get(index);
                        Cidade c = (Cidade) i.getDestino().getValor();
                        System.out.println("Código: "+ c.getCodigo());
                        System.out.println("Nome: "+ c.getNome());
                        System.out.println("Distância: "+ i.getPeso());

                    
                    }
                    
                }
                System.out.println("O QUE FAZER A SEGUIR?");
                resposta = s.nextInt();
            }
            else if(resposta == 2){
                System.out.println("Insira o código da cidade: ");
                Vertice<Cidade> comparador = new Vertice<Cidade>(new Cidade());
                resposta  = s.nextInt();
                //mostra os destinos possíveis saindo da cidade
                comparador.setValor(mapa.getVertices().get(resposta).getValor());
                comparador = mapa.obtemVertice(comparador.getValor());
                if(comparador == null){ 
                    // se o valor passado pelo comparador for null, significa que não tem uma cidade 
                    // com esse código no mapa
                    System.out.println("Não há uma cidade com este código no mapa");
                }
                else {
                    mapa.BuscaEmLargura(comparador.getValor());
                }
                System.out.println("O QUE FAZER A SEGUIR?");
                resposta = s.nextInt();
            }
        }
        System.out.println("FIM");

        s.close();     


    }
    
}
