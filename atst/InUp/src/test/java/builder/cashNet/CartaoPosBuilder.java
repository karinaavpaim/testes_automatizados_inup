package builder.cashNet;

import page.cashNet.CartaoPO;
import page.cashNet.CartaoPO.tipoCartao;

/** Classe de builder para inserir dados do cartao POS em CashNet. */
public class CartaoPosBuilder {

    // #region região dos atributos.
    private CartaoPO cartaoPO;
    private String nomeBandeira = "ELO";
    private String nomeTipoDeParcelamento = "Administradora";
    private String valor = "30000";
    private String numeroQuantidadeDeParcelas = "5";
    private String autorizacao = "gerencia";
    private String NsuHostDoc = "gerencia";
    //#endregion

    // #region região dos construtores.

    /** Construtor da classe.
     * @param cartaoPO PageObject da tela de cartao POS.
     */
    public CartaoPosBuilder(CartaoPO cartaoPO) {
        this.cartaoPO = cartaoPO;
    }
    // #endregion

    // #region região dos builders.

    /** Seleciona uma bandeira ao cartao.
     * @param nomeBandeira bandeira do cartao.
     * @return Retorna a propria classe.
     */
    public CartaoPosBuilder comBandeira(String nomeBandeira) {
        this.nomeBandeira = nomeBandeira;
        return this;
    }

    /** Seleciona o tipo de parcelamento.
     * @param nomeTipoDeParcelamento tipo de parcelamento do cartao.
     * @return Retorna a propria classe.
     */
    public CartaoPosBuilder comTipoDeParcelamento(String nomeTipoDeParcelamento) {
        this.nomeTipoDeParcelamento = nomeTipoDeParcelamento;
        return this;
    }

    /** Insere o valor a ser pago com cartao.
     * @param valor valor a ser pago com cartao.
     * @return Retorna a propria classe.
     */
    public CartaoPosBuilder comValor(String valor) {
        this.valor = valor;
        return this;
    }

    /** Insere a quantidade de parcelas.
     * @param numeroQuantidadeDeParcelas quantidade de parcelas.
     * @return Retorna a propria classe.
     */
    public CartaoPosBuilder comParcela(String numeroQuantidadeDeParcelas) {
        this.numeroQuantidadeDeParcelas = numeroQuantidadeDeParcelas;
        return this;
    }

    /** Insere uma autorizacao.
     * @param autorizacao descreve uma autorizacao.
     * @return Retorna a propria classe.
     */
    public CartaoPosBuilder comAutorizacao(String autorizacao) {
        this.autorizacao = autorizacao;
        return this;
    }

    /** Insere a NSU Host / Doc.
     * @param NsuHostDoc descreve a NSU Host / Doc.
     * @return Retorna a propria classe.
     */
    public CartaoPosBuilder comNSUHostDoc(String NsuHostDoc) {
        this.NsuHostDoc = NsuHostDoc;
        return this;
    }
    //#endregion

    //#region Região dos métodos.
    
     /** Metodo para preencher os dados de um cartao POS.
     * @param tipo Paramentro para selecionar o tipo de cartao: Credito ou Debito.
     */
    public void preencherDadosCartaoPOS(tipoCartao tipo) {
        if (tipo == tipoCartao.CREDITO) {
            cartaoPO.inputCartaoCredito.click();

            cartaoPO.limparCampoInput(cartaoPO.inputValorPOS);
            cartaoPO.limparCampoInput(cartaoPO.inputAutorizacaoPOS);
            cartaoPO.limparCampoInput(cartaoPO.inputNsuHostDoc);

            cartaoPO.inputValorPOS.sendKeys(valor);
            cartaoPO.inputAutorizacaoPOS.sendKeys(autorizacao);
            cartaoPO.inputNsuHostDoc.sendKeys(NsuHostDoc);

            cartaoPO.selecionarBandeira(nomeBandeira);
            cartaoPO.selecionarTipoDeParcelamento(nomeTipoDeParcelamento);
            cartaoPO.selecionarQuantidadeDeParcelas(numeroQuantidadeDeParcelas);      
                
        } else if (tipo == tipoCartao.DEBITO) {
            cartaoPO.inputCartaoDebito.click();
               
            cartaoPO.limparCampoInput(cartaoPO.inputValorPOS);
            cartaoPO.limparCampoInput(cartaoPO.inputAutorizacaoPOS);
            cartaoPO.limparCampoInput(cartaoPO.inputNsuHostDoc);

            cartaoPO.inputValorPOS.sendKeys(valor);
            cartaoPO.inputAutorizacaoPOS.sendKeys(autorizacao);
            cartaoPO.inputNsuHostDoc.sendKeys(NsuHostDoc);

            cartaoPO.selecionarBandeira(nomeBandeira);
            cartaoPO.selecionarTipoDeParcelamento(nomeTipoDeParcelamento);
        }
    }
    //#endregion
}
