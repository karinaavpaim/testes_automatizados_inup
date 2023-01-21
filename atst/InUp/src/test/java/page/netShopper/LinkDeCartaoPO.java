package page.netShopper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import page.BasePO;

/** Page Object da tela de pagamento com link de cartao dentro de NetShopper. */
public class LinkDeCartaoPO extends BasePO{

    //#region Regiao dos elementos
    
	/** Elemento para selecionar a opcao de link de cartao. */
	@FindBy(xpath = "//button[contains(.,'Link Cartão')]")
	public WebElement botaoLinkDeCartao;

    /** Elemento para inserir o valor a pagar com cartao. */
	@FindBy(id = "cardValue")
	public WebElement inputValorCartao;
    //#endregion

    //#region Regiao dos construtores
	
	/** Construtor da classe.
	 * @param driver Driver da classe.
	 */
    public LinkDeCartaoPO(WebDriver driver) {
        super(driver);
    }
    //#endregion

    //#region Regiao dos metodos

	 /** Metodo para realizar pagamento com link de cartao, inserindo o valor sem virgula. 
	  * @param valor valor a ser inserido. Ex. 1000 (10,00)
	 */
	public void realizarPagamentoLinkDeCartao(String valor){
		botaoLinkDeCartao.click();
		aguardarElemento(driver, 5);

		limparCampoInput(inputValorCartao);
		inputValorCartao.sendKeys(valor);
		botaoConfirmarMensagemFlutuante.click();
	}
    //#endregion
}
