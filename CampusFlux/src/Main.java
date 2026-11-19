import java.util.Scanner;

public class Main {
    private static final Scanner entrada = new Scanner(System.in);
    private static final SistemaAcademico sistema = new SistemaAcademico();

    public static void main(String[] args) {
        int opcao;
        do {
            mostrarMenu();
            opcao = lerInteiro("Escolha uma opcao: ");
            executar(opcao);
        } while (opcao != 0);
        entrada.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n========== CAMPUSFLUX ==========");
        System.out.println(" 1 - Cadastrar curso");
        System.out.println(" 2 - Buscar curso");
        System.out.println(" 3 - Remover curso");
        System.out.println(" 4 - Percorrer arvore de cursos");
        System.out.println(" 5 - Cadastrar aluno em curso");
        System.out.println(" 6 - Buscar aluno em curso");
        System.out.println(" 7 - Remover aluno de curso");
        System.out.println(" 8 - Listar alunos de curso");
        System.out.println(" 9 - Cadastrar disciplina");
        System.out.println("10 - Remover disciplina");
        System.out.println("11 - Listar disciplinas");
        System.out.println("12 - Solicitar vaga em disciplina");
        System.out.println("13 - Cancelar vaga ou sair da fila");
        System.out.println("14 - Relatorio completo de curso");
        System.out.println(" 0 - Sair");
    }

    private static void executar(int opcao) {
        int curso;
        int disciplina;
        int matricula;
        switch (opcao) {
            case 1:
                curso = lerInteiro("Codigo do curso: ");
                System.out.println(sistema.cadastrarCurso(curso, lerTexto("Nome do curso: ")));
                break;
            case 2:
                System.out.println(sistema.buscarCurso(lerInteiro("Codigo do curso: ")));
                break;
            case 3:
                System.out.println(sistema.removerCurso(lerInteiro("Codigo do curso: ")));
                break;
            case 4:
                System.out.println("\nEM ORDEM:\n" + sistema.getCursos().listarEmOrdem());
                System.out.println("PRE-ORDEM:\n" + sistema.getCursos().listarPreOrdem());
                System.out.println("POS-ORDEM:\n" + sistema.getCursos().listarPosOrdem());
                break;
            case 5:
                curso = lerInteiro("Codigo do curso: ");
                matricula = lerInteiro("Matricula do aluno: ");
                System.out.println(sistema.cadastrarAluno(curso, matricula, lerTexto("Nome do aluno: ")));
                break;
            case 6:
                curso = lerInteiro("Codigo do curso: ");
                System.out.println(sistema.buscarAluno(curso, lerInteiro("Matricula do aluno: ")));
                break;
            case 7:
                curso = lerInteiro("Codigo do curso: ");
                System.out.println(sistema.removerAluno(curso, lerInteiro("Matricula do aluno: ")));
                break;
            case 8:
                System.out.println(sistema.listarAlunos(lerInteiro("Codigo do curso: ")));
                break;
            case 9:
                curso = lerInteiro("Codigo do curso: ");
                disciplina = lerInteiro("Codigo da disciplina: ");
                String nome = lerTexto("Nome da disciplina: ");
                int vagas = lerInteiro("Quantidade de vagas: ");
                System.out.println(sistema.cadastrarDisciplina(curso, disciplina, nome, vagas));
                break;
            case 10:
                curso = lerInteiro("Codigo do curso: ");
                System.out.println(sistema.removerDisciplina(curso, lerInteiro("Codigo da disciplina: ")));
                break;
            case 11:
                System.out.println(sistema.listarDisciplinas(lerInteiro("Codigo do curso: ")));
                break;
            case 12:
                curso = lerInteiro("Codigo do curso: ");
                disciplina = lerInteiro("Codigo da disciplina: ");
                matricula = lerInteiro("Matricula do aluno: ");
                System.out.println(sistema.solicitarVaga(curso, disciplina, matricula));
                break;
            case 13:
                curso = lerInteiro("Codigo do curso: ");
                disciplina = lerInteiro("Codigo da disciplina: ");
                matricula = lerInteiro("Matricula do aluno: ");
                System.out.println(sistema.cancelarVaga(curso, disciplina, matricula));
                break;
            case 14:
                System.out.println(sistema.relatorioDoCurso(lerInteiro("Codigo do curso: ")));
                break;
            case 0:
                System.out.println("Encerrando CampusFlux.");
                break;
            default:
                System.out.println("Opcao invalida.");
        }
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = entrada.nextLine();
            try {
                return Integer.parseInt(texto.trim());
            } catch (NumberFormatException erro) {
                System.out.println("Informe um numero inteiro valido.");
            }
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return entrada.nextLine();
    }
}
