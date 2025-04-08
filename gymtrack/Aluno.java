package gymtrack;

public class Aluno extends Pessoa{
    private double peso;
    private double altura;
    private double imc;
    private double percentual_gordura;


    public Aluno(String nome, int idade, String email, String cpf, String telefone, char sexo, double peso, double altura,  double percentual_gordura) {
        super(nome, idade, email, cpf, telefone, sexo);
        this.peso = peso;
        this.altura = altura;
        this.percentual_gordura = percentual_gordura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getIMC() {
        return imc;
    }

    public void setIMC(double IMC) {
        this.imc = IMC;
    }

    public double getPercentualGordura() {
        return percentual_gordura;
    }

    public void setPercentualGordura(double percentual_gordura) {
        this.percentual_gordura = percentual_gordura;
    }

    private  double calculaIMC() {
        imc = peso / (altura * altura);
        return imc;
    }

    public void showInfo(){
        super.showInfo();
        System.out.println("Altura: " + peso);
        System.out.println("Altura: " + altura);
        System.out.println("IMC: " + calculaIMC());
        System.out.println("Percentual de gordura: " + percentual_gordura + "%");
    }
}
