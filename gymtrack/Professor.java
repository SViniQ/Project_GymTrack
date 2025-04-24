package gymtrack;

public class Professor extends Pessoa {
    private int qtd_alunos;
    private double salario;
    boolean mensalidade_paga;

    public Professor(String nome, int idade, String email, String cpf, String telefone, char sexo, int qtd_alunos, double salario, boolean mensalidade_paga) {
        super(nome, idade, email, cpf, telefone, sexo);
        this.qtd_alunos = qtd_alunos;
        this.salario = salario;
        this.mensalidade_paga = mensalidade_paga;
    }

    public int getQtd_alunos() {
        return qtd_alunos;
    }

    public void setQtd_alunos(int qtd_alunos) {
        this.qtd_alunos = qtd_alunos;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public boolean isMensalidade_paga() {
        return mensalidade_paga;
    }

    public void setMensalidade_paga(boolean mensalidade_paga) {
        this.mensalidade_paga = mensalidade_paga;
    }

    public void showInfo(){
        super.showInfo();
        System.out.println("Salario: " + salario);
        System.out.println("Quantidade de alunos: " + qtd_alunos);
        System.out.println("Mensalidade paga: " + mensalidade_paga);
    }

    @Override
    public String toString() {
        return super.toString() +
                "Quantidade de alunos: " + qtd_alunos + "\n" +
                "Salario: " + salario +  "\n" +
                "Mensalidade foi paga? " + (mensalidade_paga  ? "Sim" : "Nao") + "\n"
                ;
    }
}
