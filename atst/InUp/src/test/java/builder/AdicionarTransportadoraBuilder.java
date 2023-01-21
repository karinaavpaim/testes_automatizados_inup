package builder;

import page.transportadoras.AdicionarTransportadoraPO;
import page.transportadoras.AdicionarTransportadoraPO.tipoTransportadora;
import util.geradorDeDocumentos.GeraCNPJ;
import util.geradorDeDocumentos.GeraCPF;
import util.geradorDeDocumentos.GeraRG;

/** Classe de builder para inserir dados de uma nova transportadora. */
public class AdicionarTransportadoraBuilder {

    // #region região dos atributos.
    private AdicionarTransportadoraPO adicionarTransportadoraPO;
    private String nomeRazaoSocial = "JULIO TRANSPORTES";
    private String apelidoNomeFantasia = "JULIO TRANSPORTES";
    private String cpfCnpj = "";
    private String rg = "";
    private String email = "";
    private String telefone = "";
    private String celular = "";
    private int cep = 87083665;
    private String endereco = "Av das Amelias";
    private String bairro = "Jardins";
    private String estado = "PR";
    private String cidade = "MARINGA";
    private String cepOrigemDe = "0";
    private String cepOrigemAte = "99999999";
    private String cepDestinoDe = "0";
    private String cepDestinoAte = "99999999";
    private int pesoDe = 1;
    private int pesoAte = 99;
    private int custo = 100;
    private int tempo = 5;
    // #endregion

    // #region região dos construtores.

    /** 
     * Construtor da classe.
     * @param adicionarTransportadoraPO PageObject da tela de adicionar transportadora.
     */
    public AdicionarTransportadoraBuilder(AdicionarTransportadoraPO adicionarTransportadoraPO) {
        this.adicionarTransportadoraPO = adicionarTransportadoraPO;
    }
    // #endregion

    // #region região dos builders.

    /** 
     * Insere o nome da transportadora.
     * @param nomeRazaoSocial identifica o nome ou razao social da transportadora.
     * @return Retorna a propria classe.
     */
    public AdicionarTransportadoraBuilder comNome(String nomeRazaoSocial) {
        this.nomeRazaoSocial = nomeRazaoSocial;
        return this;
    }

    /** 
     * Insere o apelido ou nome fantasia da transportadora.
     * @param apelidoNomeFantasia identifica o apelido ou nome fantasia da transportadora.
     * @return Retorna a propria classe.
     */
    public AdicionarTransportadoraBuilder comApelidoNomeFantasia(String apelidoNomeFantasia) {
        this.apelidoNomeFantasia = apelidoNomeFantasia;
        return this;
    }

