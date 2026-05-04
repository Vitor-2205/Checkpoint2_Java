import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Entrega {
    private String id;
    private String enderecoDestino;
    private String cliente;
    private double distanciaKm;
    private double pesoKg;
    private StatusEntrega status;
    private Entregador entregadorAtribuido;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConclusao;

    public enum StatusEntrega {
        PENDENTE,
        EM_ROTA,
        ENTREGUE,
        CANCELADO
    }

    public Entrega(String id, String enderecoDestino, String cliente, double distanciaKm, double pesoKg) {
        this.id = id;
        this.enderecoDestino = enderecoDestino;
        this.cliente = cliente;
        this.distanciaKm = distanciaKm;
        this.pesoKg = pesoKg;
        this.status = StatusEntrega.PENDENTE;
        this.dataCriacao = LocalDateTime.now();
        this.entregadorAtribuido = null;
    }

    public Entrega(String id, String enderecoDestino, String cliente, double distanciaKm, double pesoKg, Entregador entregador) {
        this(id, enderecoDestino, cliente, distanciaKm, pesoKg);
        this.entregadorAtribuido = entregador;
        this.status = StatusEntrega.EM_ROTA;
        entregador.setDisponivel(false);
    }

    public String getId() { return id; }
    public String getEnderecoDestino() { return enderecoDestino; }
    public String getCliente() { return cliente; }
    public double getDistanciaKm() { return distanciaKm; }
    public double getPesoKg() { return pesoKg; }
    public StatusEntrega getStatus() { return status; }
    public Entregador getEntregadorAtribuido() { return entregadorAtribuido; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }

    public void atualizarStatus(StatusEntrega novoStatus) {
        this.status = novoStatus;
        if (novoStatus == StatusEntrega.ENTREGUE) {
            this.dataConclusao = LocalDateTime.now();
            if (entregadorAtribuido != null) {
                entregadorAtribuido.setDisponivel(true);
            }
        } else if (novoStatus == StatusEntrega.CANCELADO) {
            if (entregadorAtribuido != null) {
                entregadorAtribuido.setDisponivel(true);
                entregadorAtribuido = null;
            }
        }
    }

    public void atualizarStatus(StatusEntrega novoStatus, String observacao) {
        atualizarStatus(novoStatus);
        System.out.println("Observação: " + observacao);
    }

    public void atribuirEntregador(Entregador entregador) {
        if (entregador.isDisponivel() && entregador.getCapacidadeCargaKg() >= this.pesoKg) {
            this.entregadorAtribuido = entregador;
            this.status = StatusEntrega.EM_ROTA;
            entregador.setDisponivel(false);
            System.out.println("✅ Entrega " + id + " atribuída a " + entregador.getNome());
        } else {
            System.out.println("❌ Não foi possível atribuir! Verifique disponibilidade ou capacidade de carga.");
        }
    }

    public void exibirDetalhes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("═══════════════════════════════════");
        System.out.println("📦 ENTREGA #" + id);
        System.out.println("   Cliente: " + cliente);
        System.out.println("   Endereço: " + enderecoDestino);
        System.out.println("   Distância: " + distanciaKm + " km");
        System.out.println("   Peso: " + pesoKg + " kg");
        System.out.println("   Status: " + status);
        System.out.println("   Criado em: " + dataCriacao.format(formatter));
        if (entregadorAtribuido != null) {
            System.out.println("   Entregador: " + entregadorAtribuido.getNome() + " (" + entregadorAtribuido.getTipo() + ")");
            System.out.println("   Custo estimado: R$ " + String.format("%.2f", entregadorAtribuido.calcularCustoEntrega(distanciaKm)));
            System.out.println("   Tempo estimado: " + entregadorAtribuido.calcularTempoEntrega(distanciaKm) + " minutos");
        }
        if (dataConclusao != null) {
            System.out.println("   Concluído em: " + dataConclusao.format(formatter));
        }
        System.out.println("═══════════════════════════════════");
    }

    @Override
    public String toString() {
        return String.format("#%s - %s - %s - %s", id, cliente, enderecoDestino, status);
    }
}
