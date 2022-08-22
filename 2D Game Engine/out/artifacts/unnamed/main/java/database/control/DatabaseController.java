package database.control;


import database.dao.JdbcPlayerDao;
import database.dao.JdbcPositionDao;
import database.dao.PlayerDao;
import database.dao.PositionDao;
import database.model.PlayerModel;
import database.model.PositionModel;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class DatabaseController {

    private String databaseUrl = "jdbc:postgresql://localhost:5432/PlayerData";
    private final PlayerDao playerDao;
    private final PositionDao positionDao;
    private final JdbcTemplate jdbcTemplate;

    public DatabaseController(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        playerDao = new JdbcPlayerDao(dataSource);
        positionDao = new JdbcPositionDao(dataSource);
    }

    public PlayerModel login(String username, String password){
        PlayerModel curPlayer = null;
        String sql = "SELECT player_id, username, password FROM player WHERE username = ? AND password = ?";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, username, password);
        if(row.next()){
            curPlayer = playerDao.mapRowToPlayerModel(row);
            if(!playerDao.hasAssociatedPosition(curPlayer)){
                positionDao.createPositionModel(new PositionModel(curPlayer.getPlayerId()));
            }
        }
        return curPlayer;
    }

    public void updatePosition(PositionModel positionModel){
        positionDao.updatePositionModel(positionModel);
    }

    public PositionModel getCurrentPosition(PlayerModel curPlayer){
        if(curPlayer != null){
            return positionDao.getPositionModel(curPlayer.getPlayerId());
        }
        System.out.println("PlayerModel is null.");
        return null;
    }

}
