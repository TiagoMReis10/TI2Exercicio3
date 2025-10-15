package app;

import static spark.Spark.*;
import dao.ProdutoDAO;
import model.Produto;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aplicacao {

    public static void main(String[] args) throws Exception {
        port(4567);
        staticFiles.location("/public"); // para servir index.html

        ProdutoDAO dao = new ProdutoDAO();

     // Lista produtos e mostra no HTML
        get("/", (req, res) -> {
            List<Produto> produtos = dao.listarTodos();

            // --- ADICIONE A LINHA DE DEBUG AQUI ---
            System.out.println(">>> O método listarTodos() retornou " + produtos.size() + " produtos.");

            Map<String, Object> model = new HashMap<>();
            model.put("produtos", produtos);
            return new ModelAndView(model, "index.html");
        }, new MustacheTemplateEngine());
        
        
        // Recebe o formulário e adiciona produto
        post("/add", (req, res) -> {
            String nome = req.queryParams("nome");
            double preco = Double.parseDouble(req.queryParams("preco"));
            int quantidade = Integer.parseInt(req.queryParams("quantidade"));
            LocalDate fabricacao = LocalDate.parse(req.queryParams("fabricacao"));
            LocalDate validade = LocalDate.parse(req.queryParams("validade"));

            dao.adicionar(new Produto(nome, preco, quantidade, fabricacao, validade));
            res.redirect("/");
            return null;
        });
        
        //Função de excluir o produto
        post("/delete/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            dao.excluir(id);
            res.redirect("/");
            return null;
        });
    }
}
