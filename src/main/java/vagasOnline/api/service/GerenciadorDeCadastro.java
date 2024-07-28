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

    public void cadastrarUsuario(String json){
        this.cadastrarUsuario(Conversor.jsonParaUsuario(json));
    }

    private void cadastrarUsuario(Usuario usuario){
        if (usuario instanceof Empresa){
            this.empresasCadastradas.add((Empresa) usuario);
        } else if (usuario instanceof Pessoa) {
            this.pessoasCadastradas.add((Pessoa) usuario);
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

    public Usuario getUsuario(Usuario usuario){
        if (usuario instanceof Empresa){
            for (Empresa empresa : empresasCadastradas){
                if (empresa.equals(usuario)) return empresa;
            }
        } else if (usuario instanceof Pessoa) {
            for (Pessoa pessoa : pessoasCadastradas){
                if(pessoa.equals(usuario)) return pessoa;
            }
        }
        return null;
    }
}