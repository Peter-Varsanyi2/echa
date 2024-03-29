// Copyright 2014 theaigames.com (developers@theaigames.com)

//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at

//        http://www.apache.org/licenses/LICENSE-2.0

//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//	
//    For the full copyright and license information, please view the LICENSE
//    file that was distributed with this source code.

package bot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

import main.Region;
import move.AttackTransferMove;
import move.PlaceArmiesMove;

public class BotStarter implements Bot {
	@Override
	/**
	 * A method used at the start of the game to decide which player start with what Regions. 6 Regions are required to be returned.
	 * This example randomly picks 6 regions from the pickable starting Regions given by the engine.
	 * @return : a list of m (m=6) Regions starting with the most preferred Region and ending with the least preferred Region to start with 
	 */
	public ArrayList<Region> getPreferredStartingRegions(BotState state, Long timeOut) {
		int m = 6;
		ArrayList<Region> preferredStartingRegions = new ArrayList<Region>();
		for (int i = 0; i < m; i++) {
			double rand = Math.random();
			int r = (int) (rand * state.getPickableStartingRegions().size());
			int regionId = state.getPickableStartingRegions().get(r).getId();
			Region region = state.getFullMap().getRegion(regionId);

			if (!preferredStartingRegions.contains(region))
				preferredStartingRegions.add(region);
			else
				i--;
		}

		return preferredStartingRegions;
	}

	@Override
	/**
	 * This method is called for at first part of each round. This example puts two armies on random regions
	 * until he has no more armies left to place.
	 * @return The list of PlaceArmiesMoves for one round
	 */
	public ArrayList<PlaceArmiesMove> getPlaceArmiesMoves(BotState state, Long timeOut) {
		int armiesLeft = state.getStartingArmies();
		CaptureContinent captCont = new CaptureContinent();
		CrossToNewContinent crossToNewCont = new CrossToNewContinent();
		EarlyRush earlyRush = new EarlyRush();

		ArrayList<Strategy> strategyPlans = new ArrayList<>();

		strategyPlans.add(captCont);

		ArrayList<PlaceArmiesMove> placeArmiesMoves = new ArrayList<PlaceArmiesMove>();

		for (Strategy strat : strategyPlans) {
			System.err.println("This strat for place: " + strat);
			placeArmiesMoves.addAll(strat.executePlaceMoves(state, armiesLeft));
		}
		return placeArmiesMoves;
	}

	//
	// ArrayList<PlaceArmiesMove> placeArmiesMoves = new ArrayList<PlaceArmiesMove>();
	// String myName = state.getMyPlayerName();
	// int armies = 2;
	// int armiesLeft = state.getStartingArmies();
	// LinkedList<Region> visibleRegions = state.getVisibleMap().getRegions();
	//
	// while (armiesLeft > 0) {
	// for (Region currentRegion : visibleRegions) {
	// if (!currentRegion.ownedByPlayer(myName))
	// continue;
	//
	// CalculateEnemyMove(currentRegion, myName);
	// Region enemy = null;
	// for (Region x : currentRegion.getNeighbors()) {
	// if (x.ownedByPlayer(state.getOpponentPlayerName())) {
	// enemy = x;
	// }
	// }
	// if (enemy != null) {
	// int diff = enemy.getArmies() * 10 / 6 + 1 - currentRegion.getArmies();
	// if (diff > armiesLeft) {
	// diff = armiesLeft;
	// }
	// placeArmiesMoves.add(new PlaceArmiesMove(myName, currentRegion, diff));
	// armiesLeft -= diff;
	// } else {
	// if (armies > armiesLeft) {
	// armies = armiesLeft;
	// }
	// placeArmiesMoves.add(new PlaceArmiesMove(myName, currentRegion, armies));
	// armiesLeft -= armies;
	// }
	// }
	// // java.util.Map<Region, Integer> regionWeight = new HashMap<>();
	// // for (Region r : visibleRegions) {
	// // regionWeight.put(r, calculatePlaceWeight(r, state));
	// // }
	// // Entry<Region, Integer> maxEntry = null;
	// // for (Entry<Region, Integer> entry : regionWeight.entrySet()) {
	// // if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
	// // maxEntry = entry;
	// // }
	// // }
	// //
	// // for (Region r : visibleRegions) {
	// // if (r.ownedByPlayer(myName)) {
	// // placeArmiesMoves.add(new PlaceArmiesMove(myName, maxEntry.getKey(), armies));
	// // armiesLeft -= armies;
	// // }
	// // }
	// }
	//
	// return placeArmiesMoves;

