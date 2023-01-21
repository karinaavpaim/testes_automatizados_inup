package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import builder.AdicionarTransportadoraBuilder;
import page.transportadoras.AdicionarTransportadoraPO;
import page.transportadoras.AdicionarTransportadoraPO.tipoTransportadora;
import page.transportadoras.TransportadorasPO;
import page.transportadoras.TransportadorasPO.tipoAcao;

/** Classe de testes para a tela de transportadoras */
public class TransportadorasTest extends BaseTest{
	
	//#region Regiao dos atributos
    private static AdicionarTransportadoraPO adicionarTransportadoraPO;
	private static TransportadorasPO transportadorasPO;
	private static AdicionarTransportadoraBuilder transportadoraBuilder;
	private static String nomeTransportadoraPessoaFisica = "JULIO DE LIMA";
	private static String nomeTransportadoraPessoaJuridica = "TRANSPORTES EXPRESS";
	private static String itensPorPagina = "50";
    //#endregion

	/** Metodo para inciar os testes desta classe. */
	@BeforeClass
	public static void iniciarTestes(){
		transportadorasPO = new TransportadorasPO(driver);
		adicionarTransportadoraPO = new AdicionarTransportadoraPO(driver);
		transportadoraBuilder = new AdicionarTransportadoraBuilder(adicionarTransportadoraPO);

		transportadorasPO.navegarParaTransportadoras();
		transportadorasPO.aguardarVisibilidadeDoElemento(driver, 5, transportadorasPO.botaoAdicionarTransportadora);
	}

	/** Metodo para inciar os testes desta classe. */
	//@AfterClass
	public static void excluirTransportadorasCriadas(){
		transportadorasPO.pesquisar();
		transportadorasPO.selecionarItensPorPagina(itensPorPagina);

		transportadorasPO.localizarTransportadoraERealizarAcao(tipoAcao.EXCLUIR, nomeTransportadoraPessoaFisica);
		transportadorasPO.botaoConfirmarMensagemFlutuante.click();
		transportadorasPO.conteudoMensagemFlutuante.click();

		transportadorasPO.localizarTransportadoraERealizarAcao(tipoAcao.EXCLUIR, nomeTransportadoraPessoaJuridica);
		transportadorasPO.botaoConfirmarMensagemFlutuante.click();
		transportadorasPO.conteudoMensagemFlutuante.click();

		transportadorasPO.localizarTransportadoraERealizarAcao(tipoAcao.EXCLUIR, "TESTE EDICAO");
		transportadorasPO.botaoConfirmarMensagemFlutuante.click();
		transportadorasPO.conteudoMensagemFlutuante.click();
		transportadorasPO.conteudoMensagemFlutuante.click();
		transportadorasPO.aguardarInvisibilidadeDoElemento(driver, 5, transportadorasPO.conteudoMensagemFlutuante);
	}

	/** Metodo para inciar os testes desta classe. */
	@Before
	public void atualizarPagina(){
		transportadorasPO.atualizarPagina(5);
	}

	//#region Regiao dos testes.

	/** Testa a inclusao de uma nova transportadora pessoa fisica. */
	@Ignore ("Aguardando IN-9234")
	@Test
	public void T0001_deve_adicionar_transportadora_pessoa_fisica() {
		transportadorasPO.botaoAdicionarTransportadora.click();
		
		transportadoraBuilder
			.comNome(nomeTransportadoraPessoaFisica)
			.adicionarTransportadora(tipoTransportadora.FISICA);
		
		adicionarTransportadoraPO.botaoSalvar.click();

		Assert.assertEquals("Sucesso\nTransportadora Inserida com Sucesso.", adicionarTransportadoraPO.obterTextoMensagemFlutuante(driver, 3));

		transportadorasPO.atualizarPagina(3);

		transportadorasPO.pesquisar();
		transportadorasPO.selecionarItensPorPagina(itensPorPagina);
	
		Assert.assertTrue(transportadorasPO.verificarNomeTransportadoraNaTabela(nomeTransportadoraPessoaFisica));
	}

	/** Testa a inclusao de uma nova transportadora pessoa juridica. */
	@Ignore ("Aguardando IN-9234")
	@Test
	public void T0002_deve_adicionar_transportadora_pessoa_juridica() {
		transportadorasPO.pesquisar();
		
		transportadorasPO.botaoAdicionarTransportadora.click();
		
		transportadoraBuilder
			.comNome(nomeTransportadoraPessoaJuridica)
			.adicionarTransportadora(tipoTransportadora.JURIRICA);
		
		adicionarTransportadoraPO.botaoSalvar.click();

		Assert.assertEquals("Sucesso\nTransportadora Inserida com Sucesso.", adicionarTransportadoraPO.obterTextoMensagemFlutuante(driver, 7));
		
		transportadorasPO.atualizarPagina(3);

		transportadorasPO.pesquisar();
		transportadorasPO.selecionarItensPorPagina(itensPorPagina);

		Assert.assertTrue(transportadorasPO.verificarNomeTransportadoraNaTabela(nomeTransportadoraPessoaJuridica));
	}

	/** Testar a edicao de uma transportadora. */
	@Ignore ("Aguardando IN-9234")
	@Test
	public void T0003_deve_testar_a_edicao_de_transportadora() {
		transportadorasPO.botaoAdicionarTransportadora.click();
		
		transportadoraBuilder
			.comNome("TESTE EDICAO")
			.adicionarTransportadora(tipoTransportadora.FISICA);
		
		adicionarTransportadoraPO.botaoSalvar.click();
	
		Assert.assertEquals("Sucesso\nTransportadora Inserida com Sucesso.", adicionarTransportadoraPO.obterTextoMensagemFlutuante(driver, 3));
		
		transportadorasPO.conteudoMensagemFlutuante.click();

		transportadorasPO.pesquisar();
		transportadorasPO.avancarParaUltimaPagina();
		
		transportadorasPO.localizarTransportadoraERealizarAcao(tipoAcao.EDITAR, "TESTE EDICAO");

		adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputTelefone);
		adicionarTransportadoraPO.inputTelefone.sendKeys("9998515266");

