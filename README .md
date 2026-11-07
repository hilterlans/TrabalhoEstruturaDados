# Trabalho Prático – Estrutura de Dados I

**Instituição:** ICEV – Instituto de Ensino Superior
**Curso:** Engenharia de Software (2º período)
**Disciplina:** Algoritmo e Estrutura de Dados I
**Professor:** Hilson Silva
**Avaliação:** P2 – 2026-2
**Linguagem:** Java

## Autores

| Aluno |
|---|
| Hilterlan Silva da Costa |
| Emanuel Sousa Angelim |

**Turma:** Snyder

---

## 1. Sobre o projeto

Implementação, **do zero**, de estruturas de dados lineares, não-lineares e algoritmos de ordenação, aplicados a 24 situações-problema do cotidiano (clínica médica, fila de impressão, editor de texto, estoque, ranking de jogos, rede social etc.).

O objetivo é entender o funcionamento interno de cada estrutura, por isso nenhuma delas usa as coleções prontas do Java (`LinkedList`, `Stack`, `Queue`, `PriorityQueue`, `HashMap`, `TreeMap`, `Collections.sort`, `Arrays.sort`…). Os únicos recursos da biblioteca padrão usados são de apoio, e nenhum deles implementa as estruturas pedidas: `Scanner` (teclado, Q4), `Random` (Q15), `Objects`, `ArrayList` (simulação da Q1), `LocalDateTime` (datas da Q21), `MessageDigest` e `SecureRandom` (hash e sal das senhas da Q13), leitura de arquivo com `java.nio.file` (Q14) e as interfaces funcionais `Consumer` e `Function` (percursos das árvores).

## 2. Como executar

**Requisito:** JDK 11 ou superior (desenvolvido no IntelliJ IDEA e testado também com JDK 21).

### Pelo IntelliJ IDEA

1. Abra a pasta do projeto (`File > Open`).
2. Marque a pasta `src` como *Sources Root* (botão direito > *Mark Directory as > Sources Root*), se ainda não estiver.
3. Abra a classe `Main` da questão desejada (tabela da seção 4) e clique no botão verde de executar.

### Pelo terminal

A partir da pasta do projeto:

```bash
# compilar tudo para a pasta out/
mkdir out
javac -encoding UTF-8 -d out $(find src -name "*.java")      # Linux/macOS
# Windows (PowerShell):
#   javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src -Filter *.java).FullName

# executar uma questão (exemplo: Questão 9)
java -cp out naolinearesarvore.questao9.MainRH
```

> Dica: se os acentos saírem quebrados no terminal do Windows, execute com
> `java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -cp out ...`.

A Questão 4 pede, ao final, uma expressão digitada no teclado (basta apertar Enter para sair). Execute-a em um terminal ou no console do IntelliJ, que aceitam digitação.

## 3. Organização do código

Cada estrutura ou algoritmo é uma classe própria e reutilizável; cada questão tem a sua pasta, com as classes do problema e uma `Main` com a simulação.

```
src/
├── linearesfila/        EstruturaFila.java            + questao1/, questao2/
├── linearespilha/       EstruturaPilha.java           + questao3/, questao4/
├── lineareslista/       EstruturaLista.java           + questao5/, questao6/
├── naolinearesarvore/   EstruturaBST.java
│                        EstruturaAVL.java             + questao7/ a questao10/
├── naolinearesgrafos/   EstruturaGrafos.java         + questao11/, questao12/
├── naolineareshash/     EstruturaTabelaHash.java     + questao13/, questao14/
└── ordenacao/           EstruturaBubbleSort.java
                         EstruturaSelectionSort.java
                         EstruturaInsertionSort.java
                         ResultadoOrdenacao.java      + Questao15/ a Questao24/
```

Reaproveitamento entre as estruturas:

- `EstruturaLista` é usada nas Questões 5 e 6.
- `EstruturaBST` é usada nas Questões 7 e 8 e `EstruturaAVL` nas Questões 9 e 10.
- `EstruturaTabelaHash` é usada nas Questões 13 e 14 e também como índice de nickname na Questão 10.
- Bubble, Selection e Insertion Sort são classes genéricas (`T extends Comparable<T>`) que devolvem um `ResultadoOrdenacao` com o número de comparações e de trocas, usado nas comparações de desempenho.
- Merge Sort e Quick Sort estão implementados dentro das `Main` das Questões 21 a 24.

