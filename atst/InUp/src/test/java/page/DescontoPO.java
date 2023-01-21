package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/** Page Object da tela de desconto dentro de NetShopper e CashNet. */
public class DescontoPO extends BasePO {

    // #region Regiao dos elementos

    /** Enum para definir qual a forma que o desconto sera inserido. */
    public enum desconto { VALOR, PORCENTAGEM; }

    /** Elemento para selecionar a opção desconto. */
	@FindBy(xpath = "//button[contains(.,'Desconto')]")
	public WebElement botaoDesconto;
    
    /** Elemento para inserir o valor de desconto em reais. */
    @FindBy(id = "moneyValueDiscount")
    public WebElement inputValorDesconto;

    /** Elemento para inserir o valor de desconto em percentual. */
    @FindBy(id = "discountValue")
    public WebElement inputPercentualDesconto;

    /** Elemento para selecionar o tipo de desconto. */
    @FindBy(id = "typeDiscount")
    public WebElement selectTipoDesconto;

    /** Elemento para inserir a descricao do desconto. */
    @FindBy(id = "description")
    public WebElement inputDescricaoDesconto;

    /** Elemento de mensagem de descricao obrigatoria. */
    @FindBy(css = "#descriptionHelp > .ng-star-inserted")
    public WebElement mensagemDescricaoObrigatoria;
    // #endregion

    // #region Regiao dos construtores

    /** Construtor da classe.
	 * @param driver Driver da classe.
	 */
    public DescontoPO(WebDriver driver) {
        super(driver);
    }
    // #endregion

    // #region Regiao dos Metodos

    /** Metodo para inserir um desconto a uma venda.
     * @param forma parametro patra indicar qual a forma de desconto a ser utilizada: VALOR ou PORCENTAGEM  
     * @param valor valor a ser inserido.
     * @param tipoDeDesconto descrever o tipo de desconto a ser selecionado no elemento select.
     * @param descricaoDesconto descricao a ser inserida ao desconto. Este e obrigatorio.
     */
    public void inserirValorDesconto(desconto forma, String valor, String tipoDeDesconto, String descricaoDesconto) {
        botaoDesconto.click();
        
        aguardarElemento(driver, 5);

		aguardarCarregamentoDaPagina();

        limparCampoInput(inputValorDesconto);
        limparCampoInput(inputPercentualDesconto);
        inputValorDesconto.sendKeys("0");
        inputPercentualDesconto.click();
        
        inputPercentualDesconto.sendKeys("0");
        inputValorDesconto.click();

        if (conteudoMensagemFlutuante.isDisplayed()){
            conteudoMensagemFlutuante.click();
        }          

        if (forma == desconto.VALOR) {
            inputValorDesconto.sendKeys(valor);
        } else if (forma == desconto.PORCENTAGEM) {
            inputPercentualDesconto.sendKeys(valor);
        }

        List<WebElement> tiposDesconto = selectTipoDesconto.findElements(By.tagName("option"));
        for (WebElement tipo : tiposDesconto) {
            if (tipo.getText().contains(tipoDeDesconto)) {
                tipo.click();
            }
        }
        inputDescricaoDesconto.sendKeys(descricaoDesconto);
        botaoConfirmarMensagemFlutuante.click();
    }
    // #endregion
}
