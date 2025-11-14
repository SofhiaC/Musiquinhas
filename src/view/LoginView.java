package view;

import controller.UsuarioController;
import entities.Usuario;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginView extends Application {
    private final UsuarioController controller = new UsuarioController();

    @Override
    public void start(Stage stage) {
        // Inicializar dados de teste se banco estiver vazio
        inicializarDadosTeste();
        
        stage.setTitle("Musiquinhas - Login");

        // Cores e dimensões principais
        Color fundo = Color.web("#2A2A2A");
        Color branco = Color.WHITE;

        // Painel principal
        BorderPane root = new BorderPane();
        root.setPrefSize(1000, 700); // tamanho fixo grande
        root.setBackground(new Background(new BackgroundFill(fundo, CornerRadii.EMPTY, Insets.EMPTY)));

        // ---------- TOPO ----------
        VBox topo = new VBox(15);
        topo.setAlignment(Pos.CENTER);
        topo.setPadding(new Insets(30, 0, 10, 0));

        // Logo centralizado sem distorção
        ImageView logoView = carregarLogo();
        if (logoView != null) {
            topo.getChildren().add(logoView);
        } else {
            Label logoFallback = new Label("Musiquinhas");
            logoFallback.setTextFill(branco);
            logoFallback.setFont(Font.font("Arial", 28));
            topo.getChildren().add(logoFallback);
        }

        Label titulo = new Label("Login");
        titulo.setTextFill(branco);
        titulo.setFont(Font.font("Arial", 26));
        topo.getChildren().add(titulo);

        root.setTop(topo);

        // ---------- CENTRO ----------
        VBox centro = new VBox(20);
        centro.setAlignment(Pos.CENTER);
        centro.setPadding(new Insets(10, 0, 0, 0));

        // Campos de entrada
        VBox camposBox = new VBox(15);
        camposBox.setAlignment(Pos.CENTER);

        Label lblEmail = new Label("Email");
        lblEmail.setTextFill(branco);
        TextField emailField = new TextField();
        estilizarCampo(emailField);

        Label lblSenha = new Label("Senha");
        lblSenha.setTextFill(branco);
        PasswordField senhaField = new PasswordField();
        estilizarCampo(senhaField);

        camposBox.getChildren().addAll(lblEmail, emailField, lblSenha, senhaField);

        // Botão de login
        Button btnLogin = new Button("Entrar");
        btnLogin.setFont(Font.font("Arial", 16));
        btnLogin.setTextFill(branco);
        btnLogin.setStyle("-fx-background-color: #7E6FBB; -fx-background-radius: 8;");
        btnLogin.setPrefWidth(160);
        btnLogin.setPrefHeight(40);
        btnLogin.setAlignment(Pos.CENTER);
        btnLogin.setOnAction(e -> {
            String email = emailField.getText().trim();
            String senha = senhaField.getText();

            if (email.isEmpty() || senha.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Erro", "Informe email e senha.");
                return;
            }

            Usuario u = controller.autenticar(email, senha);
            if (u != null) {
                // Usuário foi salvo na SessionManager automaticamente pelo controller
                mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Bem-vindo, " + u.getNome() + "!");
                // Abrir HomeView na MESMA Stage do LoginView
                try {
                    new HomeView().start(stage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Falha", "Credenciais inválidas.");
            }
        });

        centro.getChildren().addAll(camposBox, btnLogin);
        root.setCenter(centro);

        // ---------- CENA ----------
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void estilizarCampo(TextField field) {
        field.setPrefWidth(80);
        field.setPrefHeight(36);
        field.setMaxWidth(200);
        field.setStyle("-fx-background-color: white; -fx-border-radius: 6; -fx-background-radius: 6;");
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String msg) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private ImageView carregarLogo() {
        try {
            Image img = new Image(getClass().getResourceAsStream("/resources/Musiquinhas.png"));
            ImageView view = new ImageView(img);
            view.setPreserveRatio(true);
            view.setFitWidth(400);
            view.setSmooth(true);
            return view;
        } catch (Exception e) {
            System.out.println("Logo não encontrada.");
            return null;
        }
    }

    /**
     * Inicializa dados de teste se o banco estiver vazio.
     */
    private void inicializarDadosTeste() {
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

    // ---------- TELA PÓS-LOGIN ----------
    // Movida para MainView.java

    public static void main(String[] args) {
        launch(args);
    }
}
