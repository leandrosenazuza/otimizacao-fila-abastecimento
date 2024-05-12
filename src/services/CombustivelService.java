package services;

import java.util.List;

import model.Combustivel;

public class CombustivelService {
	
	ArquivoService arquivoService = new ArquivoService();
	
	public List<Combustivel> retornarCombustivelConfigurado() {
		return arquivoService.retornarCombustivelConfigurado();
		
	}

}
