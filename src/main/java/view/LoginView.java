package view;

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
import controller.UsuarioController;
import entities.Usuario;

public class LoginView extends Application {
    private final UsuarioController controller = new UsuarioController();

    @Override
    public void start(Stage stage) {
        
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
            Image img = new Image(getClass().getResourceAsStream("./src/images/Musiquinhas.png"));
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

    public static void main(String[] args) {
        launch(args);
    }
}
