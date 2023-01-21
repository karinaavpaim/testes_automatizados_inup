package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/** Page Object da tela de pagamento em dinheiro dentro de NetShopper e CashNet. */
public class DinheiroPO extends BasePO{

    //#region Regiao dos elementos

    /** Elemento para selecionar a opção dinheiro. */
	@FindBy(xpath = "//button[contains(.,'Dinheiro')]")
	public WebElement botaoDinheiro;

	/** Elemento para inserir o valor a pagar com dinheiro. */
	@FindBy(id = "moneyValue")
	public WebElement inputValorDinheiro;

    /** Elemento para selecionar o valor de R$ 1,00. */
	@FindBy(css = ".col-lg-4:nth-child(1) > .btn")
	public WebElement botaoValorUmReal;

    /** Elemento para selecionar o valor de R$ 2,00. */
	@FindBy(css = ".col-lg-4:nth-child(2) > .btn")
	public WebElement botaoValorDoisReais;

    /** Elemento para selecionar o valor de R$ 5,00. */
	@FindBy(css = ".col-lg-4:nth-child(3) > .btn")
	public WebElement botaoValorCincoReais;

    /** Elemento para selecionar o valor de R$ 10,00. */
	@FindBy(css = ".col-lg-4:nth-child(4) > .btn")
	public WebElement botaoValorDezReais;

    /** Elemento para selecionar o valor de R$ 50,00. */
	@FindBy(css = ".col-lg-4:nth-child(5) > .btn")
	public WebElement botaoValorCinquentaReais;

    /** Elemento para selecionar o valor de R$ 100,00. */
	@FindBy(css = ".col-lg-4:nth-child(6) > .btn")
	public WebElement botaoValorCemReais;
    //#endregion

    //#region Regiao dos construtores

	/** Construtor da classe.
	 * @param driver Driver da classe.
	 */
    public DinheiroPO(WebDriver driver) {
        super(driver);
    }
	//#endregion
	
	//#region Regiao dos metodos

	/** Metodo para realizar pagamento em dinheiro, inserindo o valor sem virgula. 
	 * @param valor valor a ser inserido.
	*/
	public void realizarPagamentoEmDinheiro(String valor){
		botaoDinheiro.click();
		
		aguardarElemento(driver, 5);

		limparCampoInput(inputValorDinheiro);
		inputValorDinheiro.sendKeys(valor);
		botaoConfirmarMensagemFlutuante.click();
	}
    //#endregion
}
