package bot;

import java.util.ArrayList;

import move.AttackTransferMove;
import move.PlaceArmiesMove;
public interface Strategy {
	public ArrayList<AttackTransferMove> executeAttackMoves(BotState state);
	public ArrayList<PlaceArmiesMove> executePlaceMoves(BotState state, int armiesLeft);
}
