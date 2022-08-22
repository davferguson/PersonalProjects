package database.dao;

import database.model.PlayerModel;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface PlayerDao {

    PlayerModel getPlayerModel(int playerId);

    PlayerModel createPlayerModel(PlayerModel playerModel);

    void deletePlayerModel(int playerId);

    boolean hasAssociatedPosition(PlayerModel curPlayer);

    public PlayerModel mapRowToPlayerModel(SqlRowSet rowSet);
}
