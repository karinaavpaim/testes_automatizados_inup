package page.netShopper;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.BasePO;

/** Page Object da pagina do NetShopper. */
public class NetShopperPO extends BasePO{
	
	//#region Regiao dos elementos.

	/** Elemento para restaurar informações da venda. */
	@FindBy(css = ".fa-search")
	public WebElement botaoRestaurarInformacoes;

	/** Elemento para expandir o input do cliente quando fechado. */
	@FindBy(css = ".btn-link > .fa-plus")
	public WebElement botaoExpandirCliente;
	
	/** Elemento de autocomplete do campo cliente. */
	@FindBy(id = "customer")
	public WebElement inputCliente;

	/** Input do campo cliente quando o cliente ja esta selecionado. */
	@FindBy(id = "customer-2")
	public WebElement inputClienteSelecionado;

	/** Elemento para adicionar um novo cliente. */
	@FindBy(id = "button-add-customer")
	public WebElement botaoAdicionarCliente;

	/** Elemento para editar informacoes de um cliente. */
	@FindBy(css = ".fa-pencil-alt")
	public WebElement botaoEditarCliente;

	/** Elemento para selecionar a loja. */
	@FindBy(id = "store")
	public WebElement selectLoja;

	/** Elemento para selecionar um elemento da tabela de precos. */
	@FindBy(id = "priceTable")
	public WebElement selectTabelaDePreco;

	/** Elemento para selecionar o vendedor. */
	@FindBy(id = "vendors")
	public WebElement selectVendedor;

	/** Elemento para inserir a quantidade de produtos. */
	@FindBy(xpath = "//input[@id='qtd']")
	public WebElement inputQuantidade;

	/** Elemento de mensagem de quantidade obrigatoria. */
	@FindBy(css = ".input-group > #qtdHelp")
	public WebElement mensagemQuantidadeObrigatoria;

	/** Elemento para inserir o produto. */
	@FindBy(xpath = "//input[@id='product']")
	public WebElement inputProduto;

	/** Elemento de mensagem de produto não encontrado. */
	@FindBy(css = ".invalid-feedback")
	public WebElement mensagemProdutoNaoEncontrado;

	/** Elemento de limpar input produto, quando o produto não foi encontrado. */
	@FindBy(css = ".fa-xmark")
	public WebElement botaoLimparInputProduto;

	/** Elemento para excluir o item adicionado. */
	@FindBy(css = ".btn-sm > .fa-trash-alt")
	public WebElement botaoExcluirItem;

	/** Elemento de abas de novas vendas. */
	@FindBy(xpath = "//ul[contains(@role, 'tablist')]")
	public WebElement barraNovasVendas;
	
	/** Elemento para adicionar nova aba de vendas. */
	@FindBy(css = ".text-success > .fa-duotone")
	public WebElement botaoNovaAbaDeVenda;

	/** Elemento para selecionar a aba da Venda 1. */
	@FindBy(xpath = "//span[contains(text(), 'Venda 1 - ')]")
	public WebElement abaVendaUm;

	/** Elemento para excluir uma aba de venda. */
	@FindBy(css = "#ngb-nav-1 > .position-relative")
	public WebElement excluirAbaVenda;

	/** Elemento para identificar o primeiro produto adicionado. */
	@FindBy(css = ".flex-grow-1 > .h4")
	public WebElement primeiroProdutoAdicionadoAoCarrinho;
	
	/** Elemento de titulo da mensagem flutuante. */
	@FindBy(css = ".modal-title")
	public WebElement tituloMensagemFlutuante;

	/** Elemento para editar o frete. */
	@FindBy(css = ".mb-1 > span")
	public WebElement botaoEditarFrete;

	/** Elemento de valor do frete. */
	@FindBy(css = ".list-group-item > div > .float-end")
	public WebElement valorTotalFrete;

	/** Elemento para visualizar as informacoes sobre as promocoes aplicadas. */
	@FindBy(css = ".badge")
	public WebElement iconePromocaoAplicada;
	
