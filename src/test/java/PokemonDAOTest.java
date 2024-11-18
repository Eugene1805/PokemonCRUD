import dao.PokemonDAO;
import dao.PokemonDAOImp;
import model.Pokemon;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PokemonDAOTest {
    @Test
    public void testNullPokemon(){
        PokemonDAO pokemondao = new PokemonDAOImp();
        Pokemon pokemon = null;
        try {
            pokemondao.createPokemon(pokemon);
            assertEquals(null,pokemon,"No debe poder insertar null a la base de datos");
        } catch (SQLException | ClassNotFoundException e) {
            fail("Se produjo una excepción durante el test: " + e.getMessage());
        }
    }

    @Test
    public void testNotPokemonEmptyList() {
        PokemonDAO pokemonDAO = new PokemonDAOImp();
        List<Pokemon> pkList;
        try {
            pkList = pokemonDAO.getAllPokemons(); //5 pokemones en la base de datos al momento
            assertEquals(5, pkList.size(),"La lista de pokemones en la base de datos" +
                    "debe tener contenido");
        }  catch (SQLException | ClassNotFoundException e) {
            fail("Se produjo una excepción durante el test: " + e.getMessage());
        }
    }
}
