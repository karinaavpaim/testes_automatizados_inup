package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/** Page Object da pagina de login. */
public class LoginPO extends BasePO{

	//#region Regiao dos elementos.

	/** Elemento para digitar o login do usuario. */
	@FindBy(id = "email")
	public WebElement inputEmail;

	/** Elemento para digitar a senha do usuario. */
	@FindBy(id = "password")
	public WebElement inputSenha;

	/** Elemento botao "Entrar" com usuario e senha. */
	@FindBy(css = ".btn")
	public WebElement botaoEntrar;

	/** Elemento de mensagem de email em branco. */
	@FindBy(id = "emailHelp")
	public WebElement mensagemEmailEmBranco;

	/** Elemento de mensagem de email invalido. */
	@FindBy(css = ".alert")
	public WebElement mensagemEmailInvalido;

	/** Elemento de mensagem de senha obrigatoria. */
	@FindBy(id = "passwordHelp")
	public WebElement mensagemSenhaObrigatoria;

	@FindBy(xpath = "//h5[contains(text(),'QLDIN')]//ancestor::div[5]")
	public WebElement redeQLDIN;

	//#endregion

	//#region Região dos construtores.

	/** Construtor da classe.
	 * @param driver Driver da classe.
	 */
	public LoginPO(WebDriver driver) {
		super(driver);
	}
	//#endregion

	//#region Regiao dos metodos

	/** Metodo para informar se o botao entrar na tela de login esta habilitado. */
	public Boolean verificarSeBotaoEntrarEstaHabilitado() {
		if (botaoEntrar.isEnabled()){
			return true;
		}
		else {
			return false;
		}
	}

	/** Metodo para realizar um novo login com o usuario interfacenetup */
	public void realizaNovoAcessoAoInUp(String email, String senha) {
		aguardarElemento(driver, 10);
		
		limparCampoInput(inputEmail);
		limparCampoInput(inputSenha);
		
		inputEmail.sendKeys(email);
		inputSenha.sendKeys(senha);
		
		botaoEntrar.click();

		aguardarElemento(driver, 20);
	}
	//#endregion
}	
	

	
	
