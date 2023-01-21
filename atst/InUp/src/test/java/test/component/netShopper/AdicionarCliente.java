package test.component.netShopper;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import page.AdicionarClientePO;
import page.AdicionarClientePO.tipoCliente;
import page.netShopper.NetShopperPO;
import test.BaseTest;

/** Classe para testar os elementos da tela de adicionar clientes em CashNet. */
public class AdicionarCliente extends BaseTest {

	// #region Regiao dos atributos
	private static NetShopperPO netShopperPO;
	private static AdicionarClientePO adicionarClientePO;
	private static String nomeLoja = "NORTE SHOPPING";
	// #endregion

	/** Metodo para inciar os testes. */
	@BeforeClass
	public static void iniciarTestes() {
		netShopperPO = new NetShopperPO(driver);
		adicionarClientePO = new AdicionarClientePO(driver);

		netShopperPO.navegarParaNetshopper();
	}

	/** Metodo a ser realizado antes dos testes. */
	@Before
	public void atualizarPagina() {
		netShopperPO.atualizarPagina(10);

		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja, nomeLoja);
		netShopperPO.botaoAdicionarCliente.click();
	}

	/** Metodo para finalizar os testes. */
	@After
	public void finalizarTestes() {
		adicionarClientePO.botaoCancelar.click();
	}

	// #region Regiao dos testes

	/** Testar a semantica dos campos para cliente pessoa jurídica */
	@Test
	public void T0001_deve_verificar_semantica_dos_campos_adicionar_cliente_pessoa_juridica() {
		adicionarClientePO.selecionarTipoDeCliente(tipoCliente.JURIRICA);

		adicionarClientePO.inputNome.sendKeys("123 de Oliveira 4");
		adicionarClientePO.inputEmail.sendKeys("123deoliveira4");
		adicionarClientePO.inputTelefone.sendKeys("telefone");
		adicionarClientePO.inputCelular.sendKeys("celular");
		adicionarClientePO.inputCep.sendKeys("cep");
		adicionarClientePO.inputEndereco.sendKeys("12334566851515   49165156818161 %$#@!*&");
		adicionarClientePO.inputBairro.sendKeys("12334566851515   49165156818161 %$#@!*&");
		adicionarClientePO.inputNumeroDoEndereco.sendKeys("123 de Oliveira 4");
		adicionarClientePO.inputComplemento.sendKeys("123 de Oliveira 4");
		adicionarClientePO.inputCpfCnpj.sendKeys("cnpj");
		adicionarClientePO.selectDia.sendKeys("trinta");
		adicionarClientePO.selectMes.sendKeys("outubro e novembro");
		adicionarClientePO.inputAno.sendKeys("mil novecentos e oitenta e cinco");
		adicionarClientePO.inputIdentidade.sendKeys("inscricao estadual");

		Assert.assertEquals("", adicionarClientePO.selectDia.getAttribute("value"));
		Assert.assertEquals("", adicionarClientePO.selectMes.getAttribute("value"));
		Assert.assertEquals("", adicionarClientePO.inputAno.getAttribute("value"));
		Assert.assertEquals("123 de Oliveira 4", adicionarClientePO.inputNome.getAttribute("value"));
		Assert.assertEquals("123deoliveira4", adicionarClientePO.inputEmail.getAttribute("value"));
		Assert.assertEquals("(", adicionarClientePO.inputTelefone.getAttribute("value"));
		Assert.assertEquals("(", adicionarClientePO.inputCelular.getAttribute("value"));
		Assert.assertEquals("12334566851515   49165156818161 %$#@!*&",
				adicionarClientePO.inputEndereco.getAttribute("value"));
		Assert.assertEquals("", adicionarClientePO.inputCpfCnpj.getAttribute("value"));
		Assert.assertEquals("inscricao estad", adicionarClientePO.inputIdentidade.getAttribute("value"));
		Assert.assertEquals("", adicionarClientePO.inputCep.getAttribute("value"));
		Assert.assertEquals("123 de Oli", adicionarClientePO.inputNumeroDoEndereco.getAttribute("value"));
		Assert.assertEquals("123 de Oliveira 4", adicionarClientePO.inputComplemento.getAttribute("value"));
		Assert.assertEquals("12334566851515   49165156818161 %$#@!*&",
				adicionarClientePO.inputBairro.getAttribute("value"));

		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputCpfCnpj));
		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputEmail));
		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputTelefone));

		Assert.assertEquals("CNPJ é obrigatório", adicionarClientePO.mensagemCpfCnpjObrigatorio.getText());
		Assert.assertEquals("E-mail é inválido", adicionarClientePO.mensagemEmailObrigatorio.getText());
		Assert.assertEquals("Telefone obrigatório", adicionarClientePO.mensagemTelefoneObrigatorio.getText());

		Assert.assertFalse(adicionarClientePO.botaoConfirmar.isEnabled());

	//	adicionarClientePO.botaoCancelar.click();
	}

	/** Testar a semantica dos campos para cliente pessoa física. */
	@Test
	public void T0002_deve_verificar_semantica_dos_campos_adicionar_cliente_pessoa_fisica() {
		adicionarClientePO.selecionarTipoDeCliente(tipoCliente.FISICA);

		adicionarClientePO.inputNome.sendKeys("123 de Oliveira 4");
		adicionarClientePO.inputEmail.sendKeys("123deoliveira4");
		adicionarClientePO.inputTelefone.sendKeys("telefone");
		adicionarClientePO.inputCelular.sendKeys("celular");
		adicionarClientePO.inputCep.sendKeys("cep");
		adicionarClientePO.inputEndereco.sendKeys("12334566851515   49165156818161 %$#@!*&");
		adicionarClientePO.inputBairro.sendKeys("12334566851515   49165156818161 %$#@!*&");
		adicionarClientePO.inputNumeroDoEndereco.sendKeys("123 de Oliveira 4");
		adicionarClientePO.inputComplemento.sendKeys("123 de Oliveira 4");
		adicionarClientePO.inputCpfCnpj.sendKeys("cpf");
		adicionarClientePO.selectDia.sendKeys("trinta");
		adicionarClientePO.selectMes.sendKeys("outubro e novembro");
		adicionarClientePO.inputAno.sendKeys("mil novecentos e oitenta e cinco");
		adicionarClientePO.inputIdentidade.sendKeys("inscricao estadual");

		Assert.assertEquals("", adicionarClientePO.selectDia.getAttribute("value"));
		Assert.assertEquals("", adicionarClientePO.selectMes.getAttribute("value"));
		Assert.assertEquals("", adicionarClientePO.inputAno.getAttribute("value"));
		Assert.assertEquals("123 de Oliveira 4", adicionarClientePO.inputNome.getAttribute("value"));
		Assert.assertEquals("123deoliveira4", adicionarClientePO.inputEmail.getAttribute("value"));
		Assert.assertEquals("(", adicionarClientePO.inputTelefone.getAttribute("value"));
		Assert.assertEquals("(", adicionarClientePO.inputCelular.getAttribute("value"));
		Assert.assertEquals("12334566851515   49165156818161 %$#@!*&",
				adicionarClientePO.inputEndereco.getAttribute("value"));
		Assert.assertEquals("", adicionarClientePO.inputCpfCnpj.getAttribute("value"));
		Assert.assertEquals("inscricao estad", adicionarClientePO.inputIdentidade.getAttribute("value"));
		Assert.assertEquals("", adicionarClientePO.inputCep.getAttribute("value"));
		Assert.assertEquals("123 de Oli", adicionarClientePO.inputNumeroDoEndereco.getAttribute("value"));
		Assert.assertEquals("123 de Oliveira 4", adicionarClientePO.inputComplemento.getAttribute("value"));
		Assert.assertEquals("12334566851515   49165156818161 %$#@!*&",
				adicionarClientePO.inputBairro.getAttribute("value"));

		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputCpfCnpj));
		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputEmail));
		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputTelefone));

		Assert.assertEquals("CPF é obrigatório", adicionarClientePO.mensagemCpfCnpjObrigatorio.getText());
		Assert.assertEquals("E-mail é inválido", adicionarClientePO.mensagemEmailObrigatorio.getText());
		Assert.assertEquals("Telefone obrigatório", adicionarClientePO.mensagemTelefoneObrigatorio.getText());

		Assert.assertFalse(adicionarClientePO.botaoConfirmar.isEnabled());

	//	adicionarClientePO.botaoCancelar.click();
	}

	/** Testar a semantica dos campos para cliente estrangeiro. */
	@Test
	public void T0003_deve_verificar_semantica_dos_campos_adicionar_cliente_estrangeiro() {
		adicionarClientePO.selecionarTipoDeCliente(tipoCliente.ESTRANGEIRO);

		adicionarClientePO.inputNome.sendKeys("123 de Oliveira 4");
		adicionarClientePO.inputEmail.sendKeys("123deoliveira4");
		adicionarClientePO.inputTelefone.sendKeys("telefone");
		adicionarClientePO.inputCelular.sendKeys("celular");
		adicionarClientePO.inputCep.sendKeys("cep");
		adicionarClientePO.inputEndereco.sendKeys("12334566851515   49165156818161 %$#@!*&");
		adicionarClientePO.inputBairro.sendKeys("12334566851515   49165156818161 %$#@!*&");
		adicionarClientePO.inputNumeroDoEndereco.sendKeys("123 de Oliveira 4");
		adicionarClientePO.inputComplemento.sendKeys("123 de Oliveira 4");
		adicionarClientePO.inputCpfCnpj.sendKeys("cpf");
		adicionarClientePO.selectDia.sendKeys("trinta");
		adicionarClientePO.selectMes.sendKeys("outubro e novembro");
		adicionarClientePO.inputAno.sendKeys("mil novecentos e oitenta e cinco");
		adicionarClientePO.inputIdentidade.sendKeys("inscricao estadual");

		Assert.assertEquals("", adicionarClientePO.selectDia.getAttribute("value"));
		Assert.assertEquals("", adicionarClientePO.selectMes.getAttribute("value"));
		Assert.assertEquals("", adicionarClientePO.inputAno.getAttribute("value"));
		Assert.assertEquals("123 de Oliveira 4", adicionarClientePO.inputNome.getAttribute("value"));
		Assert.assertEquals("123deoliveira4", adicionarClientePO.inputEmail.getAttribute("value"));
		Assert.assertEquals("(", adicionarClientePO.inputTelefone.getAttribute("value"));
		Assert.assertEquals("(", adicionarClientePO.inputCelular.getAttribute("value"));
		Assert.assertEquals("12334566851515   49165156818161 %$#@!*&",
				adicionarClientePO.inputEndereco.getAttribute("value"));
		Assert.assertEquals("cpf", adicionarClientePO.inputCpfCnpj.getAttribute("value"));
		Assert.assertEquals("inscricao estad", adicionarClientePO.inputIdentidade.getAttribute("value"));
		Assert.assertEquals("", adicionarClientePO.inputCep.getAttribute("value"));
		Assert.assertEquals("123 de Oli", adicionarClientePO.inputNumeroDoEndereco.getAttribute("value"));
		Assert.assertEquals("123 de Oliveira 4", adicionarClientePO.inputComplemento.getAttribute("value"));
		Assert.assertEquals("12334566851515   49165156818161 %$#@!*&",
				adicionarClientePO.inputBairro.getAttribute("value"));

		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputEmail));
		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputTelefone));

		Assert.assertEquals("E-mail é inválido", adicionarClientePO.mensagemEmailObrigatorio.getText());
		Assert.assertEquals("Telefone obrigatório", adicionarClientePO.mensagemTelefoneObrigatorio.getText());
		Assert.assertEquals("Erro\nDocumento inválido", adicionarClientePO.obterTextoMensagemFlutuante(driver, 3));

		adicionarClientePO.fecharMensagensFlutuantes();

		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputTelefone));
		Assert.assertFalse(adicionarClientePO.botaoConfirmar.isEnabled());

	//	adicionarClientePO.botaoCancelar.click();
	}

	/** Testar o limite de caracteres dos campos para cliente pessoa juridica. */
	@Test
	public void T0004_deve_testar_limite_de_caracteres_dos_campos_cliente_pessoa_juridica() {
		adicionarClientePO.selecionarTipoDeCliente(tipoCliente.JURIRICA);

		adicionarClientePO.inputNome.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputEmail.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputTelefone.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputCelular.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputCep.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputEndereco.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputBairro.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputNumeroDoEndereco.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputComplemento.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputCpfCnpj.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.selectDia.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.selectMes.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputAno.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputIdentidade.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");

		Assert.assertEquals(
				"testar limites de caracteres 99999999999999999999999999999999999999999",
				adicionarClientePO.inputNome.getAttribute("value"));
		Assert.assertEquals(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999",
				adicionarClientePO.inputEmail.getAttribute("value"));
		Assert.assertEquals(
				"(99) 9999-9999",
				adicionarClientePO.inputTelefone.getAttribute("value"));
		Assert.assertEquals(
				"(99) 99999-9999",
				adicionarClientePO.inputCelular.getAttribute("value"));
		Assert.assertEquals(
				"testar limites ",
				adicionarClientePO.inputIdentidade.getAttribute("value"));
		Assert.assertEquals(
				"99-999-999/9999-99",
				adicionarClientePO.inputCpfCnpj.getAttribute("value"));
		Assert.assertEquals(
				"99999-999",
				adicionarClientePO.inputCep.getAttribute("value"));
		Assert.assertEquals(
				"testar lim",
				adicionarClientePO.inputNumeroDoEndereco.getAttribute("value"));
		Assert.assertEquals(
				"testar limites de caracteres 9",
				adicionarClientePO.inputComplemento.getAttribute("value"));
		Assert.assertEquals(
				"testar limites de caracteres 9999999999999999999999999999999",
				adicionarClientePO.inputBairro.getAttribute("value"));
		Assert.assertEquals(
				"", adicionarClientePO.selectDia.getAttribute("value"));
		Assert.assertEquals(
				"", adicionarClientePO.selectMes.getAttribute("value"));
		Assert.assertEquals(
				"9999", adicionarClientePO.inputAno.getAttribute("value"));
		Assert.assertEquals(
				"E-mail é inválido", adicionarClientePO.mensagemEmailObrigatorio.getText());
		Assert.assertEquals(
				"Erro\nCNPJ inválido", adicionarClientePO.obterTextoMensagemFlutuante(driver, 3));

		adicionarClientePO.fecharMensagensFlutuantes();

		Assert.assertFalse(adicionarClientePO.botaoConfirmar.isEnabled());

	//	adicionarClientePO.botaoCancelar.click();
	}

	/** Testar o limite de caracteres dos campos para cliente pessoa física. */
	@Test
	public void T0005_deve_testar_limite_de_caracteres_dos_campos_cliente_pessoa_fisica() {
		adicionarClientePO.selecionarTipoDeCliente(tipoCliente.FISICA);

		adicionarClientePO.inputNome.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputEmail.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputTelefone.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputCelular.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputCep.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputEndereco.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputBairro.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputNumeroDoEndereco.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputComplemento.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputCpfCnpj.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.selectDia.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.selectMes.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputAno.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputIdentidade.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");

		Assert.assertEquals(
				"testar limites de caracteres 99999999999999999999999999999999999999999",
				adicionarClientePO.inputNome.getAttribute("value"));
		Assert.assertEquals(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999",
				adicionarClientePO.inputEmail.getAttribute("value"));
		Assert.assertEquals(
				"(99) 9999-9999",
				adicionarClientePO.inputTelefone.getAttribute("value"));
		Assert.assertEquals(
				"(99) 99999-9999",
				adicionarClientePO.inputCelular.getAttribute("value"));
		Assert.assertEquals(
				"testar limites ",
				adicionarClientePO.inputIdentidade.getAttribute("value"));
		Assert.assertEquals(
				"999-999-999/99",
				adicionarClientePO.inputCpfCnpj.getAttribute("value"));
		Assert.assertEquals(
				"99999-999",
				adicionarClientePO.inputCep.getAttribute("value"));
		Assert.assertEquals(
				"testar lim",
				adicionarClientePO.inputNumeroDoEndereco.getAttribute("value"));
		Assert.assertEquals(
				"testar limites de caracteres 9",
				adicionarClientePO.inputComplemento.getAttribute("value"));
		Assert.assertEquals(
				"testar limites de caracteres 9999999999999999999999999999999",
				adicionarClientePO.inputBairro.getAttribute("value"));
		Assert.assertEquals(
				"", adicionarClientePO.selectDia.getAttribute("value"));
		Assert.assertEquals(
				"", adicionarClientePO.selectMes.getAttribute("value"));
		Assert.assertEquals(
				"9999", adicionarClientePO.inputAno.getAttribute("value"));
		Assert.assertEquals(
				"E-mail é inválido", adicionarClientePO.mensagemEmailObrigatorio.getText());
		Assert.assertEquals(
				"Erro\nCPF inválido", adicionarClientePO.obterTextoMensagemFlutuante(driver, 3));

		adicionarClientePO.fecharMensagensFlutuantes();

		Assert.assertFalse(adicionarClientePO.botaoConfirmar.isEnabled());

	//	adicionarClientePO.botaoCancelar.click();
	}

	/** Testar o limite de caracteres dos campos para cliente estrangeiro. */
	@Test
	public void T0006_deve_testar_limite_de_caracteres_dos_campos_cliente_estrangeiro() {
		adicionarClientePO.selecionarTipoDeCliente(tipoCliente.ESTRANGEIRO);

		adicionarClientePO.inputNome.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputEmail.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputTelefone.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputCelular.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputCep.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputEndereco.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputBairro.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputNumeroDoEndereco.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputComplemento.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputCpfCnpj.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.selectDia.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.selectMes.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputAno.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		adicionarClientePO.inputIdentidade.sendKeys(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");

		Assert.assertEquals(
				"testar limites de caracteres 99999999999999999999999999999999999999999",
				adicionarClientePO.inputNome.getAttribute("value"));
		Assert.assertEquals(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999",
				adicionarClientePO.inputEmail.getAttribute("value"));
		Assert.assertEquals(
				"(99) 9999-9999",
				adicionarClientePO.inputTelefone.getAttribute("value"));
		Assert.assertEquals(
				"(99) 99999-9999",
				adicionarClientePO.inputCelular.getAttribute("value"));
		Assert.assertEquals(
				"testar limites ",
				adicionarClientePO.inputIdentidade.getAttribute("value"));
		Assert.assertEquals(
				"testar limites de caracteres 9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999",
				adicionarClientePO.inputCpfCnpj.getAttribute("value"));
		Assert.assertEquals(
				"99999-999",
				adicionarClientePO.inputCep.getAttribute("value"));
		Assert.assertEquals(
				"testar lim",
				adicionarClientePO.inputNumeroDoEndereco.getAttribute("value"));
		Assert.assertEquals(
				"testar limites de caracteres 9",
				adicionarClientePO.inputComplemento.getAttribute("value"));
		Assert.assertEquals(
				"testar limites de caracteres 9999999999999999999999999999999",
				adicionarClientePO.inputBairro.getAttribute("value"));
		Assert.assertEquals(
				"", adicionarClientePO.selectDia.getAttribute("value"));
		Assert.assertEquals(
				"", adicionarClientePO.selectMes.getAttribute("value"));
		Assert.assertEquals(
				"9999", adicionarClientePO.inputAno.getAttribute("value"));
		Assert.assertEquals(
				"E-mail é inválido", adicionarClientePO.mensagemEmailObrigatorio.getText());
		Assert.assertEquals(
				"Erro\nDocumento inválido", adicionarClientePO.obterTextoMensagemFlutuante(driver, 3));

		adicionarClientePO.fecharMensagensFlutuantes();

		Assert.assertFalse(adicionarClientePO.botaoConfirmar.isEnabled());

	//	adicionarClientePO.botaoCancelar.click();
	}

	/** Testar campos obrigatorios para cliente pessoa juridica. */
	@Test
	public void T0007_deve_verificar_campos_obrigatorios_pessoa_juridica() {
		adicionarClientePO.selecionarTipoDeCliente(tipoCliente.JURIRICA);

		adicionarClientePO.inputCpfCnpj.click();
		adicionarClientePO.inputTelefone.click();
		adicionarClientePO.inputNome.click();

		Assert.assertEquals("CNPJ é obrigatório", adicionarClientePO.mensagemCpfCnpjObrigatorio.getText());
		Assert.assertEquals("Telefone obrigatório", adicionarClientePO.mensagemTelefoneObrigatorio.getText());

		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputCpfCnpj));
		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputTelefone));

		Assert.assertFalse(adicionarClientePO.botaoConfirmar.isEnabled());

	//	adicionarClientePO.botaoCancelar.click();
	}

	/** Testar os campos obrigatorios para cliente pessoa fisica. */
	@Test
	public void T0008_deve_verificar_campos_obrigatorios_pessoa_fisica() {
		adicionarClientePO.selecionarTipoDeCliente(tipoCliente.FISICA);

		adicionarClientePO.inputCpfCnpj.click();
		adicionarClientePO.inputTelefone.click();
		adicionarClientePO.inputNome.click();

		Assert.assertEquals("CPF é obrigatório", adicionarClientePO.mensagemCpfCnpjObrigatorio.getText());
		Assert.assertEquals("Telefone obrigatório", adicionarClientePO.mensagemTelefoneObrigatorio.getText());

		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputCpfCnpj));
		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputTelefone));

		Assert.assertFalse(adicionarClientePO.botaoConfirmar.isEnabled());

	//	adicionarClientePO.botaoCancelar.click();
	}

	/** Testar os campos obrigatorios para cliente estrageiro. */
	@Test
	public void T0009_deve_verificar_campos_obrigatorios_estrangeiro() {
		adicionarClientePO.selecionarTipoDeCliente(tipoCliente.ESTRANGEIRO);

		adicionarClientePO.inputCpfCnpj.click();
		adicionarClientePO.inputTelefone.click();
		adicionarClientePO.inputNome.click();

		Assert.assertEquals("Documento é obrigatório", adicionarClientePO.mensagemCpfCnpjObrigatorio.getText());
		Assert.assertEquals("Telefone obrigatório", adicionarClientePO.mensagemTelefoneObrigatorio.getText());

		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputCpfCnpj));
		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputTelefone));

		Assert.assertFalse(adicionarClientePO.botaoConfirmar.isEnabled());

	//	adicionarClientePO.botaoCancelar.click();
	}

	/** Testar as validacoes para o campo e-mail. */
	@Test
	public void T0010_deve_verificar_validacoes_campo_email() {
		adicionarClientePO.selecionarTipoDeCliente(tipoCliente.FISICA);

		adicionarClientePO.inputEmail.sendKeys("testar email@espaco.com");
		
		Assert.assertEquals("E-mail é inválido", adicionarClientePO.mensagemEmailObrigatorio.getText());
		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputEmail));

		adicionarClientePO.limparCampoInput(adicionarClientePO.inputEmail);
		adicionarClientePO.inputEmail.sendKeys(".testeemailcomecandocomponto");

		Assert.assertEquals("E-mail é inválido", adicionarClientePO.mensagemEmailObrigatorio.getText());
		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputEmail));

		adicionarClientePO.limparCampoInput(adicionarClientePO.inputEmail);
		adicionarClientePO.inputEmail.sendKeys("testeloginsemarroba.com");

		Assert.assertEquals("E-mail é inválido", adicionarClientePO.mensagemEmailObrigatorio.getText());
		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputEmail));

		adicionarClientePO.limparCampoInput(adicionarClientePO.inputEmail);
		adicionarClientePO.inputEmail.sendKeys("TESTELETRAMAIUSCULA@GMAIL.COM");

		Assert.assertEquals("E-mail é inválido", adicionarClientePO.mensagemEmailObrigatorio.getText());
		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputEmail));

		adicionarClientePO.limparCampoInput(adicionarClientePO.inputEmail);
		adicionarClientePO.inputEmail.sendKeys("@testelogininiciocomarroba.com");

		Assert.assertEquals("E-mail é inválido", adicionarClientePO.mensagemEmailObrigatorio.getText());
		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputEmail));

		adicionarClientePO.limparCampoInput(adicionarClientePO.inputEmail);
		adicionarClientePO.inputEmail.sendKeys("logincomacentuação@gmail.com");

		Assert.assertEquals("E-mail é inválido", adicionarClientePO.mensagemEmailObrigatorio.getText());
		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputEmail));

		adicionarClientePO.limparCampoInput(adicionarClientePO.inputEmail);
		adicionarClientePO.inputEmail.sendKeys("loginterminocomponto@gmail.com.");

		Assert.assertEquals("E-mail é inválido", adicionarClientePO.mensagemEmailObrigatorio.getText());
		Assert.assertTrue(adicionarClientePO.verificarCampoObrigatorio(adicionarClientePO.inputEmail));
	}
	// #endregion
}
