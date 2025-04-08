package gymtrack;

public class Aluno extends Pessoa{
    private double peso;
    private double altura;
    private double IMC;
    private double percentual_gordura;

    public Aluno(String nome, int idade, String email, String cpf, String telefone, char sexo, double peso, double altura, double IMC, double percentual_gordura) {
        super(nome, idade, email, cpf, telefone, sexo);
        this.peso = peso;
        this.altura = altura;
        this.IMC = IMC;
        this.percentual_gordura = percentual_gordura;
    }
}
