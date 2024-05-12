package services;

import model.BombaCombustivel;
import model.Combustivel;

import java.util.List;
import java.util.ArrayList;

public class BombaService {

	CombustivelService combustivelService = new CombustivelService();

	public List<BombaCombustivel> iniciarBombasOperação() {
		int numeroBomba = 0;
		ArrayList<BombaCombustivel> listaBombasCombustivel = new ArrayList<BombaCombustivel>();

		// A partir da quantidade de preços definidos no arquivo, será estabelecido a
		// quantidade de bombas que estarão em operação no posto de gasolina
		List<Combustivel> combustivelConfigurado = combustivelService.retornarCombustivelConfigurado();

		int quantidadePrecosEstabelecidos = combustivelConfigurado.size();

		for (Combustivel combustivel : combustivelConfigurado) {
			numeroBomba++;
			BombaCombustivel bombaCombustivel = new BombaCombustivel(combustivel.getTipoCombustivel(),
					combustivel.getPrecoCombustivel(), combustivel.getVazao(), true, numeroBomba);
			listaBombasCombustivel.add(bombaCombustivel);
		}

		return listaBombasCombustivel;
	}

}