## 4. Questões implementadas

### Estruturas lineares

| Nº | Problema | Estrutura | Classe para executar |
|---|---|---|---|
| 1 | Atendimento de uma clínica médica | Fila | `linearesfila.questao1.Main` |
| 2 | Fila de impressão (com cancelamento do último trabalho) | Fila | `linearesfila.questao2.Main` |
| 3 | Desfazer/refazer em editor de texto (12 ações) | Pilha | `linearespilha.questao3.Main` |
| 4 | Verificação de `()`, `[]` e `{}` balanceados | Pilha | `linearespilha.questao4.Main` |
| 5 | Playlist de músicas | Lista encadeada | `lineareslista.questao5.MainPlaylist` |
| 6 | Estoque de loja de materiais (12 produtos, simulação de uma semana) | Lista encadeada | `lineareslista.questao6.MainEstoque` |

### Estruturas não-lineares

| Nº | Problema | Estrutura | Classe para executar |
|---|---|---|---|
| 7 | Cadastro e busca de alunos por matrícula (22 alunos, com remoção) | BST | `naolinearesarvore.questao7.MainCadastro` |
| 8 | Autocomplete de produtos (32 produtos, busca por prefixo com poda) | BST | `naolinearesarvore.questao8.MainAutocomplete` |
| 9 | Índice de CPF do RH (28 funcionários, com remoção e verificação de balanceamento) | AVL | `naolinearesarvore.questao9.MainRH` |
| 10 | Ranking de jogadores (30 jogadores, atualização de pontuação e Top-K) | AVL + Tabela Hash | `naolinearesarvore.questao10.MainRanking` |
| 11 | Roteamento de entregas (13 vértices, caminho mínimo) | Grafo ponderado + Dijkstra | `naolinearesgrafos.questao11.Main` |
| 12 | Rede de amizades (18 usuários, amigos em comum, graus de separação) | Grafo não-direcionado + BFS | `naolinearesgrafos.questao12.Main` |
| 13 | Cadastro de usuários com login rápido (22 usuários) | Tabela hash com encadeamento | `naolineareshash.questao13.Main` |
| 14 | Frequência de palavras em um texto | Tabela hash | `naolineareshash.questao14.Main` |

### Algoritmos de ordenação

| Nº | Problema | Algoritmo | Classe para executar |
|---|---|---|---|
| 15 | Notas de uma turma de 25 alunos (comparação com outros algoritmos) | Bubble Sort (simples e otimizado) | `ordenacao.Questao15.Main` |
| 16 | Vitrine de 12 produtos por preço (crescente/decrescente) | Bubble Sort | `ordenacao.Questao16.Main` |
| 17 | Seleção dos melhores de 15 candidatos (Top-N) | Selection Sort | `ordenacao.Questao17.Main` |
| 18 | 14 tarefas por prioridade | Selection Sort | `ordenacao.Questao18.Main` |
| 19 | Mão de cartas ordenada (12 cartas recebidas) | Insertion Sort | `ordenacao.Questao19.Main` |
| 20 | Ranking de vendedores durante uma semana (lista ordenada vs. embaralhada) | Insertion Sort | `ordenacao.Questao20.Main` |
| 21 | Extrato de 50 transações por data/hora (ordenação estável) | Merge Sort | `ordenacao.Questao21.Main` |
| 22 | Mesclagem de duas listas de 20 contatos, sem duplicatas | Merge (etapa de intercalação) | `ordenacao.Questao22.Main` |
| 23 | Catálogo de 100 produtos por preço, nome e popularidade (pivô mediana-de-três) | Quick Sort | `ordenacao.Questao23.Main` |
| 24 | Classificação de 30 atletas e pódio | Quick Sort | `ordenacao.Questao24.Main` |

## 5. Complexidade das estruturas e algoritmos

`n` = número de elementos; `h` = altura da árvore; `V` e `E` = vértices e arestas.

