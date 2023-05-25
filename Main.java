import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Grafo<Cidade> testeGrafo = new Grafo<Cidade>();

        String path = "/workspace/Grafos/entrada.txt";

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


            }
        }
        catch(IOException e){
            System.out.println("Erro: "+ e.getMessage());
        }
    }
    
}