	/** Elemento de promocao aplicada em valor. */
	@FindBy(css = ".text-info > .float-right")
	public WebElement promocaoAplicadaValor;

	/** Elemento de promocao aplicada em porcentagem. */
	@FindBy(css = ".d-block > .float-end")
	public WebElement promocaoAplicadaPorcentagem;
	
	/** Elemento de valor total a pagar. */
	@FindBy(css = ".ng-star-inserted > .font-weight-bold")
	public WebElement valorTotalAPagar;

	/** Elemento de valor total a pagar. */
	@FindBy(css = ".ng-star-inserted:nth-child(1) > .font-weight-bold")
	public WebElement valorTotalTroco;

	/** Elemento para selecionar opcao presente. */
	@FindBy(id = "isGift")
	public WebElement inputPresente;

	/** Elemento para inserir o nome de quem envia o presente. */
	@FindBy(id = "from")
	public WebElement inputPresenteDe;

	/** Elemento para inserir o nome para quem o presente e enviado. */
	@FindBy(id = "to")
	public WebElement inputPresentePara;

	/** Elemento para inserir a mensagem do presente. */
	@FindBy(id = "message")
	public WebElement inputMensagemPresente;

	/** Elemento para selecionar o tipo de presente. */
	@FindBy(id = "type")
	public WebElement selectTipoMensagem;

	/** Elemento de opção para restaurar pedido. */
	@FindBy(xpath = "//button[contains(.,'Restaurar Pedido')]")
	public WebElement botaoRestaurarPedido;

	/** Elemento para copiar o link de pagamento. */
	@FindBy(xpath = "//button[contains(.,'Link de pagamento')]")
	public WebElement botaoCopiarLinkDePagamento;

	/** Elemento para selecionar a opcao de finalizar pedido. */
	@FindBy(xpath = "//button[contains(.,'Finalizar Pedido')]")
	public WebElement botaoFinalizarPedido;

	/** Elemento para selecionar a opcao de cancelar pedido. */
	@FindBy(xpath = "//button[contains(.,'Cancelar Pedido')]")
	public WebElement botaoCancelarPedido;

	/** Elemento para fechar a venda finalizada. */
	@FindBy(css = ".modal-footer > .btn")
	public WebElement botaoFecharVendaFinalizada;

	/** Elemento de mensagem de venda realizada com sucesso. */
	@FindBy(css = ".ng-star-inserted > .text-center:nth-child(2)")
	public WebElement mensagemVendaRealizadaComSucesso;

	/** Elemento de codigo da venda. */
	@FindBy(css = ".list-group:nth-child(3) > .list-group-item:nth-child(2) > .float-end")
	public WebElement codigoDaVenda;

	/** Elemento de status da venda exibida ao final da venda. */
	@FindBy(css = ".list-group-item:nth-child(5) > .float-right")
	public WebElement statusVenda;

	/** Elemento de select box da opção "É presente"*/
	@FindBy(xpath = "//label[contains(text(),'Tipo')]/following-sibling::select")
	public WebElement selectBoxPresente;

	/** Elemento de mensagem de modal. */
	@FindBy(css = ".modal-body > .mb-0")
	public WebElement textoMensagemModal;
	//#endregion

	//#region Regiao dos construtores

	/** Construtor da classe */
	public NetShopperPO(WebDriver driver) {
		super(driver);
	}
	//#endregion

	//#region Regiao dos metodos

	/** Metodo para navegar para o menu NetShopper. */
	public void navegarParaNetshopper(){
		menuNetShopper.click();
		aguardarElemento(driver, 5);
	}

