package dev.hevav.slendersm.commands;

import dev.hevav.slendersm.SlenderSM;
import dev.hevav.slendersm.helpers.SlenderHelper;
import dev.hevav.slendersm.listeners.SlenderListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class SlenderToggle implements CommandExecutor {
    private static boolean toggleFlag = false;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        toggleFlag = !toggleFlag;

        if (toggleFlag){
            SlenderHelper.count = 0;
            Bukkit.getPluginManager().registerEvents(new SlenderListener(), SlenderSM.getInstance());
            SlenderHelper.proceedCompass();
        }
        else{
            HandlerList.unregisterAll(SlenderSM.getInstance());
            SlenderHelper.disableCompass();
        }
        return true;
    }
}
