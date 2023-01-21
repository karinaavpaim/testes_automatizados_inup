package page.cashNet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import page.BasePO;

/** Page Object da tela de pix dentro de CashNet. */
public class PixPO extends BasePO{

     // #region Regiao dos elementos

    /** Elemento de botão Pix. */
    @FindBy(xpath = "//button[contains(.,'PIX')]")
    public WebElement botaoPix;

    /** Elemento de título do modal. */
    @FindBy(css = ".ng-pristine .modal-title")
    public WebElement tituloModal;

    /** Input do valor do pix. */
    @FindBy(id = "ValorPix")
    public WebElement inputValorDoPix;

    /** Elemento para confirmar a operação com o Pix. */
    @FindBy(xpath = "//button[contains(.,'Confirmar')]")
    public WebElement botaoConfirmar;

    /** Elemento de título do modal QRCode. */
    @FindBy(css = ".ng-untouched .modal-title")
    public WebElement tituloModalQRCode;
    
    /** Elemento para verificar o codigo. */
    @FindBy(xpath = "//button[contains(.,'Verifica PIX')]")
    public WebElement botaoVerificarPix;

    /** Elemento para fechar o modal do QRCode. */
    @FindBy(xpath = "//ngb-modal-window[2]/div/div/div/button")
    public WebElement botaoFecharModalQRCode;    
    //#endregion

    // #region Regiao dos construtores

    /** Construtor da classe.
	 * @param driver Driver da classe.
	 */
    public PixPO(WebDriver driver) {
        super(driver);
    }
    // #endregion
    
}
