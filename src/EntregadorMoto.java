public class EntregadorMoto extends Entregador {
    private static final double CUSTO_POR_KM = 1.50;
    private static final int VELOCIDADE_BASE = 40;

    public EntregadorMoto(String id, String nome, String telefone) {
        super(id, nome, telefone, VELOCIDADE_BASE, 15.0);
    }

    @Override
    public String getTipo() {
        return "MOTO";
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
