package util;

/*
 *O preço do litro da GASOLINA é R$ 2,90
 *O preço do litro do ETANOL é R$ 2,27
 *Velocidade de abastecimento da bomba de gasolina: 10 litros / minuto
 *Velocidade de abastecimento da bomba de álcool: 12 litros /minuto
 *
 *Para adicionar novos valores, basta gerar uma nova constante e especificar o novo valor e preço e vazão.
 * */

public class Constantes {
    
    // Caminhos de arquivos
    public static final String CAMINHO_ARQUIVO_MODELOS = "src/dados/modelos.csv";
    public static final String CAMINHO_ARQUIVO_VEICULOS = "src/dados/veiculos.csv";
    public static final String CAMINHO_ARQUIVO_LISTA_COMBUSTIVEL = "src/dados/listaCombustivel.csv";
	public static final String CAMINHO_ARQUIVO_COMBUSTIVEL= "src/dados/combustivel.csv";
    
    // Velocidade de abastecimento
    public static final int VELOCIDADE_ABASTECIMENTO_GASOLINA = 10; // litros/minuto
    public static final int VELOCIDADE_ABASTECIMENTO_ETANOL = 12; // litros/minuto
    
}