package test;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import page.LoginPO;

/** Classe para testar a busca de clientes e produtos. */
public class LoginTest extends BaseTest{

    //#region Regiao dos atributos
    private static LoginPO loginPO;
    private static String login = "karina.teste@dsn.in";
    private static String senha = "123"; 
    //#endregion

	/** Metodo para inciar os testes desta classe. */
	@BeforeClass
	public static void iniciarTestes(){
		loginPO = new LoginPO(driver);
		
        realizarLogoffInUp();
	}

    /** Metodo para finalizar os testes desta classe. */
    @AfterClass
    public static void encerrarTestes(){
		loginPO.limparCampoInput(loginPO.inputEmail);
		loginPO.limparCampoInput(loginPO.inputSenha);

		loginPO.inputEmail.sendKeys(login);
		loginPO.inputSenha.sendKeys(senha);
        loginPO.botaoEntrar.click();
	}

	//#region Regiao dos testes.

	/** Teste para entrar com um login valido. */
	@Test
	public void T0001_deve_testar_com_login_valido() {
		loginPO.limparCampoInput(loginPO.inputEmail);
		loginPO.limparCampoInput(loginPO.inputSenha);

		loginPO.inputEmail.sendKeys(login);
		loginPO.inputSenha.sendKeys(senha);

		Assert.assertTrue(loginPO.verificarSeBotaoEntrarEstaHabilitado());
		loginPO.botaoEntrar.click();

		realizarLogoffInUp();

		loginPO.limparCampoInput(loginPO.inputEmail);
		loginPO.limparCampoInput(loginPO.inputSenha);

		loginPO.inputEmail.sendKeys("upteste@teste.com.br");
		loginPO.inputSenha.sendKeys("123");

		Assert.assertTrue(loginPO.verificarSeBotaoEntrarEstaHabilitado());
		loginPO.botaoEntrar.click();
		
		realizarLogoffInUp();

		loginPO.inputEmail.sendKeys("netshopper@interfacenet.com.br");
		loginPO.inputSenha.sendKeys("123456");

		Assert.assertTrue(loginPO.verificarSeBotaoEntrarEstaHabilitado());
		loginPO.botaoEntrar.click();

		realizarLogoffInUp();
	}

    /** Teste para login inexistente. */
	@Test
	public void T0002_deve_testar_com_login_inexistente() {
		loginPO.limparCampoInput(loginPO.inputEmail);
		loginPO.limparCampoInput(loginPO.inputSenha);

		loginPO.inputEmail.sendKeys("karina@dsn.in");
		loginPO.inputSenha.sendKeys(senha);

		Assert.assertTrue(loginPO.verificarSeBotaoEntrarEstaHabilitado());
		loginPO.botaoEntrar.click();

		Assert.assertEquals("Usuário ou senha inválido.", loginPO.mensagemEmailInvalido.getText());
	}

	/** Teste para login com senha incorreta. */
	@Test
	public void T0003_deve_testar_senha_incorreta() {
		loginPO.limparCampoInput(loginPO.inputEmail);
		loginPO.limparCampoInput(loginPO.inputSenha);

		loginPO.inputEmail.sendKeys(login);
		loginPO.inputSenha.sendKeys("alteradata");

		Assert.assertTrue(loginPO.verificarSeBotaoEntrarEstaHabilitado());
		loginPO.botaoEntrar.click();

		Assert.assertEquals("Usuário ou senha inválido.", loginPO.mensagemEmailInvalido.getText());
	}
	//#endregion
}
