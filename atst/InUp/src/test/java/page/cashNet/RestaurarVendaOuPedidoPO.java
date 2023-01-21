package page.cashNet;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import page.BasePO;

/** Page Object da tela de restaurar vendas dentro de CashNet. */
public class RestaurarVendaOuPedidoPO extends BasePO {

    // #region Regiao dos elementos

    /** Enum para navegar pela paginacao. */
    public enum paginacao {
        ULTIMA_PAGINA, PROXIMA_PAGINA, PRIMEIRA_PAGINA, PAGINA_ANTERIOR;
    }

    /** Elemento de botão Restaurar Venda. */
    @FindBy(xpath = "//button[contains(.,'Restaurar Venda')]")
    public WebElement botaoRestaurarVenda;

    /** Elemento de botão Confirmar a restauração da venda. */
    @FindBy(xpath = "//button[contains(.,'Confirmar')]")
    public WebElement botaoConfirmar;

    /** Elemento de botão Fechar a restauração da venda. */
    @FindBy(xpath = "//button[contains(.,'Fechar')]")
    public WebElement botaoFechar;

    /** Elemento de tabela das pre vendas. */
    @FindBy(xpath = "//table")
    public WebElement tabela;

    // #endregion

    // #region Regiao dos construtores

    /**
     * Construtor da classe.
     * 
     * @param driver Driver da classe.
     */
    public RestaurarVendaOuPedidoPO(WebDriver driver) {
        super(driver);
    }
    // #endregion

    // #region Regiao dos metodos

    /**
     * Metodo para selecionar a paginacao dentro de restaurar vendas.
     * 
     * @param numeroDaPagina numero da pagina a navegar para localizar a venda.
     */
    public void selecionarPagina(int numeroDaPagina) {
        driver.findElement(By.xpath("//a[contains(text(),'" + numeroDaPagina + "')]")).click();
    }

    /**
     * Metodo para navegar pela paginacao
     * 
     * @param tipoPaginacao tipo de navegacao a ser feita pela peginacao
     */
    public void preencherDadosCartaoPOS(paginacao tipoPaginacao) {
        if (tipoPaginacao == paginacao.PRIMEIRA_PAGINA) {
            driver.findElement(By.xpath("//span[contains(.,'««')]")).click();
        } else if (tipoPaginacao == paginacao.ULTIMA_PAGINA) {
            driver.findElement(By.xpath("//span[contains(.,'»»')]")).click();
        } else if (tipoPaginacao == paginacao.PROXIMA_PAGINA) {
            driver.findElement(By.xpath("//a[contains(.,'»')]")).click();
        } else if (tipoPaginacao == paginacao.PAGINA_ANTERIOR) {
            driver.findElement(By.xpath("//a[contains(.,'«')]")).click();
        }
    }

    /**
     * Metodo para selecionar a venda que deseja restaurar
     * 
     * @param codigoPreVenda codigo da pre-venda que deseja restaurar
     */
    public void selecionarPreVendaPeloCodigo(String codigoPreVenda) {
        List<WebElement> colunas = tabela.findElements(By.tagName("td"));
        for (int i = 0; i < colunas.size(); i++) {
            if (colunas.get(i).getText().equalsIgnoreCase(codigoPreVenda)) {
                WebElement tr = colunas.get(i).findElement(By.xpath(".."));
                tr.findElement(By.tagName("input")).click();
                break;
            }
        }
    }

    /**
     * Metodo para restaurar a pre venda pelo codigo 
     * 
     * @param codigoPreVenda codigo da pre-venda que deseja restaurar
     */
    public void restaurarPreVendaPeloCodigo(String codigoPreVenda) {
        selecionarPreVendaPeloCodigo(codigoPreVenda);
        botaoConfirmar.click();
    }

    /**
     * Metodo para retornar a quantidade de pre-vendas cadastradas para uma loja 
     */
    public int retornarQuantidadeDePreVendas() {
        List<WebElement> listaDeVendas = tabela.findElements(By.tagName("tbody"));

        return listaDeVendas.size();
    }
    // #endregion
}
