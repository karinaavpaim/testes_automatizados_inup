package page;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**Classe base para todas as POs do sistema. Todas as POs do sistema devem herdar dessa classe.*/
public abstract class BasePO {

    //#region Regiao dos elementos.

	/** Driver a ser utilizado por todas os POs no sistema.*/
	protected WebDriver driver;

	/** Menu CashNet. */
	@FindBy(xpath = "//*[contains(text(),'CashNet')]")
	public WebElement menuCashNet;

	/** Menu NetShopper. */
	@FindBy(xpath = "//*[contains(text(),'Netshopper')]")
	public WebElement menuNetShopper;

	/** Menu Transportadoras. */
	@FindBy(xpath = "//*[contains(text(),'Transportadoras')]")
	public WebElement menuTransportadoras;

	/** Menu Gestor de Pedidos. */
	@FindBy(xpath = "//*[contains(text(),'Gestor de Pedidos')]")
	public WebElement menuGestorDePedidos;

	/** Menu Gestor de Notas. */
	@FindBy(xpath = "//*[contains(text(),'Gestor de Notas')]")
	public WebElement menuGestorDeNotass;

	/** Menu Consulta de Vendas. */
	@FindBy(xpath = "//*[contains(text(),'Consulta de Vendas')]")
	public WebElement menuConsultaDeVendas;

	/** Menu Acompanhamento de Metas. */
	@FindBy(xpath = "//*[contains(text(),'Acompanhamento de Metas')]")
	public WebElement menuAcompanhamentoDeMetas;

	/** Menu Acompanhamento de Metas. */
	@FindBy(xpath = "//*[contains(text(),'Tipos de Mensagens')]")
	public WebElement menuTiposDeMensagens;

	/** Elemento de titulo da pagina. */
	@FindBy(css = ".h4")
	public WebElement tituloDaPagina;

	/** Elemento de titulo da pagina. */
	@FindBy(css = ".modal-title")
	public WebElement tituloModal;
	
	/** Elemento de mensagem flutuante. */
	@FindBy(id = "toast-container")
	public WebElement conteudoMensagemFlutuante;

	/** Elemento para confirmar mensagem flutuante. */
	@FindBy(xpath = "//button[contains(.,'Confirmar')]")
	public WebElement botaoConfirmarMensagemFlutuante;

	/** Elemento para adicionar algum elemento na mensagem flutuante. */
	@FindBy(xpath = "//button[text()='Adicionar']")
	public WebElement botaoAdicionarMensagemFlutuante;

	/** Elemento para cancelar mensagem flutuante. */
	@FindBy(css = ".modal-footer > .btn-secondary")
	public WebElement botaoCancelarMensagemFlutuante;

	/** Elemento para cancelar mensagem flutuante. */
    @FindBy(xpath = "//button[contains(text(),'Cancelar')]")
	public WebElement botaoCancelar;

	/** Elemento para selecionar Salvar. */
    @FindBy(xpath = "//button[contains(text(),'Salvar')]")
    public WebElement botaoSalvar;

	/** Elemento para selecionar Excluir. */
    @FindBy(xpath = "//button[contains(text(),'Excluir')]")
    public WebElement botaoExcluir;

	/** Elemento para fechar qualquer tela de mensagem flutuante. */
	@FindBy(xpath = "//button[contains(.,'Fechar')]")
	public WebElement botaoFechar;

	/** Elemento para fechar o modal da mensagem de alerta. */
	@FindBy(xpath = "//button[contains(.,'OK')]")
	public WebElement botaoOK;

	/** Elemento de carregamento da pagina. */
	@FindBy(css = ".ngx-background-spinner div:nth-child(3)")
	public WebElement carregamentoDaPagina;

	/** Elemento de carregamento da tela quando navegar. */
	@FindBy(css = ".ngx-overlay")
	public WebElement carregamentoDaTela;
	
	/** Elemento de tabela das trasnportadoras. */
	@FindBy(xpath = "//table/tbody")
	public WebElement tabela;

	/** Elemento para selecionar as lojas. */
	@FindBy(tagName = "ng-dropdown-panel")
	public WebElement selectLojas;

