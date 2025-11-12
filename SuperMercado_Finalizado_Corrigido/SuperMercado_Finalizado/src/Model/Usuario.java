package Model;

public class Usuario {
    private String nome;
    private String cpf;
    private boolean funcao;

    public Usuario(String nome, String cpf, boolean funcao) {
        this.nome = nome;
        this.cpf = cpf;
        this.funcao = funcao;
    }

    public Usuario() {
    }

    public void imprimir() {
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        if (this.funcao) {
            System.out.println("Função: Administrador");
        } else {
            System.out.println("Função: Funcionário");
        }
        System.out.println("##################################");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    // ❌ ERRADO antes: cpf = cpf; (não atribuía ao atributo da classe)
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isFuncao() {
        return funcao;
    }

    public void setFuncao(boolean funcao) {
        this.funcao = funcao;
    }
}