| Estrutura / algoritmo | Operações principais | Espaço |
|---|---|---|
| Fila (lista encadeada) | `enqueue`, `dequeue`, `front`, `size`: O(1). `removerUltimo` (Q2): O(n) | O(n) |
| Pilha | `push`, `pop`, `peek`: O(1) | O(n) |
| Lista encadeada | inserir no início: O(1); inserir no fim, buscar e remover: O(n) | O(n) |
| BST | inserir, buscar, remover: O(h), ou seja, O(log n) em média e O(n) no pior caso | O(n) |
| AVL | inserir, buscar, remover: **O(log n) no pior caso**; Top-K: O(log n + k) | O(n) |
| Grafo (lista de adjacência) | Dijkstra: O(V²); BFS: O(V + E) | O(V + E) |
| Tabela hash (encadeamento) | inserir, buscar, remover: O(1) em média; O(n) no pior caso | O(n + m) |
| Bubble Sort | O(n²); versão otimizada O(n) no melhor caso | O(1) |
| Selection Sort | O(n²) em todos os casos, no máximo n−1 trocas | O(1) |
| Insertion Sort | O(n²); O(n) no melhor caso (já ordenado) | O(1) |
| Merge Sort | O(n log n) em todos os casos; estável | O(n) |
| Quick Sort | O(n log n) em média; O(n²) no pior caso (pivô mediana-de-três na Q23 reduz esse risco; a Q24 usa o último elemento como pivô) | O(log n) em média (pilha de recursão); O(n) no pior caso |

A justificativa de cada escolha está no comentário de abertura das classes (por exemplo, `EstruturaAVL`, `Estoque`, `AutocompleteProdutos`, `RankingJogos`) e no relatório técnico.

## 6. Destaques e decisões de projeto

- **Q4:** a verificação usa a pilha própria (`EstruturaPilha`) e reconhece os três tipos de delimitador.
- **Q7:** a BST trata os três casos de remoção (nó folha, um filho e dois filhos, usando o sucessor em ordem).
- **Q8:** os nomes ficam ordenados na BST, então os que começam com um prefixo formam um bloco contíguo. A busca **poda** subárvores que não podem conter resultados e para ao atingir o limite de sugestões.
- **Q9:** a simulação insere os CPFs em ordem crescente, que é o pior caso de uma BST. A BST comum chega a altura 28 e a AVL fica em 5, com `estaBalanceada()` confirmando que todo nó respeita |fator| ≤ 1.
- **Q10:** a árvore é ordenada por pontuação e, no empate, pelo nickname. Sem esse desempate, dois jogadores com a mesma pontuação seriam tratados como iguais pela árvore. Uma tabela hash (nickname → jogador) localiza a pontuação antiga em O(1) médio para atualizá-la.
- **Q15 e Q20:** os algoritmos de ordenação contam comparações e trocas, o que permite comparar os métodos com números e não só com tempo.
- **Q21:** o Merge Sort é estável: transações no mesmo segundo mantêm a ordem de registro.
- **Q23:** o Quick Sort usa pivô mediana-de-três e ordena por três critérios diferentes.
- **Dados de teste:** os CPFs e e-mails usados nas simulações são **fictícios**. Quando a questão pede volume (por exemplo, 100 produtos ou 50 transações), os dados são gerados por fórmula, sem aleatoriedade, para a saída ser a mesma a cada execução.
- **Tratamento de erros:** lista ou fila vazia, chave inexistente, chave repetida, valores negativos e estoque insuficiente são tratados com retorno `false`/`null` ou com exceção e mensagem clara.

## 7. Testes

Cada `Main` é uma simulação que mostra as entradas e as saídas no console, incluindo casos de erro (busca de item inexistente, cadastro repetido, venda acima do estoque etc.). Nas árvores, a própria saída traz a prova do resultado: listagem em ordem, altura da árvore, fator de balanceamento e `estaBalanceada()` após cada inserção ou remoção.

## 8. Entrega

- Código-fonte completo (pasta `src`)
- Relatório técnico (justificativas, complexidades, exemplos de entrada e saída, dificuldades e conclusão)
- Ficha obrigatória preenchida
- Envio pela plataforma do ICEV Digital
