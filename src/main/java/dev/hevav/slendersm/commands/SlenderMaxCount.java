package dev.hevav.slendersm.commands;

import dev.hevav.slendersm.SlenderSM;
import dev.hevav.slendersm.helpers.SlenderHelper;
import dev.hevav.slendersm.listeners.SlenderListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class SlenderMaxCount implements CommandExecutor {
    private static boolean toggleFlag = false;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length < 1)
            return false;

        SlenderSM.config.set("maxcount", Integer.valueOf(strings[0]));
        return true;
    }
}
