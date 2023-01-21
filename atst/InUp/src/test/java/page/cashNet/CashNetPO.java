package page.cashNet;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.BasePO;

/** Page Object da tela Cashnet. */
public class CashNetPO extends BasePO {

	// #region Regiao dos elementos

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

	/** Elemento de autocomplete do campo produto. */
	@FindBy(xpath = "//input[@id='product']")
	public WebElement inputProduto;

	/** Elemento de mensagem de produto não encontrado. */
	@FindBy(css = ".invalid-feedback")
	public WebElement mensagemProdutoNaoEncontrado;

	/** Elemento de limpar input produto, quando o produto não foi encontrado. */
	@FindBy(css = ".fa-xmark")
	public WebElement botaoLimparInputProduto;

	/** Elemento para editar o item adicionado. */
	@FindBy(xpath = "//button[contains(.,'Editar item')]")
	public WebElement botaoEditarItem;

	/** Elemento para excluir o item adicionado. */
	@FindBy(css = ".btn-sm > .fa-trash-alt")
	public WebElement botaoExcluirItem;

	/** Elemento onde ficam as abas de novas vendas. */
	@FindBy(xpath = "//ul[contains(@role, 'tablist')]")
	public WebElement barraNovasVendas;

	/** Elemento para adicionar nova aba de vendas. */
	@FindBy(css = ".text-success > .fa-duotone")
	public WebElement botaoNovaAbaDeVenda;

	/** Elemento para identificar o primeiro produto adicionado. */
	@FindBy(css = ".flex-grow-1 > .h4")
	public WebElement primeiroProdutoAdicionadoAoCarrinho;

	/** Elemento de titulo da mensagem flutuante. */
	@FindBy(css = ".modal-title")
	public WebElement tituloMensagemFlutuante;

	/** Elemento para visualizar as informacoes sobre as promocoes aplicadas. */
	@FindBy(css = ".badge")
	public WebElement iconePromocaoAplicada;

	/** Elemento de promocao aplicada em porcentagem. */
	@FindBy(css = ".d-block > .float-end")
	public WebElement promocaoAplicadaPorcentagem;

	/** Elemento para selecionar opcao para presente. */
	@FindBy(id = "isGift")
	public WebElement inputPresente;

	/** Elemento para inserir o nome de quem envia o presente. */
	@FindBy(id = "from")
	public WebElement inputPresenteDe;

	/** Elemento para inserir o nome para quem o presente é enviado. */
	@FindBy(id = "to")
	public WebElement inputPresentePara;

	/** Elemento para inserir a mensagem do presente. */
	@FindBy(id = "message")
	public WebElement inputMensagemPresente;

	/** Elemento para selecionar o tipo de presente. */
	@FindBy(id = "type")
	public WebElement selectTipoMensagem;

	/** Elemento de opção para restaurar venda. */
	@FindBy(xpath = "//button[contains(.,'Restaurar Venda')]")
	public WebElement botaoRestaurarVenda;

	/** Elemento de opção para cartao. */
	@FindBy(xpath = "//button[contains(.,'Cartão')]")
	public WebElement botaoCartao;

	/** Elemento de opção para Pix. */
	@FindBy(xpath = "//button[contains(.,'Pix')]")
	public WebElement botaoPix;

	/** Elemento de opção para CashBack. */
	@FindBy(xpath = "//button[contains(.,'CashBack')]")
	public WebElement botaoCashBack;

	/** Elemento para selecionar a opção de finalizar pedido. */
	@FindBy(xpath = "//button[contains(.,'Finalizar Venda')]")
	public WebElement botaoFinalizarVenda;

	/** Elemento para selecionar a opção de cancelar pedido. */
	@FindBy(xpath = "//button[contains(.,'Cancelar Venda')]")
	public WebElement botaoCancelarVenda;

	/** Elemento de mensagem de venda realizada com sucesso. */
	@FindBy(css = ".ng-star-inserted > .text-center:nth-child(2)")
	public WebElement mensagemVendaRealizadaComSucesso;

