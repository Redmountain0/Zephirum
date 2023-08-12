package zephirum.contents;

import arc.*;
import arc.graphics.*;
import mindustry.*;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.type.*;

public class ZepItems {
    public static Item wood, stone, copperOre, leadOre, coalOre,
    iridium, chargedIridium, surgium;

    public static void load() {
        wood = new Item("wood", Color.valueOf("ccaa88")){{
            cost = 0.3f;
            hardness = 0;
        }};

        stone = new Item("stone", Color.valueOf("888888")){{
            cost = 0.3f;
            hardness = 0;
        }};

        copperOre = new Item("copper-ore", Color.valueOf("ccaa88")){{
            cost = 0.5f;
            hardness = 1;
        }};

        leadOre = new Item("lead-ore", Color.valueOf("888888")){{
            cost = 0.5f;
            hardness = 1;
        }};

        coalOre = new Item("coal-ore", Color.valueOf("222222")){{
            cost = 0.6f;
            hardness = 1;
        }};

        iridium = new Item("iridium", Color.valueOf("00ffff")){{
            charge = 0.5f;
            cost = 2.0f;
            hardness = 6;
        }};

        chargedIridium = new Item("charged-iridium", Color.valueOf("00ffff")){{
            charge = 2.0f;
            explosiveness = 0.5f;
            radioactivity = 0.5f;
            cost = 2.5f;
            hardness = 6;
        }};

        surgium = new Item("surgium", Color.valueOf("c4bc62")){{
            charge = 0.2f;
            cost = 1.0f;
            hardness = 5;
        }};
    }
}
