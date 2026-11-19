public class Disciplina {
    private final int codigo;
    private final String nome;
    private final int vagas;
    private final ListaAlunos matriculados = new ListaAlunos();
    private final FilaEspera fila = new FilaEspera();

    public Disciplina(int codigo, String nome, int vagas) {
        this.codigo = codigo;
        this.nome = nome;
        this.vagas = vagas;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getVagas() {
        return vagas;
    }

    public ListaAlunos getMatriculados() {
        return matriculados;
    }

    public FilaEspera getFila() {
        return fila;
    }

    public String solicitarVaga(Aluno aluno) {
        int matricula = aluno.getMatricula();
        if (matriculados.buscar(matricula) != null) {
            return "Aluno ja matriculado nesta disciplina.";
        }
        if (fila.buscar(matricula) != null) {
            return "Aluno ja esta na fila desta disciplina.";
        }
        if (matriculados.tamanho() < vagas) {
            matriculados.adicionar(aluno);
            return "Matricula na disciplina confirmada para " + aluno.getNome() + ".";
        }
        fila.enfileirar(aluno);
        return "Disciplina lotada. " + aluno.getNome() + " entrou na posicao " + fila.tamanho() + " da fila.";
    }

    public String cancelarSolicitacao(int matricula) {
        if (matriculados.remover(matricula)) {
            Aluno promovido = fila.desenfileirar();
            if (promovido == null) {
                return "Matricula cancelada. A vaga esta livre.";
            }
            matriculados.adicionar(promovido);
            return "Matricula cancelada. " + promovido.getNome() + " recebeu a vaga pela ordem da fila.";
        }
        if (fila.remover(matricula)) {
            return "Aluno removido da fila de espera.";
        }
        return "Aluno nao estava matriculado nem na fila desta disciplina.";
    }

    public String resumo() {
        return codigo + " - " + nome + " | Vagas ocupadas: " + matriculados.tamanho()
                + "/" + vagas + " | Na fila: " + fila.tamanho();
    }

    public String detalhar() {
        return resumo() + "\nMatriculados:\n" + matriculados.listar()
                + "\nFila de espera:\n" + fila.listar();
    }
}
