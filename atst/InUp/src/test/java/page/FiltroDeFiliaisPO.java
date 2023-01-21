package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/** Page Object da tela Filtro de Filiais. */
public class FiltroDeFiliaisPO extends BasePO{

    //#region Regiao dos elementos.

	/** Enum para definir o tipo de loja a ser selecionado. */
	public enum tipoDeLoja { LOJASPROPRIAS, FRANQUIAS, FEIRA_REPRESENTANTES, LOJASPROPRIAS_FRANQUIAS; }

	/** Elemento de botao de filiais. */
	@FindBy(xpath = "//button[text()=' Filiais ']")
	public WebElement botaoFiliais;
	
	/** Elemento para digitar o codigo da boleta. */
	@FindBy(css = ".fa-plus")
	public WebElement botaoAdicionarGrupo;

	/** Elemento de tabela de filiais. */
	@FindBy(xpath = "//table[4]")
	public WebElement tabelaFiliais;

	/** Elemento de titulo para os tipo de loja. */
	@FindBy(css = ".mb-4 > .card-header")
	public WebElement tituloTipoDeLoja;

	/** Input para buscar uma loja. */
	@FindBy(id = "filterShops")
	public WebElement inputBuscarLoja;	

	/** Input para selecionar inativos. */
	@FindBy(css = ".form-group > .custom-switch > .custom-control-label")
	public WebElement inputInativos;

	/** Elemento para limpar filtros. */
	@FindBy(xpath = "//button[contains(.,'Limpar Filtros')]")
	public WebElement botaoLimparFiltros;

    //#endregion

	//#region Região dos construtores.

	/** Construtor da classe.
	 * @param driver Driver da classe.
	 */
	public FiltroDeFiliaisPO(WebDriver driver) {
		super(driver);
	}
	//#endregion

	//#region Regiao dos metodos.

	/** Metodo para selecionar a filial pelo nome. 
	 * @param filial Nome da filial a ser selecionado.
	*/
	public void selecionarFilial(String filial){
		aguardarElemento(driver, 10);

		WebElement linhaFilial = driver.findElement(By.xpath("//small[contains(.,'" + filial + "')]"));
		WebElement tdPai = linhaFilial.findElement(By.xpath(".."));
		WebElement trPai = tdPai.findElement(By.xpath(".."));
		
		WebElement input = trPai.findElement(By.tagName("input"));
	
		input.click();
	} 

	/** Metodo para selecionar o tipo de loja. 
	 * @param tipo tipo de loja.
	*/
	public void selecionarTipoDeLoja(tipoDeLoja tipo){
		aguardarElemento(driver, 10);

		rolarPagina(tituloTipoDeLoja);
				
		if (tipo == tipoDeLoja.LOJASPROPRIAS) {
			WebElement label = driver.findElement(By.xpath("//label[contains(.,'Lojas próprias')]"));
					
			label.click();
		} else if (tipo == tipoDeLoja.FRANQUIAS) {
			WebElement label = driver.findElement(By.xpath("//label[contains(.,'Franquias')]"));
			
			label.click();
		} else if (tipo == tipoDeLoja.FEIRA_REPRESENTANTES) {
			WebElement label = driver.findElement(By.xpath("//label[contains(.,'Feira / Representantes')]"));
			
			label.click();
		} else if (tipo == tipoDeLoja.LOJASPROPRIAS_FRANQUIAS) {
			WebElement label = driver.findElement(By.xpath("//label[contains(.,'Lojas próprias / Franquias')]"));
			
			label.click();
		}
	}

	/** Metodo para pesquisar uma loja. 
	* @param nomeLoja Nome da loja que deseja pesquisar.
	*/
	public void pesquisarLoja(String nomeLoja){
		aguardarElemento(driver, 10);

		limparCampoInput(inputBuscarLoja);
		inputBuscarLoja.sendKeys(nomeLoja);
	}

	/** Metodo para verificar nome de uma loja de acordo com a pesquisa realizada. 
	* @param nomeLoja Nome da loja que deseja conferir.
	*/
	public boolean conferirLojaPesquisada(String nomeLoja){
		aguardarElemento(driver, 10);

		String nomeLojaListagem = driver.findElement(By.xpath("//small[contains(.,'" + nomeLoja + "')]")).getText();

		if (nomeLojaListagem.contains(nomeLoja)){
			return true;
			}
		return false;
	}
}
