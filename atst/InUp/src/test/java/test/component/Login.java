package test.component;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import page.LoginPO;
import test.BaseTest;

/** Classe para testar os componentes da tela de login. */
public class Login extends BaseTest {

    // #region Regiao dos atributos
    private static LoginPO loginPO;
    private static String login = "karina.teste@dsn.in";
    private static String senha = "123"; 
    // #endregion

    /** Metodo para inciar os testes desta classe. */
    @BeforeClass
    public static void iniciarTestes() {
        loginPO = new LoginPO(driver);

		realizarLogoffInUp();
    }

    /** Metodo para finalizar os testes. */
    @AfterClass
    public static void encerrarTestes(){
		loginPO.limparCampoInput(loginPO.inputEmail);
		loginPO.limparCampoInput(loginPO.inputSenha);

		loginPO.inputEmail.sendKeys(login);
		loginPO.inputSenha.sendKeys(senha);
        loginPO.botaoEntrar.click();
	}

	//#region Regiao dos testes

    /** Teste para login sem arroba. */
	@Test
	public void T0001_deve_testar_com_login_sem_arroba() {
		loginPO.limparCampoInput(loginPO.inputEmail);
		loginPO.limparCampoInput(loginPO.inputSenha);

		loginPO.inputEmail.sendKeys("karina.teste");
		loginPO.inputSenha.sendKeys(senha);

		Assert.assertEquals("O campo é inválido", loginPO.mensagemEmailEmBranco.getText());
		Assert.assertFalse(loginPO.verificarSeBotaoEntrarEstaHabilitado());
	}

	/** Teste para login com arroba com ponto no final do login. */
	@Test
	public void T0002_deve_testar_com_login_com_arroba_e_ponto_ao_final() {
		loginPO.limparCampoInput(loginPO.inputEmail);
		loginPO.limparCampoInput(loginPO.inputSenha);

		loginPO.inputEmail.sendKeys("karina.teste@dsn.");
		loginPO.inputSenha.sendKeys(senha);

		Assert.assertEquals("O campo é inválido", loginPO.mensagemEmailEmBranco.getText());
		Assert.assertFalse(loginPO.verificarSeBotaoEntrarEstaHabilitado());
	}

    /** Teste para login inciando com o arroba. */
	@Test
	public void T0003_deve_testar_com_login_iniciando_com_arroba() {
		loginPO.limparCampoInput(loginPO.inputEmail);
		loginPO.limparCampoInput(loginPO.inputSenha);

		loginPO.inputEmail.sendKeys("@karina.dsn.in");

		Assert.assertFalse(loginPO.verificarSeBotaoEntrarEstaHabilitado());

		Assert.assertEquals("O campo é inválido", loginPO.mensagemEmailEmBranco.getText());
        
        loginPO.limparCampoInput(loginPO.inputEmail);
	}
    
	/** Teste para login com acentuacao. */
	@Test
	public void T0004_deve_testar_login_com_acentuacao() {
		loginPO.limparCampoInput(loginPO.inputEmail);
		loginPO.limparCampoInput(loginPO.inputSenha);

		loginPO.inputEmail.sendKeys("érica.flores@dsn.in");
		loginPO.inputSenha.sendKeys(senha);

		Assert.assertFalse(loginPO.verificarSeBotaoEntrarEstaHabilitado());

		Assert.assertEquals("O campo é inválido", loginPO.mensagemEmailEmBranco.getText());

		loginPO.limparCampoInput(loginPO.inputEmail);
		loginPO.limparCampoInput(loginPO.inputSenha);

		loginPO.inputEmail.sendKeys("andre.gonçalves@dsn.in");
		loginPO.inputSenha.sendKeys(senha);

		Assert.assertFalse(loginPO.verificarSeBotaoEntrarEstaHabilitado());

		Assert.assertEquals("O campo é inválido", loginPO.mensagemEmailEmBranco.getText());

		loginPO.limparCampoInput(loginPO.inputEmail);
		loginPO.limparCampoInput(loginPO.inputSenha);

		loginPO.inputEmail.sendKeys("marcão@dsn.in");
		loginPO.inputSenha.sendKeys(senha);

		Assert.assertFalse(loginPO.verificarSeBotaoEntrarEstaHabilitado());

		Assert.assertEquals("O campo é inválido", loginPO.mensagemEmailEmBranco.getText());
	}
    
    /** Teste para conferir o retorno das mensagens para os campos em branco. */
	@Test
	public void T0005_deve_testar_mensagem_para_campo_em_branco_e_se_o_botao_esta_desabilitado() {
        driver.navigate().refresh();
                
        loginPO.inputSenha.click();
        loginPO.inputEmail.click();
        loginPO.inputSenha.click();

		Assert.assertEquals("O campo é inválido", loginPO.mensagemEmailEmBranco.getText());
		Assert.assertEquals("O campo é obrigatório", loginPO.mensagemSenhaObrigatoria.getText());

		Assert.assertFalse(loginPO.verificarSeBotaoEntrarEstaHabilitado());
	}
	//#endregion
}
