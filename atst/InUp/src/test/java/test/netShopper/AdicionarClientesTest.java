package test.netShopper;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import builder.AdicionarClienteBuilder;
import page.AdicionarClientePO;
import page.AdicionarClientePO.tipoCliente;
import page.netShopper.NetShopperPO;
import test.BaseTest;

/** Classe de testes para adicionar clientes na tela do NetShopper. */
public class AdicionarClientesTest extends BaseTest{

	//#region Regiao dos atributos
	private static NetShopperPO netShopperPO;
	private static AdicionarClientePO adicionarClientePO;
	private static AdicionarClienteBuilder dadosClienteBuilder;
	private static String nomeClientePessoaFisica = "Vanessa Camargo";
	private static String nomeClientePessoaJuridica = "Flores Confeccoes Ltda";
	private static String nomeClientePessoaEstrangeiro = "John Smith Gardener";
	private static String nomeLoja = "NORTE SHOPPING";
	//#endregion

	/** Metodo para inciar os testes desta classe. */
	@BeforeClass
	public static void iniciarTeste(){
		netShopperPO = new NetShopperPO(driver);
		adicionarClientePO = new AdicionarClientePO(driver);
		dadosClienteBuilder = new AdicionarClienteBuilder(adicionarClientePO);

		netShopperPO.navegarParaNetshopper();
	}

	//#region Regiao dos testes.

	/** Teste para adicionar um cliente pessoa fisica. */
	@Test
	public void T0001_deve_adicionar_cliente_pessoa_fisica(){
		netShopperPO.atualizarPagina(10);

		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja, nomeLoja);

		netShopperPO.botaoAdicionarCliente.click();
		
		dadosClienteBuilder
			.comNome(nomeClientePessoaFisica)
			.preencherDadosDoNovoCliente(tipoCliente.FISICA);
		
		adicionarClientePO.botaoConfirmar.click();
		
