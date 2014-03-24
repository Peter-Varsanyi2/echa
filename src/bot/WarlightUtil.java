package bot;

import java.util.ArrayList;
import java.util.LinkedList;

import main.Region;
import main.SuperRegion;
import move.AttackTransferMove;

public class WarlightUtil {

	public static boolean shouldDefeat(int source, int target) {
		if (source > target * 1.66 + 1) {
			return true;
		}
		return false;
	}

	public static SuperRegion bestSuperRegionByPercent(LinkedList<SuperRegion> linkedList, BotState state,
			String playerName) {
		SuperRegion best = null;
		for (SuperRegion superRegion : state.getVisibleMap().getSuperRegions()) {
			if (best == null || superRegionPercent(state, superRegion, playerName) > superRegionPercent(state, best, playerName)) {
				best = superRegion;
			}

		}
		return best;
	}

	public static int superRegionPercent(BotState state, SuperRegion superRegion, String playerName) {

		int owned = 0;

		for (Region region : superRegion.getSubRegions()) {
			if (region.ownedByPlayer(playerName)) {
				owned++;
			}
		}
		return owned * 100 / superRegion.getSubRegions().size();

	}

	public static int getArmiesAroundRegion(Region region, String playerName) {
		int armiesCount = 0;
		for (Region regionNeighbour : region.getNeighbors()) {
			if (regionNeighbour.ownedByPlayer(playerName)) {
				armiesCount += regionNeighbour.getArmies();
			}
		}
		return armiesCount;
	}

	public static ArrayList<AttackTransferMove> attackRegionWithNearbyRegions(Region target, int armies,
			String playerName) {
		ArrayList<AttackTransferMove> moves = new ArrayList<>();
		int movesUsed = 0;
		for (Region attackRegion : target.getNeighbors()) {
			if (movesUsed >= armies) {
				continue;
			}
			if (attackRegion.ownedByPlayer(playerName)) {
				moves.add(new AttackTransferMove(playerName, attackRegion, target, attackRegion.getArmies() - 1));
				movesUsed += attackRegion.getArmies() - 1;

			}
		}
		return moves;
	}

}
