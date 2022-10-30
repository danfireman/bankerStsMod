package banker.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import banker.BankerBossMod;

import java.util.Collections;
import java.util.Random;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.dungeons.TheBeyond",
        method = "initializeBoss"
)
public class SetBeyondBoss {
    public SetBeyondBoss() {
    }

    @SpirePrefixPatch
    public static SpireReturn<?> BossSwapper(TheBeyond __instance) {
        if (BankerBossMod.forceMirrorBoss) {
            TheBeyond.bossList.clear();
            TheBeyond.bossList.add(BankerBossMod.BankerBoss);
            Collections.shuffle(TheBeyond.bossList, new Random(TheBeyond.monsterRng.randomLong()));
        }
        else {
            TheBeyond.bossList.clear();
            TheBeyond.bossList.add("Donu and Deca");
            TheBeyond.bossList.add("Awakened One");
            TheBeyond.bossList.add("Time Eater");
        }
        return SpireReturn.Return(null);
    }
}
