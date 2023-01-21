package util.geradorDeDocumentos;

import java.util.Random;

/** Classe para gerar numeracao de um RG. */
public class GeraRG {
	
	public String rg(boolean comPontos) {
		String numerosConcatenados;
		String numeroGerado;
		Random numeroAleatorio = new Random();
		//numeros gerados
		int n1 = numeroAleatorio.nextInt(10);
		int n2 = numeroAleatorio.nextInt(10);
		int n3 = numeroAleatorio.nextInt(10);
		int n4 = numeroAleatorio.nextInt(10);
		int n5 = numeroAleatorio.nextInt(10);
		int n6 = numeroAleatorio.nextInt(10);
		int n7 = numeroAleatorio.nextInt(10);
		int n8 = numeroAleatorio.nextInt(10);
		int n9 = numeroAleatorio.nextInt(10);

		//Concatenando os numeros
		numerosConcatenados = String.valueOf(n1) + String.valueOf(n2) + String.valueOf(n3)  + String.valueOf(n4) +
				String.valueOf(n5) + String.valueOf(n6) + String.valueOf(n7) +String.valueOf(n8)  +
				String.valueOf(n9);
		numeroGerado = numerosConcatenados;

        if (comPontos)
            numeroGerado = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9;
        else
            numeroGerado = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9;

		return numeroGerado;
	}

	/** Gerador de RG para testes de cadastros novos de clientes pessoa fisica, servindo
	 * como inscricao estadual para pessoas jurudicas e documento para pessoa estrangeira*/
	public static void main(String[] args) {
		GeraRG geraRG = new GeraRG();
		String rg = geraRG.rg(true);
		System.out.printf(rg);
	}

	public static String imprimeCNPJ(String CNPJ) {
		// mascara do CNPJ: 99.999.999.9999-99
		return (CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "." + CNPJ.substring(5, 8) + "." + CNPJ.substring(8, 12) + "-" + CNPJ.substring(12, 14));
	}

	/** Metodo para gerar um RG.
	 * @return Retorna o numero gerado.
	 */
	public String comIdentidade() {
		return rg(true);
	}

}