	/** Elemento de codigo da venda. */
	@FindBy(css = ".list-group:nth-child(3) > .list-group-item:nth-child(2) > .float-right")
	public WebElement codigoDaVenda;

	/** Elemento de status da venda exibida ao final da venda. */
	@FindBy(css = ".list-group-item > .mb-0")
	public WebElement statusVenda;

	/** Elemento de texto do modal. */
	@FindBy(css = ".modal-body > .mb-0")
	public WebElement textoMensagemModal;
	// #endregion

	// #region Regiao dos construtores

	/**
	 * Construtor da classe
	 * 
	 * @param driver Driver da classe.
	 */
	public CashNetPO(WebDriver driver) {
		super(driver);
	}
	// #endregion

	// #region Regiao dos metodos

	/** Metodo para navegar para o menu CashNet. */
	public void navegarParaCashNet() {
		menuCashNet.click();
	}

	/**
	 * Metodo para buscar por um determinado produto no campo autocomplete e depois
	 * seleciona-lo para seguir com a venda.
	 * 
	 * @param nomeProduto produto a ser buscado e selecionado no campo input.
	 * @param index       Seleciona por index o tamanho ou cor do produto.
	 */
	public void adicionarProdutoAoCarrinho(String nomeProduto, int index) {
		selecionarNomeAutocomplete(inputProduto, nomeProduto);
		aguardarCarregamentoDaPagina();

		if (driver.findElements(By.cssSelector(".modal-title")).size() != 0) {
			String titulo = tituloMensagemFlutuante.getText();

			if (titulo.contains("Grade")) {
				aguardarVisibilidadeDoElemento(driver, 10, tabela);

				List<WebElement> spans = tabela.findElements(By.tagName("span"));
				List<WebElement> grade = tabela.findElements(By.tagName("input"));

				aguardarVisibilidadeDoElemento(driver, 10, spans.get(index));
				grade.get(index).click();

				botaoAdicionarMensagemFlutuante.click();
			}
		} else {
			inputQuantidade.click();
		}
	}

	/** Metodo para fechar tela de venda finalizada sem Nota Fiscal gerada. */
	public void fecharVendaFinalizadaSemNotaFiscal() {
		WebDriverWait aguardar = new WebDriverWait(driver, Duration.ofSeconds(20));

		aguardar.until(ExpectedConditions.textToBePresentInElement(tituloMensagemFlutuante, "Venda finalizada"));
		aguardar.until(ExpectedConditions.textToBePresentInElement(statusVenda,
				"Object reference not set to an instance of an object."));

		botaoFechar.click();
	}

	/** Metodo para aguardar a geração de Nota Fiscal. */
	public void aguardarGeracaoDaNotaFiscal() {
		WebDriverWait aguardar = new WebDriverWait(driver, Duration.ofSeconds(50));
		aguardar.until(ExpectedConditions.textToBePresentInElement(tituloMensagemFlutuante, "Venda finalizada"));
		aguardar.until(ExpectedConditions.textToBePresentInElement(statusVenda, "NFC-e autorizada."));
	}

	/** Metodo para fechar tela de venda finalizada quando gerada a Nota Fiscal. */
	public void fecharVendaFinalizadaComNotaFiscal() {
		aguardarGeracaoDaNotaFiscal();
		botaoFechar.click();
	}

	/**
	 * Metodo para preencher os campos para presente.
	 * 
	 * @param de       Campo para preencher de quem é o presente.
	 * @param para     Campo para preencher para quem é o presente.
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

	/**
	 * Metodo para selecionar uma aba de venda pelo index.
	 * 
	 * @param indexAbaVenda Insere o index da aba de venda que deseja selecionar.
	 */
	public void selecionarAbaDeVenda(int indexAbaVenda) {
		List<WebElement> abas = barraNovasVendas.findElements(By.xpath("//li[@class='nav-item ng-star-inserted']"));
		abas.get(indexAbaVenda).click();
	}

