package dao.impl;

import dao.SessionDao;
import dao.model.Session;
import util.ConnectionManager;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class UserSessionDao implements SessionDao<Session> {

    private static final UserSessionDao INSTANCE = new UserSessionDao();


    private static final String SQL_SAVE = """
            INSERT INTO sessions (id, user_id, expires_at) 
            VALUES (?,?,?)
            """;



    private static final String SQL_FIND_BY_ID = """ 
            SELECT id,user_id,expires_at
            FROM sessions
            WHERE id = ?
            """;


    private static final String SQL_DELETE_BY_TIME = """
            DELETE FROM sessions
            WHERE expires_at <= ?
            """;



    private UserSessionDao() {
    }


    @Override
    public Optional<Session> findById(UUID id) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setString(1, id.toString());
            ResultSet resultSet = ps.executeQuery();
            Session session = null;
            if (resultSet.next()) {
                Timestamp ts = resultSet.getTimestamp("expires_at");
                LocalDateTime time = null;
                if (ts != null)
                    time = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneOffset.UTC);
                session = new Session(UUID.fromString(resultSet.getString("id")), resultSet.getLong("user_id"), time);
            }
            return Optional.ofNullable(session);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void deleteSessionExpiredAtTime(LocalDateTime currentTime) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BY_TIME)) {
            Timestamp ts = new Timestamp(currentTime.toInstant(ZoneOffset.UTC)
                    .toEpochMilli());
            ps.setTimestamp(1, ts);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void save(Session entity) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement ps = connection.prepareStatement(SQL_SAVE)) {

            ps.setString(1, entity.getId()
                    .toString());
            ps.setLong(2, entity.getUserId());
            LocalDateTime expiresAt = entity.getExpiresAt();
            Timestamp ts = new Timestamp(expiresAt.toInstant(ZoneOffset.UTC)
                    .toEpochMilli());
            ps.setTimestamp(3, ts);

            ps.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public static UserSessionDao getInstance() {
        return INSTANCE;
    }


    }

