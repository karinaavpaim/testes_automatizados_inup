package builder;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import page.AdicionarClientePO;
import page.AdicionarClientePO.tipoCliente;
import util.geradorDeDocumentos.GeraCNPJ;
import util.geradorDeDocumentos.GeraCPF;
import util.geradorDeDocumentos.GeraRG;

/** Classe de builder para inserir dados do cliente a ser adicionado. */
public class AdicionarClienteBuilder {

    // #region região dos atributos.
    private AdicionarClientePO adicionarClientePO;
    private String nome = "Vania Souza";
    private String cpfCnpj = "";
    private String rg = "";
    private String email = "vaniasouza@yahoo.com";
    private String dia = "10";
    private String mes = "10";
    private String ano = "1985";
    private String telefone = "2130460202";
    private String celular = "21987654728";
    private Integer cep = 87083551;
    private String endereco = "Avenida das Grevileas";
    private String numero = "";
    private String complemento = "";
    private String bairro = "Vila da Penha";
    private String estado = "RS";
    private String cidade = "GRAMADO";
    private String pais = "Brasil";
    // #endregion

    // #region região dos construtores.

    /** 
     * Construtor da classe.
     * @param adicionarClientePO PageObject da tela de adicionar cliente.
     */
    public AdicionarClienteBuilder(AdicionarClientePO adicionarClientePO) {
        this.adicionarClientePO = adicionarClientePO;
    }
    // #endregion

    // #region região dos builders.

