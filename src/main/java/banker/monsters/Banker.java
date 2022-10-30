package banker.monsters;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * banker 300(320)hp:
 *
 * mortgage attack:
 * * X daBanker now
 * * Or can loan highest rarity card in draw pile (assuming you have common+ rarity in draw):
 * ** Rare card, X/3 interest daBanker over four turns
 * ** Uncommon card, x/2 interest over three turns
 * ** Common card, x interest daBanker over two turns
 * ** You can default on the loan if you refuse to take daBanker for that turn, this removes the loaned card from your deck permanently and makes the Banker summon the bailiff
 * *** Bailiff does interest-amount daBanker per turn, has 60 hp
 *
 * auction:
 * * offers a choice between three colorless cards (that are boss usable), you must take one, the others are used against you:
 * ** Trip - every turn
 * ** Blind - every turn
 * ** Swift Strike - every turn
 * ** Good Instincts - every turn
 * ** Hand of Greed - every three turns
 * ** The Bomb - every three turns
 * ** Panic Button - once
 * ** Dark Shackles - once
 * ** Panacea - once
 *
 *
 * Actions:
 * * Standard attack (bully) - 15 daBanker
 * ** On even turns
 * * Auction
 * ** 50% chance on odd turns
 * ** Can only be used three times total as there are 9 cards
 * * Mortgage attack - 30(36) daBanker
 * ** 50% chance on odd turns
 */
public class Banker extends AbstractMonster {
    public static final byte MORTGAGE_ATTACK_MOVE = (byte) 1;
    public static final byte AUCTION_MOVE = (byte) 2;
    public static final byte STANDARD_ATTACK_MOVE = (byte) 3;
    public static final List<Byte> MOVES_ENUM = Arrays.asList(MORTGAGE_ATTACK_MOVE, AUCTION_MOVE, STANDARD_ATTACK_MOVE);
    private static final String tempImgUrl = "img/monsters/banker/Banker.png";

    public List<Byte> _movesRecord = new ArrayList<>();
    public Banker() {
        this(200.0F, -10.0F);
    }
    private Banker(float offsetX, float offsetY) {
        super("Banker", "banker", 420, 0.0F, 40.0F, 220.0F, 400.0F, tempImgUrl, offsetX, offsetY);
    }

    @Override
    public void takeTurn() {

    }

    @Override
    public void getMove(int num) {

    }
}

