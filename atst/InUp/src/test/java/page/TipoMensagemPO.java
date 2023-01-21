package page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import util.TabelaUtil;

/** Page Objects da tela Tipo de Mensagens. */
public class TipoMensagemPO extends BasePO {

    //#region Elementos

    /** Enum para definir o tipo de ação a ser executada. */
    public enum tipoAcao {EDITAR, EXCLUIR, REATIVAR;}

    /** Texto para validar que a pagina foi aberta */
    @FindBy(xpath = "//p[contains(text(),'Gerencie os tipos de mensagens para presentes')]")
    public WebElement subtituloDaPagina;

    /** Botão de "Adicionar tipo" */
    @FindBy(xpath = "//button[@ngbtooltip=\"Adicionar item\"]")
    public WebElement botaoAdicionarItem;

    /** Elemento de título da mensagem flutuante */
    @FindBy(id = "modal-basic-title")
    public WebElement tituloDaMensagemFlutuante;
    
    /** Campo "Digite o tipo" */
    @FindBy(xpath = "//input[@maxlength=\"50\" and @id=\"type\"]")
    public WebElement inputTipo;

    /** Botão "Ativos" */
    @FindBy(xpath = "//label[contains(text(),'Ativos')]")
    public WebElement inputAtivos;

    /** Botão "Pesquisar" */
    @FindBy(xpath = "//button[contains(text(),' Pesquisar ')]")
    public WebElement botaoPesquisar;
    //#endregion

    //#region Regiao dos construtores

    /** Construtor da classe.
	 * @param driver Driver da classe.
	 */
    public TipoMensagemPO(WebDriver driver) {
        super(driver);
    }
    //#endregion

    //#region Metodos

    /** Metodo para criar uma nova mensagem com texto aleatório */
    public void navegarParaTiposDeMensagens(){
        aguardarElemento(driver, 5);

        menuTiposDeMensagens.click();
    }

    /** Metodo para criar uma nova mensagem com texto aleatório
     * @param mensagem Mensagem a ser inserida.
     */
    public void criaUmaNovaMensagem(String mensagem){
        verificarSeElementoEstaVisivelEDisponivel(subtituloDaPagina);
        Assert.assertEquals(subtituloDaPagina.getText(), "Gerencie os tipos de mensagens para presentes");

        verificarSeElementoEstaVisivelEDisponivel(botaoAdicionarItem);
        botaoAdicionarItem.click();

        inputTipo.sendKeys(mensagem);
        botaoSalvar.click();
    }

    /** Metodo para pesquisar mensagens na tabela */
    public void pesquisar(){
        botaoPesquisar.click();

        aguardarCarregamentoDaPagina();
    }

    /** Metodo para realizar ações na tabela
     * @param valor nome do item a ser localizado
     * @param acao ação a ser tomada
    */
    public void realizaAcoes(String valor, tipoAcao acao){
        TabelaUtil tabelaUtil = new TabelaUtil(driver);

        int idLinha = tabelaUtil.obterLinha(valor, tabela);
       
        WebElement linha = tabela.findElement(By.xpath("//tr[" + idLinha + "]"));

        if(acao == tipoAcao.EDITAR){
            if (idLinha == 1){
                driver.findElement(By.cssSelector(".ng-star-inserted:nth-child(1) .btn-primary > .fa-duotone")).click();
            } else {
                linha.findElement(By.xpath("*//button[@class='btn btn-primary']")).click();
            }
        } else if (acao == tipoAcao.EXCLUIR){
            if (idLinha == 1){
                driver.findElement(By.cssSelector(".ng-star-inserted:nth-child(1) .btn-danger > .fa-duotone")).click();
                verificarSeElementoEstaVisivelEDisponivel(botaoExcluir);
                botaoExcluir.click();
            } else {
                linha.findElement(By.xpath("*//button[@class='btn btn-danger ng-star-inserted']")).click();
                verificarSeElementoEstaVisivelEDisponivel(botaoExcluir);
                botaoExcluir.click();
            }
        } else if(acao == tipoAcao.REATIVAR){
            if (idLinha == 1){
                driver.findElement(By.cssSelector(".ng-star-inserted:nth-child(1) .btn-success > .fa-duotone")).click();
            } else {
            linha.findElement(By.xpath("*//button[@class='btn btn-success ng-star-inserted']")).click();
            }
        }
    }
    //#endregion
}
