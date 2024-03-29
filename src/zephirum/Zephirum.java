package zephirum;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;
import zephirum.contents.*;

public class Zephirum extends Mod{

    public Zephirum(){
        Log.info("Loaded Zephirum constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("startDialog");
                dialog.cont.add("SANS").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                dialog.cont.image(Core.atlas.find("zephirum-frog")).pad(20f).row();
                dialog.cont.button("Okay", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading content.");

        ZepAttributes.load();
        ZepItems.load();
        ZepBlocks.load();
        vanillaOverride.load();
    }

}
