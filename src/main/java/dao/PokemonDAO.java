package dao;

import model.Pokemon;

import java.sql.SQLException;
import java.util.List;

public interface PokemonDAO {
    public void createPokemon(Pokemon pokemon) throws SQLException, ClassNotFoundException;
    public Pokemon readPokemon(int id) throws SQLException, ClassNotFoundException;
    public void updatePokemon(Pokemon pokemon) throws SQLException, ClassNotFoundException;
    public void deletePokemon(Pokemon pokemon) throws SQLException, ClassNotFoundException;
    public List<Pokemon> getAllPokemons() throws SQLException, ClassNotFoundException;
}