	private void CalculateEnemyMove(Region currentRegion, String myName) {
		// TODO Auto-generated method stub

	}

	public class RegionComparator implements Comparator<Region> {

		@Override
		public int compare(Region source, Region target) {
			// System.err.println("Source: "+ source.getArmies() + " Target:" + target.getArmies());
			if (source.getArmies() >= (target.getArmies() * 10 / 6 + 1)) {
				return (target.getArmies() * 10 / 6) + 2;
			}
			return 0;
		}

	}

	boolean enemyAround(Region region, String botName) {
		for (Region n : region.getNeighbors()) {
			if (n.ownedByPlayer(botName))
				return true;
		}
		return false;
	}

	@Override
	/**
	 * This method is called for at the second part of each round. This example attacks if a region has
	 * more than 6 armies on it, and transfers if it has less than 6 and a neighboring owned region.
	 * @return The list of PlaceArmiesMoves for one round
	 */
	public ArrayList<AttackTransferMove> getAttackTransferMoves(BotState state, Long timeOut) {

		CaptureContinent captCont = new CaptureContinent();
		CrossToNewContinent crossToNewCont = new CrossToNewContinent();
		EarlyRush earlyRush = new EarlyRush();

		ArrayList<Strategy> strategyPlans = new ArrayList<>();

		strategyPlans.add(captCont);

		ArrayList<AttackTransferMove> attackTransferMoves = new ArrayList<AttackTransferMove>();

		for (Strategy strat : strategyPlans) {
			System.err.println("This strat is: " + strat);
			attackTransferMoves.addAll(strat.executeAttackMoves(state));
		}
		return attackTransferMoves;
	}

	// String myName = state.getMyPlayerName();
	//
	// int armies = 5;
	// RegionComparator comp = new RegionComparator();
	//
	// ArrayList<Region> targets = new ArrayList<>();
	//
	// for (Region targetRegion : state.getVisibleMap().getRegions()) {
	// if (targetRegion.ownedByPlayer(state.getOpponentPlayerName())) {
	// for (Region myRegion : targetRegion.getNeighbors()) {
	// if (myRegion.ownedByPlayer(myName)) {
	// int requiredArmies = comp.compare(myRegion, targetRegion);
	// if (requiredArmies > 0) {
	// if (myRegion.getArmies() > requiredArmies) {
	// attackTransferMoves.add(new AttackTransferMove(myName, myRegion, targetRegion,
	// requiredArmies));
	//
	// }
	// }
	// }
	// }
	// }
	// }
	//
	// for (Region fromRegion : state.getVisibleMap().getRegions()) {
	// if (fromRegion.ownedByPlayer(myName)) // do an attack
	// {
	//
	// ArrayList<Region> possibleToRegions = new ArrayList<Region>();
	// possibleToRegions.addAll(fromRegion.getNeighbors());
	//
	// for (Region toRegion : possibleToRegions) {
	//
	// int requiredArmies = comp.compare(fromRegion, toRegion);
	// if (requiredArmies > 0) {
	//
	// if (fromRegion.getArmies() > requiredArmies) {
	// attackTransferMoves
	// .add(new AttackTransferMove(myName, fromRegion, toRegion, requiredArmies));
	// }
	//
	// }
	// }
	// }
	//
	// }

	/**
	 * @param state
	 * @param myName
	 * @param comp
	 * @param toRegion
	 * @param myRegion
	 * @return
	 */

	public static void main(String[] args) {
		BotParser parser = new BotParser(new BotStarter());
		parser.run();
	}

}
