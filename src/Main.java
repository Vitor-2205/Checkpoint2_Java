import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Entregador> entregadores = new ArrayList<>();
    private static List<Entrega> entregas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextEntregadorId = 1;
    private static int nextEntregaId = 1;

    public static void main(String[] args) {
        inicializarDados();

        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarEntregador();
                    break;
                case 2:
                    listarEntregadores();
                    break;
                case 3:
                    criarEntrega();
                    break;
                case 4:
                    listarEntregas();
                    break;
                case 5:
                    atribuirEntrega();
                    break;
                case 6:
                    atualizarStatusEntrega();
                    break;
                case 7:
                    exibirDetalhesEntrega();
                    break;
                case 0:
                    System.out.println("👋 Encerrando sistema...");
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void inicializarDados() {
        entregadores.add(new EntregadorMoto("M001", "Carlos Silva", "(11) 99999-1111"));
        entregadores.add(new EntregadorCarro("C001", "Mariana Souza", "(11) 99999-2222"));
        entregadores.add(new EntregadorBicicleta("B001", "João Pedro", "(11) 99999-3333"));

        entregas.add(new Entrega("E001", "Rua A, 123 - Centro", "Ana Costa", 5.2, 2.5));
        entregas.add(new Entrega("E002", "Av B, 456 - Sul", "Roberto Lima", 12.0, 8.0));
    }

    private static void exibirMenu() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║     SISTEMA DE ENTREGAS E-COMMERCE   ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ 1 - Cadastrar Entregador             ║");
        System.out.println("║ 2 - Listar Entregadores              ║");
        System.out.println("║ 3 - Criar Entrega                    ║");
        System.out.println("║ 4 - Listar Entregas                  ║");
        System.out.println("║ 5 - Atribuir Entrega a Entregador    ║");
        System.out.println("║ 6 - Atualizar Status da Entrega      ║");
        System.out.println("║ 7 - Detalhes da Entrega              ║");
        System.out.println("║ 0 - Sair                             ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    private static void cadastrarEntregador() {
        System.out.println("\n📝 CADASTRO DE ENTREGADOR");
        System.out.println("Tipos disponíveis:");
        System.out.println("1 - Moto");
        System.out.println("2 - Carro");
        System.out.println("3 - Bicicleta");

        int tipo = lerInteiro("Escolha o tipo: ");
        String nome = lerString("Nome: ");
        String telefone = lerString("Telefone: ");
        String id = gerarId(tipo);

        Entregador novo;
        switch (tipo) {
            case 1:
                novo = new EntregadorMoto(id, nome, telefone);
                break;
            case 2:
                novo = new EntregadorCarro(id, nome, telefone);
                break;
            case 3:
                novo = new EntregadorBicicleta(id, nome, telefone);
                break;
            default:
                System.out.println("❌ Tipo inválido!");
                return;
        }

        entregadores.add(novo);
        System.out.println("✅ Entregador cadastrado com sucesso!");
        System.out.println(novo.exibirInfo());
    }

    private static String gerarId(int tipo) {
        String prefixo = "";
        switch (tipo) {
            case 1: prefixo = "M"; break;
            case 2: prefixo = "C"; break;
            case 3: prefixo = "B"; break;
        }
        return prefixo + String.format("%03d", nextEntregadorId++);
    }

    private static void listarEntregadores() {
        System.out.println("\n📋 LISTA DE ENTREGADORES");
        if (entregadores.isEmpty()) {
            System.out.println("Nenhum entregador cadastrado.");
            return;
        }
        for (Entregador e : entregadores) {
            System.out.println(e.exibirInfo());
        }
    }

    private static void criarEntrega() {
        System.out.println("\n📦 CRIAR NOVA ENTREGA");
        String id = "E" + String.format("%03d", nextEntregaId++);
        String endereco = lerString("Endereço de destino: ");
        String cliente = lerString("Nome do cliente: ");
        double distancia = lerDouble("Distância (km): ");
        double peso = lerDouble("Peso (kg): ");

        Entrega novaEntrega = new Entrega(id, endereco, cliente, distancia, peso);
        entregas.add(novaEntrega);

        System.out.println("✅ Entrega criada com ID: " + id);
        System.out.println("Status: PENDENTE - aguardando atribuição");
    }

    private static void listarEntregas() {
        System.out.println("\n📋 LISTA DE ENTREGAS");
        if (entregas.isEmpty()) {
            System.out.println("Nenhuma entrega cadastrada.");
            return;
        }
        for (Entrega e : entregas) {
            System.out.println(e);
        }
    }

    private static void atribuirEntrega() {
        System.out.println("\n🔄 ATRIBUIR ENTREGA A ENTREGADOR");

        List<Entrega> pendentes = new ArrayList<>();
        for (Entrega e : entregas) {
            if (e.getStatus() == Entrega.StatusEntrega.PENDENTE) {
                pendentes.add(e);
            }
        }

        if (pendentes.isEmpty()) {
            System.out.println("❌ Não há entregas pendentes!");
            return;
        }

        System.out.println("Entregas pendentes:");
        for (int i = 0; i < pendentes.size(); i++) {
            System.out.println((i + 1) + " - " + pendentes.get(i));
        }

        int idxEntrega = lerInteiro("Escolha a entrega: ") - 1;
        if (idxEntrega < 0 || idxEntrega >= pendentes.size()) {
            System.out.println("❌ Opção inválida!");
            return;
        }

        Entrega entrega = pendentes.get(idxEntrega);

        List<Entregador> disponiveis = new ArrayList<>();
        for (Entregador e : entregadores) {
            if (e.isDisponivel() && e.getCapacidadeCargaKg() >= entrega.getPesoKg()) {
                disponiveis.add(e);
            }
        }

        if (disponiveis.isEmpty()) {
            System.out.println("❌ Nenhum entregador disponível com capacidade suficiente!");
            return;
        }

        System.out.println("\nEntregadores disponíveis:");
        for (int i = 0; i < disponiveis.size(); i++) {
            Entregador e = disponiveis.get(i);
            System.out.println((i + 1) + " - " + e.exibirInfo());
            System.out.println("   Custo estimado: R$ " + String.format("%.2f", e.calcularCustoEntrega(entrega.getDistanciaKm())));
            System.out.println("   Tempo estimado: " + e.calcularTempoEntrega(entrega.getDistanciaKm()) + " min");
        }

        int idxEntregador = lerInteiro("Escolha o entregador: ") - 1;
        if (idxEntregador < 0 || idxEntregador >= disponiveis.size()) {
            System.out.println("❌ Opção inválida!");
            return;
        }

        entrega.atribuirEntregador(disponiveis.get(idxEntregador));
    }

    private static void atualizarStatusEntrega() {
        System.out.println("\n🔄 ATUALIZAR STATUS DA ENTREGA");

        List<Entrega> ativas = new ArrayList<>();
        for (Entrega e : entregas) {
            if (e.getStatus() != Entrega.StatusEntrega.ENTREGUE && e.getStatus() != Entrega.StatusEntrega.CANCELADO) {
                ativas.add(e);
            }
        }

        if (ativas.isEmpty()) {
            System.out.println("❌ Não há entregas ativas!");
            return;
        }

        System.out.println("Entregas ativas:");
        for (int i = 0; i < ativas.size(); i++) {
            System.out.println((i + 1) + " - " + ativas.get(i));
        }

        int idxEntrega = lerInteiro("Escolha a entrega: ") - 1;
        if (idxEntrega < 0 || idxEntrega >= ativas.size()) {
            System.out.println("❌ Opção inválida!");
            return;
        }

        Entrega entrega = ativas.get(idxEntrega);

        System.out.println("\nNovos status disponíveis:");
        System.out.println("1 - EM_ROTA");
        System.out.println("2 - ENTREGUE");
        System.out.println("3 - CANCELADO");

        int opc = lerInteiro("Escolha o novo status: ");
        Entrega.StatusEntrega novoStatus;
        switch (opc) {
            case 1: novoStatus = Entrega.StatusEntrega.EM_ROTA; break;
            case 2: novoStatus = Entrega.StatusEntrega.ENTREGUE; break;
            case 3: novoStatus = Entrega.StatusEntrega.CANCELADO; break;
            default:
                System.out.println("❌ Opção inválida!");
                return;
        }

        if (novoStatus == Entrega.StatusEntrega.CANCELADO) {
            String motivo = lerString("Motivo do cancelamento: ");
            entrega.atualizarStatus(novoStatus, motivo);
        } else {
            entrega.atualizarStatus(novoStatus);
        }

        System.out.println("✅ Status atualizado para: " + novoStatus);
    }

    private static void exibirDetalhesEntrega() {
        System.out.println("\n🔍 DETALHES DA ENTREGA");
        String id = lerString("Informe o ID da entrega: ");

        for (Entrega e : entregas) {
            if (e.getId().equalsIgnoreCase(id)) {
                e.exibirDetalhes();
                return;
            }
        }
        System.out.println("❌ Entrega não encontrada!");
    }

    private static String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.print("Valor inválido! " + mensagem);
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextDouble()) {
            System.out.print("Valor inválido! " + mensagem);
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }
}