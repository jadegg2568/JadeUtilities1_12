package ru.jadegg2568.jadeutilities;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;
import ru.jadegg2568.jadeutilities.commands.ChatUtilCommand;

@Mod(modid = Main.MOD_ID, name = Main.NAME, version = Main.VERSION)
public class Main
{
    public static final String MOD_ID = "jadeutilities";
    public static final String NAME = "JadeUtilities";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "ru.jadegg2568.jadeutilities.ClientProxy", serverSide = "ru.jadegg2568.jadeutilities.CommonProxy")
    public static CommonProxy proxy;

    public static Logger LOGGER;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        LOGGER = e.getModLog();
        proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
