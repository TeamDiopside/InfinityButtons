package nl.teamdiopside.infinitybuttons.neoforge;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddPackFindersEvent;

@EventBusSubscriber(
        modid = "infinitybuttons"
)
public class IBDatapacks {
    @SubscribeEvent
    public static void addPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() != PackType.SERVER_DATA) {
            return;
        }

        event.addPackFinders(
                net.minecraft.resources.ResourceLocation
                        .fromNamespaceAndPath("infinitybuttons", "datapacks/more_buttons"),
                PackType.SERVER_DATA,
                Component.literal("More Buttons"),
                PackSource.BUILT_IN,
                false,
                Pack.Position.TOP
        );
    }
}
