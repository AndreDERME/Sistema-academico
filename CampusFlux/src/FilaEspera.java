public class FilaEspera {
    private static class NoEspera {
        Aluno aluno;
        NoEspera proximo;

        NoEspera(Aluno aluno) {
            this.aluno = aluno;
        }
    }

    private NoEspera inicio;
    private NoEspera fim;
    private int tamanho;

    public boolean enfileirar(Aluno aluno) {
        if (buscar(aluno.getMatricula()) != null) {
            return false;
        }
        NoEspera novo = new NoEspera(aluno);
        if (fim == null) {
            inicio = novo;
        } else {
            fim.proximo = novo;
        }
        fim = novo;
        tamanho++;
        return true;
    }

    public Aluno buscar(int matricula) {
        NoEspera atual = inicio;
        while (atual != null) {
            if (atual.aluno.getMatricula() == matricula) {
                return atual.aluno;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public Aluno desenfileirar() {
        if (inicio == null) {
            return null;
        }
        Aluno aluno = inicio.aluno;
        inicio = inicio.proximo;
        if (inicio == null) {
            fim = null;
        }
        tamanho--;
        return aluno;
    }

    public boolean remover(int matricula) {
        NoEspera anterior = null;
        NoEspera atual = inicio;
        while (atual != null) {
            if (atual.aluno.getMatricula() == matricula) {
                if (anterior == null) {
                    inicio = atual.proximo;
                } else {
                    anterior.proximo = atual.proximo;
                }
                if (atual == fim) {
                    fim = anterior;
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
        if (inicio == null) {
            return "Fila vazia.";
        }
        StringBuilder resultado = new StringBuilder();
        NoEspera atual = inicio;
        int posicao = 1;
        while (atual != null) {
            resultado.append(posicao).append("o - ").append(atual.aluno).append("\n");
            atual = atual.proximo;
            posicao++;
        }
        return resultado.toString();
    }

    public int tamanho() {
        return tamanho;
    }
}
