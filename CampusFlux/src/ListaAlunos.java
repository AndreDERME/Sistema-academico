public class ListaAlunos {
    private static class NoAluno {
        Aluno aluno;
        NoAluno proximo;

        NoAluno(Aluno aluno) {
            this.aluno = aluno;
        }
    }

    private NoAluno primeiro;
    private int tamanho;

    public boolean adicionar(Aluno aluno) {
        if (buscar(aluno.getMatricula()) != null) {
            return false;
        }

        NoAluno novo = new NoAluno(aluno);
        if (primeiro == null) {
            primeiro = novo;
        } else {
            NoAluno atual = primeiro;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novo;
        }
        tamanho++;
        return true;
    }

    public Aluno buscar(int matricula) {
        NoAluno atual = primeiro;
        while (atual != null) {
            if (atual.aluno.getMatricula() == matricula) {
                return atual.aluno;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public boolean remover(int matricula) {
        NoAluno anterior = null;
        NoAluno atual = primeiro;

        while (atual != null) {
            if (atual.aluno.getMatricula() == matricula) {
                if (anterior == null) {
                    primeiro = atual.proximo;
                } else {
                    anterior.proximo = atual.proximo;
                }
                tamanho--;
                return true;
            }
            anterior = atual;
            atual = atual.proximo;
        }
        return false;
    }

    public String listar() {
        if (primeiro == null) {
            return "Nenhum aluno.";
        }
        StringBuilder resultado = new StringBuilder();
        NoAluno atual = primeiro;
        while (atual != null) {
            resultado.append(atual.aluno).append("\n");
            atual = atual.proximo;
        }
        return resultado.toString();
    }

    public int tamanho() {
        return tamanho;
    }
}
