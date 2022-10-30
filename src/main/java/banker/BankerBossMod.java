package banker;

import banker.monsters.Banker;
import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import basemod.interfaces.PostInitializeSubscriber;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@SpireInitializer
public class BankerBossMod implements PostInitializeSubscriber {
    public static final Logger logger = LogManager.getLogger(BankerBossMod.class.getName());
    public static boolean forceMirrorBoss = true;
    public static String replaceMirrorBoss = "Banker Boss";
    public static final boolean DEBUGMODE  = false;
    public BankerBossMod() {
        BaseMod.subscribe(this);
        logger.info("Subscribed to Banker Boss Mod!");
        // TODO: make an awesome mod!
    }

    private static SpireConfig makeConfig() {
        Properties defaultProperties = new Properties();
        defaultProperties.setProperty(FORCE_MIRROR_BOSS_PROPERTY, Boolean.toString(true));
        defaultProperties.setProperty(REPLACE_MIRROR_BOSS_PROPERTY, "Banker Boss");

        try {
            return new SpireConfig("Banker Boss", "Banker-boss-config", defaultProperties);
        } catch (IOException var2) {
            return null;
        }
    }

    public static void initialize() {
        new BankerBossMod();
        config = makeConfig();
        setProperties();
    }

    private static final String FORCE_MIRROR_BOSS_PROPERTY = "force-Banker-boss";
    private static final String REPLACE_MIRROR_BOSS_PROPERTY = "replace-Banker-boss";

    private static void setProperties() {
        if (config != null) {
            forceMirrorBoss = config.getBool(FORCE_MIRROR_BOSS_PROPERTY);
            replaceMirrorBoss = config.getString(REPLACE_MIRROR_BOSS_PROPERTY);
        }
    }

    public static final String BankerBoss = "Banker Boss";
    @Override
    public void receivePostInitialize() {
        Texture badgeTexture = new Texture("img/bossMod_badge.png");
        ModPanel settingsPanel = new ModPanel();
        ModLabeledToggleButton forceMirrorCheckbox = new ModLabeledToggleButton("Force Banker Boss", 350.0F, 500.0F, Settings.CREAM_COLOR, FontHelper.charDescFont, forceMirrorBoss, settingsPanel, (label) -> {
        }, (button) -> {
            forceMirrorBoss = button.enabled;
            setBoolean(FORCE_MIRROR_BOSS_PROPERTY, forceMirrorBoss);
        });
        settingsPanel.addUIElement(forceMirrorCheckbox);
        List<String> bossNames = Arrays.asList("Donu", "Awakened One", "Time Eater", "Banker Boss");
        for (String boss: bossNames) {
            ModLabeledToggleButton forceNormalBossCheckbox = new ModLabeledToggleButton("Force " + boss + " Boss", 350.0F, 550.0F + bossNames.indexOf(boss) * 50, Settings.CREAM_COLOR, FontHelper.charDescFont, replaceMirrorBoss.equals(boss), settingsPanel, (label) -> {
            }, (button) -> {
                replaceMirrorBoss = boss;
                setString(FORCE_MIRROR_BOSS_PROPERTY, boss);
            });
            settingsPanel.addUIElement(forceNormalBossCheckbox);
        }
        BaseMod.registerModBadge(badgeTexture, "Banker Boss", "dyth", "Configuration for the Mirrors mod.", settingsPanel);

        BaseMod.addMonster(BankerBoss, "Banker Boss", () -> new MonsterGroup(new AbstractMonster[]{
                new Banker()
        }));
        BaseMod.addBoss(TheBeyond.ID, "Banker Boss", "img/monsters/banker/Banker.png", "img/monsters/banker/Banker.png");
    }

    private static SpireConfig config;

    private static void setBoolean(String key, Boolean value) {
        config.setBool(key, value);

        try {
            config.save();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    private static void setString(String key, String value) {
        config.setString(key, value);

        try {
            config.save();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public static List<AbstractMonster> liveMonsters() {
        return AbstractDungeon.getMonsters().monsters.stream().filter(m -> !m.isDying).collect(Collectors.toList());
    }
}