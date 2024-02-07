package com.vladislavlevchik.actions.initActions;

import com.vladislavlevchik.WorldMap;
import com.vladislavlevchik.actions.Action;
import com.vladislavlevchik.actions.turnActions.StaticObjectsTrackingAction;

public class SaveStaticObjectsAction extends Action {
    @Override
    public void prefer(WorldMap worldMap) {
        StaticObjectsTrackingAction.addToStaticEntityMap(worldMap);
    }
}
