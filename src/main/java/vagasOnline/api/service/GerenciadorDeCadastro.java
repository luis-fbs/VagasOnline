package vagasOnline.api.service;

import org.springframework.stereotype.Service;
import vagasOnline.api.usuario.Empresa;
import vagasOnline.api.usuario.Pessoa;
import vagasOnline.api.usuario.Usuario;
import vagasOnline.api.util.Conversor;
import vagasOnline.api.vaga.Vaga;

import java.util.ArrayList;
import java.util.List;

@Service
public class GerenciadorDeCadastro {
    private List<Empresa> empresasCadastradas;
    private List<Pessoa> pessoasCadastradas;
    private List<Vaga> vagasCadastradas;

    public GerenciadorDeCadastro() {
        this.empresasCadastradas = new ArrayList<Empresa>();
        this.pessoasCadastradas = new ArrayList<Pessoa>();
        this.vagasCadastradas = new ArrayList<Vaga>();
    }

    public List<Empresa> getEmpresasCadastradas() {
        return empresasCadastradas;
    }

    public List<Pessoa> getPessoasCadastradas() {
        return pessoasCadastradas;
    }

    public List<Vaga> getVagasCadastradas() {
        return vagasCadastradas;
    }

    public void cadastrarUsuario(String json){
        Usuario usuario = Conversor.jsonParaUsuario(json);
        if (!usuarioExiste(usuario)){
            this.cadastrarUsuario(usuario);
        }
    }

    private void cadastrarUsuario(Usuario usuario){
        if (usuario instanceof Empresa){
            this.empresasCadastradas.add((Empresa) usuario);
        } else if (usuario instanceof Pessoa) {
            this.pessoasCadastradas.add((Pessoa) usuario);
        }
    }

    public String informacoesPessoa(int index){
        try {
            return Conversor.usuarioParaJson(pessoasCadastradas.get(index));
        } catch (Exception e) {
            return null;
        }
    }

    public String informacoesEmpresa(int index){
        try {
            return Conversor.usuarioParaJson(empresasCadastradas.get(index));
        } catch (Exception e) {
            return null;
        }
    }

    public void excluirUsuario(String json){
        this.excluirUsuario(Conversor.jsonParaUsuario(json));
    }

    public void excluirUsuario(Usuario usuario){
        if (usuario instanceof Empresa){
            this.empresasCadastradas.remove((Empresa) usuario);
        } else if (usuario instanceof Pessoa) {
            this.pessoasCadastradas.remove((Pessoa) usuario);
        }
    }

    public void cadastrarVaga(Vaga vaga){
        this.vagasCadastradas.add(vaga);
    }

    public void excluirVaga(Vaga vaga){
        this.vagasCadastradas.remove(vaga);
    }

    public boolean usuarioExiste(Usuario usuario){
        if (usuario instanceof Empresa){
            return this.empresasCadastradas.contains(usuario);
        } else if (usuario instanceof Pessoa) {
            return this.pessoasCadastradas.contains(usuario);
        } else return false;
    }
}