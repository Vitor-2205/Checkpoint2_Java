public class EntregadorBicicleta extends Entregador {
    private static final double CUSTO_POR_KM = 0.50;
    private static final int VELOCIDADE_BASE = 15;

    public EntregadorBicicleta(String id, String nome, String telefone) {
        super(id, nome, telefone, VELOCIDADE_BASE, 10.0);
    }

    @Override
    public String getTipo() {
        return "BICICLETA";
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