		Assert.assertEquals("(99) 9851-5266", adicionarTransportadoraPO.inputTelefone.getAttribute("value"));

		adicionarTransportadoraPO.botaoSalvar.click();
		
		Assert.assertEquals("Sucesso\nTransportadora Inserida com Sucesso.", adicionarTransportadoraPO.obterTextoMensagemFlutuante(driver, 5));
		
		transportadorasPO.conteudoMensagemFlutuante.click();

		transportadorasPO.pesquisar();
		transportadorasPO.avancarParaUltimaPagina();
	
		Assert.assertTrue(transportadorasPO.verificarNomeTransportadoraNaTabela("TESTE EDICAO"));
	}

	/** Testar a exclusao de uma transportadora. */
	@Ignore ("Aguardando IN-9234")
	@Test
	public void T0004_deve_testar_a_exclusao_de_transportadora() {
		transportadorasPO.botaoAdicionarTransportadora.click();
		
		transportadoraBuilder
			.comNome("TESTE EXCLUSAO")
			.adicionarTransportadora(tipoTransportadora.JURIRICA);
		
		adicionarTransportadoraPO.botaoSalvar.click();
	
		Assert.assertEquals("Sucesso\nTransportadora Inserida com Sucesso.", adicionarTransportadoraPO.obterTextoMensagemFlutuante(driver, 5));
	
		transportadorasPO.pesquisar();
		transportadorasPO.avancarParaUltimaPagina();

		transportadorasPO.localizarTransportadoraERealizarAcao(tipoAcao.EXCLUIR, "TESTE EXCLUSAO");
		transportadorasPO.botaoConfirmarMensagemFlutuante.click();

		Assert.assertEquals("Sucesso\nTransportadora excluída com sucesso.", adicionarTransportadoraPO.obterTextoMensagemFlutuante(driver, 5));

		transportadorasPO.conteudoMensagemFlutuante.click();

		Assert.assertFalse(transportadorasPO.verificarNomeTransportadoraNaTabela("TESTE EXCLUSAO"));
	}

	/** Testar a pesquisa de uma transportadora ativa pelo nome, razao social e CNPJ. */
	@Test
	public void T0005_deve_pesquisar_por_transportadora_ativa() {
		transportadorasPO.pesquisarTransportadora("GLAMOUR LONG");
	
		Assert.assertTrue(transportadorasPO.verificarNomeTransportadoraNaTabela("GLAMOUR LONG"));

		transportadorasPO.pesquisarTransportadora("763-217-460/00");
	
		Assert.assertTrue(transportadorasPO.verificarNomeTransportadoraNaTabela("763-217-460/00"));
		
		transportadorasPO.pesquisarTransportadora("glamour@gmail.com");
	
		Assert.assertTrue(transportadorasPO.verificarNomeTransportadoraNaTabela("glamour@gmail.com"));
	}

	/** Testar a pesquisa de uma transportadora inativa pelo nome, razao social e CNPJ. */
	@Test
	public void T0006_deve_pesquisar_por_transportadora_inativa() {
		transportadorasPO.checkboxInativo.click();

		transportadorasPO.pesquisarTransportadora("TESTE EXCLUIDA");
	
		Assert.assertTrue(transportadorasPO.verificarNomeTransportadoraNaTabela("TESTE EXCLUIDA"));

		transportadorasPO.pesquisarTransportadora("31144886000170");
	
		Assert.assertTrue(transportadorasPO.verificarNomeTransportadoraNaTabela("31144886000170"));
		
		transportadorasPO.pesquisarTransportadora("excluida@gmail.com");
	
		Assert.assertTrue(transportadorasPO.verificarNomeTransportadoraNaTabela("excluida@gmail.com"));
	}

	/** Testar a inativacao de uma transportadora e depois coloca-la ativa novamente. */
	@Ignore ("Aguardando IN-7229")
	@Test
	public void T0007_deve_testar_a_inativacao_e_ativacao_de_transportadora() {
		transportadorasPO.botaoAdicionarTransportadora.click();
		
		transportadoraBuilder
			.comNome("ATIVACAO")
			.adicionarTransportadora(tipoTransportadora.FISICA);
		
		adicionarTransportadoraPO.botaoSalvar.click();
	
		transportadorasPO.atualizarPagina(3);
		
		transportadorasPO.pesquisar();
		transportadorasPO.selecionarItensPorPagina(itensPorPagina);
		
		transportadorasPO.localizarTransportadoraERealizarAcao(tipoAcao.EXCLUIR, "ATIVACAO");
		
		transportadorasPO.checkboxInativo.click();
		transportadorasPO.pesquisarTransportadora("ATIVACAO");
		
		transportadorasPO.localizarTransportadoraERealizarAcao(tipoAcao.EDITAR, "ATIVACAO");

		adicionarTransportadoraPO.checkboxInativo.click();
		adicionarTransportadoraPO.botaoSalvar.click();

		transportadorasPO.atualizarPagina(3);
		transportadorasPO.pesquisarTransportadora("ATIVACAO");

		Assert.assertTrue(transportadorasPO.verificarNomeTransportadoraNaTabela("ATIVACAO"));
	}
	//#endregion
}
