package bot;

import java.util.ArrayList;

import main.Region;
import main.SuperRegion;
import move.AttackTransferMove;
import move.PlaceArmiesMove;

public class CaptureContinent implements Strategy {

	// private ArrayList<Region> regionsNotOwnedInSuperRegion(BotState state, SuperRegion superRegion, String
	// playerName) {
	// ArrayList<Region> notOwned = new ArrayList<Region>();
	// for (Region region : superRegion.getSubRegions()) {
	// if (!region.ownedByPlayer(playerName)) {
	// notOwned.add(region);
	// }
	// }
	// return notOwned;
	// }

	@Override
	public ArrayList<PlaceArmiesMove> executePlaceMoves(BotState state, int armiesLeft) {

		ArrayList<PlaceArmiesMove> moves = new ArrayList<>();
//		ArrayList<Region> regionToConquer = new ArrayList<>();
		// regionToConquer = getRegionsToConquer(state);
		// System.err.println("debug1");
		for (Region target : state.getVisibleMap().regions) {
			if (target.ownedByPlayer(state.getMyPlayerName())) {
				System.err.println(state.getRoundNumber() + ".round Placing " + armiesLeft + " on id: "
						+ target.getId());
				moves.add(new PlaceArmiesMove(state.getMyPlayerName(), target, armiesLeft));
			}
		}
		return moves;
	}

	private ArrayList<Region> getRegionsToConquer(BotState state) {
		ArrayList<Region> regionToConquer = new ArrayList<>();
		String playerName = state.getMyPlayerName();

		SuperRegion best = WarlightUtil.bestSuperRegionByPercent(state.getVisibleMap().getSuperRegions(), state,
				playerName);
		//System.err.println();

		for (Region region : best.getSubRegions()) {
			if (!region.ownedByPlayer(playerName)) {
				regionToConquer.add(region);
			}

		}
		System.out.println("Regions: "+ regionToConquer.size());
		return regionToConquer;
	}

	@Override
	public ArrayList<AttackTransferMove> executeAttackMoves(BotState state) {
		String playerName = state.getMyPlayerName();
		ArrayList<AttackTransferMove> moves = new ArrayList<>();
		ArrayList<Region> regionToConquer = getRegionsToConquer(state);
		for (Region region : regionToConquer) {
			int armies = WarlightUtil.getArmiesAroundRegion(region, state.getMyPlayerName());
			System.err.println(state.getRoundNumber() + ".round Armies required to attack region: " + armies
					+ " regionId " + region.getId());
			System.err.println(state.getRoundNumber() + ".round Should defeat?: "
					+ WarlightUtil.shouldDefeat(region.getArmies(), armies));

			if (WarlightUtil.shouldDefeat(region.getArmies(), armies)) {
				moves.addAll(WarlightUtil.attackRegionWithNearbyRegions(region, armies, playerName));
			}
		}

		return moves;
	}
}
