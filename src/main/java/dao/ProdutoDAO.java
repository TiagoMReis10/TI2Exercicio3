package dao;

import model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO{

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/produtosExercicio3TI2", 
            "tiagomreis",  
            "nina"     
        );
    }

    // Adiciona produto
    public void adicionar(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto (nome, preco, quantidade, fabricacao, validade) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setDate(4, java.sql.Date.valueOf(produto.getFabricacao()));
            stmt.setDate(5, java.sql.Date.valueOf(produto.getValidade()));
            stmt.executeUpdate();
        }
    }
    
    //Função de excluir os produtos
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM produto WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Lista todos os produtos
    public List<Produto> listarTodos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto ORDER BY id";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setFabricacao(rs.getDate("fabricacao").toLocalDate());
                p.setValidade(rs.getDate("validade").toLocalDate());
                produtos.add(p);
            }
        }
        
        
        return produtos;
    }
}
