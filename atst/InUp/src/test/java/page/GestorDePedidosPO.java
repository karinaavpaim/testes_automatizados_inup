package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import page.netShopper.NetShopperPO;
import util.TabelaUtil;

/** Page Object da pagina de Gestor de Pedidos. */
public class GestorDePedidosPO extends BasePO{

	//#region Regiao dos elementos.

	/** Enum para definir onde sera realizada a venda. */
	public enum tipoDeVenda { NETSHOPPER, CASHNET; }

	/** Elemento de input para selecionar as lojas. */
	@FindBy(css = ".custom > .ng-select-container input")
	public WebElement inputLoja;

	/** Elemento para selecionar filiais. */
	@FindBy(css = ".ng-star-inserted > .btn")
	public WebElement botaoFiliais;

	/** Elemento para pesquisar os pedidos. */
	@FindBy(xpath = "//button[contains(.,'Pesquisar')]")
	public WebElement botaoPesquisar;

	/** Elemento para listar os pedidos com status de aguardando pagamentos. */
	@FindBy(css = ".ng-star-inserted:nth-child(1) > a .d-none")
	public WebElement botaoAguardandoPagamento;
	
	/** Elemento para listar os pedidos com status de prontos para expedir. */
	@FindBy(css = ".ng-star-inserted:nth-child(2) .msg")
	public WebElement botaoProntosParaExpedir;

	/** Elemento para listar os pedidos com status de aguardando notas. */
	@FindBy(css = ".ng-star-inserted:nth-child(3) > a .d-none")
	public WebElement botaoAguardandoNotas;

	/** Input para procurar algum pedido. */
	@FindBy(css = ".form-control")
	public WebElement inputProcurar;

	/** Tabela de todos os pedidos. */
	@FindBy(tagName = "table")
	public WebElement tabela;

	/** Input para selecionar todos os elementos da lista. */
	@FindBy(css = ".justify-content-end .form-check-input")
	public WebElement inputParaSelecionarTodosDaLista;
	
	/** Elemento checkbox para selecionar todos os itens da listagem. */
	@FindBy(css = ".justify-content-end .form-check-input")
	public WebElement checkboxParaTodosOsPedidos;

	/** Elemento checkbox do primeiro item da listagem. */
	@FindBy(css = ".form-check > .ng-untouched")
	public WebElement checkbox;

	/** Elemento de primeiro coridgo de venda da listagem. */
	@FindBy(css = ".ng-star-inserted:nth-child(1) > .align-middle:nth-child(2)")
	public WebElement primeirCodigoDeVendaDaListagem;

	/** Elemento de primeira filial da listagem. */
	@FindBy(css = ".ng-star-inserted:nth-child(1) > .ng-star-inserted:nth-child(5) > .text-start")
	public WebElement primeiraFilialDaListagem;

	/** Elemento de primeiro cliente da listagem. */
	@FindBy(css = ".ng-star-inserted:nth-child(1) > .ng-star-inserted:nth-child(8) > .text-start")
	public WebElement primeiroClienteDaListagem;
	
	/** Elemento de primeira transportadora da listagem. */
	@FindBy(css = ".ng-star-inserted:nth-child(1) > .ng-star-inserted:nth-child(7) > .text-start")
	public WebElement primeiraTransportadoraDaListagem;

	/** Elemento de primeiro cliente da listagem de Aguardando Notas. */
	@FindBy(css = ".ng-star-inserted:nth-child(1) > .ng-star-inserted:nth-child(11) > .text-start")
	public WebElement primeiroClienteDaListagemAguardandoNotas;
		
	/** Elemento para aprovacao do pagamento. */
	@FindBy(css = ".list-group:nth-child(2) > .btn")
	public WebElement botaoAprovarPagamento;

	/** Elemento para gerar separação do pedido. */
	@FindBy(css = ".btn > .fa-luggage-cart")
	public WebElement botaoGerarSeparacao;
	
	/** Elemento para alterar transportadora. */
	@FindBy(css = ".fa-truck-moving")
	public WebElement botaoAlterarTransportadora;

	/** Elemento para cancelar venda. */
	@FindBy(css = ".fa-trash")
	public WebElement botaoCancelarVenda;
	
	/** Elemento para selecionar uma transportadora. */
	@FindBy(id = "tipo")
	public WebElement selectTipoTransportadora;	
	//#endregion

	//#region Região dos construtores.

