public class Curso {
    private final int codigo;
    private final String nome;
    private final ListaAlunos alunos = new ListaAlunos();
    private final ListaDisciplinas disciplinas = new ListaDisciplinas();

    public Curso(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public ListaAlunos getAlunos() {
        return alunos;
    }

    public ListaDisciplinas getDisciplinas() {
        return disciplinas;
    }

    @Override
    public String toString() {
        return codigo + " - " + nome + " | Alunos: " + alunos.tamanho()
                + " | Disciplinas: " + disciplinas.tamanho();
    }
}