	/**
	 * Metodo para buscar por um determinado produto no campo autocomplete e depois seleciona-lo para seguir com a venda.
	 * @param nomeProduto produto a ser buscado e selecionado no campo input.
	 * @param index seleciona o index da cor ou tamanho que deseja.
	 */
	public void adicionarProdutoAoCarrinho(String nomeProduto, int index) {
		selecionarNomeAutocomplete(inputProduto, nomeProduto);
		aguardarCarregamentoDaPagina();

    	if (driver.findElements(By.cssSelector(".modal-title")).size()!=0){
			String titulo = tituloMensagemFlutuante.getText();
			
			if (titulo.contains("Grade")){
				List<WebElement> grade = tabela.findElements(By.tagName("input"));
				grade.get(index).click();
			
				botaoAdicionarMensagemFlutuante.click();
			}
		} else {
			inputQuantidade.click();
		}
	}

	/** Metodo para selecionar o frete pelo nome.
	 * @param frete nome do frete a ser selecionado.
	*/
	public void selecionarFrete(String frete) {
		aguardarInvisibilidadeDoElemento(driver, 10, carregamentoDaPagina);

		if (driver.findElements(By.cssSelector(".modal-title")).size()!=0){
			String titulo = tituloMensagemFlutuante.getText();
			
			if (titulo.contains("Selecione o Frete")){
				WebElement spanFrete = driver.findElement(By.xpath("//label[contains(.,'" + frete + "')]"));
				WebElement tagnamePai = spanFrete.findElement(By.xpath(".."));
				WebElement inputFrete = tagnamePai.findElement(By.xpath("input"));
		
				inputFrete.click();
				botaoConfirmarMensagemFlutuante.click();
			}
		} else {
			inputQuantidade.click();
		}
	}

	/** Metodo para fechar tela de venda finalizada. */
	public void fecharVendaFinalizada() {
		String titulo = tituloMensagemFlutuante.getText();
		
		WebDriverWait aguardar = new WebDriverWait(driver, Duration.ofSeconds(5));
		aguardar.until(ExpectedConditions.textToBePresentInElement(tituloMensagemFlutuante, "Venda finalizada"));
		
		if (titulo.contains("Venda finalizada")){
			botaoCancelarMensagemFlutuante.click();
		}
	}
	
	/** Metodo para preencher os campos para presente.
	 * @param de Campo para preencher de quem é o presente.
	 * @param para Campo para preencher para quem é o presente.
	 * @param mensagem Campo para preencher a mensagem do presente.
	 */
	public void selecionarOpcaoParaPresente(String de, String para, String mensagem) {
		aguardarElemento(driver, 10);
		inputPresente.click();

		limparCampoInput(inputPresenteDe);
		limparCampoInput(inputPresentePara);
		limparCampoInput(inputMensagemPresente);	

		inputPresenteDe.sendKeys(de);
		inputPresentePara.sendKeys(para);
		inputMensagemPresente.sendKeys(mensagem);	
	}
	
