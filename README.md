# Taal - Partição de Conjuntos

Este projeto resolve o clássico **Problema de Partição de Conjuntos** (Set Partition Problem), cujo objetivo é dividir um conjunto de inteiros em dois subconjuntos distintos que tenham exatamente a mesma soma.

O projeto implementa e compara duas abordagens:
1. **Backtracking:** Uma exploração completa (força bruta otimizada) do espaço de busca das soluções.
2. **Branch and Bound:** Uma técnica mais refinada que realiza "podas" na árvore de busca para encontrar o subconjunto de forma muito mais rápida.

---

## 🛠️ Pré-requisitos e Ambiente

Para rodar este projeto do zero, você precisará preparar o seu ambiente com as seguintes ferramentas:

### 1. Java Development Kit (JDK)
Você precisa ter o Java instalado na sua máquina (recomendamos o **Java 17 ou superior**).
- O download oficial e gratuito pode ser feito pela distribuidora [Adoptium (Eclipse Temurin)](https://adoptium.net/) ou pela [Oracle](https://www.oracle.com/java/technologies/downloads/).
- Após a instalação, para verificar se o Java foi configurado corretamente, abra o seu terminal (Prompt de Comando ou PowerShell) e digite os comandos separadamente:
  ```bash
  java -version
  javac -version
  ```
  A tela deverá mostrar os detalhes da versão instalada.

### 2. Editor de Código (IDE)
Esta aplicação roda perfeitamente pelo terminal, mas se preferir ver o código mais amigavelmente, recomenda-se uma IDE (plataforma de edição):
- **IntelliJ IDEA:** (Sugerido pela praticidade pura para projetos Java).
- **Visual Studio Code:** (Você precisará baixar uma extensão recomendada pela Microsoft chamada `Extension Pack for Java`).

---

## 🚀 Como Executar o Projeto

Você tem duas formas principais de testar as partições com o código do projeto: usando a sua IDE ou usando a Linha de Comando clássica (Terminal).

### Opção A: Usando a sua IDE Guiada (Recomendado)
1. **Abra** a pasta principal do projeto `Taal - Partição de Conjuntos` dentro da sua IDE (IntelliJ ou VS Code).
2. Na aba lateral exploradora (onde ficam as pastas), identifique e abra o arquivo `src/app/Main.java`.
3. O programa baseia suas partições em um arquivo de teste de entrada localizado na pasta de dados: **`data/input.txt`**. Altere esse arquivo com os números que deseja usar de teste.
4. Clique no botão de **"Run" / "Play"** verde, ou na opção de rodar pelo menu secundário.
5. Um painel com o Console se abrirá embaixo. Ele exibirá o diagnóstico e o "Tempo de Execução" das operações. Mais importante, o documento com as partições será salvo com sucesso em **`data/output.txt`**.

### Opção B: Via Terminal do Windows / Prompt
1. Em qualquer terminal, navegue até o local onde a pasta do seu projeto foi salva no PC:
   ```bash
   cd "C:\Caminho\Do\Seu\Computador\Taal - Particao de Conjuntos"
   ```
2. **Compilando:** Instrua o seu sistema a preparar e "traduzir" os códigos que foram escritos em classe legível pela máquina (`.class`). Digite e aperte a tecla ENTER no comando:
   ```bash
   javac src/app/Main.java src/core/PartitionSolver.java src/io/FileHandler.java src/models/PartitionResult.java
   ```
3. **Executando:** Rodando a classe responsável por inicializar as outras (`Main`):
   ```bash
   java -cp src app.Main
   ```
4. Se tudo deu certo, surgirá a mensagem avisando que os resultados estão salvos em `data/output.txt`. Vá até esta pasta para investigar e conferir.

---

## 📂 Estrutura de Pastas e Componentes

A divisão e separação dos elementos de lógica é focada na escalabilidade.

* `src/app/`
  * Contém a classe iterativa _`Main.java`_, responsável por orquestrar em sequências o ato de ler e ordenar quem tem que aplicar as equações.
* `src/core/`
  * Contém a classe pesada _`PartitionSolver.java`_, onde fica concentrada a Matemática/Regras de negócio. Nela, habitam as arquiteturas que fazem a divisão forçada do _Backtracking_ e a separação restritiva por soma lógica do _Branch and Bound_.
* `src/io/`
  * Guarda os auxiliadores utilitários. A classe _`FileHandler.java`_ tem total abstração das pastas para lidar exclusivamente com gravação de caracteres e conversão para Console.
* `src/models/`
  * Estruturas base. A _`PartitionResult.java`_ blinda e segura as partições e diz se foi bem-sucedida a resposta.
* `data/`
  * A gaveta de simulação de arquivos. _`input.txt`_: o que entra de variáveis | _`output.txt`_: O recibo de processamento.
