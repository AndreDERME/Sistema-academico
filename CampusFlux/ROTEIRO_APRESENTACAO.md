# Roteiro de apresentação — 4 integrantes

## Integrante 1 — Problema e ABB

- Problema real: uma coordenação precisa cadastrar cursos e encontrar rapidamente um curso pelo código.
- Mostrar `ArvoreCursos.java`: cada nó contém um curso e ponteiros para esquerda/direita.
- Explicar que códigos menores vão à esquerda e maiores à direita.
- Demonstrar inserção, busca, remoção de folha, um filho e dois filhos.
- Diferenciar in-ordem, pré-ordem e pós-ordem.

## Integrante 2 — Lista encadeada de alunos

- Mostrar `ListaAlunos.java`: cada nó guarda um aluno e o endereço do próximo.
- Explicar que o cadastro ocorre dentro de um curso encontrado na ABB.
- Demonstrar adicionar, buscar, remover e listar.
- Explicar por que a lista não usa `ArrayList` nem `LinkedList` da biblioteca.

## Integrante 3 — Fila de espera

- Mostrar `FilaEspera.java`: a fila guarda referência ao início e ao fim.
- Explicar FIFO: o primeiro a entrar é o primeiro a sair.
- Demonstrar uma disciplina com 1 vaga e 3 alunos interessados.
- Cancelar uma matrícula e mostrar a promoção automática.
- Demonstrar a remoção de alguém que está apenas na fila.

## Integrante 4 — Integração e qualidade

- Mostrar `SistemaAcademico.java`: coordena árvore, listas e filas.
- Mostrar `Main.java`: menu de operações.
- Explicar por que um aluno inexistente não pode entrar numa disciplina.
- Executar `TesteSistema.java` e apresentar os resultados.
- Mostrar como a remoção de um aluno de um curso atualiza disciplinas e filas.

## Perguntas que todos devem saber responder

1. Por que o curso é buscado na ABB antes de cadastrar um aluno?
2. O que acontece ao excluir um nó da ABB que tem dois filhos?
3. Qual é a diferença entre uma lista encadeada e uma fila?
4. Como o programa garante que uma disciplina nunca ultrapasse a capacidade?
5. O que acontece com a fila quando um aluno matriculado desiste?
6. E se o aluno que sai estiver apenas na fila?
7. O que acontece com matrículas e filas ao excluir um aluno do curso?
8. Existe uma situação em que uma ABB tem busca lenta?
9. Por que é necessário testar cadastro duplicado e remoção de elementos inexistentes?
10. Por que `Scanner` não viola a proibição de Collections?
