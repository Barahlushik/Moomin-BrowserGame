package dao.impl;

import dao.Dao;
import dao.model.MoominSon;
import dao.model.Winner;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MoominWinnerDao implements Dao<Long, Winner> {
    private static final MoominWinnerDao INSTANCE = new MoominWinnerDao();
    private static final String SQL_SAVE = """
    INSERT INTO winners (login, count_sec)
    VALUES (?,?)
    """;

    private static final String SQL_FIND_ALL = """
    SELECT id, login, count_sec 
    FROM winners
    """;



    public List<Winner> findAll() {
        try(Connection connection = ConnectionManager.open();
        PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL)) {
            ResultSet resultSet = ps.executeQuery();
            List<Winner> winners = new ArrayList<>();
            while (resultSet.next()) {
                winners.add(new Winner(resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getLong("count_sec")));
            }
            return winners;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Optional<Winner> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Winner save(Winner winner) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement ps = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, winner.getLogin());
            ps.setLong(2, winner.getSeconds());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                winner.setId(generatedKeys.getLong("id"));
            }
            return winner;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static MoominWinnerDao getInstance() {
        return INSTANCE;
    }
}
