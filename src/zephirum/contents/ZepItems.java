package zephirum.contents;

import arc.*;
import arc.graphics.*;
import mindustry.*;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.type.*;

public class ZepItems {
    public static Item stone, iridium, chargedIridium, surgium;

    public static void load() {
        stone = new Item("stone", Color.valueOf("888888")){{
            cost = 0.3f;
            hardness = 0;
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
