import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        Grafo<Cidade> testeGrafo = new Grafo<Cidade>();

        String path = "C:\\Users\\luizh\\OneDrive\\Documentos\\TPA (java)\\Grafos\\entrada.txt";

        int quantidade = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line = br.readLine();
            // recebe a quantidade de cidades no arquivo
            quantidade = Integer.parseInt(line);

            // lê a primeira cidade
            line = br.readLine();
            for(int i = 0; i < quantidade; i++){
                String data[] = line.split(",");
                Cidade cidade = new Cidade(Integer.parseInt(data[0]), data[1]);


            }
        }
        catch(IOException e){
            System.out.println("Erro: "+ e.getMessage());
        }
    }
    
}
