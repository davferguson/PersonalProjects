package database.dao;

import database.model.PlayerModel;
import database.model.PositionModel;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

public class JdbcPlayerDao implements PlayerDao{

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public JdbcPlayerDao(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public PlayerModel getPlayerModel(int playerId) {
        return null;
    }

    @Override
    public PlayerModel createPlayerModel(PlayerModel playerModel) {
        String sql = "INSERT INTO player (username, password) VALUES (?, ?) RETURNING player_id";
        try{
            Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, playerModel.getUserName(), playerModel.getPassword());
            playerModel.setPlayerId(newId);
        } catch (DuplicateKeyException e){
            System.out.println("username already exists. " + e.getMessage());
        }

        return playerModel;
    }

    @Override
    public void deletePlayerModel(int playerId) {

    }

    @Override
    public boolean hasAssociatedPosition(PlayerModel curPlayer){
        String sql = "SELECT player_id FROM position WHERE player_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, curPlayer.getPlayerId());
        return rowSet.next();
    }

    @Override
    public PlayerModel mapRowToPlayerModel(SqlRowSet rowSet){
        PlayerModel playerModel = new PlayerModel();
        playerModel.setPlayerId(rowSet.getInt("player_id"));
        playerModel.setUserName(rowSet.getString("username"));
        playerModel.setPassword(rowSet.getString("password"));
        return playerModel;
    }
}
