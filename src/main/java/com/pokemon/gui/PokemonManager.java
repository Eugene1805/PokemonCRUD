package com.pokemon.gui;

import dao.PokemonDAO;
import dao.PokemonDAOImp;
import model.Pokemon;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PokemonManager extends JFrame {
    private DefaultListModel<String> pokemonListModel;
    private JList<String> pokemonList;
    private JTextField searchField;
    private ArrayList<String> pokemons;

    public PokemonManager() {
        setTitle("Gestion de Pokemon");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        pokemons = new ArrayList<>();
        pokemonListModel = new DefaultListModel<>();
        pokemonList = new JList<>(pokemonListModel);
        searchField = new JTextField();

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel searchLabel = new JLabel("Buscar Pokemon:");
        topPanel.add(searchLabel, BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);

        JButton addButton = new JButton("Agregar");
        JButton updateButton = new JButton("Actualizar");
        JButton deleteButton = new JButton("Eliminar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(pokemonList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        searchField.addActionListener(e -> filterPokemonList());

        addButton.addActionListener(e -> addPokemon());
        updateButton.addActionListener(e -> updatePokemon());
        deleteButton.addActionListener(e -> deletePokemon());

        loadInitialPokemons();
    }

    private void loadInitialPokemons() {
        PokemonDAO pkD = new PokemonDAOImp();
        ArrayList<Pokemon> p;
        try {
            p = (ArrayList<Pokemon>) pkD.getAllPokemons();
            pokemons = (ArrayList<String>) p.stream()
                    .map(Pokemon::getName)
                    .collect(Collectors.toList());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        updatePokemonList();
    }

    private void updatePokemonList() {
        pokemonListModel.clear();
        for (String pokemon : pokemons) {
            pokemonListModel.addElement(pokemon);
        }
    }

    private void filterPokemonList() {
        String searchText = searchField.getText().toLowerCase();
        pokemonListModel.clear();
        for (String pokemon : pokemons) {
            if (pokemon.toLowerCase().contains(searchText)) {
                pokemonListModel.addElement(pokemon);
            }
        }
    }

    private void addPokemon() {
        String newPokemon = JOptionPane
                .showInputDialog(this, "Ingrese el nombre del nuevo Pokemon:");
        if (newPokemon != null && !newPokemon.trim().isEmpty()) {
            pokemons.add(newPokemon.trim());
            PokemonDAO pkD = new PokemonDAOImp();
            Pokemon p = new Pokemon(newPokemon);
            try {
                pkD.createPokemon(p);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            updatePokemonList();
        }
    }

    private void updatePokemon() {
        String selectedPokemon = pokemonList.getSelectedValue();
        if (selectedPokemon != null) {
            String updatedPokemon = JOptionPane
                    .showInputDialog(this, "Actualizar nombre de Pokemon:", selectedPokemon);
            if (updatedPokemon != null && !updatedPokemon.trim().isEmpty()) {
                pokemons.set(pokemons.indexOf(selectedPokemon), updatedPokemon.trim());
                PokemonDAO pkD = new PokemonDAOImp();
                Pokemon p = new Pokemon(selectedPokemon);
                try {
                    pkD.updatePokemon(p);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                updatePokemonList();
            }
        } else {
            JOptionPane
                    .showMessageDialog(this, "Seleccione un Pokemon para actualizar.");
        }
    }

    private void deletePokemon() {
        String selectedPokemon = pokemonList.getSelectedValue();
        if (selectedPokemon != null) {
            pokemons.remove(selectedPokemon);
            PokemonDAO pkD = new PokemonDAOImp();
            Pokemon p = new Pokemon(selectedPokemon);
            try {
                pkD.deletePokemon(p);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            updatePokemonList();
        } else {
            JOptionPane
                    .showMessageDialog(this,
                            "Seleccione un Pokemon para eliminar.");
        }
    }
}