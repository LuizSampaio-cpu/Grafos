public class Cidade {
    private int codigo;
    private String nome;

    public Cidade(int codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }

    public Cidade(Integer codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString(){
        return "Codigo: "+ this.codigo + "\nNome: "+ this.nome;
    }
    


}
