public abstract class Entregador implements Calculavel {
    private String id;
    private String nome;
    private String telefone;
    private boolean disponivel;
    protected double velocidadeMediaKmH;
    protected double capacidadeCargaKg;

    public Entregador(String id, String nome, String telefone, double velocidadeMediaKmH, double capacidadeCargaKg) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.velocidadeMediaKmH = velocidadeMediaKmH;
        this.capacidadeCargaKg = capacidadeCargaKg;
        this.disponivel = true;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
    public double getVelocidadeMediaKmH() { return velocidadeMediaKmH; }
    public double getCapacidadeCargaKg() { return capacidadeCargaKg; }

    public abstract String getTipo();

    public String exibirInfo() {
        return String.format("[%s] %s - Vel: %.1f km/h - Carga: %.1f kg - %s",
                getTipo(), nome, velocidadeMediaKmH, capacidadeCargaKg,
                disponivel ? "Disponível" : "Ocupado");
    }

    @Override
    public String toString() {
        return exibirInfo();
    }
}