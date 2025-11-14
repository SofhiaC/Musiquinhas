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


/**
     * Inicializa dados de teste se o banco estiver vazio.
     */
    public void inicializarDadosTeste() {
        try {
            dao.UsuarioDAO dao = new dao.UsuarioDAO();
            
            // Verificar se existem usuários
            var usuarios = dao.listarTodos();
            if (usuarios == null || usuarios.isEmpty()) {
                System.out.println("\n=== Criando usuários de teste ===\n");
                
                // Usuário 1
                Usuario user1 = new Usuario();
                user1.setNome("Sofhia");
                user1.setEmail("sofhia@email.com");
                user1.setSenha("1234");
                dao.criar(user1);
                System.out.println("Sofhia criado!");
                System.out.println("   Email: sofhia@email.com | Senha: 1234");
                
                // Usuário 2
                Usuario user2 = new Usuario();
                user2.setNome("João");
                user2.setEmail("joao@email.com");
                user2.setSenha("senha123");
                dao.criar(user2);
                System.out.println("João criado!");
                System.out.println("   Email: joao@email.com | Senha: senha123");
                
                // Usuário 3
                Usuario user3 = new Usuario();
                user3.setNome("Maria");
                user3.setEmail("maria@email.com");
                user3.setSenha("maria456");
                dao.criar(user3);
                System.out.println("Maria criado!");
                System.out.println("   Email: maria@email.com | Senha: maria456");
                
                System.out.println("\n=== Usuários prontos para login ===\n");
            }
        } catch (Exception e) {
            System.err.println("Erro ao inicializar dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
