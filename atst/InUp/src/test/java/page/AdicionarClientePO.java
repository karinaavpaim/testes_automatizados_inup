package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/** Page Object da tela de adicionar clientes dentro de NetShopper e CashNet. */
public class AdicionarClientePO extends BasePO {

	// #region Regiao dos elementos

	/** Enum para definir o tipo de cliente que sera adicionado. */
	public enum tipoCliente { FISICA, JURIRICA, ESTRANGEIRO; }

	/** Elemento para selecionar o tipo de cliente: pessoa fisica, pessoa juridica ou estrageiro. */
	@FindBy(id = "tipo")
	public WebElement selectTipo;

	/** Elemento para inserir o nome do cliente. */
	@FindBy(id = "nome")
	public WebElement inputNome;

	/** Elemento para inserir o cpf ou cnpj do cliente. */
	@FindBy(id = "documento")
	public WebElement inputCpfCnpj;

	/** Elemento para inserir a identidade do cliente. */
	@FindBy(id = "identidade")
	public WebElement inputIdentidade;

	/** Elemento para inserir o email do cliente. */
	@FindBy(id = "email")
	public WebElement inputEmail;

	/** Elemento para inserir o dia de nascimento ou de fundacao do cliente. */
	@FindBy(id = "dia")
	public WebElement selectDia;

	/** Elemento para inserir o mes de nascimento ou de fundacao do cliente. */
	@FindBy(id = "mes")
	public WebElement selectMes;

	/** Elemento para inserir o ano de nascimento ou de fundacao do cliente. */
	@FindBy(id = "ano")
	public WebElement inputAno;

	/** Elemento para inserir o telefone do cliente. */
	@FindBy(id = "telefone")
	public WebElement inputTelefone;

	/** Elemento para inserir o celular do cliente. */
	@FindBy(id = "celular")
	public WebElement inputCelular;

	/** Elemento para inserir o CEP do cliente. */
	@FindBy(id = "cep")
	public WebElement inputCep;

	/** Elemento para inserir o endereco do cliente. */
	@FindBy(id = "endereco")
	public WebElement inputEndereco;

	/** Elemento para inserir o bairro do cliente. */
	@FindBy(id = "bairro")
	public WebElement inputBairro;

	/** Elemento para selecionar a busca pelo CEP. */
	@FindBy(css = ".btn-primary > .fa-search")
	public WebElement botaoBuscarCep;

	/** Elemento para inserir o numero do endereco do cliente. */
	@FindBy(id = "numero")
	public WebElement inputNumeroDoEndereco;

	/** Elemento para inserir o complemento do endereco do cliente. */
	@FindBy(id = "complemento")
	public WebElement inputComplemento;

	/** Elemento para selecionar o estado do cliente. */
	@FindBy(id = "estado")
	public WebElement selectEstado;

	/** Elemento para selecionar a cidade do cliente. */
	@FindBy(id = "cidade")
	public WebElement selectCidade;

	/** Elemento para selecionar o país do cliente. */
	@FindBy(id = "pais")
	public WebElement selectPais;

	/** Elemento de mensagem de preenchimento dos campos obrigatorios. */
	@FindBy(css = ".mt-0")
	public WebElement mensagemCamposObrigatorios;
	
	/** Elemento de mensagem de CEP obrigatorio. */
	@FindBy(id = "cepHelp")
	public WebElement mensagemCepObrigatorio;

	/** Elemento de mensagem de CPF ou CNPJ obrigatorio. */
	@FindBy(css = ".invalid-feedback")
	public WebElement mensagemCpfCnpjObrigatorio;

	/** Elemento de mensagem de endereco obrigatorio. */
	@FindBy(id = "enderecoHelp")
	public WebElement mensagemEnderecoObrigatorio;

	/** Elemento de mensagem de email obrigatorio. */
	@FindBy(css = ".row:nth-child(2) .invalid-feedback")
	public WebElement mensagemEmailObrigatorio;

	/** Elemento de mensagem de data obrigatoria. */
	@FindBy(id = "dataHelp")
	public WebElement mensagemDataObrigatoria;

	/** Elemento de mensagem de nome obrigatorio. */
	@FindBy(id = "nomeHelp")
	public WebElement mensagemNomeObrigatorio;

	/** Elemento de mensagem de telefone obrigatorio. */
	@FindBy(css = ".col-sm-6:nth-child(1) .invalid-feedback")
	public WebElement mensagemTelefoneObrigatorio;

	/** Elemento para confirmar o registro do novo cliente. */
	@FindBy(css = ".modal-footer > .btn-success")
	public WebElement botaoConfirmar;

	/** Elemento para cancelar o registro do novo cliente. */
	@FindBy(css = ".modal-footer > .btn-secondary")
	public WebElement botaoCancelar;

	/** Elemento para voltar a tela de seleção de tipo de cliente a ser adicionado. */
	@FindBy(xpath = "//button[contains(.,'Voltar')]")
	public WebElement botaoVoltar;
	// #endregion

	// #regionn Regiao dos construtores

	/** Construtor da classe.
	 * @param driver Driver da classe.
	 */
	public AdicionarClientePO(WebDriver driver) {
		super(driver);
	}
	// #endregion

	// #region Regiao dos metodos

	/** Metodo para escolher qual tipo de cliente a ser adicionado.
	 * @param tipo Tipo de cliente se pessoa fisica, juridica ou estrangeiro.
	 */
	public void selecionarTipoDeCliente(tipoCliente tipo) {
		aguardarElemento(driver, 10);
		if (tipo == tipoCliente.FISICA) {
			selecionarElementoSelect(selectTipo, "Física");
		} else if (tipo == tipoCliente.JURIRICA) {
			selecionarElementoSelect(selectTipo, "Jurídica");
		} else if (tipo == tipoCliente.ESTRANGEIRO) {
			selecionarElementoSelect(selectTipo, "Estrangeiro");
		}
	}
	// #endregion
}
