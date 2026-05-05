# Sistema de Entregas para E-commerce

## Repositorio

[https://github.com/Vitor-2205/Checkpoint2_Java.git](https://github.com/Vitor-2205/Checkpoint2_Java.git)

## Identificacao

| Membro | RM |
|--------|-----|
| Vitor Carvalho Alexandre | 562298 |

## Explicacao do Sistema

O sistema desenvolvido gerencia as operacoes de entregas de um e-commerce. Ele permite o controle completo do fluxo de entregas desde o cadastro dos entregadores ate a finalizacao das entregas.

### Funcionalidades

1. Cadastrar entregadores (Moto, Carro ou Bicicleta)
2. Listar todos os entregadores cadastrados
3. Criar novas entregas/pedidos
4. Listar todas as entregas
5. Atribuir uma entrega a um entregador disponivel
6. Atualizar o status da entrega (PENDENTE, EM_ROTA, ENTREGUE, CANCELADO)
7. Exibir detalhes completos de uma entrega

### Tipos de Entregadores

| Tipo | Velocidade Media | Capacidade de Carga | Custo por km |
|------|------------------|---------------------|--------------|
| Moto | 40 km/h | 15 kg | R$ 1,50 |
| Carro | 50 km/h | 100 kg | R$ 2,50 |
| Bicicleta | 15 km/h | 10 kg | R$ 0,50 |

## Decisoes de Modelagem

### 1. Hierarquia de Classes (Heranca)

Foi criada uma classe abstrata chamada `Entregador` que contem os atributos e metodos comuns a todos os tipos de entregadores. As classes `EntregadorMoto`, `EntregadorCarro` e `EntregadorBicicleta` herdam desta classe abstrata.

**Beneficios:**
- Reutilizacao de codigo
- Padronizacao dos metodos
- Facil manutencao

### 2. Interface

Foi criada a interface `Calculavel` que define metodos padrao para calculos relacionados as entregas:

public interface Calculavel {
    double calcularCustoEntrega(double distanciaKm);
    int calcularTempoEntrega(double distanciaKm);
}

A classe `Entregador` implementa esta interface, forcando que todos os tipos de entregadores tenham seus proprios calculos de custo e tempo.

### 3. Encapsulamento

Todos os atributos das classes sao declarados como `private` ou `protected` e o acesso e feito atraves de metodos getters e setters. Isso garante:

- Protecao dos dados
- Controle sobre as alteracoes
- Validacao de regras de negocio

### 4. Polimorfismo (Sobrescrita de Metodos)

Os seguintes metodos foram sobrescritos nas classes filhas:

- `getTipo()`: Cada tipo de entregador retorna sua propria identificacao
- `calcularCustoEntrega()`: Cada tipo calcula o custo de forma diferente
- `calcularTempoEntrega()`: Cada tipo calcula o tempo baseado em sua velocidade
- `toString()`: Personaliza a representacao textual do objeto

### 5. Sobrecarga de Metodos

O sistema utiliza sobrecarga em dois casos:

**Construtores da classe Entrega:**
- `Entrega(id, endereco, cliente, distancia, peso)` - Cria entrega sem entregador
- `Entrega(id, endereco, cliente, distancia, peso, entregador)` - Cria entrega ja atribuida

**Metodo atualizarStatus da classe Entrega:**
- `atualizarStatus(StatusEntrega)` - Atualiza apenas o status
- `atualizarStatus(StatusEntrega, String)` - Atualiza status com observacao

## Diagrama de Classes UML
<img width="1184" height="632" alt="image" src="https://github.com/user-attachments/assets/0e77a186-1f04-4fd7-b860-51182ab5d20a" />

## Como Executar o Projeto

### Pre-requisitos

- Java JDK 17 ou superior instalado
- Git (opcional, para clonar o repositorio)

### Passo a Passo

**Opcao 1: Executar pelo IntelliJ IDEA**

1. Abra o IntelliJ IDEA
2. Clique em File > Open e selecione a pasta do projeto
3. Localize e abra o arquivo `Main.java`
4. Clique com o botao direito no arquivo e selecione `Run 'Main.main()'`
5. Ou clique no icone verde de play ao lado do metodo main

**Opcao 2: Executar pelo Terminal**

# Compilar todos os arquivos
javac *.java

# Executar o programa
java Main

### Exemplo do Menu Principal
Imagem no teams

<img width="325" height="254" alt="image" src="https://github.com/user-attachments/assets/95d13e50-05e0-40b4-93e4-039fab7fb759" />
╝

### Estrutura dos Arquivos
<img width="444" height="479" alt="image" src="https://github.com/user-attachments/assets/7e2e245d-c0ea-46ee-b9f3-4a2966715b52" />





### Dados de Exemplo

Ao iniciar o sistema, os seguintes dados ja estao cadastrados:

**Entregadores:**
- M001 - Carlos Silva (Moto) - Disponivel
- C001 - Mariana Souza (Carro) - Disponivel
- B001 - Joao Pedro (Bicicleta) - Disponivel

**Entregas:**
- E001 - Rua A, 123 - Cliente: Ana Costa - Status: PENDENTE
- E002 - Av B, 456 - Cliente: Roberto Lima - Status: PENDENTE

## Parte 3 - Perguntas Discursivas

### 1. Heranca

**Como a heranca foi utilizada no seu sistema?**

A heranca foi utilizada atraves da criacao de uma classe abstrata `Entregador` que contem os atributos e metodos comuns a todos os entregadores. As classes `EntregadorMoto`, `EntregadorCarro` e `EntregadorBicicleta` estendem a classe `Entregador` utilizando a palavra-chave `extends`.

**Qual problema ela resolveu?**

A heranca resolveu os seguintes problemas:
- Eliminou a duplicacao de codigo, pois atributos como id, nome, telefone e disponibilidade estao declarados apenas uma vez na classe mae
- Garantiu que todos os tipos de entregadores tenham uma estrutura padrao
- Facilitou a manutencao, pois alteracoes nos atributos comuns precisam ser feitas em apenas um lugar
- Permitiu o polimorfismo, onde um objeto pode ser tratado como seu tipo base

**Classes envolvidas:**

| Classe Mae (Base) | Classes Filhas (Derivadas) |
|-------------------|---------------------------|
| Entregador (abstrata) | EntregadorMoto |
| Entregador (abstrata) | EntregadorCarro |
| Entregador (abstrata) | EntregadorBicicleta |

### 2. Interfaces

**Qual interface foi criada no sistema?**

Foi criada a interface `Calculavel` com a seguinte estrutura:

public interface Calculavel {
    double calcularCustoEntrega(double distanciaKm);
    int calcularTempoEntrega(double distanciaKm);
}

**Por que voce decidiu utiliza-la?**

Decidi utilizar a interface para:
- Garantir que todos os entregadores implementem obrigatoriamente os metodos de calculo de custo e tempo
- Padronizar a forma como os calculos sao feitos no sistema
- Permitir que diferentes classes implementem esses metodos de maneiras distintas

**Qual vantagem ela trouxe?**

As principais vantagens foram:
1. Contrato obrigatorio: A classe `Entregador` e todas as suas subclasses sao forcadas a implementar os metodos de calculo
2. Flexibilidade: Cada tipo de entregador pode implementar seus proprios calculos (moto com custo de R$ 1,50/km, carro com R$ 2,50/km, bicicleta com R$ 0,50/km)
3. Desacoplamento: A logica de calculo nao fica presa a uma classe especifica
4. Extensibilidade: Se no futuro for criado um novo tipo de entregador, ele ja sabe que precisa implementar esses metodos

### 3. Classe Abstrata

**Explique o papel da classe abstrata no seu sistema.**

A classe `Entregador` e uma classe abstrata que serve como modelo base para todos os tipos de entregadores. Seu papel e:
- Fornecer a estrutura basica que toda entrega deve ter (id, nome, telefone, disponibilidade, velocidade, capacidade)
- Implementar metodos concretos que sao uteis para todas as subclasses como `exibirInfo()` e `toString()`
- Declarar metodos abstratos como `getTipo()` que forcam as subclasses a implementarem seu proprio comportamento

**Por que ela nao poderia ser uma classe comum?**

A classe `Entregador` nao poderia ser uma classe comum (concreta) pelos seguintes motivos:

1. Nao faz sentido instanciar um "Entregador" generico: No mundo real, um entregador sempre tem um tipo especifico (moto, carro ou bicicleta). Nao existe um entregador sem tipo definido.

2. Metodo `getTipo()` nao teria implementacao padrao: Cada tipo de entregador deve retornar sua propria identificacao. Uma classe comum teria que implementar um metodo padrao que seria sobrescrito, o que e menos seguro.

3. Forcamento de implementacao: A classe abstrata garante que as subclasses implementem os metodos necessarios. Se fosse uma classe comum, nada impediria que uma nova subclasse deixasse de implementar o metodo `getTipo()`.



