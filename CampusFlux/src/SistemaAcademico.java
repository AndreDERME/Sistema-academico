public class SistemaAcademico {
    private final ArvoreCursos cursos = new ArvoreCursos();

    public ArvoreCursos getCursos() {
        return cursos;
    }

    public String cadastrarCurso(int codigo, String nome) {
        if (codigo <= 0 || nome == null || nome.isBlank()) {
            return "Informe codigo positivo e nome do curso.";
        }
        if (!cursos.inserir(new Curso(codigo, nome.trim()))) {
            return "Ja existe um curso com esse codigo.";
        }
        return "Curso cadastrado com sucesso.";
    }

    public String buscarCurso(int codigo) {
        Curso curso = cursos.buscar(codigo);
        return curso == null ? "Curso nao encontrado." : curso.toString();
    }

    public String removerCurso(int codigo) {
        if (!cursos.remover(codigo)) {
            return "Curso nao encontrado.";
        }
        return "Curso removido, incluindo seus alunos, disciplinas e filas.";
    }

    public String cadastrarAluno(int codigoCurso, int matricula, String nome) {
        Curso curso = cursos.buscar(codigoCurso);
        if (curso == null) {
            return "Curso nao encontrado.";
        }
        if (matricula <= 0 || nome == null || nome.isBlank()) {
            return "Informe matricula positiva e nome do aluno.";
        }
        if (!curso.getAlunos().adicionar(new Aluno(matricula, nome.trim()))) {
            return "Matricula de aluno ja cadastrada neste curso.";
        }
        return "Aluno cadastrado no curso.";
    }

    public String buscarAluno(int codigoCurso, int matricula) {
        Curso curso = cursos.buscar(codigoCurso);
        if (curso == null) {
            return "Curso nao encontrado.";
        }
        Aluno aluno = curso.getAlunos().buscar(matricula);
        return aluno == null ? "Aluno nao encontrado." : aluno.toString();
    }

    public String removerAluno(int codigoCurso, int matricula) {
        Curso curso = cursos.buscar(codigoCurso);
        if (curso == null) {
            return "Curso nao encontrado.";
        }
        if (curso.getAlunos().buscar(matricula) == null) {
            return "Aluno nao encontrado.";
        }
        curso.getDisciplinas().removerAlunoDeTodas(matricula);
        curso.getAlunos().remover(matricula);
        return "Aluno removido do curso e de suas disciplinas e filas.";
    }

    public String listarAlunos(int codigoCurso) {
        Curso curso = cursos.buscar(codigoCurso);
        return curso == null ? "Curso nao encontrado." : curso.getAlunos().listar();
    }

    public String cadastrarDisciplina(int codigoCurso, int codigoDisciplina, String nome, int vagas) {
        Curso curso = cursos.buscar(codigoCurso);
        if (curso == null) {
            return "Curso nao encontrado.";
        }
        if (codigoDisciplina <= 0 || vagas <= 0 || nome == null || nome.isBlank()) {
            return "Informe codigo positivo, nome e numero de vagas maior que zero.";
        }
        if (!curso.getDisciplinas().adicionar(new Disciplina(codigoDisciplina, nome.trim(), vagas))) {
            return "Ja existe uma disciplina com esse codigo neste curso.";
        }
        return "Disciplina cadastrada no curso.";
    }

    public String removerDisciplina(int codigoCurso, int codigoDisciplina) {
        Curso curso = cursos.buscar(codigoCurso);
        if (curso == null) {
            return "Curso nao encontrado.";
        }
        if (!curso.getDisciplinas().remover(codigoDisciplina)) {
            return "Disciplina nao encontrada.";
        }
        return "Disciplina removida, incluindo matriculas e fila.";
    }

    public String listarDisciplinas(int codigoCurso) {
        Curso curso = cursos.buscar(codigoCurso);
        return curso == null ? "Curso nao encontrado." : curso.getDisciplinas().listar();
    }

    public String solicitarVaga(int codigoCurso, int codigoDisciplina, int matricula) {
        Curso curso = cursos.buscar(codigoCurso);
        if (curso == null) {
            return "Curso nao encontrado.";
        }
        Disciplina disciplina = curso.getDisciplinas().buscar(codigoDisciplina);
        if (disciplina == null) {
            return "Disciplina nao encontrada.";
        }
        Aluno aluno = curso.getAlunos().buscar(matricula);
        if (aluno == null) {
            return "Aluno nao pertence a este curso.";
        }
        return disciplina.solicitarVaga(aluno);
    }

    public String cancelarVaga(int codigoCurso, int codigoDisciplina, int matricula) {
        Curso curso = cursos.buscar(codigoCurso);
        if (curso == null) {
            return "Curso nao encontrado.";
        }
        Disciplina disciplina = curso.getDisciplinas().buscar(codigoDisciplina);
        if (disciplina == null) {
            return "Disciplina nao encontrada.";
        }
        return disciplina.cancelarSolicitacao(matricula);
    }

    public String relatorioDoCurso(int codigoCurso) {
        Curso curso = cursos.buscar(codigoCurso);
        if (curso == null) {
            return "Curso nao encontrado.";
        }
        return "CURSO: " + curso + "\n\nALUNOS DO CURSO:\n"
                + curso.getAlunos().listar() + "\nDISCIPLINAS E FILAS:\n"
                + curso.getDisciplinas().detalhar();
    }
}
