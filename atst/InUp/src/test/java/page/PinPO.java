package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/** Page Object da tela de PIN dentro de NetShopper e CashNet. */
public class PinPO extends BasePO{

    //#region Regiao dos elementos
    
    /** Botão para inserir o PIN. */
	@FindBy(xpath = "//button[contains(.,'PIN')]")
	public WebElement botaoPIN;

    /** Elemento para inserir o PIN. */
    @FindBy(id = "pin")
    public WebElement inputPin;

    //#endregion

    //#region Regiao dos construtores

    /** Construtor da classe.
	 * @param driver Driver da classe.
	 */
    public PinPO(WebDriver driver) {
        super(driver);
    }
    //#endregion

    //#region Regiao dos metodos

    /** Metodo para inserir o PIN da promocao. 
     * @param pin PIN a ser inserido.
    */
    public void inserirPin(String pin) {
        botaoPIN.click();
        
        inputPin.sendKeys(pin);
        botaoConfirmarMensagemFlutuante.click();
    }
    //#endregion

}
