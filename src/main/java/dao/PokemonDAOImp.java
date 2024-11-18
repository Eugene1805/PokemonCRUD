package dao;

import connection.DBConnection;
import model.Pokemon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAOImp implements PokemonDAO {
    private String tableName;

    public PokemonDAOImp(String tablename) {
        this.tableName = "pokemon";
    }

    public PokemonDAOImp() {this.tableName = "pokemon";}

    @Override
    public void createPokemon(Pokemon pokemon) throws SQLException, ClassNotFoundException {
        if(pokemon==null) return;
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO " + tableName + "(name) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, pokemon.getName());
        ps.execute();
    }

    @Override
    public Pokemon readPokemon(int id) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "SELECT id, name FROM " + tableName + " WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Pokemon pokemon = new Pokemon();
        if (rs.next()) {
            pokemon.setId(rs.getInt(1));
            pokemon.setName(rs.getString(2));
        }
        return pokemon;
    }

    @Override
    public void updatePokemon(Pokemon pokemon) throws SQLException, ClassNotFoundException {
        if(pokemon==null) return;
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "UPDATE " + tableName + " SET name = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, pokemon.getName());
        ps.setInt(2, pokemon.getId());
        ps.executeUpdate();
    }

    @Override
    public void deletePokemon(Pokemon pokemon) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "DELETE FROM " + tableName + " WHERE name = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, pokemon.getName());
        ps.executeUpdate();
    }

    @Override
    public List<Pokemon> getAllPokemons() throws SQLException, ClassNotFoundException {
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        Connection conn = DBConnection.getInstance().getConnection();
        String selectQuery = "SELECT id, name FROM " + tableName;
        PreparedStatement stmt = conn.prepareStatement(selectQuery);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Pokemon pokemon = new Pokemon();
            pokemon.setId(rs.getInt(1));
            pokemon.setName(rs.getString(2));
            pokemons.add( pokemon );
        }
        return pokemons;
    }
}
