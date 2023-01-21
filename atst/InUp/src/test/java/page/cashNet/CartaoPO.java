package page.cashNet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import page.BasePO;

/** Page Object da tela de cartao dentro de CashNet. */
public class CartaoPO extends BasePO {

    // #region Regiao dos elementos

    /** Enum para selecionar o tipo de cartao POS. */
    public enum tipoCartao { CREDITO, DEBITO;}

    /** Elemento de opcao para cartao POS. */
    @FindBy(xpath = "//button[contains(.,'POS')]")
    public WebElement botaoCartaoPOS;

    /** Elemento de opcao para cartao de debito. */
    @FindBy(id = "debito1")
    public WebElement inputCartaoDebito;

    /** Elemento de opcao para cartao de credito. */
    @FindBy(id = "credito1")
    public WebElement inputCartaoCredito;

    /** Elemento de opcao para cartao bandeira. */
    @FindBy(id = "brand")
    public WebElement selectBandeira;

    /** Elemento de opcao para parcelamento. */
    @FindBy(id = "installmentType")
    public WebElement selectTipoDeParcelamento;

    /** Elemento para inserir o valor do cartao POS. */
    @FindBy(id = "value")
    public WebElement inputValorPOS;

    /** Elemento de opcao para quantidade de parcelas. */
    @FindBy(id = "installments")
    public WebElement selectQuantidadeParcelas;

    /** Elemento para inserir a autorizacao. */
    @FindBy(id = "authorization")
    public WebElement inputAutorizacaoPOS;

    /** Elemento para inserir NSU Host / Doc. */
    @FindBy(id = "nsuHostDoc")
    public WebElement inputNsuHostDoc;

    /** Elemento para editar informacoes de um cartao adicionado. */
    @FindBy(css = ".fa-pencil-alt")
    public WebElement botaoEditarCartao;

    /** Elemento para excluir um cartao adicionado. */
    @FindBy(css = ".fa-trash-alt")
    public WebElement botaoExcluirCartao;

    /** Elemento para fechar o modal do cartão POS. */
    @FindBy(xpath = "//button[contains(.,'Fechar')]")
    public WebElement botaoFechar;
    // #endregion

    // #region Regiao dos construtores

    /** Construtor da classe.
	 * @param driver Driver da classe.
	 */
    public CartaoPO(WebDriver driver) {
        super(driver);
    }
    // #endregion

    // #region Regiao dos metodos

    /** Metodo para selecionar a bandeira pelo nome.
     * @param nomeBandeira nome a ser informado da bandeira do cartao.
     */
    public void selecionarBandeira(String nomeBandeira) {
        Select selecionarBandeira = new Select(selectBandeira);
        int index = 0;
        for (WebElement bandeira : selecionarBandeira.getOptions()) {
            if (bandeira.getText().contains(nomeBandeira))
                break;
            index++;
        }
        selecionarBandeira.selectByIndex(index);
    }

    /** Metodo para selecionar o tipo de parcelamento.
     * @param nomeDoTipoDeParcelamento nome do tipo de parcelamento a ser selecionado.
     */
    public void selecionarTipoDeParcelamento(String nomeDoTipoDeParcelamento) {
        Select selecionarTipoParcelamento = new Select(selectTipoDeParcelamento);

        int index = 0;
        for (WebElement bandeira : selecionarTipoParcelamento.getOptions()) {
            if (bandeira.getText().contains(nomeDoTipoDeParcelamento))
                break;
            index++;
        }
        selecionarTipoParcelamento.selectByIndex(index);
    }

    /** Metodo para selecionar a quantidade de parcelas.
     * @param numeroQuantidadeDeParcelas numero de quantidade de parcelas a ser selecionada.
     */
    public void selecionarQuantidadeDeParcelas(String numeroQuantidadeDeParcelas) {
        Select selecionarQuantidadeDeParcelas = new Select(selectQuantidadeParcelas);

        int index = 0;
        for (WebElement quantidade : selecionarQuantidadeDeParcelas.getOptions()) {
            if (quantidade.getText().contains(numeroQuantidadeDeParcelas + " X R$"))
                break;
            index++;
        }
        selecionarQuantidadeDeParcelas.selectByIndex(index);
    }
    //#endregion
}
