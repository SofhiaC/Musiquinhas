package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AssinaturaView {

    public void start(Stage stage) {

        // ----- Título -----
        Label titulo = new Label("Assine Musiquinhas Premium!");
        titulo.setStyle("-fx-font-size: 32px; -fx-text-fill: white;");

        // ----- Layout Principal -----
        VBox container = new VBox(40);
        container.setAlignment(Pos.CENTER);

        // ----- GRID COM 4 ASSINATURAS -----
        GridPane grid = new GridPane();
        grid.setHgap(40);
        grid.setVgap(40);
        grid.setAlignment(Pos.CENTER);

        // Adiciona 4 planos no grid 2x2
        grid.add(criarPlano("Plano Mensal", "Individual", "R$ 15,90/mês", "1 mês",
                "Sem anúncios, músicas ilimitadas"), 0, 0);

        grid.add(criarPlano("Plano Duo", "Dupla", "R$ 14,90", "1 mês",
                "Sem anúncios, áudio HD"), 1, 0);

        grid.add(criarPlano("Plano Família", "Para 5 pessoas", "R$ 29,90", "1 mês",
                "2 contas, músicas ilimitadas"), 0, 1);

        grid.add(criarPlano("Plano Estudante", "Individual", "R$ 8,90", "1 mês",
                "5 contas, áudio premium"), 1, 1);

        container.getChildren().addAll(titulo, grid);

        // ----- Botão Voltar -----
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setStyle("-fx-background-color: #42327a; -fx-text-fill: white; -fx-padding: 10 20;");
        btnVoltar.setOnAction(e -> {
            HomeView home = new HomeView();
            home.start(stage); // ← volta para a HOME no mesmo stage
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
    private VBox criarPlano(String tituloPlano, String tipo, String preco, String meses, String beneficios) {

        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(15));
        box.setStyle("-fx-background-color: #42327a; -fx-background-radius: 10;");
        box.setPrefSize(350, 180); // <<< TAMANHO REDUZIDO

        Label lblTitulo = new Label(tituloPlano);
        Label lblTipo = new Label(tipo);
        Label lblPreco = new Label(preco);
        Label lblMeses = new Label(meses);
        Label lblBeneficios = new Label(beneficios);

        lblTitulo.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        lblTipo.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");
        lblPreco.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");
        lblMeses.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");
        lblBeneficios.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");

        Button btnAssinar = new Button("Assinar");
        btnAssinar.setStyle("-fx-background-color: #bfa8ff; -fx-padding: 6 12; -fx-font-size: 14px;");
        btnAssinar.setOnAction(e -> {
            System.out.println("Usuário assinou → " + tituloPlano);
        });

        box.getChildren().addAll(lblTitulo, lblTipo, lblPreco, lblMeses, lblBeneficios, btnAssinar);
        return box;
    }
}
