package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/** Page Object da tela de editar item dentro de NetShopper e CashNet. */
public class EditarItemPO extends BasePO{

    //#region Regiao dos elementos

	/** Enum para definir o tipo de edicao será realizado no valor do item. */
	public enum tipoEditarItem { DESCONTO, ACRESCIMO; }

	/** Enum para definir o tipo de edicao será realizado no valor do item. */
	public enum tipoValor { VALOR, PERCENTUAL; }

    /** Elemento para editar o item adicionado. */
	@FindBy(xpath = "//button[contains(.,'Editar item')]")
	public WebElement botaoEditarItem;

	/** Elemento para selecionar o input de desconto. */
	@FindBy(id = "type-1")
	public WebElement inputDesconto;

    /** Elemento para selecionar o input de acrescimo. */
	@FindBy(id = "type-2")
	public WebElement inputAcrescimo;

    /** Elemento para inserir um valor em Real. */
	@FindBy(id = "priceItemChange")
	public WebElement inputValorEmReal;

    /** Elemento para inserir um valor em porcentagem. */
	@FindBy(id = "discount")
	public WebElement inputValorPorcentagem;

    /** Elemento para inserir a quantidade. */
	@FindBy(css = ".input-group:nth-child(4) > #qtd")
	public WebElement inputQuantidade;

    /** Elemento para verificar o valor do item. */
	@FindBy(id = "priceItem")
	public WebElement inputValorItem; 
    //#endregion
    
    //#region Regiao dos construtores

	/** Construtor da classe.
	 * @param driver Driver da classe.
	 */
    public EditarItemPO(WebDriver driver) {
        super(driver);
    }
	//#endregion

    //#region Regiao dos metodos

	/** Metodo para editar um item. 
	 * @param quantidade valor a ser inserido.
	*/
	public void editarQuantidade(int quantidade){
		botaoEditarItem.click();

		limparCampoInput(inputQuantidade);
		inputQuantidade.sendKeys(String.valueOf(quantidade));
		
		botaoConfirmarMensagemFlutuante.click();
	}

	/** Metodo para aplicar um descontou ou acrescimo.
	 * @param tipo selecionar se é desconto ou acrescimo.
	 * @param valorTipo selecionar se é valor em Real ou em percentual. 
	 * @param valor valor a ser inserido.
	*/
	public void aplicarDescontoOuAcrescimo(tipoEditarItem tipo, tipoValor valorTipo, String valor){
		botaoEditarItem.click();
		
		aguardarElemento(driver, 10);

		limparCampoInput(inputValorEmReal);
		limparCampoInput(inputValorPorcentagem);
				
		if (tipo == tipoEditarItem.DESCONTO) {
			inputDesconto.click();
		} else { 
			inputAcrescimo.click();
		}

		if (valorTipo == tipoValor.VALOR) {
			inputValorEmReal.sendKeys(valor);
		} else {
			inputValorPorcentagem.sendKeys(valor);
		}
		botaoConfirmarMensagemFlutuante.click();
	}
    //#endregion
}
