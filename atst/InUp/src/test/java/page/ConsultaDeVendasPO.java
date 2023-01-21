package page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/** Page Object da pagina de Consulta de Vendas. */
public class ConsultaDeVendasPO extends BasePO{

	//#region Regiao dos elementos.

	/** Elemento para digitar o codigo da boleta. */
	@FindBy(id = "codigo")
	public WebElement inputBoleta;

	/** Elemento para digitar o produto. */
	@FindBy(id = "produto")
	public WebElement inputProduto;

	/** Elemento para selecionar o produto conforme digitado no autocomplete. */
	@FindBy(css = ".text-truncate")
	public WebElement selecionarProduto;

	/** Elemento para digitar o nome do cliente. */
	@FindBy(id = "clientes")
	public WebElement inputCliente;

	/** Elemento de botao de filiais. */
	@FindBy(xpath = "//button[text()=' Filiais ']")
	public WebElement botaoFiliais;

	/** Elemento para selecionar o vendedor. */
	@FindBy(id = "vendedor")
	public WebElement selectVendedor;

	/** Elemento de periodo. */
	@FindBy(id = "Período")
	public WebElement inputPeriodo;

	/** Elemento para selecionar o periodo. */
	@FindBy(css = ".fa-calendar-alt")
	public WebElement botaoCalendario;

	/** Elemento para selecionar uma data pré estabelecida. */
	@FindBy(css = ".form-select-sm")
	public WebElement selectDataPreEstabelecida;	

	/** Elemento para selecionar o tipo de vendas. */
	@FindBy(id = "tipo")
	public WebElement selectTipoDeVendas;

	/** Elemento para selecionar somente vendas excluidas. */
	@FindBy(id = "status")
	public WebElement checkboxVendasExcluidas;

	/** Elemento para pesquisar as vendas.*/
	@FindBy(xpath = "//button[text()=' Pesquisar Vendas']")
	public WebElement botaoPesquisarVendas;

	/** Elemento de primeiro nome do cliente na listagem.*/
	@FindBy(xpath = "//td[4]")
	public WebElement nomeClienteListagem;

	/** Elemento de primeiro numero da boleta na listagem.*/
	@FindBy(xpath = "//td[2]")
	public WebElement numeroBoletaListagem;

	/** Elemento de primeira filial na listagem.*/
	@FindBy(xpath = "//td[5]")
	public WebElement nomeFilialListagem;

	/** Elemento de primeiro tipo de venda na listagem.*/
	@FindBy(xpath = "//td[6]/span")
	public WebElement tipoDeVendaListagem;

	/** Elemento de status da primeira venda da listagem.*/
	@FindBy(xpath = "//td[7]/span")
	public WebElement statusListagem;

	/** Elemento para cancelar a primeira venda da listagem.*/
	@FindBy(css = ".btn-danger")
	public WebElement botaoCancelarVenda;

	/** Elemento de mensagem de confirmação.*/
	@FindBy(css = ".modal-body > .mb-0")
	public WebElement mensagemConfirmacao;	
	
	/** Elemento para abrir os detalhes do primeiro item da listagem.*/
	@FindBy(css = ".fa-plus")
	public WebElement botaoExpandirDetalhesVenda;

	/** Elemento para fechar os detalhes do primeiro item da listagem.*/
	@FindBy(css = ".fa-minus")
	public WebElement botaoFecharDetalhesPrimeiroItem;

	/** Elemento de código da boleta nos detalhes do primeiro item da listagem.*/
	@FindBy(css = ".w-50:nth-child(1)")
	public WebElement codigoBoleta;

	/** Elemento de nome do cliente nos detalhes do primeiro item da listagem.*/
	@FindBy(css = "p:nth-child(4)")
	public WebElement nomeCliente;

	/** Elemento de valor da venda nos detalhes do primeiro item da listagem.*/
	@FindBy(css = "p:nth-child(8)")
	public WebElement valorDaVenda;
	//#endregion

	//#region Região dos construtores.

