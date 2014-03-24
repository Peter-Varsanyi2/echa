import static org.junit.Assert.*;

import java.util.ArrayList;

import io.IORobot;
import main.Player;

import org.junit.Test;

import bot.Bot;
import bot.BotStarter;
import bot.BotState;
import move.AttackTransferMove;

public class testCase1 {

	@Test
	public void test() {
		Bot bot1 = new BotStarter();
		BotState state = new BotState();
		String[] setupMap = {
				"setup_map super_regions 1 5 2 2 3 5 4 3 5 7 6 2",
				"setup_map regions 1 1 2 1 3 1 4 1 5 1 6 1 7 1 8 1 9 1 10 2 11 2 12 2 13 2 14 3 15 3 16 3 17 3 18 3 19 3 20 3 21 4 22 4 23 4 24 4 25 4 26 4 27 5 28 5 29 5 30 5 31 5 32 5 33 5 34 5 35 5 36 5 37 5 38 5 39 6 40 6 41 6 42 6",
				"setup_map neighbors 1 2,4,30 2 4,3,5 3 5,6,14 4 5,7 5 6,7,8 6 8 7 8,9 8 9 9 10 10 11,12 11 12,13 12 13,21 14 15,16 15 16,18,19 16 17 17 19,20,27,32,36 18 19,20,21 19 20 20 21,22,36 21 22,23,24 22 23,36 23 24,25,26,36 24 25 25 26 27 28,32,33 28 29,31,33,34 29 30,31 30 31,34,35 31 34 32 33,36,37 33 34,37,38 34 35 36 37 37 38 38 39 39 40,41 40 41,42 41 42"
		};
		String[] scenario1 = {
				
		};
		String[] updateMap = {
				"update_map 15 player1 6 16 player1 6 35 player1 4 14 neutral 2 18 neutral 2 19 neutral 2 17 neutral 2 30 neutral 2 34 neutral 2"
		};
		state.setupMap(setupMap);
		state.updateMap(updateMap);
		ArrayList<AttackTransferMove> moves;
		moves = bot1.getAttackTransferMoves(state, 2000l);
		for(AttackTransferMove move : moves) {
			System.err.println(move.getString());
		}
//		fail("Not yet implemented");

//		int startingArmies = 5;
//		Player player1 = new Player("player1", bot1, startingArmies);
//
//		bot1.writeInfo("settings your_bot " + player1.getName());
//		bot1.writeInfo("settings opponent_bot " + player2.getName());
//		sendSetupMapInfo(player1.getBot(), initMap);
//		sendSetupMapInfo(player2.getBot(), initMap);

	}
}
