# CampusFlux — Sistema de Gestão Acadêmica

Projeto acadêmico em Java de linha de comando para gerenciar cursos, alunos, disciplinas e vagas de forma integrada.

## Objetivo

Implementar manualmente as estruturas exigidas no Tema 05:

- Árvore Binária de Busca (ABB): organiza os cursos pelo código.
- Lista Encadeada: mantém os alunos cadastrados dentro de cada curso e os matriculados em cada disciplina.
- Fila: mantém os alunos que aguardam vaga em disciplina lotada, respeitando a ordem de chegada.

A lista de disciplinas também é encadeada e foi implementada manualmente. Nenhuma classe da API de Collections é usada.

## Organização

```text
CampusFlux/
├── src/
│   ├── Main.java
│   ├── SistemaAcademico.java
│   ├── ArvoreCursos.java
│   ├── Curso.java
│   ├── Aluno.java
│   ├── ListaAlunos.java
│   ├── Disciplina.java
│   ├── ListaDisciplinas.java
│   └── FilaEspera.java
├── testes/
│   └── TesteSistema.java
├── .gitignore
├── README.md
└── ROTEIRO_APRESENTACAO.md
```

## Etapa 1 — Abrir no VS Code

1. Instale o JDK 17 ou superior.
2. No VS Code, instale a extensão **Extension Pack for Java**, da Microsoft.
3. Extraia este ZIP e abra a pasta **CampusFlux** no VS Code.
4. Abra `src/Main.java`. Você pode iniciar com **Run Java** ou usar os comandos abaixo.

## Etapa 2 — Compilar pelo terminal

Abra um terminal **na pasta CampusFlux**.

Para compilar o sistema no Windows (PowerShell), Linux ou macOS:

```bash
javac -encoding UTF-8 -d out -sourcepath src src/Main.java
```

Para executar:

```bash
java -cp out Main
```

## Etapa 3 — Executar os testes

Execute, nesta ordem:

```bash
javac -encoding UTF-8 -d out -sourcepath src src/Main.java
javac -encoding UTF-8 -cp out -d out testes/TesteSistema.java
java -cp out TesteSistema
```

O programa de testes deve apresentar: `Todos os 65 testes passaram.`

## Etapa 4 — Demonstração do fluxo real

1. Cadastre o curso `101 - Engenharia de Software` pela opção **1**.
2. Cadastre três alunos pela opção **5**: `1001 - Ana`, `1002 - Bia`, `1003 - Caio`.
3. Cadastre a disciplina `501 - Estruturas de Dados` com **1 vaga**, pela opção **9**.
4. Solicite a vaga para Ana (1001) pela opção **12**: matrícula confirmada.
5. Solicite a vaga para Bia (1002): Bia entra na **posição 1 da fila**.
6. Solicite a vaga para Caio (1003): Caio entra na **posição 2 da fila**.
7. Abra o relatório pela opção **14**: 1 matrícula e 2 pessoas na fila.
8. Cancele a vaga de Ana pela opção **13**: Bia recebe automaticamente a vaga.
9. Abra o relatório novamente: Bia está matriculada e Caio é o primeiro da fila.
10. Remova Bia do curso pela opção **7**: Caio recebe a vaga e Bia é removida do cadastro.
11. Cadastre outros cursos com os códigos `50, 30, 70, 20, 40, 60, 80` e use a opção **4** para demonstrar os três percursos da ABB.
12. Remova, nessa ordem, os cursos 20, 30 e 50 para explicar os três casos de exclusão na árvore (folha, um filho, dois filhos, conforme os nós ainda existentes).

## Métodos implementados

| Estrutura | Inserção | Busca | Exclusão | Listagem |
| --- | --- | --- | --- | --- |
| ABB (`ArvoreCursos`) | `inserir()` | `buscar()` | `remover()` | `listarEmOrdem()`, `listarPreOrdem()`, `listarPosOrdem()` |
| Lista (`ListaAlunos`) | `adicionar()` | `buscar()` | `remover()` | `listar()` |
| Fila (`FilaEspera`) | `enfileirar()` | `buscar()` | `remover()` / `desenfileirar()` | `listar()` |
| Lista de disciplinas (`ListaDisciplinas`) | `adicionar()` | `buscar()` | `remover()` | `listar()` |

## Regras de negócio

- Curso tem código único dentro da ABB.
- Aluno tem matrícula única **dentro do curso**.
- Disciplina tem código único **dentro do curso**.
- Um aluno só pode pedir vaga em uma disciplina do curso no qual está cadastrado.
- Não é permitido ficar simultaneamente matriculado e na fila de uma mesma disciplina.
- A disciplina não pode ultrapassar a capacidade definida.
- Se a disciplina está cheia, o aluno entra ao final da fila.
- Se uma matrícula é cancelada, o primeiro da fila recebe a vaga.
- Ao remover um aluno do curso, suas matrículas e esperas em todas as disciplinas são removidas. Uma vaga liberada pode promover outro aluno automaticamente.
- Ao remover um curso, seus alunos, disciplinas e filas deixam de ser acessíveis no sistema.

## Escopo e limitações

- Os dados são mantidos apenas em memória; ao encerrar o programa, não ficam salvos.
- Não há interface gráfica nem banco de dados nesta primeira versão.
- A ABB não é autobalanceada: suas buscas são eficientes quando a árvore tem altura moderada, mas podem se degradar em inserções ordenadas.
- As listas e filas são de nós encadeados criados manualmente. `Scanner` é usado somente para a entrada de dados, e `StringBuilder` para montar texto.

## Etapa 5 — Organização do grupo no GitHub

Sugestão de distribuição com cada integrante participando da integração e dos testes:

1. **Integrante 1:** ABB, inserção, busca, remoção e percursos dos cursos.
2. **Integrante 2:** lista encadeada, cadastro, busca e exclusão de alunos.
3. **Integrante 3:** fila de espera, solicitação de vaga e promoção automática.
4. **Integrante 4:** integração do sistema, menu, testes e demonstração funcional.

Façam os commits usando as contas individuais. Mantenham um repositório principal compartilhado e confirmem com o professor se ele exige também um fork ou uma cópia no perfil de cada integrante.

## Antes da entrega

Este material é uma **base de estudo e prototipação**. O enunciado não aceita código copiado nem trabalhos iguais. O grupo deve compreender cada método, alterar e desenvolver a sua própria versão, documentar as decisões no README, testar todos os casos e conseguir explicar o código sem depender de leitura pronta.