	/** Construtor da classe.
	 * @param driver Driver da classe.
	 */
	public ConsultaDeVendasPO(WebDriver driver) {
		super(driver);
	}
	//#endregion

	//#region Regiao dos metodos.

	/** Metodo para navegar para o menu Consulta de Vendas. */
	public void navegarParaConsultaDeVendas(){
		menuConsultaDeVendas.click();
		
		aguardarInvisibilidadeDoElemento(driver, 20, carregamentoDaPagina);
		aguardarElemento(driver, 10);
	}

	/** Metodo para pesquisar vendas. */
	public void pesquisarVendas(){
		aguardarElementoSerClicavel(driver, 5, botaoPesquisarVendas);
		botaoPesquisarVendas.click();

		aguardarCarregamentoDaPagina();
	}

	/** Metodo para selecionar o tipo de venda.
	 * @param index indicar por index o tipo de venda.
	 */
	public void selecionarTipoDeVenda(int index){
		Select tipos = new Select(selectTipoDeVendas);
		
		tipos.selectByIndex(index);
	}

	/** Metodo para localizar o codigo da venda.
	 * @param codigo codigo da venda a ser inserido.
	 */
	public void buscarCodigoVenda(String codigo){
		aguardarInvisibilidadeDoElemento(driver, 10, carregamentoDaPagina);
		
		limparCampoInput(inputBoleta);
		inputBoleta.sendKeys(codigo);
		
		botaoPesquisarVendas.click();
			
		aguardarInvisibilidadeDoElemento(driver, 10, carregamentoDaPagina);
	}

	/** Metodo para abrir os detalhes dos itens da listagem por index.
	 * @param index index do item da listagem que deseja abrir
	*/
	public String obterValorFreteDetalhes(int index) {
		List<WebElement> listaElementos = tabela.findElements(By.xpath("//p[contains(.,'Frete:')]"));
	
		return listaElementos.get(index).getText().replace("Frete: R$ ", "").replace(",", ".");
	}

	/** Metodo para obter valor do troco no resumo.*/
	public String obterValorTroco() {
	WebElement span = driver.findElement(By.xpath("//span[contains(.,'Troco:')]"));
	WebElement tagnamePai = span.findElement(By.xpath(".."));
	WebElement spanValor = tagnamePai.findElement(By.xpath("span[2]"));

	return spanValor.getText().replace("R$ ", "").replace(".", "").replace(",", ".");
	}

    /** Metodo para retornar a soma das vendas da listagem*/
	public String somaDosValoresDaListagem() {
		List<WebElement> valores = tabela.findElements(By.xpath("//td[contains(text(),'R$')]"));
   
    	List<Double> listaValores = new ArrayList<>();

    	for(int i = 0; i < valores.size(); i ++){

      		listaValores.add(Double.valueOf(valores.get(i).getText()
			  .replace("R$ ", "")
			  .replace(".", "")
			  .replace(",", ".")));
		}
		double valorSoma = listaValores.stream().mapToDouble(Double::doubleValue).sum();
		
		return formatarValorDecimal(valorSoma);
	}

	/** Metodo para retornar a soma dos valores dentro do card.
	 * @param descricao descricao dos valores dentro do card a serem somados.
	*/
	public String somaDosValoresDoCard(String descricao) {
		List<WebElement> cards = driver.findElements(By.cssSelector(".fa-plus"));

		for(int i = 0; i < cards.size(); i ++){
			cards.get(i).click();
		}
			
		List<WebElement> b =  driver.findElements(By.xpath("//p[contains(.,'" + descricao + ":')]"));
		List<Double> listaValores = new ArrayList<>();
		
		for(int j = 0; j < b.size(); j ++){
			listaValores.add(Double.valueOf(b.get(j).getText()
			.replace(descricao + ":", "")
			.replace("R$", "")
			.replace(".", "")
			.replace(",", ".")
			.replace(" ", "")));	   		
		}
		double valorSoma = listaValores.stream().mapToDouble(Double::doubleValue).sum();
				
		return formatarValorDecimal(valorSoma);
	}
	//#endregion
}

