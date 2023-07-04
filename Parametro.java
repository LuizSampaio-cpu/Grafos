//a classe abaixo foi gerada para auxiliar no armazenamento de uma aresta e um vértice em um só item de uma lista, tirando a necessidade
//de criar uma lista para vértices e uma para arestas

public class Parametro<T, U> {
    private T item1;
    private U item2;

    public Parametro(T item1, U item2){
        this.item1 = item1;
        this.item2 = item2;
    }
    public Parametro(){}


    public T getItem1() {
        return item1;
    }
    public void setItem1(T item1) {
        this.item1 = item1;
    }
    public U getItem2() {
        return item2;
    }
    public void setItem2(U item2) {
        this.item2 = item2;
    }

}
