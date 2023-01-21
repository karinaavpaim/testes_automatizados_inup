package page.transportadoras;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import page.BasePO;
import util.TabelaUtil;

/** Page Object da pagina de Transportadoras. */
public class TransportadorasPO extends BasePO{

	//#region Regiao dos elementos.
	
	/** Enum para definir o tipo de acao a ser tomada para uma transportadora. */
	public enum tipoAcao { EDITAR, EXCLUIR; }

	/** Elemento de input para localizar uma transportadora. */
	@FindBy(id = "search")
	public WebElement inputTransportadora;

	/** Elemento de titulo de Listagem. */
	@FindBy(css = ".mt-3 .flex-grow-1")
	public WebElement tituloListagem;

	/** Elemento para selecionar somente transportadoras inativas. */
	@FindBy(id = "status")
	public WebElement checkboxInativo;

	/** Elemento para pesquisar transportadoras. */
	@FindBy(xpath = "//button[contains(.,' Pesquisar transportadora(s)')]")
	public WebElement botaoPesquisar;

	/** Elemento para adicionar nova transportadoras. */
	@FindBy(css = ".fa-plus")
	public WebElement botaoAdicionarTransportadora;

	/** Elemento para editar as informacoes de uma transportadora. */
	@FindBy(css = ".fa-pencil-alt > path")
	public WebElement botaoEditarTransportadora;

	/** Elemento para excluir uma transportadora. */
	@FindBy(css = ".fa-trash > path")
	public WebElement botaoExcluirTransportadora;

	/** Elemento de primeira razao social da listagem. */
	@FindBy(css = ".align-middle:nth-child(1) > .text-truncate")
	public WebElement primeiraRazaoSocialListagem;

	/** Elemento de primeiro nome da listagem. */
	@FindBy(css = ".align-middle:nth-child(2) > .text-truncate")
	public WebElement primeiraNomeListagem;

	/** Elemento de primeiro CPF ou CNPJ da listagem. */
	@FindBy(css = ".text-right:nth-child(3) > .text-truncate")
	public WebElement primeiroCPFouCNPJListagem;

	/** Elemento de primeiro telefone da listagem. */
	@FindBy(css = ".text-right:nth-child(4) > .text-truncate")
	public WebElement primeiroTelefoneListagem;

	/** Elemento de primeiro contato da listagem. */
	@FindBy(css = ".align-middle:nth-child(5) > .text-truncate")
	public WebElement primeiroContatoListagem;
	//#endregion

	//#region Região dos construtores.	

	/** Construtor da classe.
	 * @param driver Driver da classe.
	 */
	public TransportadorasPO(WebDriver driver) {
		super(driver);
	}
	//#endregion

	//#region Regiao dos metodos

	/** Metodo para navegar para o menu Transportadoras. */
	public void navegarParaTransportadoras(){
		menuTransportadoras.click();
		aguardarElemento(driver, 5);
	}

	/** Metodo para pesquisar por uma Transportadora.
	 * @param nome nome da transportadora a ser pesquisado.
	 */
	public void pesquisarTransportadora(String nome){
		limparCampoInput(inputTransportadora);
		inputTransportadora.sendKeys(nome);
		
		botaoPesquisar.click();
		aguardarCarregamentoDaPagina();
	}

	/** Metodo para pesquisar todas as transportadoras. */
	public void pesquisar(){
		botaoPesquisar.click();
	
		aguardarCarregamentoDaPagina();
	}

	/** Metodo para verificar se uma transportadora esta na tabela.
	 * @param nome Nome da transportadora.
	 */
	public boolean verificarNomeTransportadoraNaTabela(String nome) {
		List<WebElement> razaoSocial = tabela.findElements(By.tagName("th"));
		List<WebElement> informacaoTransportadora = tabela.findElements(By.tagName("td"));
		for (WebElement nomeTransportadora : razaoSocial)
		for (WebElement transportadora : informacaoTransportadora)

		if (nomeTransportadora.getText().equalsIgnoreCase(nome)){
			return true;
		} else if  (transportadora.getText().equalsIgnoreCase(nome)){
			return true;
		}
		return false;
	}

	/** Metodo para buscar uma transportadora e realizar a acao de editar ou excluir.
	 * @param acao Selecionar qual acao ira realizar: editar ou excluir.
	 * @param nomeTransportadora Nome da transportadora para editar ou excluir.
	*/
	public void localizarTransportadoraERealizarAcao(tipoAcao acao, String nomeTransportadora) {
		
		TabelaUtil tabelaUtil = new TabelaUtil(driver);

		if (acao == tipoAcao.EDITAR) {
			WebElement tabela = driver.findElement(By.xpath("//table/tbody"));
			int idLinha = tabelaUtil.obterIndiceLinhaTransportadora(nomeTransportadora, tabela);

			WebElement botaoEditar = tabela.findElement(By.xpath("//tr["+idLinha+"]/td/div/button[@class='btn btn-primary btn-sm']"));
			rolarPagina(botaoEditar);
			botaoEditar.click();
		} else if (acao == tipoAcao.EXCLUIR) {
			WebElement tabela = driver.findElement(By.xpath("//table/tbody"));
			int idLinha = tabelaUtil.obterIndiceLinhaTransportadora(nomeTransportadora, tabela);

			WebElement botaoExcluir = tabela.findElement(By.xpath("//tr["+idLinha+"]/td/div/button[@class='btn btn-danger btn-sm']"));
			rolarPagina(botaoExcluir);
			botaoExcluir.click();
		}
	}
	//#endregion
}
