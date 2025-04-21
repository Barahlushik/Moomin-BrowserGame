package dao.impl;

import dao.Dao;
import dao.model.MoominSon;
import util.ConnectionManager;

import java.sql.*;
import java.util.Optional;

public class MoominDao implements Dao<Long, MoominSon> {

    private static final MoominDao INSTANCE = new MoominDao();

    private static final String SQL_DELETE = """
    DELETE FROM moomins
    WHERE id = ?
    """;
    private static final String SQL_SAVE = """
    INSERT INTO moomins (username, password)
    VALUES (?,?)
    """;

    private static final String SQL_FIND_BY_LOGIN = """
    SELECT username, password
    FROM moomins 
    WHERE username = ? 
    """;
    private static final String SQL_FIND_BY_ID = """
    SELECT username, password
    FROM moomins
    WHERE id = ? 
    """;

    private MoominDao(){}



    public Optional<MoominSon> findByLogin(String id) {
        try(Connection connection = ConnectionManager.open();
        PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_LOGIN)) {
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            MoominSon user = null;
            if (resultSet.next()) {
                user = new MoominSon(resultSet.getLong("id"), resultSet.getString("username"),
                        resultSet.getString("password"));
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<MoominSon> findById(Long id) {
        try(Connection connection = ConnectionManager.open();
            PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            MoominSon user = null;
            if (resultSet.next()) {
                user = new MoominSon(id, resultSet.getString("username"),
                        resultSet.getString("password"));
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE)
        ) {
            ps.setLong(1 , id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public MoominSon save(MoominSon user) {
        try (Connection connection = ConnectionManager.open();
        PreparedStatement ps = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    public static MoominDao getInstance() {
        return INSTANCE;
    }
}
