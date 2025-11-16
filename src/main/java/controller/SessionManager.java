package controller;

import entities.Usuario;

/**
 * Gerenciador de Sessão (Singleton)
 * Mantém a sessão do usuário logado em toda a aplicação
 * 
 * Uso:
 *   SessionManager.getInstance().setUsuarioLogado(usuario);
 *   Usuario user = SessionManager.getInstance().getUsuarioLogado();
 */
public class SessionManager {
    private static SessionManager instance;
    private Usuario usuarioLogado;

    // Construtor privado para Singleton
    private SessionManager() {
    }

    // Método para obter instância única
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    /**
     * Define o usuário logado na sessão
     */
    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    /**
     * Obtém o usuário logado
     */
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * Retorna true se há usuário logado
     */
    public boolean isLogado() {
        return usuarioLogado != null;
    }

    /**
     * Faz logout (limpa a sessão)
     */
    public void logout() {
        this.usuarioLogado = null;
    }

    /**
     * Retorna nome do usuário logado
     */
    public String getNomeUsuario() {
        return usuarioLogado != null ? usuarioLogado.getNome() : "Visitante";
    }

    /**
     * Retorna email do usuário logado
     */
    public String getEmailUsuario() {
        return usuarioLogado != null ? usuarioLogado.getEmail() : null;
    }
}