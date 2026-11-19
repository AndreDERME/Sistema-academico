public class TesteSistema {
    private static int verificacoes;

    public static void main(String[] args) {
        testarArvore();
        testarEstruturasAuxiliares();
        testarFluxoAcademico();
        System.out.println("Todos os " + verificacoes + " testes passaram.");
    }

    private static void validar(boolean condicao, String descricao) {
        verificacoes++;
        if (!condicao) {
            throw new AssertionError("Falhou: " + descricao);
        }
    }

    private static void testarArvore() {
        ArvoreCursos arvore = new ArvoreCursos();
        int[] codigos = {50, 30, 70, 20, 40, 60, 80, 65};
        for (int codigo : codigos) {
            validar(arvore.inserir(new Curso(codigo, "Curso " + codigo)), "inserir curso " + codigo);
        }
        validar(!arvore.inserir(new Curso(50, "Duplicado")), "impedir curso duplicado");
        validar(arvore.buscar(60) != null, "buscar curso existente");
        validar(arvore.buscar(99) == null, "buscar curso inexistente");
        validar(arvore.listarEmOrdem().indexOf("20 -") < arvore.listarEmOrdem().indexOf("80 -"), "em ordem crescente");
        validar(arvore.listarPreOrdem().startsWith("50 -"), "pre-ordem com raiz primeiro");
        validar(arvore.listarPosOrdem().trim().endsWith("50 - Curso 50 | Alunos: 0 | Disciplinas: 0"), "pos-ordem com raiz por ultimo");
        validar(arvore.remover(20), "remover folha");
        validar(arvore.remover(60), "remover no com um filho");
        validar(arvore.remover(50), "remover no com dois filhos");
        validar(arvore.buscar(50) == null, "curso removido nao encontrado");
        validar(arvore.buscar(65) != null, "subarvore preservada");
        validar(!arvore.remover(123), "remover curso inexistente");
    }

    private static void testarEstruturasAuxiliares() {
        ListaAlunos alunos = new ListaAlunos();
        Aluno ana = new Aluno(1, "Ana");
        Aluno bia = new Aluno(2, "Bia");
        Aluno caio = new Aluno(3, "Caio");
        validar(alunos.adicionar(ana), "adicionar na lista");
        validar(alunos.adicionar(bia), "adicionar no fim da lista");
        validar(!alunos.adicionar(ana), "impedir duplicacao na lista");
        validar(alunos.buscar(2) == bia, "buscar na lista");
        validar(alunos.listar().contains("Ana"), "listar alunos");
        validar(alunos.remover(1), "remover inicio da lista");
        validar(alunos.remover(2), "remover fim da lista");
        validar(alunos.tamanho() == 0, "lista vazia");

        FilaEspera fila = new FilaEspera();
        validar(fila.enfileirar(ana), "enfileirar primeiro");
        validar(fila.enfileirar(bia), "enfileirar segundo");
        validar(fila.enfileirar(caio), "enfileirar terceiro");
        validar(!fila.enfileirar(caio), "impedir duplicacao na fila");
        validar(fila.buscar(2) == bia, "buscar na fila");
        validar(fila.listar().contains("Caio"), "listar fila");
        validar(fila.remover(2), "remover elemento interno da fila");
        validar(fila.desenfileirar() == ana, "ordem FIFO apos remocao");
        validar(fila.desenfileirar() == caio, "segundo FIFO apos remocao");
        validar(fila.desenfileirar() == null, "desenfileirar fila vazia");
        validar(fila.enfileirar(bia), "reutilizar fila vazia");
        validar(fila.remover(2), "remover unico elemento da fila");
        validar(fila.tamanho() == 0, "fila vazia apos remocao");
    }

    private static void testarFluxoAcademico() {
        SistemaAcademico sistema = new SistemaAcademico();
        validar(sistema.cadastrarCurso(101, "Engenharia de Software").contains("sucesso"), "cadastro de curso");
        validar(sistema.cadastrarCurso(101, "Outro").contains("Ja existe"), "codigo de curso unico");
        validar(sistema.cadastrarAluno(101, 1001, "Ana").contains("cadastrado"), "cadastrar Ana");
        validar(sistema.cadastrarAluno(101, 1002, "Bia").contains("cadastrado"), "cadastrar Bia");
        validar(sistema.cadastrarAluno(101, 1003, "Caio").contains("cadastrado"), "cadastrar Caio");
        validar(sistema.cadastrarDisciplina(101, 501, "Estruturas de Dados", 1).contains("cadastrada"), "cadastro de disciplina");
        validar(sistema.cadastrarDisciplina(101, 501, "Repetida", 2).contains("Ja existe"), "disciplina unica");
        validar(sistema.solicitarVaga(101, 501, 1001).contains("confirmada"), "primeira matricula");
        validar(sistema.solicitarVaga(101, 501, 1002).contains("posicao 1"), "primeira pessoa na fila");
        validar(sistema.solicitarVaga(101, 501, 1003).contains("posicao 2"), "segunda pessoa na fila");
        validar(sistema.solicitarVaga(101, 501, 1003).contains("ja esta"), "impedir duplicacao da fila");
        Disciplina disciplina = sistema.getCursos().buscar(101).getDisciplinas().buscar(501);
        validar(disciplina.getMatriculados().tamanho() == 1, "lotacao respeitada");
        validar(disciplina.getFila().tamanho() == 2, "tamanho da fila");
        validar(sistema.cancelarVaga(101, 501, 1001).contains("Bia"), "promocao do primeiro da fila");
        validar(disciplina.getMatriculados().buscar(1002) != null, "Bia ocupa vaga");
        validar(disciplina.getFila().buscar(1003) != null, "Caio segue na fila");
        validar(sistema.removerAluno(101, 1002).contains("removido"), "aluno excluido do curso");
        validar(disciplina.getMatriculados().buscar(1003) != null, "promocao apos exclusao do aluno");
        validar(disciplina.getFila().tamanho() == 0, "fila esvaziada");
        validar(sistema.relatorioDoCurso(101).contains("Caio"), "relatorio integrado");
        validar(sistema.solicitarVaga(101, 501, 9999).contains("nao pertence"), "negar aluno externo");
        validar(sistema.removerDisciplina(101, 501).contains("removida"), "remover disciplina");
        validar(sistema.removerCurso(101).contains("removido"), "remover curso");
        validar(sistema.getCursos().buscar(101) == null, "curso excluido da arvore");
    }
}
