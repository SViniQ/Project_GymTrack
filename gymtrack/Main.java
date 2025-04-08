package gymtrack;

public class Main {
    public static void main(String[] args) {
       System.out.println("Bem vindo ao Gym Track!");

       Aluno aluno = new Aluno("Dereck", 31, "dereck@test.com", "99999999999", "999999999", 'M', 81.5, 1.82, 20.5);
        System.out.println("Info dos alunos: ");
        aluno.showInfo();
    }
}