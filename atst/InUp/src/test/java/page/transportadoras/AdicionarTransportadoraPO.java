package page.transportadoras;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import page.BasePO;

/** Page Object da tela para adicionar uma transportadora. */
public class AdicionarTransportadoraPO extends BasePO {

	// #region Regiao dos elementos.
	
	/**Enum para definir o tipo de transportadora que sera adicionado.*/
	public enum tipoTransportadora { FISICA, JURIRICA; }

	/** Elemento para selecionar o tipo. */
	@FindBy(id = "type")
	public WebElement selectTipo;

	/** Elemento para inserir o CPF ou CNPJ. */
	@FindBy(id = "cgc")
	public WebElement inputCpfCnpj;

	/** Elemento para inserir o nome fantasia. */
	@FindBy(id = "nome")
	public WebElement inputNome;

	/** Elemento para inserir o documento. */
	@FindBy(id = "doc")
	public WebElement inputIdentidade;

	/** Elemento para inserir a razao social. */
	@FindBy(id = "razaoSocial")
	public WebElement inputRazaoSocial;

	/** Elemento para inserir o CEP. */
	@FindBy(id = "cep")
	public WebElement inputCep;

	/** Elemento para selecionar o estado. */
	@FindBy(id = "estado")
	public WebElement selectEstado;

	/** Elemento para inserir o endereco. */
	@FindBy(id = "address")
	public WebElement inputEndereco;

	/** Elemento para inserir o bairro. */
	@FindBy(id = "bairro")
	public WebElement inputBairro;

	/** Elemento para selecionar a cidade. */
	@FindBy(id = "cidade")
	public WebElement selectCidade;

	/** Elemento para inserir o telefone. */
	@FindBy(id = "telefone")
	public WebElement inputTelefone;

	/** Elemento para inserir o celular. */
	@FindBy(id = "celular")
	public WebElement inputCelular;

	/** Elemento para inserir um contato. */
	@FindBy(id = "contato")
	public WebElement inputContato;

	/** Elemento para selecionar se a transportadora esta inativa. */
	@FindBy(css = ".col-6 .custom-control-label")
	public WebElement checkboxInativo;

	/** Elemento para inserir o cep de origem inicial. */
	@FindBy(id = "cepOrigemDe")
	public WebElement inputCepOrigemDe;

	/** Elemento para inserir o cep de origem final. */
	@FindBy(id = "cepOrigemAte")
	public WebElement inputCepOrigemAte;

	/** Elemento para inserir o cep de destino inicial. */
	@FindBy(id = "cepDestinoDe")
	public WebElement inputCepDestinoDe;

	/** Elemento para inserir o cep de destino final. */
	@FindBy(id = "cepDestinoAte")
	public WebElement inputCepDestinoAte;

	/** Elemento para inserir o peso inicial. */
	@FindBy(id = "pesoDe")
	public WebElement inputPesoDe;

	/** Elemento para inserir o peso final. */
	@FindBy(id = "pesoAte")
	public WebElement inputPesoAte;

	/** Elemento para inserir o custo. */
	@FindBy(id = "custo")
	public WebElement inputCusto;

	/** Elemento para inserir o tempo. */
	@FindBy(id = "tempo")
	public WebElement inputTempo;

	/** Elemento para adicionar a transportadora. */
	@FindBy(css = ".text-right > .btn-success")
	public WebElement botaoAdicionar;

	/** Elemento para importar um arquivo. */
	@FindBy(id = "file")
	public WebElement botaoImportar;

	/** Elemento para cancelar a insercao de transportadora. */
	@FindBy(css = ".btn-dark")
	public WebElement botaoCancelar;

	/** Elemento para salvar a insercao de transportadora. */
	@FindBy(css = ".btn-success:nth-child(2)")
	public WebElement botaoSalvar;
	// #endregion

	// #region Regiao dos construtores

	/** Construtor da classe.
	 * @param driver Driver da classe.
	 */
	public AdicionarTransportadoraPO(WebDriver driver) {
		super(driver);
	}
	// #endregion

	// #region Regiao dos metodos

	/** Metodo para escolher qual tipo de transportadora a ser adicionado.
	 * @param tipo selecionar o tipo de transportadora (pessoa fisica ou juridica).
	 */
	public void selecionarTipoTransportadora(tipoTransportadora tipo) {
		if (tipo == tipoTransportadora.FISICA) {
			selecionarElementoSelect(selectTipo, "Física");
		} else if (tipo == tipoTransportadora.JURIRICA) {
			selecionarElementoSelect(selectTipo, "Jurídica");
		}
	}
	//#endregion
}
