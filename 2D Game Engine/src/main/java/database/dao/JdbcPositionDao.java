package database.dao;

import database.model.PositionModel;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

public class JdbcPositionDao implements PositionDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcPositionDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public PositionModel getPositionModel(int playerId) {
        PositionModel positionModel = null;
        String sql = "SELECT * FROM position WHERE player_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, playerId);
        if(rowSet.next()){
            positionModel = mapRowToPositionModel(rowSet);
        }
        return positionModel;
    }

    @Override
    public PositionModel createPositionModel(PositionModel positionModel) {
        String sql = "INSERT INTO position (player_id, x_pos, y_pos) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, positionModel.getPlayerId(), positionModel.getxPos(), positionModel.getyPos());
        return positionModel;
    }

    @Override
    public void updatePositionModel(PositionModel positionModel) {
        String sql = "UPDATE position SET player_id = ?, x_pos = ?, y_pos = ?";
        jdbcTemplate.update(sql, positionModel.getPlayerId(), positionModel.getxPos(), positionModel.getyPos());
    }

    private PositionModel mapRowToPositionModel(SqlRowSet rowSet){
        PositionModel positionModel = new PositionModel();
        positionModel.setPlayerId(rowSet.getInt("player_id"));
        positionModel.setxPos(rowSet.getInt("x_pos"));
        positionModel.setyPos(rowSet.getInt("y_pos"));
        return positionModel;
    }
}