    /** 
     * Insere um nome para o cliente.
     * @param nome identifica o cliente pelo nome a ser inserido.
     * @return Retorna a propria classe.
     */
    public AdicionarClienteBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    /** 
     * Insere um CPF ou CNPJ para o cliente.
     * @param cpfCnpj identifica o CPF ou CNPJ.
     * @return Retorna a propria classe.
     */
    public AdicionarClienteBuilder comCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
        return this;
    }

    /** 
     * Insere um RG ou numero de inscricao estadual para o cliente.
     * @param rg identifica o RG ou inscricao estadual do cliente.
     * @return Retorna a propria classe.
     */
    public AdicionarClienteBuilder comRg(String rg) {
        this.rg = rg;
        return this;
    }

    /** 
     * Insere o dia.
     * @param dia identificador do dia na tela de adicionar clientes.
     * @return Retorna a propria classe.
     */
    public AdicionarClienteBuilder comDia(String dia) {
        this.dia = dia;

        Select selecionar = new Select(adicionarClientePO.selectDia);
        selecionar.selectByVisibleText(dia);

        return this;
    }

    /** 
     * Insere o mes.
     * @param mes identificador do mes na tela de adicionar clientes.
     * @return Retorna a propria classe.
     */
    public AdicionarClienteBuilder comMes(String mes) {
        this.mes = mes;

        Select selecionar = new Select(adicionarClientePO.selectMes);
        selecionar.selectByVisibleText(mes);
        
        return this;
    }

    /** 
     * Insere o ano.
     * @param ano identificador do ano na tela de adicionar clientes.
     * @return Retorna a propria classe.
     */
    public AdicionarClienteBuilder comAno(String ano) {
        this.ano = ano;
        return this;
    }

    /** 
     * Insere o email do cliente.
     * @param email insere o email do cliente ex: pessoa@gmail.com
     * @return Retorna a propria classe.
     */
    public AdicionarClienteBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    /** 
     * Insere o telefone. 
     * @param telefone identificador do telefone do cliente com 11 digitos.
     * @return Retorna a propria classe.
     */
    public AdicionarClienteBuilder comTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    /** 
     * Insere o celular.
     * @param celular identificador do celular do cliente com 11 digitos.
     * @return Retorna a propria classe.
     */
    public AdicionarClienteBuilder comCelular(String celular) {
        this.celular = celular;
        return this;
    }

    /** 
     * Insere o cep. 
     * @param cep identificador do cep do cliente com 8 digitos .
     * @return Retorna a propria classe.
     */
    public AdicionarClienteBuilder comCep(Integer cep) {
        this.cep = cep;
        return this;
    }

    /** 
     * Insere o endereco. 
     * @param endereco identificador do endereco do cliente.
     * @return Retorna a propria classe.
     */
    public AdicionarClienteBuilder comEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    /** 
     * Insere o numero do endereco. 
     * @param numero identificador do numero do endereco do cliente.
     * @return Retorna a propria classe.
     */
    public AdicionarClienteBuilder comNumero(String numero) {
        this.numero = numero;
        return this;
    }

    /** 
     * Insere o complemento do endereco.
     * @param complemento identificador do numero do endereco do cliente.
     * @return Retorna a propria classe.
     */
    public AdicionarClienteBuilder comComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    /** 
     * Insere o bairro. 
     * @param bairro identificador do bairro do cliente.
     * @return Retorna a propria classe.
     */
    public AdicionarClienteBuilder comBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    /** 
     * Seleciona o estado do novo cliente.
    * @param estado inserir o estado que deseja selecionar.
    */
    public AdicionarClienteBuilder comEstado(String estado){
        this.estado = estado;

        Select selecionarEstado = new Select(adicionarClientePO.selectEstado);

        if (estado.equals("")) {
            selecionarEstado.selectByIndex(0);
        } else if (!estado.equals("")){
            int index = 0;
			for (WebElement nomeEstado : selecionarEstado.getOptions()) {
					if (nomeEstado.getText().equalsIgnoreCase(estado))
						break;
						index++;
			}
			selecionarEstado.selectByIndex(index);
		}
        return this;
    }
	
	 /** 
      * Seleciona a cidade do novo cliente.
      * @param cidade inserir a cidade que deseja selecionar.
      */
    public AdicionarClienteBuilder comCidade(String cidade){
        this.cidade = cidade;
    
        if (!cidade.equals("")) {
                adicionarClientePO.selecionarElementoSelect(adicionarClientePO.selectCidade, cidade);
        } else {
                adicionarClientePO.selectCidade.click();
        }       
        return this;
    }
	
	/** 
     * Seleciona o pais do novo cliente.
      * @param pais inserir o pais que deseja selecionar.
      */
      public AdicionarClienteBuilder comPais(String pais){
        this.pais = pais;

        Select selecionarPais = new Select(adicionarClientePO.selectPais);
		
        if (pais.equals("")) {
            selecionarPais.selectByIndex(0);
        } else if (!pais.equals("")){
			int index = 0;
			for (WebElement nomePais : selecionarPais.getOptions()) {
				if (nomePais.getText().equalsIgnoreCase(pais))
					break;
					index++;
				}
				selecionarPais.selectByIndex(index);
			}
            return this;
		}
    // #endregion

    // #region Regiao dos metodos

    /** Metodo para adicionar o cliente inserindo todos seus dados.
     * @param tipo Paramentro para selecionar o tipo de cliente a ser adicionado.
     */
    public void preencherDadosDoNovoCliente(tipoCliente tipo) {
        GeraCPF geraCPF = new GeraCPF();
        GeraCNPJ geraCNPJ = new GeraCNPJ();
        GeraRG geraRG = new GeraRG();

        if (tipo == tipoCliente.JURIRICA) {
            adicionarClientePO.selecionarTipoDeCliente(tipoCliente.JURIRICA);
            
            adicionarClientePO.limparCampoInput(adicionarClientePO.inputCpfCnpj);
            adicionarClientePO.inputCpfCnpj.sendKeys(cpfCnpj);

            adicionarClientePO.limparCampoInput(adicionarClientePO.inputIdentidade);
            adicionarClientePO.inputIdentidade.sendKeys(rg);

            if (adicionarClientePO.inputCpfCnpj.getAttribute("value").equals("")){
                adicionarClientePO.inputCpfCnpj.sendKeys(geraCNPJ.comCnpj());
            }

            if (adicionarClientePO.inputIdentidade.getAttribute("value").equals("")){
                adicionarClientePO.inputIdentidade.sendKeys(geraRG.comIdentidade());
            }
        } else if (tipo == tipoCliente.ESTRANGEIRO) {
            adicionarClientePO.selecionarTipoDeCliente(tipoCliente.ESTRANGEIRO);
            
            adicionarClientePO.limparCampoInput(adicionarClientePO.inputCpfCnpj);
            adicionarClientePO.inputCpfCnpj.sendKeys(cpfCnpj);

            adicionarClientePO.limparCampoInput(adicionarClientePO.inputIdentidade);
            adicionarClientePO.inputIdentidade.sendKeys(rg);

            if (adicionarClientePO.inputCpfCnpj.getAttribute("value").equals("")){
                adicionarClientePO.inputCpfCnpj.sendKeys(geraCPF.comCpf());
            }

            if (adicionarClientePO.inputIdentidade.getAttribute("value").equals("")){
                adicionarClientePO.inputIdentidade.sendKeys(geraRG.comIdentidade());
            }
        } else if (tipo == tipoCliente.FISICA) {
            adicionarClientePO.selecionarTipoDeCliente(tipoCliente.FISICA);
            
            adicionarClientePO.limparCampoInput(adicionarClientePO.inputCpfCnpj);
            adicionarClientePO.inputCpfCnpj.sendKeys(cpfCnpj);

            adicionarClientePO.limparCampoInput(adicionarClientePO.inputIdentidade);
            adicionarClientePO.inputIdentidade.sendKeys(rg);

            if (adicionarClientePO.inputCpfCnpj.getAttribute("value").equals("")){
                adicionarClientePO.inputCpfCnpj.sendKeys(geraCPF.comCpf());
            }

            if (adicionarClientePO.inputIdentidade.getAttribute("value").equals("")){
                adicionarClientePO.inputIdentidade.sendKeys(geraRG.comIdentidade());
            }
        }
       
        adicionarClientePO.limparCampoInput(adicionarClientePO.inputNome);
        adicionarClientePO.inputNome.sendKeys(nome);

        adicionarClientePO.limparCampoInput(adicionarClientePO.inputEmail);
        adicionarClientePO.inputEmail.sendKeys(email);

        comDia(dia);
        comMes(mes);

        adicionarClientePO.limparCampoInput(adicionarClientePO.inputAno);
        adicionarClientePO.inputAno.sendKeys(ano);

        adicionarClientePO.limparCampoInput(adicionarClientePO.inputTelefone);
        adicionarClientePO.inputTelefone.sendKeys(telefone);

        adicionarClientePO.limparCampoInput(adicionarClientePO.inputCelular);
        adicionarClientePO.inputCelular.sendKeys(celular);

        adicionarClientePO.limparCampoInput(adicionarClientePO.inputCep);
        adicionarClientePO.inputCep.sendKeys((cep != null) ? cep.toString() : "");
        adicionarClientePO.botaoBuscarCep.click();

        adicionarClientePO.inputTelefone.click();

        comEstado(estado);

        if (adicionarClientePO.inputEndereco.getAttribute("value").isEmpty()) {
            adicionarClientePO.inputEndereco.sendKeys(endereco);
        }
        if (adicionarClientePO.inputBairro.getAttribute("value").isEmpty()) {
            adicionarClientePO.inputBairro.sendKeys(bairro);
        }

        adicionarClientePO.limparCampoInput(adicionarClientePO.inputNumeroDoEndereco);
        adicionarClientePO.inputNumeroDoEndereco.sendKeys(numero);

        adicionarClientePO.limparCampoInput(adicionarClientePO.inputComplemento);
        adicionarClientePO.inputComplemento.sendKeys(complemento);

        comCidade(cidade);
        comPais(pais);
    }
    // #endregion
}
