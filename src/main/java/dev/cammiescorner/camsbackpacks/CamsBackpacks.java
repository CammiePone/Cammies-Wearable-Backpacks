package dev.cammiescorner.camsbackpacks;

import dev.cammiescorner.camsbackpacks.common.network.EquipBackpackPacket;
import dev.cammiescorner.camsbackpacks.common.network.OpenBackpackScreenPacket;
import dev.cammiescorner.camsbackpacks.common.network.PlaceBackpackPacket;
import dev.cammiescorner.camsbackpacks.compat.universalgraves.UniversalGravesCompat;
import dev.cammiescorner.camsbackpacks.core.BackpacksConfig;
import dev.cammiescorner.camsbackpacks.core.registry.ModBlockEntities;
import dev.cammiescorner.camsbackpacks.core.registry.ModBlocks;
import dev.cammiescorner.camsbackpacks.core.registry.ModScreenHandlers;
import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class CamsBackpacks implements ModInitializer {
    public static final String MOD_ID = "camsbackpacks";

    @Override
    public void onInitialize(ModContainer mod) {
        MidnightConfig.init(MOD_ID, BackpacksConfig.class);

        ServerPlayNetworking.registerGlobalReceiver(PlaceBackpackPacket.ID, PlaceBackpackPacket::handle);
        ServerPlayNetworking.registerGlobalReceiver(OpenBackpackScreenPacket.ID, OpenBackpackScreenPacket::handle);
        ServerPlayNetworking.registerGlobalReceiver(EquipBackpackPacket.ID, EquipBackpackPacket::handle);

        ModBlocks.register();
        ModBlockEntities.register();
        ModScreenHandlers.register();

        if (QuiltLoader.isModLoaded("universal-graves"))
            UniversalGravesCompat.load();
    }

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
}