	/** Metodo para adicionar uma nova aba de venda. */
	public void adicionarNovaAbaDeVenda() {
		List<WebElement> abas = barraNovasVendas.findElements(By.xpath("//li[@class='nav-item ng-star-inserted']"));
		int quantidade = abas.size();

		WebDriverWait aguardar = new WebDriverWait(driver, Duration.ofSeconds(5));
		aguardar.until(ExpectedConditions.visibilityOfAllElements(abas));

		botaoNovaAbaDeVenda.click();
		
		aguardar.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("cd-timer"), quantidade));
	}

	/** Metodo para selecionar uma aba de venda pelo index.
	 * @param indexAbaVenda Insere o index da aba de venda que deseja selecionar.
	 */
	public void selecionarAbaDeVenda(int indexAbaVenda) {
		List<WebElement> abas = barraNovasVendas
.findElements(By.xpath("//li[@class='nav-item ng-star-inserted']"));
			abas.get(indexAbaVenda).click();
	}

	/** Metodo para fechar uma aba de venda. 
	 * @param indexAbaVenda Insere o index da aba de venda que deseja fechar.
	*/
	public void excluirAbaDeVenda(int indexAbaVenda) {
		List<WebElement> abas = barraNovasVendas.findElements(By.xpath("//li[@class='nav-item ng-star-inserted']"));
		int quantidade = abas.size();

		WebDriverWait aguardar = new WebDriverWait(driver, Duration.ofSeconds(5));
		aguardar.until(ExpectedConditions.visibilityOfAllElements(abas));
		
		List<WebElement> aba = barraNovasVendas.findElements(By.xpath("//span[contains(.,'×')]"));
		aba.get(indexAbaVenda).click();
				
		aguardar.until(ExpectedConditions.numberOfElementsToBeLessThan(By.tagName("cd-timer"), quantidade));
	}	

	/** Metodo para obter valores de acordo com os produtos adicionados.
	 * @param nomeProduto nome do produto que deseja obter o valor.
	 */
	public String obterValorProdutoAdicionado(String nomeProduto) {
		WebElement span = driver.findElement(By.xpath("//span[contains(.,'" + nomeProduto +"')]"));
		WebElement tagnamePai = span.findElement(By.xpath(".."));
		WebElement tagname = tagnamePai.findElement(By.xpath(".."));
		WebElement div = tagname.findElement(By.xpath("div[2]"));
		WebElement preco = div.findElement(By.cssSelector(".d-none:nth-child(3) > .h3"));	
	
		return preco.getText().replace("R$ ", "").replace(".", "").replace(",", ".");		
	}
	
	/** Metodo para aguardar a troca de aplicacao da promocao.*/
	public void aguardarTrocaDeAplicacaoDaPromocao() {
		WebDriverWait aguardar = new WebDriverWait(driver, Duration.ofSeconds(5));

		if(obterPorcentagemPromocaoAplicada().equals("0")){
		aguardar.until(ExpectedConditions.not(ExpectedConditions.textToBe(By.cssSelector(".d-block > .float-right"), "0")));
		} else {
			promocaoAplicadaPorcentagem.click();
		}	
	}
	
	/** Metodo para obter o valor total em % de promocao aplicado.*/
	public String obterPorcentagemPromocaoAplicada() {
		return promocaoAplicadaPorcentagem.getText();
	}
	
	/** Metodo para obter o valor de do frete.*/
	public String obterValorResumoFrete() {
		return valorTotalFrete.getText().replace("R$ ", "").replace(".", "").replace(",", ".");
	}

	/** Metodo para verificar se campos estão vazios na tela de vendas.*/
	public boolean verificarCamposVazios() {
		aguardarElementoSerClicavel(driver, 20, selectLoja);
		aguardarElementoSerClicavel(driver, 20, inputCliente);

		try {
			Select selectFiliais = new Select(selectLoja);

			if (inputCliente.getAttribute("value").equals("") |
					selectFiliais.getFirstSelectedOption().getAccessibleName().equals("SELECIONE A FILIAL") |
					inputProduto.getAttribute("value").equals("")) {
				return true;
			}
			return false;
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			Select selectFiliais = new Select(selectLoja);

			if (inputCliente.getAttribute("value").equals("") |
					selectFiliais.getFirstSelectedOption().getAccessibleName().equals("SELECIONE A FILIAL") |
					inputProduto.getAttribute("value").equals("")) {
				return true;
			}
			return false;
		}
	}

	/** Metodo para informar se o input produto esta habilitado. */
	public Boolean verificarSeInputProdutoEstaHabilitado() {
		if (inputProduto.isEnabled()){
			return true;
		}
		else {
			return false;
		}
	}

	/** Metodo para obter nome do cliente selecionado. */
	public String obterNomeDoClienteSelecionado() {
		aguardarVisibilidadeDoElemento(driver, 20, inputClienteSelecionado);
	
		return inputClienteSelecionado.getAttribute("value");
	}

	/** Metodo para fechar modal de configuração de link de cartão. */
	public void fecharModalAvisoLinkCartao() {
		aguardarVisibilidadeDoElemento(driver, 10, driver.findElement(By.cssSelector(".modal-title")));
		
		if (botaoOK.isDisplayed()) {
			botaoOK.click();
		}
	}
	//#endregion
}