	/** Elemento para abrir o select das lojas. */
	@FindBy(css = ".custom > .ng-select-container input")
	public WebElement inputLoja;

	/** Elemento de valor dentro do input lojas. */
	@FindBy(xpath = "//div[@class='ng-select-container ng-has-value']")
	public WebElement valorInputLoja;

	/** Elemento de lista dos produtos no autocomplete. */
	@FindBy(tagName = "ngb-typeahead-window")
	public WebElement listaRetornoAutocomplete;

	/** Elemento para selecionar o mes dentro do calendario. */
	@FindBy(css = ".ngb-dp-navigation-select > .form-select:nth-child(1)")
	public WebElement selectMes;
	
	/** Elemento para selecionar o ano dentro do calendario. */
	@FindBy(css = ".ngb-dp-navigation-select > .form-select:nth-child(2)")
	public WebElement selectAno;

	/** Elemento para confirmar a data selecionada no calendario. */
	@FindBy(css = ".fa-check")
	public WebElement confirmarDataCalendario;

	/** Elemento para selecionar a quantidade de itens por página. */
	@FindBy(xpath = "//ng-select[@id='itensPage']/div/div/div[3]")
	public WebElement selectItensPorPagina;

	/** Elemento de quantidade de itens por pagina. */
	@FindBy(css = "#itensPage .ng-input")
	public WebElement selectQuantidadeItensPorPagina;
	
	/** Elemento para navegar para a primeira pagina da listagem. */
	@FindBy(css = ".page-item:nth-child(1) .ng-star-inserted")
	public WebElement botaoNavegarInicioLista;
	
	/** Elemento para navegar para a última pagina da listagem. */
	@FindBy(xpath = "//span[contains(.,'»»')]")
	public WebElement botaoAvancarUltimaPagina;

	/** Elemento para voltar a navegacao da pagina da listagem. */
	@FindBy(css = ".page-item:nth-child(2)")
	public WebElement botaoVoltarLista;

	/** Elemento para avançar a navegacao da pagina da listagem. */
	@FindBy(xpath = "//*[contains(.,'»')]")
	public WebElement botaoAvancarLista;

	/** Menu do usuario. */
	@FindBy(css = ".fa-circle-user")
	public WebElement menuUsuario;

	/** Elemento para fazer o logoff. */
	@FindBy(linkText = "Sair")
	public WebElement menuSair;
	//#endregion

	//#region região dos construtores.

	/** Construtor da classe.
	 * @param driver Driver da classe.
	 */
	protected BasePO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//#endregion

	//#region Regiao dos metodos.

	/** Metodo para obter o titulo das paginas acessadas. */
	public String obterTituloPagina() {
		return tituloDaPagina.getText();
	}

	/** Metodo para aguardar até que alguma tela seja carregada. */
	public void aguardarCarregamentoDaPagina() {		
		aguardarVisibilidadeDoElemento(driver, 20, carregamentoDaPagina);
		aguardarInvisibilidadeDoElemento(driver, 20, carregamentoDaPagina);

		aguardarInvisibilidadeDoElemento(driver, 20, carregamentoDaTela);
	}

	/** Metodo para fechar modais. */
	public void fecharModais() {
		aguardarVisibilidadeDoElemento(driver, 10, driver.findElement(By.cssSelector(".modal-title")));
		
		if (botaoFechar.isDisplayed()) {
			aguardarElementoSerClicavel(driver, 10, botaoFechar);
			botaoFechar.sendKeys(Keys.ESCAPE);
		}
	}

	/** Fecha as mensagens flutuantes. */
	public void fecharMensagensFlutuantes() {
		aguardarVisibilidadeDoElemento(driver, 10, conteudoMensagemFlutuante);

		List<WebElement> mensagens = driver.findElements(By.cssSelector(".toast-close-button"));
				
		for(int i = 0; i < mensagens.size(); i++){
			mensagens.get(i).click();
		}
	}
	
