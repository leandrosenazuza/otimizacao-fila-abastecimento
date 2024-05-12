package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import model.BombaCombustivel;
import model.ConsumoVeiculo;
import model.Veiculo;

public class AbastecimentoService {

	Double tempoTotal = 0.0;
    private List<String> registrosAbastecimento = new ArrayList<>();
    private LocalDateTime inicioAbastecimento = LocalDateTime.of(2024, 5, 11, 0, 0);

    public void operacao(List<Veiculo> listaVeiculosCarregados, List<BombaCombustivel> listaBomba) {

        for (Veiculo veiculo : listaVeiculosCarregados) {
            realizarAbastecimento(veiculo, listaBomba);
        }

        imprimirResumoSimulacao(listaBomba);
    }

    private void realizarAbastecimento(Veiculo veiculo, List<BombaCombustivel> listaBomba) {
        String tipoCombustivelMaisVantajoso = determinarCombustivelMaisVantajoso(veiculo, listaBomba);

        BombaCombustivel bomba = encontrarBomba(veiculo, tipoCombustivelMaisVantajoso.toUpperCase(), listaBomba);

        if (bomba != null) {
            double capacidadeTanque = veiculo.getModeloVeiculo().getCapacidadeTanque();
            double volumeAbastecido = bomba.getVolumeAbastecido() != null ? bomba.getVolumeAbastecido() : 0.0;

            double tempoGasto = capacidadeTanque / (bomba.getVelocidadeAbastecimento() * 60);
            
            tempoTotal += tempoGasto;

            bomba.setVolumeAbastecido(volumeAbastecido + capacidadeTanque);
            bomba.setEmOperacao(true);

            registrosAbastecimento.add("[" + calcularTempoAbastecimento(tempoTotal) + "] "
                    + "Veículo modelo " + veiculo.getModeloVeiculo().getModelo() + ", placa " + veiculo.getPlaca()
                    + " foi abastecido com " + veiculo.getModeloVeiculo().getCapacidadeTanque() + " litros de "
                    + tipoCombustivelMaisVantajoso + ".");
        } else {
            registrosAbastecimento.add("Não há bomba disponível para o veículo " + veiculo.getModeloVeiculo().getModelo()
                    + ", placa " + veiculo.getPlaca() + ".");
        }
    }

    private BombaCombustivel encontrarBomba(Veiculo veiculo, String tipoCombustivel, List<BombaCombustivel> listaBomba) {
        Double menorPreco = Double.MAX_VALUE;
        BombaCombustivel bombaMaisVantajosa = null;

        for (BombaCombustivel bomba : listaBomba) {
            if (bomba.getTipoCombustivel().equals(tipoCombustivel.toUpperCase()) && bomba.getEmOperacao()) {
                if (bomba.getValorCombustivel() < menorPreco) {
                    menorPreco = bomba.getValorCombustivel();
                    bombaMaisVantajosa = bomba;
                }
            }
        }

        return bombaMaisVantajosa;
    }

    private String calcularTempoAbastecimento(double tempoGasto) {
        long minutos = (long) (tempoGasto * 60); 
        LocalDateTime tempoAtual = inicioAbastecimento.plusMinutes(minutos);
        return DateTimeFormatter.ofPattern("HH:mm").format(tempoAtual);
    }

    private void imprimirResumoSimulacao(List<BombaCombustivel> listaBomba) {
        System.out.println("Resultado da simulação");
        System.out.println("---------------------------");
        registrosAbastecimento.forEach(System.out::println);

        System.out.println("\nResumo da simulação");
        System.out.println("---------------------------");
        for (BombaCombustivel bomba : listaBomba) {
            System.out.println("Total abastecido na bomba " + bomba.getNumeroBomba() + " (" + bomba.getTipoCombustivel() + "): " + bomba.getVolumeAbastecido() + " litros");
        }

        double totalGeralGasolina = listaBomba.stream()
                .filter(bomba -> bomba.getTipoCombustivel().equals("GASOLINA"))
                .mapToDouble(BombaCombustivel::getVolumeAbastecido)
                .sum();
        System.out.println("Total geral abastecido de GASOLINA: " + totalGeralGasolina + " litros");
    }

    private String determinarCombustivelMaisVantajoso(Veiculo veiculo, List<BombaCombustivel> listaBomba) {
        String combustivelMaisVantajoso = null;
        double menorCustoPorKm = Double.MAX_VALUE;

        for (ConsumoVeiculo consumoVeiculo : veiculo.getModeloVeiculo().getConsumo()) {
            String tipoCombustivel = consumoVeiculo.getTipoCombustivel();
            double consumo = consumoVeiculo.getConsumo();

            Double precoCombustivel = null;
            for (BombaCombustivel bomba : listaBomba) {
                if (bomba.getTipoCombustivel().equals(tipoCombustivel.toUpperCase())) {
                    precoCombustivel = bomba.getValorCombustivel();
                    break;
                }
            }

            if (precoCombustivel != null) {
                double custoPorKm = precoCombustivel * consumo;
                if (custoPorKm < menorCustoPorKm) {
                    menorCustoPorKm = custoPorKm;
                    combustivelMaisVantajoso = tipoCombustivel.toUpperCase();
                }
            }
        }

        return combustivelMaisVantajoso;
    }
}
