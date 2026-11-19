public class ListaDisciplinas {
    private static class NoDisciplina {
        Disciplina disciplina;
        NoDisciplina proximo;

        NoDisciplina(Disciplina disciplina) {
            this.disciplina = disciplina;
        }
    }

    private NoDisciplina primeiro;
    private int tamanho;

    public boolean adicionar(Disciplina disciplina) {
        if (buscar(disciplina.getCodigo()) != null) {
            return false;
        }
        NoDisciplina novo = new NoDisciplina(disciplina);
        if (primeiro == null) {
            primeiro = novo;
        } else {
            NoDisciplina atual = primeiro;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novo;
        }
        tamanho++;
        return true;
    }

    public Disciplina buscar(int codigo) {
        NoDisciplina atual = primeiro;
        while (atual != null) {
            if (atual.disciplina.getCodigo() == codigo) {
                return atual.disciplina;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public boolean remover(int codigo) {
        NoDisciplina anterior = null;
        NoDisciplina atual = primeiro;
        while (atual != null) {
            if (atual.disciplina.getCodigo() == codigo) {
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

    public void removerAlunoDeTodas(int matricula) {
        NoDisciplina atual = primeiro;
        while (atual != null) {
            atual.disciplina.cancelarSolicitacao(matricula);
            atual = atual.proximo;
        }
    }

    public String listar() {
        if (primeiro == null) {
            return "Nenhuma disciplina cadastrada.";
        }
        StringBuilder resultado = new StringBuilder();
        NoDisciplina atual = primeiro;
        while (atual != null) {
            resultado.append(atual.disciplina.resumo()).append("\n");
            atual = atual.proximo;
        }
        return resultado.toString();
    }

    public String detalhar() {
        if (primeiro == null) {
            return "Nenhuma disciplina cadastrada.";
        }
        StringBuilder resultado = new StringBuilder();
        NoDisciplina atual = primeiro;
        while (atual != null) {
            resultado.append("\n").append(atual.disciplina.detalhar()).append("\n");
            atual = atual.proximo;
        }
        return resultado.toString();
    }

    public int tamanho() {
        return tamanho;
    }
}
