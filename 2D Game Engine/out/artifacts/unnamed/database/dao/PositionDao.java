package database.dao;

import database.model.PositionModel;

public interface PositionDao {
    PositionModel getPositionModel(int playerId);
    PositionModel createPositionModel(PositionModel positionModel);
    void updatePositionModel(PositionModel positionModel);
}