		Assert.assertEquals("Cliente adicionado\n" + nomeClientePessoaFisica, netShopperPO.obterTextoMensagemFlutuante(driver, 3));
		netShopperPO.conteudoMensagemFlutuante.click();	
	}

	/** Teste para adicionar um cliente pessoa juridica. */
	@Test
	public void T0002_deve_adicionar_cliente_pessoa_juridica(){
		netShopperPO.atualizarPagina(10);

		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja, nomeLoja);

		netShopperPO.botaoAdicionarCliente.click();
		
		dadosClienteBuilder
			.comNome(nomeClientePessoaJuridica)
			.preencherDadosDoNovoCliente(tipoCliente.JURIRICA);
	
		adicionarClientePO.botaoConfirmar.click();

		Assert.assertEquals("Cliente adicionado\n" + nomeClientePessoaJuridica, netShopperPO.obterTextoMensagemFlutuante(driver, 3));
		netShopperPO.conteudoMensagemFlutuante.click();	
	}

	/** Teste para adicionar um cliente estrangeiro. */
	@Test
	public void T0003_deve_adicionar_cliente_estrangeiro(){
		netShopperPO.atualizarPagina(10);

		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja, nomeLoja);

		netShopperPO.botaoAdicionarCliente.click();
		
		dadosClienteBuilder
			.comNome(nomeClientePessoaEstrangeiro)
			.preencherDadosDoNovoCliente(tipoCliente.ESTRANGEIRO);
	
		adicionarClientePO.botaoConfirmar.click();

		Assert.assertEquals("Cliente adicionado\n" + nomeClientePessoaEstrangeiro, netShopperPO.obterTextoMensagemFlutuante(driver, 3));
		netShopperPO.conteudoMensagemFlutuante.click();	
	}

	/** Teste para editar informações de um cliente. */
	@Ignore ("IN-10232")
	@Test
	public void T0004_deve_alterar_informacoes_de_cliente(){
		netShopperPO.atualizarPagina(10);

		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "Lucas Padilha");
		Assert.assertEquals("LUCAS PADILHA", netShopperPO.inputClienteSelecionado.getAttribute("value"));
		
		netShopperPO.botaoEditarCliente.click();
		
		String nomeAntigo = adicionarClientePO.inputNome.getAttribute("value");
		String cpfAntigo = adicionarClientePO.inputCpfCnpj.getAttribute("value");
		String idAntigo = adicionarClientePO.inputIdentidade.getAttribute("value");
		String dataAntigo = adicionarClientePO.selectDia.getAttribute("value");
		String mesAntigo = adicionarClientePO.selectMes.getAttribute("value");
		String anoAntigo = adicionarClientePO.selectAno.getAttribute("value");
		String telefoneAntigo = adicionarClientePO.inputTelefone.getAttribute("value");
		String celularAntigo = adicionarClientePO.inputCelular.getAttribute("value");
		String cepAntigo = adicionarClientePO.inputCep.getAttribute("value");
		String enderecoAntigo = adicionarClientePO.inputEndereco.getAttribute("value");
		String numeroAntigo = adicionarClientePO.inputNumeroDoEndereco.getAttribute("value");
		String complementoAntigo = adicionarClientePO.inputComplemento.getAttribute("value");
		String bairroAntigo = adicionarClientePO.inputBairro.getAttribute("value");
		String estadoAntigo = adicionarClientePO.selectEstado.getAttribute("value");
		String cidadeAntigo = adicionarClientePO.selectCidade.getAttribute("value");
		String paisAntigo = adicionarClientePO.selectPais.getAttribute("value");

		netShopperPO.limparCampoInput(adicionarClientePO.inputNome);
		adicionarClientePO.inputNome.sendKeys("LUCAS PADILHA GONCALVES");

		netShopperPO.limparCampoInput(adicionarClientePO.inputCpfCnpj);
		adicionarClientePO.inputCpfCnpj.sendKeys("070.828.310-13");

		netShopperPO.limparCampoInput(adicionarClientePO.inputIdentidade);
		adicionarClientePO.inputIdentidade.sendKeys("66526089");

		netShopperPO.limparCampoInput(adicionarClientePO.inputTelefone);
		adicionarClientePO.inputTelefone.sendKeys("22980245");

		netShopperPO.limparCampoInput(adicionarClientePO.inputCelular);
		adicionarClientePO.inputCelular.sendKeys("2298024579");

		netShopperPO.limparCampoInput(adicionarClientePO.inputCep);
		adicionarClientePO.inputCep.sendKeys("21221260");
		adicionarClientePO.botaoBuscarCep.click();

		netShopperPO.limparCampoInput(adicionarClientePO.inputNumeroDoEndereco);
		adicionarClientePO.inputNumeroDoEndereco.sendKeys("100");

		netShopperPO.limparCampoInput(adicionarClientePO.inputComplemento);
		adicionarClientePO.inputComplemento.sendKeys("apto 305");

		netShopperPO.limparCampoInput(adicionarClientePO.inputAno);
		adicionarClientePO.inputAno.sendKeys("1980");

		dadosClienteBuilder.comDia("12");
		dadosClienteBuilder.comMes("12");
		dadosClienteBuilder.comPais("BRASIL");

		String nomeNovo = adicionarClientePO.inputNome.getAttribute("value");
		String cpfNovo = adicionarClientePO.inputCpfCnpj.getAttribute("value");
		String idNovo = adicionarClientePO.inputIdentidade.getAttribute("value");
		String dataNovo = adicionarClientePO.selectDia.getAttribute("value");
		String mesNovo = adicionarClientePO.selectMes.getAttribute("value");
		String anoNovo = adicionarClientePO.selectAno.getAttribute("value");
		String telefoneNovo = adicionarClientePO.inputTelefone.getAttribute("value");
		String celularNovo = adicionarClientePO.inputCelular.getAttribute("value");
		String cepNovo = adicionarClientePO.inputCep.getAttribute("value");
		String enderecoNovo = adicionarClientePO.inputEndereco.getAttribute("value");
		String numeroNovo = adicionarClientePO.inputNumeroDoEndereco.getAttribute("value");
		String complementoNovo = adicionarClientePO.inputComplemento.getAttribute("value");
		String bairroNovo = adicionarClientePO.inputBairro.getAttribute("value");
		String estadoNovo = adicionarClientePO.selectEstado.getAttribute("value");
		String cidadeNovo = adicionarClientePO.selectCidade.getAttribute("value");
		String paisNovo = adicionarClientePO.selectPais.getAttribute("value");
		
		adicionarClientePO.botaoConfirmar.click();

		Assert.assertEquals("Cliente adicionado\n" + nomeNovo, netShopperPO.obterTextoMensagemFlutuante(driver, 5));
		
		netShopperPO.atualizarPagina(5);
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "Lucas Padilha");

		netShopperPO.botaoEditarCliente.click();

		 Assert.assertNotEquals(nomeAntigo, nomeNovo);
		 Assert.assertNotEquals(cpfAntigo, cpfNovo);
		 Assert.assertNotEquals(idAntigo, idNovo);
		 Assert.assertNotEquals(dataAntigo, dataNovo);
		 Assert.assertNotEquals(mesAntigo, mesNovo);
		 Assert.assertNotEquals(anoAntigo, anoNovo);
		 Assert.assertNotEquals(telefoneAntigo, telefoneNovo);
		 Assert.assertNotEquals(celularAntigo, celularNovo);
		 Assert.assertNotEquals(cepAntigo, cepNovo);
		 Assert.assertNotEquals(enderecoAntigo, enderecoNovo);
		 Assert.assertNotEquals(numeroAntigo, numeroNovo);
		 Assert.assertNotEquals(complementoAntigo, complementoNovo);
		 Assert.assertNotEquals(bairroAntigo, bairroNovo);
		 Assert.assertNotEquals(estadoAntigo, estadoNovo);
		 Assert.assertNotEquals(cidadeAntigo, cidadeNovo);
		 Assert.assertNotEquals(paisAntigo, paisNovo);

		netShopperPO.limparCampoInput(adicionarClientePO.inputNome);
		adicionarClientePO.inputNome.sendKeys(nomeAntigo);

		netShopperPO.limparCampoInput(adicionarClientePO.inputCpfCnpj);
		adicionarClientePO.inputCpfCnpj.sendKeys(cpfAntigo);

		netShopperPO.limparCampoInput(adicionarClientePO.inputIdentidade);
		adicionarClientePO.inputIdentidade.sendKeys(idAntigo);

		netShopperPO.limparCampoInput(adicionarClientePO.inputTelefone);
		adicionarClientePO.inputTelefone.sendKeys(telefoneAntigo);

		netShopperPO.limparCampoInput(adicionarClientePO.inputCelular);
		adicionarClientePO.inputCelular.sendKeys(celularAntigo);

		netShopperPO.limparCampoInput(adicionarClientePO.inputCep);
		adicionarClientePO.inputCep.sendKeys(cepAntigo);
		adicionarClientePO.botaoBuscarCep.click();

		netShopperPO.limparCampoInput(adicionarClientePO.inputNumeroDoEndereco);
		adicionarClientePO.inputNumeroDoEndereco.sendKeys(numeroAntigo);

		netShopperPO.limparCampoInput(adicionarClientePO.inputComplemento);
		adicionarClientePO.inputComplemento.sendKeys(complementoAntigo);

		netShopperPO.limparCampoInput(adicionarClientePO.inputAno);
		adicionarClientePO.inputAno.sendKeys(anoAntigo);

		dadosClienteBuilder.comDia(dataAntigo);
		dadosClienteBuilder.comMes(mesAntigo);
		dadosClienteBuilder.comPais(paisAntigo);
	}
	//#endregion
}