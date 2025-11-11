package controller;

import dao.UsuarioDAO;
import entities.Usuario;

/**
 * Controller responsável por regras de autenticação relacionadas ao usuário.
 * Mantém separação entre view e DAO.
 * Integrado com SessionManager para manter sessão em toda a aplicação.
 */
public class UsuarioController {
    private final UsuarioDAO usuarioDao = new UsuarioDAO();
    private final SessionManager sessionManager = SessionManager.getInstance();

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
}
