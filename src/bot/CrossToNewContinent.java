package bot;

import java.util.ArrayList;

import main.Map;
import main.Region;
import main.SuperRegion;
import move.AttackTransferMove;
import move.PlaceArmiesMove;

public class CrossToNewContinent implements Strategy {

	public ArrayList<Region> regionInSuperRegionByOwner(BotState state, SuperRegion superRegion, String playerName) {
		ArrayList<Region> ownRegions = new ArrayList<>();
		for(Region region : superRegion.getSubRegions()) {
			if (region.ownedByPlayer(playerName)) {
				ownRegions.add(region);
			}
		}
		return ownRegions;
	}
	
	@Override
	public ArrayList<AttackTransferMove> executeAttackMoves(BotState state) {
		Map map = state.getFullMap();
		ArrayList<SuperRegion> superRegionsToConquer = new ArrayList<>();
		for(SuperRegion superRegion : map.getSuperRegions()) {
			if (regionInSuperRegionByOwner(state, superRegion, state.getMyPlayerName()).isEmpty()) {
				superRegionsToConquer.add(superRegion);
			}
		}
		
		
		map.getSuperRegions();
//		for(Region region )
		return null;
	}

	@Override
	public ArrayList<PlaceArmiesMove> executePlaceMoves(BotState state, int armiesLeft) {
		// TODO Auto-generated method stub
		return null;
	}



}
