package gymtrack;
import java.util.ArrayList;
import java.util.List;

public class Repositorio {
    private List<Professor> professors = new ArrayList<>();
    private List<Aluno> alunos = new ArrayList<>();

    public void addProfessor(Professor professor) {
        professors.add(professor);
    }
    public void addAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Professor> getTreinadores() {
        return professors;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }
}
