package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.*;
import util.Constantes;

public class ArquivoService {

	/*
	 * Carrega a lista de veículos
	 */
	public List<Veiculo> retornarVeiculos() {
		return this.carregarListaVeiculo();
	}

	/*
	 * Execução das rotinas de arquivos para extrair características dos Modelos de
	 * Veículos
	 */
	private List<ModeloVeiculo> retornarModeloVeiculos() {
		return this.carregarListaModeloVeiculo(lerModelos(), identificarTiposCombustivel(lerModelos()));
	}

	private List<Veiculo> carregarListaVeiculo() {
		List<ModeloVeiculo> modelosVeiculo = retornarModeloVeiculos();
		String[][] listaVeiculos = lerVeiculos();
		ArrayList<Veiculo> veiculoLista = new ArrayList<>();
		
		

		for (int linhaIndex = 1; linhaIndex < listaVeiculos.length; linhaIndex++) {
			String[] atributos = listaVeiculos[linhaIndex];
			String nomeModelo = atributos[0];
			String placaModelo = atributos[1];

			ModeloVeiculo modeloVeiculo = null;
			for (ModeloVeiculo modelo : modelosVeiculo) {
				if (modelo.getModelo().equals(nomeModelo)) {
					modeloVeiculo = modelo;
					break;
				}
			}

			if (modeloVeiculo != null) {
				Veiculo veiculo = new Veiculo(modeloVeiculo, placaModelo);
				veiculoLista.add(veiculo);
			} 
		}

		return veiculoLista;
	}

	/*
	 * Carrega a lista de modelos de veículo, considerando os tipos de combustível
	 * disponíveis
	 */
	private List<ModeloVeiculo> carregarListaModeloVeiculo(String[][] matrizArquivo, List<String> tiposCombustivel) {
	    ArrayList<ModeloVeiculo> modeloVeiculoLista = new ArrayList<>();
	    int numeroCombustivel = tiposCombustivel.size();

	    for (int linhaIndex = 1; linhaIndex < matrizArquivo.length; linhaIndex++) {
	        String[] atributos = matrizArquivo[linhaIndex];

	        if (atributos.length == numeroCombustivel + 2) {
	            String modelo = atributos[0];
	            ArrayList<ConsumoVeiculo> consumoLista = new ArrayList<>();

	            for (int i = 1; i <= numeroCombustivel; i++) {
	                String combustivel = tiposCombustivel.get(i - 1);
	                String consumoStr = atributos[i].trim();
	                double consumoValor = 0.0;
	                if (!consumoStr.isEmpty()) {
	                    consumoValor = Double.parseDouble(consumoStr.replace(',', '.'));
	                } else {
	                    // Para controlar os valores que vem nulo, e não dar como "combustível vantajoso", joguei ele no valor máximo, para nunca ser considerado na escolha.
	                    consumoValor = Double.MAX_VALUE;
	                }
	                ConsumoVeiculo consumoVeiculo = new ConsumoVeiculo(combustivel, consumoValor);
	                consumoLista.add(consumoVeiculo);
	            }

	            int capacidadeTanque = Integer.parseInt(atributos[(numeroCombustivel + 1)]);

	            ModeloVeiculo modeloVeiculo = new ModeloVeiculo(modelo, consumoLista, capacidadeTanque);
	            modeloVeiculoLista.add(modeloVeiculo);
	        } 
	    }

	    return modeloVeiculoLista;
	}


	/*
	 * Realiza a leitura dos arquivos .csv
	 */
	private String[][] lerArquivoCSV(String caminhoArquivo) {
		List<String[]> linhasArquivo = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				String[] atributos = linha.split(";");
				linhasArquivo.add(atributos);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[][] matrizArquivo = new String[linhasArquivo.size()][];
		for (int i = 0; i < linhasArquivo.size(); i++) {
			matrizArquivo[i] = linhasArquivo.get(i);
		}

		return matrizArquivo;
	}