	/**
	 * Metodo para aguardar ate determinado limite de tempo ate que a mensagem flutuante apareca.
	 * @param driver              driver do sistema.
	 * @param tempoMaximoDeEspera tempo maximo que o sistema vai aguardar.
	 */
	public String obterTextoMensagemFlutuante(WebDriver driver, int tempoMaximoDeEspera) {
		WebDriverWait aguardar = new WebDriverWait(driver, Duration.ofSeconds(tempoMaximoDeEspera));
		aguardar.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		return conteudoMensagemFlutuante.getText();
	}

	/**
	 * Metodo para buscar por um determinado nome em um campo autocomplete e depois seleciona-lo.
	 * @param elementoInput input onde sera informado o nome a ser pesquisado e selecionado.
	 * @param nomeASerSelecionado nome a ser buscado e selecionado no campo input.
	 */
	public void selecionarNomeAutocomplete(WebElement elementoInput, String nomeASerSelecionado) {
		limparCampoInput(elementoInput);
		elementoInput.sendKeys(nomeASerSelecionado);
		aguardarVisibilidadeDoElemento(driver, 50, listaRetornoAutocomplete);
		elementoInput.sendKeys(Keys.ENTER);
	}
	
	/** Metodo para selecionar elemento a partir de uma lista select.
	 * @param elemento elemento de select.
	 * @param nomeElemento texto visivel do select a ser selecionado.
	 */
	public void selecionarElementoSelect(WebElement elemento, String nomeElemento){
		aguardarElementoSerClicavel(driver, 10, elemento);
		elemento.click();
		aguardarElemento(driver, 10);
		
		Select item = new Select(elemento);
		item.selectByVisibleText(nomeElemento);;
	}
	
	/**
	 * Metodo para aguardar ate determinado limite de tempo ate que o elemento esteja
	 * habilitado.
	 * @param driver driver do sistema.
	 * @param tempoMaximoDeEspera tempo maximo que o sistema vai aguardar um elemento.
	 * @param elemento elemento a ser aguardado para ser clicado.
	 */
	public void aguardarElementoSerClicavel(WebDriver driver, int tempoMaximoDeEspera, WebElement elemento) {
		WebDriverWait aguardar = new WebDriverWait(driver, Duration.ofSeconds(tempoMaximoDeEspera));
		aguardar.until(ExpectedConditions.elementToBeClickable(elemento));
	}

	/**
	 * Metodo para aguardar ate determinado limite de tempo ate que algum elemento apareca.
	 * @param driver              driver do sistema.
	 * @param tempoMaximoDeEspera tempo maximo que o sistema vai aguardar.
	 * @param elemento elemento a ser aguardado ate que seja visivel.
	 */
	public void aguardarVisibilidadeDoElemento(WebDriver driver, int tempoMaximoDeEspera, WebElement elemento) {
		WebDriverWait aguardar = new WebDriverWait(driver, Duration.ofSeconds(tempoMaximoDeEspera));
		aguardar.until(ExpectedConditions.visibilityOf(elemento));
	}

	/**
	 * Metodo para aguardar ate determinado limite de tempo ate que algum elemento nao esteja visivel.
	 * @param driver              driver do sistema.
	 * @param tempoMaximoDeEspera tempo maximo que o sistema vai aguardar.
	 * @param elemento elemento a ser aguardado ate que nao esteja mais visivel.
	 */
	public void aguardarInvisibilidadeDoElemento(WebDriver driver, int tempoMaximoDeEspera, WebElement elemento) {
		WebDriverWait aguardar = new WebDriverWait(driver, Duration.ofSeconds(tempoMaximoDeEspera));
		aguardar.until(ExpectedConditions.invisibilityOf(elemento));
	}

