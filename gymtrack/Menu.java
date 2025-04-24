package gymtrack;

import repositorio.Repositorio;

import java.util.Scanner;


public class Menu {

    private Scanner scanner;
    private Repositorio repositorio = new Repositorio();

    public Menu(){
        scanner = new Scanner(System.in);
    }

    public void menu(){
        int opcao;

        do{
            System.out.println("=== GymTrack System ===");
            System.out.println("1. Criar treinador");
            System.out.println("2. Criar aluno");
            System.out.println("3. Listar treinadores");
            System.out.println("4. Listar alunos");
            System.out.println("0. Exit");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch(opcao){
                case 1:
                    criarTreinador();
                    break;
                case 2:
                    criarAluno();
                    break;
                case 3:
                    listarTreinadores();
                    break;
                case 4:
                    listarAlunos();
                    break;
            }
        } while (opcao != 0);
    }

    public void criarTreinador(){
        System.out.println("Criando treinador...");

        System.out.println("Digite o nome: ");
        String nomeTreinador = scanner.nextLine();

        System.out.println("Digite a idade: ");
        int idadeTreinador = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o email: ");
        String emailTreinador = scanner.nextLine();

        System.out.println("Digite o CPF: ");
        String cpfTreinador = scanner.nextLine();

        System.out.println("Digite o telefone: ");
        String telefoneTreinador = scanner.nextLine();

        System.out.println("Digite sexo: ");
        String sexoTreinador =  scanner.nextLine();

        System.out.println("Digite a quantidade de alunos que o professor vai auxiliar: ");
        int quantidadeAlunos = scanner.nextInt();

        System.out.println("Digite o salario que o professor vai ganhar: ");
        double salarioProfessor = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("A mensalidade do professor ja foi paga? (S/N) ");
        String mensalidade_paga = scanner.nextLine();
        boolean foiPaga = mensalidade_paga.equalsIgnoreCase("S");

        Professor professor = new Professor(
                nomeTreinador,
                idadeTreinador,
                emailTreinador,
                cpfTreinador,
                telefoneTreinador,
                sexoTreinador.charAt(0),
                quantidadeAlunos,
                salarioProfessor,
                foiPaga
        );

        repositorio.addProfessor(professor);

        System.out.println("Professor criado com sucesso!");

    }

    public void criarAluno(){
        System.out.println("Criando aluno...");

        System.out.println("Digite o nome: ");
        String nomeAluno = scanner.nextLine();

        System.out.println("Digite a idade: ");
        int idadeAluno = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o email: ");
        String emailAluno = scanner.nextLine();

        System.out.println("Digite o CPF: ");
        String cpfAluno = scanner.nextLine();

        System.out.println("Digite o telefone: ");
        String telefoneAluno = scanner.nextLine();

        System.out.println("Digite sexo: ");
        String sexoAluno =  scanner.nextLine();

        System.out.println("Digite o peso: ");
        double peso = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Digite a altura: ");
        double altura = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Digite o percentual de gordura ");
        double percentual_gordura = scanner.nextDouble();


        Aluno aluno = new Aluno(
                nomeAluno,
                idadeAluno,
                emailAluno,
                cpfAluno,
                telefoneAluno,
                sexoAluno.charAt(0),
                peso,
                altura,
                percentual_gordura
        );

        repositorio.addAluno(aluno);

        System.out.println("Aluno criado com sucesso!");

    }

    public void listarTreinadores() {
        System.out.println("Listando treinadores...");

        for(Professor p: repositorio.getTreinadores()){
            System.out.println(p);
        }
    }

    public void listarAlunos() {
        System.out.println("Listando alunos..");

        for(Aluno a: repositorio.getAlunos()){
            System.out.println(a);
        }
    }
}
