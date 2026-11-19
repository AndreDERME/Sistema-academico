public class ArvoreCursos {
    private static class NoCurso {
        Curso curso;
        NoCurso esquerda;
        NoCurso direita;

        NoCurso(Curso curso) {
            this.curso = curso;
        }
    }

    private NoCurso raiz;

    public boolean inserir(Curso curso) {
        if (buscar(curso.getCodigo()) != null) {
            return false;
        }
        raiz = inserirRecursivo(raiz, curso);
        return true;
    }

    private NoCurso inserirRecursivo(NoCurso atual, Curso curso) {
        if (atual == null) {
            return new NoCurso(curso);
        }
        if (curso.getCodigo() < atual.curso.getCodigo()) {
            atual.esquerda = inserirRecursivo(atual.esquerda, curso);
        } else {
            atual.direita = inserirRecursivo(atual.direita, curso);
        }
        return atual;
    }

    public Curso buscar(int codigo) {
        NoCurso atual = raiz;
        while (atual != null) {
            if (codigo == atual.curso.getCodigo()) {
                return atual.curso;
            }
            atual = codigo < atual.curso.getCodigo() ? atual.esquerda : atual.direita;
        }
        return null;
    }

    public boolean remover(int codigo) {
        if (buscar(codigo) == null) {
            return false;
        }
        raiz = removerRecursivo(raiz, codigo);
        return true;
    }

    private NoCurso removerRecursivo(NoCurso atual, int codigo) {
        if (atual == null) {
            return null;
        }
        if (codigo < atual.curso.getCodigo()) {
            atual.esquerda = removerRecursivo(atual.esquerda, codigo);
        } else if (codigo > atual.curso.getCodigo()) {
            atual.direita = removerRecursivo(atual.direita, codigo);
        } else {
            if (atual.esquerda == null) {
                return atual.direita;
            }
            if (atual.direita == null) {
                return atual.esquerda;
            }
            NoCurso sucessor = menor(atual.direita);
            atual.curso = sucessor.curso;
            atual.direita = removerRecursivo(atual.direita, sucessor.curso.getCodigo());
        }
        return atual;
    }

    private NoCurso menor(NoCurso atual) {
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }

    public String listarEmOrdem() {
        StringBuilder resultado = new StringBuilder();
        emOrdem(raiz, resultado);
        return resultado.length() == 0 ? "Nenhum curso cadastrado." : resultado.toString();
    }

    private void emOrdem(NoCurso atual, StringBuilder resultado) {
        if (atual != null) {
            emOrdem(atual.esquerda, resultado);
            resultado.append(atual.curso).append("\n");
            emOrdem(atual.direita, resultado);
        }
    }

    public String listarPreOrdem() {
        StringBuilder resultado = new StringBuilder();
        preOrdem(raiz, resultado);
        return resultado.length() == 0 ? "Nenhum curso cadastrado." : resultado.toString();
    }

    private void preOrdem(NoCurso atual, StringBuilder resultado) {
        if (atual != null) {
            resultado.append(atual.curso).append("\n");
            preOrdem(atual.esquerda, resultado);
            preOrdem(atual.direita, resultado);
        }
    }

    public String listarPosOrdem() {
        StringBuilder resultado = new StringBuilder();
        posOrdem(raiz, resultado);
        return resultado.length() == 0 ? "Nenhum curso cadastrado." : resultado.toString();
    }

    private void posOrdem(NoCurso atual, StringBuilder resultado) {
        if (atual != null) {
            posOrdem(atual.esquerda, resultado);
            posOrdem(atual.direita, resultado);
            resultado.append(atual.curso).append("\n");
        }
    }
}