	/** Construtor da classe.
	 * @param driver Driver da classe.
	 */
	public GestorDePedidosPO(WebDriver driver) {
		super(driver);
	}
	//#endregion

	//#region Regiao dos metodos.

	/** Metodo para navegar para o menu Gestor de Pedidos. */
	public void navegarParaGestorDePedidos(){
		menuGestorDePedidos.click();

		aguardarInvisibilidadeDoElemento(driver, 10, carregamentoDaPagina);
		aguardarVisibilidadeDoElemento(driver, 10, inputProcurar);
		aguardarVisibilidadeDoElemento(driver, 10, tabela);
	}

	/** Metodo para Pesquisar um pedido. */
	public void pesquisarPedidos(){
		botaoPesquisar.click();

		aguardarCarregamentoDaPagina();
	}

	/** Metodo para localizar determinada venda. 
	 * @param objetoASerLocalizado inserir o que deseja localizar dentro da tela gestor de pedidos.
	*/
	public void localizarPedido(String objetoASerLocalizado){
		aguardarInvisibilidadeDoElemento(driver, 10, carregamentoDaPagina);
		aguardarVisibilidadeDoElemento(driver, 10, tabela);
		aguardarVisibilidadeDoElemento(driver, 10, inputProcurar);
		aguardarElementoSerClicavel(driver, 10, inputProcurar);

		limparCampoInput(inputProcurar);
		inputProcurar.sendKeys(objetoASerLocalizado);
		
		aguardarInvisibilidadeDoElemento(driver, 10, carregamentoDaPagina);
	}

	/** Metodo para buscar um item na tabela a partir do nome do item a ser pesquisado.
	 * @param item Item a ser localizado na tabela.
	*/
	public void localizarPedidoESelecionar(String item) {
		TabelaUtil tabelaUtil = new TabelaUtil(driver);

		aguardarVisibilidadeDoElemento(driver, 20, tabela);

		int idLinha = tabelaUtil.obterLinha(item, tabela);
		WebElement linha = tabela.findElement(By.xpath("//tr[" + idLinha + "]"));	
	  	WebElement input = linha.findElement(By.xpath("*//input"));
				
		aguardarElementoSerClicavel(driver, 7, input);
		input.click();

		aguardarVisibilidadeDoElemento(driver, 7, botaoAlterarTransportadora);
	}

	/** Metodo para realizar uma venda no NetShopper antes de realizar as ações na tela Gestor de Pedidos e retornar o 
	 * código da venda.
	 * @param nomeCliente Nome do cliente.
	 * @param nomeProduto Nome do produto.
	 * @param indexCorOuTamanho index do tamanho ou cor do produto selecionado.
	 * @param frete nome da transportadora.
	*/
	public String obterCodigoDeVenda(String nomeCliente, String nomeProduto, int indexCorOuTamanho, String frete) {
		NetShopperPO netShopperPO = new NetShopperPO(driver);
		DinheiroPO dinheiroPO = new DinheiroPO(driver);

		netShopperPO.navegarParaNetshopper();
		
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja, "NORTE SHOPPING");
		netShopperPO.selecionarElementoSelect(netShopperPO.selectVendedor, "Marysol");
        netShopperPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanho);
        netShopperPO.selecionarFrete(frete);

        String valorTotal = netShopperPO.obterValorResumoVenda("Total");
    
        dinheiroPO.realizarPagamentoEmDinheiro(valorTotal);
    
        netShopperPO.botaoFinalizarPedido.click();
        netShopperPO.botaoConfirmarMensagemFlutuante.click();

		System.out.println(netShopperPO.codigoDaVenda.getText());
		return netShopperPO.codigoDaVenda.getText();
	}

	/** Metodo para alterar transportadora.
	 * @param nomeTransportadora Nome da transportadora a ser alterada.
	*/
	public void alterarTransportadora(String nomeTransportadora) {
		botaoAlterarTransportadora.click();

		selectTipoTransportadora.click();

		Select transportadora = new Select(selectTipoTransportadora);
		transportadora.selectByVisibleText(nomeTransportadora);

		botaoConfirmarMensagemFlutuante.click();
		botaoCancelarMensagemFlutuante.click();
	}

	/** Metodo para cancelar venda.
	 * @param codigoVenda Codigo da venda a ser cancelada.
	*/
	public void cancelarVenda(String codigoVenda) {
		localizarPedido(codigoVenda);

		checkbox.click();
        botaoCancelarVenda.click();
		botaoConfirmarMensagemFlutuante.click();
	}
	//#endregion
}