    /** 
     * Insere um CPF ou CNPJ para o cliente.
     * @param cpfCnpj identifica o CPF ou CNPJ.
     * @return Retorna a propria classe.
     */
    public AdicionarTransportadoraBuilder comCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
        return this;
    }

    /** 
     * Insere um RG ou numero de inscricao estadual.
     * @param rg identifica o RG ou inscricao estadual.
     * @return Retorna a propria classe.
     */
    public AdicionarTransportadoraBuilder comRg(String rg) {
        this.rg = rg;
        return this;
    }

    /** 
     * Insere o email.
     * @param email insere o email ex: "transportadora@gmail.com".
     * @return Retorna a propria classe.
     */
    public AdicionarTransportadoraBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    /** 
     * Insere o telefone.
     * @param telefone identificador do telefone.
     * @return Retorna a propria classe.
     */
    public AdicionarTransportadoraBuilder comTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    /** 
     * Insere o celular.
     * @param celular identificador do celular.
     * @return Retorna a propria classe.
     */
    public AdicionarTransportadoraBuilder comCelular(String celular) {
        this.celular = celular;
        return this;
    }

    /** 
     * Insere o cep.
     * @param cep identificador do cep.
     * @return Retorna a propria classe.
     */
    public AdicionarTransportadoraBuilder comCep(int cep) {
        this.cep = cep;
        return this;
    }

    /** 
     * Insere o endereco.     
     * @param endereco identificador do endereco.
     * @return Retorna a propria classe.
     */
    public AdicionarTransportadoraBuilder comEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    /** 
     * Insere o bairro.     * 
     * @param bairro identificador do bairro.
     * @return Retorna a propria classe.
     */
    public AdicionarTransportadoraBuilder comBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    /** 
     * Insere o cep inicial de origem.
     * @param cepOrigemDe identificador do cep origem de.
     * @return Retorna a propria classe.
     */
    public AdicionarTransportadoraBuilder comCepOrigemDe(String cepOrigemDe) {
        this.cepOrigemDe = cepOrigemDe;
        return this;
    }

    /** 
     * Insere o cep final de origem. 
     * @param cepOrigemAte identificador do cep origem ate.
     * @return Retorna a propria classe.
     */
    public AdicionarTransportadoraBuilder comCepOrigemAte(String cepOrigemAte) {
        this.cepOrigemAte = cepOrigemAte;
        return this;
    }

    /** 
     * Insere o cep inicial de destino. 
     * @param cepDestinoDe identificador do cep destino de.
     * @return Retorna a propria classe.
     */
    public AdicionarTransportadoraBuilder comCepDestinoDe(String cepDestinoDe) {
        this.cepDestinoDe = cepDestinoDe;
        return this;
    }

    /** 
     * Insere o cep final de destino.
     * @param cepDestinoAte identificador do cep destino ate.
     * @return Retorna a propria classe.
     */
    public AdicionarTransportadoraBuilder comCepDestinoAte(String cepDestinoAte) {
        this.cepDestinoAte = cepDestinoAte;
        return this;
    }

    /** 
     * Insere o peso inicial.
     * @param pesoDe identificador do peso de.
     * @return Retorna a propria classe.
     */
    public AdicionarTransportadoraBuilder comPesoDe(int pesoDe) {
        this.pesoDe = pesoDe;
        return this;
    }

    /** 
     * Insere o peso final.
     * @param pesoAte identificador do peso ate.
     * @return Retorna a propria classe.
     */
    public AdicionarTransportadoraBuilder comPesoAte(int pesoAte) {
        this.pesoAte = pesoAte;
        return this;
    }

    /** Insere o valor do custo.
     * @param custo identifica o valor do custo.
     * @return Retorna a propria classe.
     */
    public AdicionarTransportadoraBuilder comCusto(int custo) {
        this.custo = custo;
        return this;
    }

    /** Insere o custo do transporte.
     * @param tempo identifica o custo do transporte.
     * @return Retorna a propria classe.
     */
    public AdicionarTransportadoraBuilder comTempo(int tempo) {
        this.tempo = tempo;
        return this;
    }

     /** Seleciona o estado.
    * @param estado inserir o estado que deseja selecionar.
    */
    public AdicionarTransportadoraBuilder comEstado(String estado){
        this.estado = estado;
       
        adicionarTransportadoraPO.selecionarElementoSelect(adicionarTransportadoraPO.selectEstado, estado);
       
        return this;
    }

	 /** Seleciona a cidade.
      * @param cidade inserir a cidade que deseja selecionar.
      */
    public AdicionarTransportadoraBuilder comCidade(String cidade){
        this.cidade = cidade;

        adicionarTransportadoraPO.selecionarElementoSelect(adicionarTransportadoraPO.selectCidade, cidade);

        return this;
    }
    // #endregion

    // #region Regiao dos metodos

    /**Metodo para adicionar uma transportadora.
     * @param tipo Paramentro para selecionar o tipo de transportadora.
     */
    public void adicionarTransportadora(tipoTransportadora tipo) {
        GeraCPF geraCPF = new GeraCPF();
        GeraCNPJ geraCNPJ = new GeraCNPJ();
        GeraRG geraRG = new GeraRG();
        
        if (tipo == tipoTransportadora.JURIRICA) {
            adicionarTransportadoraPO.selecionarTipoTransportadora(tipoTransportadora.JURIRICA);
            
            adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputCpfCnpj);
            adicionarTransportadoraPO.inputCpfCnpj.sendKeys(cpfCnpj);

            adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputIdentidade);
            adicionarTransportadoraPO.inputIdentidade.sendKeys(rg);

            if (adicionarTransportadoraPO.inputCpfCnpj.getAttribute("value").equals("")){
                adicionarTransportadoraPO.inputCpfCnpj.sendKeys(geraCNPJ.comCnpj());
            }

            if (adicionarTransportadoraPO.inputIdentidade.getAttribute("value").equals("")){
                adicionarTransportadoraPO.inputIdentidade.sendKeys(geraRG.comIdentidade());
            }
        } else if (tipo == tipoTransportadora.FISICA) {
            adicionarTransportadoraPO.selecionarTipoTransportadora(tipoTransportadora.FISICA);
            
            adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputCpfCnpj);
            adicionarTransportadoraPO.inputCpfCnpj.sendKeys(cpfCnpj);

            adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputIdentidade);
            adicionarTransportadoraPO.inputIdentidade.sendKeys(rg);

            if (adicionarTransportadoraPO.inputCpfCnpj.getAttribute("value").equals("")){
                adicionarTransportadoraPO.inputCpfCnpj.sendKeys(geraCPF.comCpf());
            }

            if (adicionarTransportadoraPO.inputIdentidade.getAttribute("value").equals("")){
                adicionarTransportadoraPO.inputIdentidade.sendKeys(geraRG.comIdentidade());
            }
        }

        adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputCep);
        adicionarTransportadoraPO.inputCep.sendKeys(String.valueOf(cep));

        adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputRazaoSocial);
        adicionarTransportadoraPO.inputRazaoSocial.sendKeys(nomeRazaoSocial);

        adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputNome);
        adicionarTransportadoraPO.inputNome.sendKeys(apelidoNomeFantasia);
       
        adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputContato);
        adicionarTransportadoraPO.inputContato.sendKeys(email);

        adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputTelefone);
        adicionarTransportadoraPO.inputTelefone.sendKeys(telefone);

        adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputCelular);
        adicionarTransportadoraPO.inputCelular.sendKeys(celular);

        comEstado(estado);

        adicionarTransportadoraPO.inputEndereco.click();

        if (adicionarTransportadoraPO.inputEndereco.getAttribute("value").isEmpty()) {
            adicionarTransportadoraPO.inputEndereco.sendKeys(endereco);
        }
        if (adicionarTransportadoraPO.inputBairro.getAttribute("value").isEmpty()) {
            adicionarTransportadoraPO.inputBairro.sendKeys(bairro);
        }

        comCidade(cidade);

        adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputCepOrigemDe);
        adicionarTransportadoraPO.inputCepOrigemDe.sendKeys(cepOrigemDe);

        adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputCepOrigemAte);
        adicionarTransportadoraPO.inputCepOrigemAte.sendKeys(cepOrigemAte);

        adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputCepDestinoDe);
        adicionarTransportadoraPO.inputCepDestinoDe.sendKeys(cepDestinoDe);

        adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputCepDestinoAte);
        adicionarTransportadoraPO.inputCepDestinoAte.sendKeys(cepDestinoAte);

        adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputPesoDe);
        adicionarTransportadoraPO.inputPesoDe.sendKeys(String.valueOf(pesoDe));

        adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputPesoAte);
        adicionarTransportadoraPO.inputPesoAte.sendKeys(String.valueOf(pesoAte));

        adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputCusto);
        adicionarTransportadoraPO.inputCusto.sendKeys(String.valueOf(custo));

        adicionarTransportadoraPO.limparCampoInput(adicionarTransportadoraPO.inputTempo);
        adicionarTransportadoraPO.inputTempo.sendKeys(String.valueOf(tempo));
    }
    // #endregion
}