	/**
	 * Metodo para fechar uma aba de venda.
	 * 
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

	/**
	 * Metodo para obter valores de acordo com os produtos adicionados.
	 * 
	 * @param nomeProduto nome do produto que deseja obter o valor.
	 */
	public String obterValorProdutoAdicionado(String nomeProduto) {
		WebElement span = driver.findElement(By.xpath("//span[contains(.,'" + nomeProduto + "')]"));
		WebElement tagnamePai = span.findElement(By.xpath(".."));
		WebElement tagname = tagnamePai.findElement(By.xpath(".."));
		WebElement div = tagname.findElement(By.xpath("div[2]"));
		WebElement preco = div.findElement(By.cssSelector(".d-none:nth-child(3) > .h3"));

		return preco.getText().replace("R$ ", "").replace(".", "").replace(",", ".");
	}

	/** Metodo para aguardar a troca de aplicacao da promocao. */
	public void aguardarTrocaDeAplicacaoDaPromocao() {
		WebDriverWait aguardar = new WebDriverWait(driver, Duration.ofSeconds(5));

		if (obterPorcentagemPromocaoAplicada().equals("0")) {
			aguardar.until(ExpectedConditions
					.not(ExpectedConditions.textToBe(By.cssSelector(".d-block > .float-right"), "0")));
		} else {
			promocaoAplicadaPorcentagem.click();
		}
	}

	/** Metodo para obter o valor total em % de promocao aplicado. */
	public String obterPorcentagemPromocaoAplicada() {
		return promocaoAplicadaPorcentagem.getText();
	}

	/** Metodo para verificar se campos estão vazios na tela de vendas. */
	public boolean verificarCamposVazios() {
		aguardarVisibilidadeDoElemento(driver, 10, selectLoja);
		aguardarElementoSerClicavel(driver, 20, selectLoja);

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

	/** Metodo para fechar modais de avisos sobre configurações. */
	public void fecharModaisAvisos() {
		aguardarInvisibilidadeDoElemento(driver, 10, carregamentoDaPagina);

		List<WebElement> listaModais = driver.findElements(By.cssSelector(".modal-body"));

		if (listaModais.size() > 1) {
			driver.findElement(By.xpath("//ngb-modal-window[2]/div/div/div[3]/button")).sendKeys(Keys.ESCAPE);
			driver.findElement(By.xpath("//ngb-modal-window/div/div/div[3]/button")).sendKeys(Keys.ESCAPE);
		} else {
			botaoOK.click();
		}
	}

	/**
	 * Metodo para obter texto do aviso que a loja selecionada não possui
	 * configuracao de cartao.
	 */
	public String textoModalConfiguracaoCartao() {
		aguardarCarregamentoDaPagina();

		List<WebElement> listaModais = driver.findElements(By.cssSelector(".modal-body"));

		if (listaModais.size() > 1) {
			if (listaModais.get(0).getText()
					.equalsIgnoreCase(
							"Filial selecionada não possui cartões configurados\na forma de pagamento informada ficará bloqueada")) {
				return listaModais.get(0).getText();
			} else {
				return listaModais.get(1).getText();
			}
		} else {
			return driver.findElement(By.cssSelector(".modal-body")).getText();
		}
	}

	/**
	 * Metodo para obter texto do aviso que a loja selecionada não possui
	 * configuracao de NFC-e.
	 */
	public String textoModalConfiguracaoNFCe() {
		aguardarCarregamentoDaPagina();

		List<WebElement> listaModais = driver.findElements(By.cssSelector(".modal-body"));

		if (listaModais.size() >= 1) {
			if (listaModais.get(0).getText()
					.equalsIgnoreCase(
							"Filial selecionada não possui configuração para emissão de NFC-e, por favor realize a configuração através do Retaguarda ou selecione outra filial.")) {
				return listaModais.get(0).getText();
			} else {
				return listaModais.get(1).getText();
			}
		} else {
			return driver.findElement(By.cssSelector(".modal-body")).getText();
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
	// #endregion
}
