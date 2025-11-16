package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import controller.AssinaturaController;
import entities.Assinatura;

import java.util.List;

public class AssinaturaView {

    public void start(Stage stage) {

        AssinaturaController controller = new AssinaturaController();
        List<Assinatura> assinaturas = controller.listarAssinaturas();

        // ----- Título -----
        Label titulo = new Label("Assine Musiquinhas Premium!");
        titulo.setStyle("-fx-font-size: 32px; -fx-text-fill: white;");

        // ----- Grid que vai receber os planos -----
        GridPane grid = new GridPane();
        grid.setHgap(40);
        grid.setVgap(40);
        grid.setAlignment(Pos.CENTER);

        // Adiciona as assinaturas no grid dinamicamente (2 colunas)
        int col = 0;
        int row = 0;

        for (Assinatura a : assinaturas) {
            grid.add(criarPlano(a), col, row);

            col++;
            if (col == 2) { // quando chegar na 2ª coluna, muda de linha
                col = 0;
                row++;
            }
        }

        // ----- Container principal -----
        VBox container = new VBox(40);
        container.setAlignment(Pos.CENTER);
        container.getChildren().addAll(titulo, grid);

        // ----- Botão Voltar -----
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setStyle("-fx-background-color: #42327a; -fx-text-fill: white; -fx-padding: 10 20;");
        btnVoltar.setOnAction(e -> {
            HomeView home = new HomeView();
            home.start(stage);
        });

        HBox boxVoltar = new HBox(btnVoltar);
        boxVoltar.setAlignment(Pos.CENTER);
        boxVoltar.setPadding(new Insets(20));

        // ----- BorderPane -----
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #292929;");
        root.setCenter(container);
        root.setBottom(boxVoltar);

        Scene cena = new Scene(root, 1000, 700);
        stage.setScene(cena);
        stage.setTitle("Assinaturas");
        stage.show();
    }

    // ----- Método para criar cada plano -----
    private VBox criarPlano(Assinatura a) {

        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(15));
        box.setStyle("-fx-background-color: #42327a; -fx-background-radius: 10;");
        box.setPrefSize(350, 180);

        Label lblTitulo = new Label(a.getNome());
        Label lblTipo = new Label(a.getTipo());
        Label lblPreco = new Label(String.valueOf(a.getPreco()));
        Label lblDuracao = new Label(a.getDuracao());
        Label lblBeneficios = new Label(a.getBeneficios());

        lblTitulo.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        lblTipo.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");
        lblPreco.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");
        lblDuracao.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");
        lblBeneficios.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");

        Button btnAssinar = new Button("Assinar");
        btnAssinar.setStyle("-fx-background-color: #bfa8ff; -fx-padding: 6 12; -fx-font-size: 14px;");
        btnAssinar.setOnAction(e -> {
            System.out.println("Usuário assinou → " + a.getNome());
        });

        box.getChildren().addAll(lblTitulo, lblTipo, lblPreco, lblDuracao, lblBeneficios, btnAssinar);
        return box;
    }
}
