package controller;

import java.util.List;

import dao.UsuarioDAO;
import entities.Artista;
import entities.Usuario;

/**
 * Controller responsável por regras de autenticação relacionadas ao usuário.
 * Mantém separação entre view e DAO.
 * Integrado com SessionManager para manter sessão em toda a aplicação.
 */
public class UsuarioController {
    private final UsuarioDAO usuarioDao = new UsuarioDAO();
    private final SessionManager sessionManager = SessionManager.getInstance();


    public Usuario criaUsuario(String nome, String email, String senha) {
        Usuario u = new Usuario(nome, email, senha);
        usuarioDao.criar(u);
        return u;
    }
    /**
     * Tenta autenticar o usuário com email e senha.
     * Se sucesso, salva na sessão global.
     * @return Usuario se sucesso, null caso contrário
     */
    public Usuario autenticar(String email, String senha) {
        if (email == null || senha == null) return null;
        Usuario u = usuarioDao.buscarPorEmail(email);
        if (u != null && senha.equals(u.getSenha())) {
            // Salvar na sessão global
            sessionManager.setUsuarioLogado(u);
            return u;
        }
        return null;
    }

    /**
     * Obtém usuário logado da sessão
     */
    public Usuario getUsuarioLogado() {
        return sessionManager.getUsuarioLogado();
    }

    /**
     * Verifica se há usuário logado
     */
    public boolean isLogado() {
        return sessionManager.isLogado();
    }

    /**
     * Faz logout
     */
    public void logout() {
        sessionManager.logout();
    }

    public List<Usuario> listarUsuarios(){
        return usuarioDao.listarTodos();
    }
    /**
     * Atualiza os dados do usuário no banco de dados
     */
    public void atualizarUsuario(Usuario usuario) {
        usuarioDao.atualizar(usuario);
    }

    /**
     * Deleta um usuário do banco de dados
     */
    public void deletarUsuario(Long usuarioId) {
        usuarioDao.deletar(usuarioId);
    }

}
