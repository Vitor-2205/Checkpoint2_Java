public class EntregadorCarro extends Entregador {
    private static final double CUSTO_POR_KM = 2.50;
    private static final int VELOCIDADE_BASE = 50;

    public EntregadorCarro(String id, String nome, String telefone) {
        super(id, nome, telefone, VELOCIDADE_BASE, 100.0);
    }

    @Override
    public String getTipo() {
        return "CARRO";
    }

    @Override
    public double calcularCustoEntrega(double distanciaKm) {
        return distanciaKm * CUSTO_POR_KM;
    }

    @Override
    public int calcularTempoEntrega(double distanciaKm) {
        return (int) Math.ceil((distanciaKm / velocidadeMediaKmH) * 60);
    }
}