	/**
	 * Metodo para aguardar ate determinado limite de tempo ate que o elemento apareca e
	 * consiga realizar a proxima acao desejada.
	 * @param driver              driver do sistema.
	 * @param tempoMaximoDeEspera tempo maximo que o sistema vai aguardar um elemento.
	 */
	public void aguardarElemento(WebDriver driver, int tempoMaximoDeEspera) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(tempoMaximoDeEspera));
	}

	/**
	 * Metodo para limpar um campo input.
	 * @param elementoInput elemento de input.
	 */
	public void limparCampoInput(WebElement elementoInput) {
			elementoInput.clear();
	}

	/**
	 * Metodo para rolar a pagina ate que algum elemento esteja visivel.
	 * @param elemento determina qual elemento devera estar visivel ao rolar a pagina.
	 */
	public void rolarPagina(WebElement elemento) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", elemento);
	}

	/** Metodo para verificar se um elemento existe na tela. 
	 * @param cssSelectorDoElemento localizar um elemento pelo css.
	 * Ex: ""
	*/
	public boolean verificarSeElementoEstaNaTela(String cssSelectorDoElemento) {
		aguardarElemento(driver, 10);

		List <WebElement> lista = driver.findElements(By.cssSelector(cssSelectorDoElemento));
		if (lista.size() == 0) {
			return false;
			}
		return true;
	}

	/** Metodo para verificar se uma transportadora esta na tabela.
	 * @param nome Nome da transportadora.
	 */
	public boolean verificarSeNomeEstaNaTabela(String nome) {
		List<WebElement> nomeElemento = tabela.findElements(By.tagName("td"));
		for (WebElement nomeDoElemento : nomeElemento)

		if (nomeDoElemento.getText().contains(nome)){
			return true;
		}
		return false;
	}

	/** Metodo para verificar que o campo é obrigatorio. 
	 * @param elemento elemento de input que sera testada a obrigatoriedade.
	*/
	public boolean verificarCampoObrigatorio(WebElement elemento) {
		if (elemento.getAttribute("class").contains("is-invalid")){
		return true;
		}
		return false;
	}

	/** Metodo para verificar se a promocao foi aplicada. */
	public boolean verificarPromocaoAplicada() {
		aguardarElemento(driver, 10);
		if (driver.findElements(By.cssSelector(".text-info > .float-end")).size()!=0){
			return true;
			}
		return false;
	}

	/** Metodo para calcular o valor com a porcentagem do desconto ou de acrescimo.
	 * @param valorPorcentagem Valor em porcentagem a ser descontada.
	 * @param valor Valor total a ser aplicado o desconto ou acrescimo.
	 */
	public double valorASerDescontadoOuAcrescido(double valorPorcentagem, double valor) {
		double valorDaPorcentagem = valorPorcentagem / 100;
		double valorDoDescontoOuAcrescimo = valor * valorDaPorcentagem;
		return valorDoDescontoOuAcrescimo;
	}

	/** Metodo para aguardar a aplicacao do motor de promocao. 
	 * @param tempoMaximoDeEspera tempo maximo para aguardar ate que a promocao seja aplicada, ou retirada.
	*/
	public void aguardarAplicacaoOuRetiradaDaPromocaoNaVenda(int tempoMaximoDeEspera) {
		aguardarElemento(driver, 10);

		WebDriverWait aguardar = new WebDriverWait(driver, Duration.ofSeconds(tempoMaximoDeEspera));
		List<WebElement> elementos = driver.findElements(By.cssSelector(".text-info > .float-end"));
		if (elementos.size()!=0){
			aguardar.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(".text-info > .float-end"), 1));
		} else { 
			aguardar.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".text-info > .float-end"), 0));
		}
	}

	/** Metodo para atualizar a pagina.
	 * @param tempoMaximoDeEspera tempo maximo que o teste deve aguardar ate que passa interagir com o elemento.
	 */
	public void atualizarPagina(int tempoMaximoDeEspera) {
		driver.navigate().refresh();
		aguardarElemento(driver, tempoMaximoDeEspera);
	}

	/** Metodo para selecionar lojas.
	 * @param nomeLoja nome da filial a ser inserido.
	 */
	public void selecionarFiliais(String nomeLoja){
		WebDriverWait aguardar = new WebDriverWait(driver, Duration.ofSeconds(10));

		limparCampoInput(inputLoja);
		inputLoja.sendKeys(nomeLoja);

		aguardar.until(ExpectedConditions.not(ExpectedConditions.textToBe(By.cssSelector(".ng-option"), "No items found")));
		
		List<WebElement> lojas = selectLojas.findElements(By.tagName("div"));
		for (WebElement loja : lojas) {
			if (loja.getText().equalsIgnoreCase(nomeLoja)){
				loja.click();
			}
		}    
	}

	/** Metodo para obter o valor dentro do input loja. */
	public String obterValorInputLoja() {
		List<WebElement> lojasSelecionadas = driver.findElements(By.xpath("//div[@class='ng-select-container ng-has-value']"));
		
		WebElement input = lojasSelecionadas.get(0);
		String valorInput = input.getText();

		return valorInput.replace("×", "").replace("\n", "");
	}

	/** Metodo para retirar a seleção de lojas.
	 * @param nomeLoja nome da filial a ser retirada da seleção.
	 */
	public void retirarSelecaoFiliais(String nomeLoja){
		List<WebElement> lojas = valorInputLoja.findElements(By.tagName("span"));
    	int index = 1;
    	for (int i = 1; i < lojas.size(); i++) {
			if (lojas.get(i).getText().equalsIgnoreCase(nomeLoja)) {
				index = i;
				break;
			}
		}
    	lojas.get(index - 1).click();
	}

	/** Metodo para formatar o valor em "0.00".
	 * @param valor Valor total a ser aplicado o desconto ou acrescimo.
	 */
	public String formatarValorDecimal(double valor) {
		DecimalFormat df = new DecimalFormat("0.00");      
    	return df.format(valor).replace(",", ".");
	}

	/** Metodo para obter a data de hoje, no formato "d/M/aaaa". */
	public String obterDataDeHoje() {
		LocalDate hoje = LocalDate.now();
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("d/M/yyyy");
  
		return hoje.format(formatoData);
	}

	/** Metodo para obter a data de ontem, no formato "d/M/aaaa". */
	public String obterDataDeOntem() {
		LocalDate hoje = LocalDate.now();
		LocalDate ontem = hoje.minusDays(1);
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("d/M/yyyy");
  
		return ontem.format(formatoData);
	}

	/** Metodo para obter o período dos últimos 30 dias, no formato "d/M/aaaa". */
	public String obterPeriodoUltimosTrintaDias() {
		LocalDate hoje = LocalDate.now();
		LocalDate ultimosTrintaDias = hoje.minusDays(30);
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("d/M/yyyy");
  
		return ultimosTrintaDias.format(formatoData);
	}

	/** Metodo para obter o período do mês anterior, no formato "d/M/aaaa". */
	public String obterPeriodoMesAnterior() {
		LocalDate hoje = LocalDate.now();
		LocalDate mes = hoje.minusMonths(1);

		DateTimeFormatter formatoMes = DateTimeFormatter.ofPattern("M");
		DateTimeFormatter formatoAno = DateTimeFormatter.ofPattern("yyyy");
		DateTimeFormatter formatoMesAno = DateTimeFormatter.ofPattern("M/yyyy");

		Integer ano = Integer.valueOf(mes.format(formatoAno));
		YearMonth anoMes = YearMonth.of(ano, 2);
		int diasDoMes = anoMes.lengthOfMonth();

		if (
			mes.format(formatoMes).equals("1") ||
			mes.format(formatoMes).equals("3") ||
			mes.format(formatoMes).equals("5") ||
			mes.format(formatoMes).equals("7") ||
			mes.format(formatoMes).equals("8") ||
			mes.format(formatoMes).equals("10") ||
			mes.format(formatoMes).equals("12")) {
			
			String mesAnterior = mes.format(formatoMesAno);
			return "1/" + mesAnterior + " até 31/" + mesAnterior;
		} else if (
			mes.format(formatoMes).equals("4") ||
			mes.format(formatoMes).equals("6") ||
			mes.format(formatoMes).equals("9") ||
			mes.format(formatoMes).equals("11")) {
				
			String mesAnterior = mes.format(formatoMesAno);
			return "1/" + mesAnterior + " até 30/" + mesAnterior;
		} else if (mes.format(formatoMes).equals("2") && diasDoMes == 28) {
			String mesAnterior = mes.format(formatoMesAno);
			return "1/" + mesAnterior + " até 28/" + mesAnterior;
		} else if (mes.format(formatoMes).equals("2") && diasDoMes == 29) {
			String mesAnterior = mes.format(formatoMesAno);
			return "1/" + mesAnterior + " até 29/" + mesAnterior;
		}
			return "Dia Inválido";
	}

	/** Metodo para obter o período do mês corrente. */
	public String obterPeriodoEsteMes() {
		LocalDate hoje = LocalDate.now();
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("M/yyyy");

		String mes = hoje.format(formatoData);
  
		return "1/" + mes + " até " + obterDataDeHoje();
	}

	/** Metodo para obter o período do ano corrente. */
	public String obterPeriodoEsteAno() {
		LocalDate hoje = LocalDate.now();
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy");

		String ano = hoje.format(formatoData);
  
		return "1/1/" + ano + " até " + obterDataDeHoje();
	}

	/** Metodo para obter o período da semana corrente. */
	public String obterPeriodoEstaSemana() {
		ConsultaDeVendasPO consultaDeVendasPO = new ConsultaDeVendasPO(driver);
		LocalDate dataAtual = LocalDate.now();
		DayOfWeek dia = dataAtual.getDayOfWeek();
		
		String diaDaSemana = dia.toString();
	
		System.out.println(diaDaSemana);
	
		if (diaDaSemana.equals("MONDAY")){
		  return consultaDeVendasPO.obterDataDeHoje() + " até " + consultaDeVendasPO.obterDataDeHoje();    
		} 
			else if (diaDaSemana.equals("TUESDAY")){
		  	return consultaDeVendasPO.obterDataDeOntem() + " até " + consultaDeVendasPO.obterDataDeHoje();   
		} 
			else if (diaDaSemana.equals("WEDNESDAY")){
			LocalDate segundaFeira = dataAtual.minusDays(2);
			DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("d/M/yyyy");
	  
			return segundaFeira.format(formatoData) + " até " + consultaDeVendasPO.obterDataDeHoje();  
		} 
			else if (diaDaSemana.equals("THURSDAY")){
		  	LocalDate segundaFeira = dataAtual.minusDays(3);
		  	DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("d/M/yyyy");
	 
		  	return segundaFeira.format(formatoData) + " até " + consultaDeVendasPO.obterDataDeHoje();
		} 
			else if (diaDaSemana.equals("FRIDAY")){
		  	LocalDate segundaFeira = dataAtual.minusDays(4);
		  	DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("d/M/yyyy");
	 
		  	return segundaFeira.format(formatoData) + " até " + consultaDeVendasPO.obterDataDeHoje();  
		}
		return "Dia Inválido";
	}

	/** Metodo para selecionar uma data inicial dentro do calendario.
	 * @param data Data a ser selecionada no calendario. Ex. 1, 3, 31.
	 * @param mes Mês a ser selecionado no calendario. Ex. Jan, Fev, etc.
	 * @param ano Ano a ser selecionado no calendario. Ex. 2012, 2015, 2020, etc.
	*/
	public void selecionarDataInicial(String data, String mes, String ano) {
		aguardarVisibilidadeDoElemento(driver, 5, selectMes);
		selecionarElementoSelect(selectAno, ano);

		aguardarElemento(driver, 5);
		selecionarElementoSelect(selectMes, mes);
	
		List<WebElement> calendarios = driver.findElements(By.tagName("ngb-datepicker-month"));
		WebElement calendario = calendarios.get(0);

		calendario.findElement(By.xpath("//span[contains(.,'" + data + "')]")).click();
	}

	/** Metodo para selecionar uma data final dentro do calendario.
	 * @param data Data a ser selecionada no calendario. Ex. 1, 3, 31.
	 * @param mes Mês a ser selecionado no calendario. Ex. Jan, Fev, etc.
	 * @param ano Ano a ser selecionado no calendario. Ex. 2012, 2015, 2020, etc.
	*/
	public void selecionarDataFinal(String data, String mes, String ano) {
		aguardarVisibilidadeDoElemento(driver, 5, selectMes);
		selecionarElementoSelect(selectAno, ano);

		aguardarElementoSerClicavel(driver, 5, selectMes);
		selecionarElementoSelect(selectMes, mes);

		List<WebElement> calendarios = driver.findElements(By.tagName("ngb-datepicker-month"));
		WebElement calendario = calendarios.get(0);

		calendario.findElement(By.xpath("//span[contains(.,'" + data + "')]")).click();
	}

	/** Metodo para selecionar Itens por Página.
	 * @param quantidade Quantidade de itens por página.
	 */
	public void selecionarItensPorPagina(String quantidade) {
		aguardarInvisibilidadeDoElemento(driver, 10, carregamentoDaPagina);

		rolarPagina(selectQuantidadeItensPorPagina);

	   	selectItensPorPagina.click();

    	WebElement lista = driver.findElement(By.tagName("ng-dropdown-panel"));
		List<WebElement> quantidades = lista.findElements(By.tagName("span"));

		for (WebElement quantidadeItens : quantidades) {
			if (quantidadeItens.getText().equals(quantidade)){
				quantidadeItens.click();
			}
		}
		
		aguardarInvisibilidadeDoElemento(driver, 20, carregamentoDaPagina);
	}

	/** Metodo para selecionar qual número da paginação deseja clicar.
	 * @param numeroPaginacao Quantidade de itens por página.
	 */
	public void selecionarPaginacao(int numeroPaginacao) {
		aguardarInvisibilidadeDoElemento(driver, 10, carregamentoDaPagina);

	   	driver.findElement(By.xpath("//a[contains(text(),'" + numeroPaginacao + "')]")).click();
		
		aguardarCarregamentoDaPagina();
	}

	/** Metodo para avançar para a última pagina da listagem. */
	public void avancarParaUltimaPagina() {
		aguardarCarregamentoDaPagina();

		botaoAvancarUltimaPagina.click();
		
		aguardarCarregamentoDaPagina();
	}

	/** Metodo para verificar se o elemento está visivel e disponivel para interações*/
	public void verificarSeElementoEstaVisivelEDisponivel(WebElement webElement){
		WebDriverWait aguardar = new WebDriverWait(driver, Duration.ofSeconds(10));
		Assert.assertTrue(webElement.isDisplayed());
		Assert.assertTrue(webElement.isEnabled());
		aguardar.until(ExpectedConditions.elementToBeClickable(webElement));
	}

	/** Metodo para verificar se o valor está dentro do SelectBox*/
	public void verificarSeValorExisteSelectBox(WebElement webElement, String valor){
		Select select = new Select(webElement);
		List<WebElement> opcoes = select.getOptions();

		boolean verifica = false;
		for(WebElement opcao:opcoes){
			if (opcao.getText().equals(valor)){
				verifica = true;
				break;
			}
		}
		Assert.assertTrue(verifica);
	}

	/** Metodo para obter o valor de algum campo do resumo.
	 * @param campoResumo Campo do resumo que deseja obter o valor.
	*/
	public String obterValorResumoVenda(String campoResumo) {
		if (campoResumo == "Troco" || campoResumo == "A pagar"){
			List<WebElement> ul = driver.findElements(By.tagName("ul"));
    		WebElement span = ul.get(1).findElement(By.xpath("//span[contains(.,'" + campoResumo + ":')]"));
			String spanValor = span.getText();			

			System.out.println(spanValor);
			return spanValor
				.replace(campoResumo + ":", "")
				.replace("\n", "")
				.replace("R$ ", "")
				.replace(".", "")
				.replace(",", ".");
		}

		WebElement span = driver.findElement(By.xpath("//span[contains(.,'" + campoResumo + ":')]"));
		WebElement tagnamePai = span.findElement(By.xpath(".."));
		WebElement spanValor = tagnamePai.findElement(By.xpath("span[2]"));
	
		return spanValor.getText().replace("R$ ", "").replace(".", "").replace(",", ".").replace("\ni", "");
	}

	/** Metodo para aguardar até que um texto esteja presente em algum elemento. */
	public void aguardarTextoNoElementoInput(WebElement elemento, String texto) {
		WebDriverWait aguardar = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		aguardar.until(ExpectedConditions.textToBePresentInElementValue(elemento, texto));
	}

	/** Metodo para aguardar até que um texto esteja presente em algum elemento. */
	public void aguardarTextoNoElemento(WebElement elemento, String texto) {
		WebDriverWait aguardar = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		aguardar.until(ExpectedConditions.textToBePresentInElement(elemento, texto));
	}
	//#endregion
}