	public List<String> identificarTiposCombustivel(String[][] matrizArquivo) {
		List<String> tiposCombustivel = new ArrayList<>();

		if (matrizArquivo.length > 0) {
			String[] cabecalho = matrizArquivo[0];

			for (int i = 1; i < cabecalho.length; i++) {
				String parte = cabecalho[i];
				if (parte.contains("Consumo") && parte.contains("(Km/L)")) {
					String tipoCombustivel = parte
							.substring(parte.indexOf("Consumo") + "Consumo ".length(), parte.indexOf(" (Km/L)")).trim();
					tiposCombustivel.add(tipoCombustivel);
				}
			}
		}

		gravarArquivoTiposCombustivel(tiposCombustivel);

		return tiposCombustivel;
	}

	private void gravarArquivoTiposCombustivel(List<String> tiposCombustivel) {
		File arquivo = new File(Constantes.CAMINHO_ARQUIVO_LISTA_COMBUSTIVEL);

		try {
			if (!arquivo.exists()) {
				arquivo.createNewFile();
			}

			try (BufferedWriter escrever = new BufferedWriter(new FileWriter(arquivo))) {
				for (String tipoCombustivel : tiposCombustivel) {
					escrever.write(tipoCombustivel.toUpperCase());
					escrever.newLine();
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * Retorna a lista de Combustíveis-Preço
	 */

	public List<Combustivel> retornarCombustivelConfigurado() {
		return carregarCombustivel(lerCombustivel());
	}

	private List<Combustivel> carregarCombustivel(String[][] matrizArquivo) {
		ArrayList<Combustivel> combustivelLista = new ArrayList<>();

		for (int linhaIndex = 1; linhaIndex < matrizArquivo.length; linhaIndex++) {
			String[] atributos = matrizArquivo[linhaIndex];

			if (atributos.length >= 3) {
				String tipoCombustivel = atributos[0];
				Double precoCombustivel = 0.0;
				Double vazao = 0.0;

				if (!atributos[1].isEmpty()) {
					precoCombustivel = Double.parseDouble(atributos[1].replace(',', '.'));
				}

				if (!atributos[2].isEmpty()) {
					vazao = Double.parseDouble(atributos[2].replace(',', '.'));
				}

				Combustivel combustivel = new Combustivel(tipoCombustivel, precoCombustivel, vazao);
				combustivelLista.add(combustivel);
			} 
		}

		return combustivelLista;
	}

	/*
	 * Retorna a lista de Combustíveis cadastrados
	 */

	public List<String> retornarListaCombustiveis() {
		return this.carregarListaCombustiveis(lerCombustivel());
	}

	private List<String> carregarListaCombustiveis(String[][] matrizArquivo) {
		List<String> combustivelLista = new ArrayList<>();

		for (String[] linha : matrizArquivo) {
			if (linha != null && linha.length > 0) {
				combustivelLista.add(linha[0]);
			}
		}

		return combustivelLista;
	}

	/*
	 * Realiza a leitura do arquivo responsável pelos modelos de veículos, ou seja,
	 * modelos.csv
	 */
	private String[][] lerCombustivel() {
		return lerArquivoCSV(Constantes.CAMINHO_ARQUIVO_COMBUSTIVEL);
	}

	/*
	 * Realiza a leitura do arquivo responsável pelos modelos de veículos, ou seja,
	 * modelos.csv
	 */
	private String[][] lerListaCombustiveis() {
		return lerArquivoCSV(Constantes.CAMINHO_ARQUIVO_LISTA_COMBUSTIVEL);
	}

	/*
	 * Realiza a leitura do arquivo responsável pelos modelos de veículos, ou seja,
	 * modelos.csv
	 */
	private String[][] lerModelos() {
		return lerArquivoCSV(Constantes.CAMINHO_ARQUIVO_MODELOS);
	}

	/*
	 * Realiza a leitura do arquivo responsável pelos veículos, ou seja,
	 * veiculos.csv
	 */
	private String[][] lerVeiculos() {
		return lerArquivoCSV(Constantes.CAMINHO_ARQUIVO_VEICULOS);
	}
}
