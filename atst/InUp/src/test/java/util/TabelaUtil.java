package util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/** Classe de metodos que utilizam qualquer tabela dentro do sistema. */
public class TabelaUtil {

	// #region regiao dos atributos.

	/** Driver que será utilizado na tabela */
	public WebDriver driver;

	// #endregion

	// #region Regiao dos construtores.
	public TabelaUtil(WebDriver driver) {
		this.driver = driver;
	}
	// #endregion

	// #region regiao dos metodos.

	/**
	 * Metodo para obter a linha de determinado elemento.
	 * 
	 * @param valor  Nome ou valor a ser localizado na linha.
	 * @param tabela tabela a ser utilizada para o metodo.
	 */
	public int obterIndiceLinhaTransportadora(String valor, WebElement tabela) {
		List<WebElement> linhas = tabela.findElements(By.tagName("th"));
		int idLinha = 0;
		for (int i = 0; i < linhas.size(); i++) {
			if (linhas.get(i).getText().equalsIgnoreCase(valor)) {
				idLinha = i + 1;
				break;
			}
		}
		return idLinha;
	}

	/**
	 * Metodo para obter a linha de determinado elemento.
	 * 
	 * @param valor  Nome ou valor a ser localizado na linha.
	 * @param tabela tabela a ser utilizada para o metodo.
	 */
	public int obterLinha(String valor, WebElement tabela) {
		List<WebElement> linhas = tabela.findElements(By.tagName("tr"));

		int idLinha = 0;
		for (int i = 0; i < linhas.size(); i++) {
			List<WebElement> colunas = linhas.get(i).findElements(By.xpath("td"));
			for (int j = 0; j < colunas.size(); j++) {
				if (colunas.get(j).getText().equalsIgnoreCase(valor)) {
					idLinha = i + 1;
					break;
				}
			}
		}
		return idLinha;
	}

	/**
	 * Metodo para obter a linha de determinado elemento.
	 * 
	 * @param valor  Nome ou valor a ser localizado na linha.
	 * @param tabela tabela a ser utilizada para o metodo.
	 */
	public int obterLinhas(String valor, WebElement tabela) {
		List<WebElement> linhas = tabela.findElements(By.tagName("tr"));

		int idLinha = 0;
		for (int i = 0; i < linhas.size(); i++) {

			System.out.println(linhas.size());

			List<WebElement> colunas = linhas.get(i).findElements(By.xpath("td"));
			for (int j = 0; j < colunas.size(); j++) {
				if (colunas.get(j).getText().equalsIgnoreCase(valor)) {
					idLinha = i;
					System.out.println(idLinha);
					break;
				}
			}
		}
		return idLinha;
	}

	/**
	 * Metodo para obter a coluna de determinado elemento.
	 * 
	 * @param coluna Nome ou valor da coluna a ser localizado.
	 * @param tabela tabela a ser utilizada para o metodo.
	 */
	public int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.tagName("th"));
		int idColuna = 0;
		for (int i = 0; i < colunas.size(); i++) {
			if (colunas.get(i).getText().equalsIgnoreCase(coluna)) {
				idColuna = i + 1;
				break;
			}
		}
		return idColuna;
	}

	/** Metodo usado de exemplo para geracao de novos metodos utilizando tabelas. */
	public String obterStatusDoOrcamento(String colunaCodigo, String codigo, String colunaStatus) {
		WebElement tabela = driver.findElement(By.tagName("table"));
		int idLinha = obterIndiceLinhaTransportadora(codigo, tabela);
		int idColunaStatus = obterIndiceColuna(colunaStatus, tabela);
		String status = tabela.findElement(By.xpath("//tr[" + idLinha + "]/td[" + idColunaStatus + "]/span/span[1]"))
				.getText();
		return status;
	}
	// #endregion
}
