package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import controller.SessionManager;
import controller.UsuarioController;
import entities.Usuario;

/**
 * Tela de configuração de conta do usuário.
 * Permite editar nome, email e senha do usuário logado.
 * 
 * Fluxo:
 * - Ao abrir, fecha a HomeView
 * - Se salvar alterações, reabre HomeView com dados atualizados
 * - Se cancelar, reabre HomeView sem alterações
 * - Se excluir conta, vai para LoginView
 */
public class UsuarioView extends Application {
    private final UsuarioController controller = new UsuarioController();
    private final SessionManager sessionManager = SessionManager.getInstance();

    private TextField tfNome;
    private TextField tfEmail;
    private PasswordField pfSenha;

    @Override
    public void start(Stage stage) {
        // Verificar se usuário está logado
        if (!sessionManager.isLogado()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Acesso Negado", "Você deve estar logado para acessar esta tela.");
            return;
        }

        stage.setTitle("Musiquinhas - Configurações de Conta");

        // Cores principais
        Color fundoEscuro = Color.web("#2A2A2A");
        Color roxo = Color.web("#7E6FBB");
        Color branco = Color.WHITE;

        // Layout principal com BorderPane
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(fundoEscuro, CornerRadii.EMPTY, Insets.EMPTY)));

        // ---------- CABEÇALHO ----------
        VBox cabecalho = criarCabecalho(branco);
        root.setTop(cabecalho);

        // ---------- CONTEÚDO PRINCIPAL ----------
        VBox conteudo = criarConteudoPrincipal(roxo, branco, stage);
        root.setCenter(conteudo);

        // Configurar cena
        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    /**
     * Cria o cabeçalho com título
     */
    private VBox criarCabecalho(Color branco) {
        VBox cabecalho = new VBox(10);
        cabecalho.setPadding(new Insets(20));
        cabecalho.setAlignment(Pos.CENTER_LEFT);
        cabecalho.setStyle("-fx-background-color: #1a1a1a; -fx-border-color: #7E6FBB; -fx-border-width: 0 0 2 0;");

        Label lblTitulo = new Label("Edite suas informações");
        lblTitulo.setFont(Font.font("Arial", 24));
        lblTitulo.setTextFill(branco);

        cabecalho.getChildren().add(lblTitulo);
        return cabecalho;
    }

    /**
     * Cria o conteúdo principal com os campos de edição
     */
    private VBox criarConteudoPrincipal(Color roxo, Color branco, Stage stage) {
        VBox conteudo = new VBox(20);
        conteudo.setPadding(new Insets(40, 50, 40, 50));
        conteudo.setAlignment(Pos.TOP_CENTER);
        conteudo.setStyle("-fx-background-color: #2A2A2A;");

        // Obter dados do usuário logado
        Usuario usuarioAtual = sessionManager.getUsuarioLogado();

        // Campo de Nome
        Label lblNome = new Label("Nome");
        lblNome.setTextFill(branco);
        lblNome.setFont(Font.font("Arial", 14));
        tfNome = new TextField();
        tfNome.setText(usuarioAtual.getNome());
        estilizarCampo(tfNome);

        // Campo de Email
        Label lblEmail = new Label("Email");
        lblEmail.setTextFill(branco);
        lblEmail.setFont(Font.font("Arial", 14));
        tfEmail = new TextField();
        tfEmail.setText(usuarioAtual.getEmail());
        estilizarCampo(tfEmail);

        // Campo de Senha
        Label lblSenha = new Label("Senha");
        lblSenha.setTextFill(branco);
        lblSenha.setFont(Font.font("Arial", 14));
        pfSenha = new PasswordField();
        pfSenha.setText(usuarioAtual.getSenha());
        estilizarCampo(pfSenha);

        // Adicionar campos ao conteúdo
        conteudo.getChildren().addAll(
            lblNome, tfNome,
            lblEmail, tfEmail,
            lblSenha, pfSenha
        );

        // Espaçador
        Region espacador = new Region();
        VBox.setVgrow(espacador, Priority.ALWAYS);
        conteudo.getChildren().add(espacador);

        // Criar barra de botões
        HBox botoesBox = criarBotoesAcao(stage);
        conteudo.getChildren().add(botoesBox);

        return conteudo;
    }

    /**
     * Cria a barra de botões (Cancelar, Salvar, Excluir)
     */
    private HBox criarBotoesAcao(Stage stage) {
        HBox botoesBox = new HBox(15);
        botoesBox.setAlignment(Pos.CENTER);
        botoesBox.setPadding(new Insets(20, 0, 0, 0));

        // Botão Cancelar
        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setStyle("-fx-background-color: #555555; -fx-text-fill: #FFFFFF; -fx-font-size: 12; -fx-padding: 10 20; -fx-background-radius: 6;");
        btnCancelar.setFont(Font.font("Arial", 12));
        btnCancelar.setCursor(javafx.scene.Cursor.HAND);
        btnCancelar.setOnMouseEntered(e -> btnCancelar.setStyle("-fx-background-color: #777777; -fx-text-fill: #FFFFFF; -fx-font-size: 12; -fx-padding: 10 20; -fx-background-radius: 6;"));
        btnCancelar.setOnMouseExited(e -> btnCancelar.setStyle("-fx-background-color: #555555; -fx-text-fill: #FFFFFF; -fx-font-size: 12; -fx-padding: 10 20; -fx-background-radius: 6;"));
        btnCancelar.setOnAction(e -> {
            // Fechar UsuarioView e reabrir HomeView sem alterações
            new HomeView().start(stage);
        });

        // Botão Salvar
        Button btnSalvar = new Button("Salvar");
        btnSalvar.setStyle("-fx-background-color: #7E6FBB; -fx-text-fill: #FFFFFF; -fx-font-size: 12; -fx-padding: 10 20; -fx-background-radius: 6;");
        btnSalvar.setFont(Font.font("Arial", 12));
        btnSalvar.setCursor(javafx.scene.Cursor.HAND);
        btnSalvar.setOnMouseEntered(e -> btnSalvar.setStyle("-fx-background-color: #9988DD; -fx-text-fill: #FFFFFF; -fx-font-size: 12; -fx-padding: 10 20; -fx-background-radius: 6;"));
        btnSalvar.setOnMouseExited(e -> btnSalvar.setStyle("-fx-background-color: #7E6FBB; -fx-text-fill: #FFFFFF; -fx-font-size: 12; -fx-padding: 10 20; -fx-background-radius: 6;"));
        btnSalvar.setOnAction(e -> salvarAlteracoes(stage));

        // Botão Excluir
        Button btnExcluir = new Button("Excluir Conta");
        btnExcluir.setStyle("-fx-background-color: #D32F2F; -fx-text-fill: #FFFFFF; -fx-font-size: 12; -fx-padding: 10 20; -fx-background-radius: 6;");
        btnExcluir.setFont(Font.font("Arial", 12));
        btnExcluir.setCursor(javafx.scene.Cursor.HAND);
        btnExcluir.setOnMouseEntered(e -> btnExcluir.setStyle("-fx-background-color: #F44336; -fx-text-fill: #FFFFFF; -fx-font-size: 12; -fx-padding: 10 20; -fx-background-radius: 6;"));
        btnExcluir.setOnMouseExited(e -> btnExcluir.setStyle("-fx-background-color: #D32F2F; -fx-text-fill: #FFFFFF; -fx-font-size: 12; -fx-padding: 10 20; -fx-background-radius: 6;"));
        btnExcluir.setOnAction(e -> excluirConta(stage));

        botoesBox.getChildren().addAll(btnCancelar, btnSalvar, btnExcluir);
        return botoesBox;
    }

    /**
     * Estiliza os campos de entrada
     */
    private void estilizarCampo(TextInputControl field) {
        field.setPrefWidth(300);
        field.setPrefHeight(36);
        field.setStyle("-fx-background-color: white; -fx-border-radius: 6; -fx-background-radius: 6; -fx-padding: 8;");
    }

    /**
     * Salva as alterações do usuário e reabre HomeView
     */
    private void salvarAlteracoes(Stage stage) {
        String novoNome = tfNome.getText().trim();
        String novoEmail = tfEmail.getText().trim();
        String novaSenha = pfSenha.getText();

        // Validações básicas
        if (novoNome.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campo Vazio", "O nome não pode estar vazio.");
            return;
        }

        if (novoEmail.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campo Vazio", "O email não pode estar vazio.");
            return;
        }

        if (novaSenha.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campo Vazio", "A senha não pode estar vazia.");
            return;
        }

        try {
            // Obter usuário logado e atualizar dados
            Usuario usuario = sessionManager.getUsuarioLogado();
            usuario.setNome(novoNome);
            usuario.setEmail(novoEmail);
            usuario.setSenha(novaSenha);

            // Atualizar no banco de dados
            controller.atualizarUsuario(usuario);

            // Atualizar na sessão
            sessionManager.setUsuarioLogado(usuario);

            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Suas informações foram atualizadas com sucesso!");
            
            // Fechar UsuarioView e reabrir HomeView com dados atualizados
            new HomeView().start(stage);

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Ocorreu um erro ao salvar as alterações: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Exclui a conta do usuário
     */
    private void excluirConta(Stage stage) {
        // Confirmação
        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacao.setTitle("Excluir Conta");
        confirmacao.setHeaderText("Tem certeza que deseja excluir sua conta?");
        confirmacao.setContentText("Esta ação não pode ser desfeita!");

        var resultado = confirmacao.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            try {
                Usuario usuario = sessionManager.getUsuarioLogado();
                
                // Deletar do banco de dados
                controller.deletarUsuario(usuario.getId());

                // Fazer logout
                sessionManager.logout();

                mostrarAlerta(Alert.AlertType.INFORMATION, "Conta Excluída", "Sua conta foi excluída com sucesso.");

                // Fechar UsuarioView e abrir LoginView na mesma Stage
                new LoginView().start(stage);

            } catch (Exception e) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Ocorreu um erro ao excluir a conta: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Exibe um alerta na tela
     */
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String msg) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    //testes
    public static void main(String[] args) {
        launch(args);
    }
}
