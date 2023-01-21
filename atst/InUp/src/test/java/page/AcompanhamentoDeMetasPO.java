package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/** Page Object da pagina de Acompanhamento de Metas. */
public class AcompanhamentoDeMetasPO extends BasePO{

    //#region Regiao dos elementos.

	/** Elemento para inserir o nome da filial. */
	@FindBy(css = ".ng-input > input")
	public WebElement inputFiliais;

    //#endregion

    //#region Região dos construtores.

	/** Construtor da classe.
	 * @param driver Driver da classe.
	 */
	public AcompanhamentoDeMetasPO(WebDriver driver) {
		super(driver);
	}
	//#endregion

	//#region Regiao dos metodos.

	/** Metodo para navegar para o menu Consulta de Vendas. */
	public void navegarParaAcompanhamentoDeMetas(){
		menuAcompanhamentoDeMetas.click();
		aguardarElemento(driver, 10);
	}
	//#endregion    
}
