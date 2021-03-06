/*
 * The MIT License
 *
 * Copyright 2014 Grzegorz.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package pl.grzegorz2047.openguild2047.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import pl.grzegorz2047.openguild2047.Data;
import pl.grzegorz2047.openguild2047.GenConf;
import pl.grzegorz2047.openguild2047.SimplePlayerGuild;

/**
 *
 * @author Grzegorz
 */
public class PlayerChat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if(e.isCancelled() || !GenConf.guildprefixinchat) {
            return;
        }
        if(Data.getInstance().guildsplayers.containsKey(e.getPlayer().getUniqueId())) {
            SimplePlayerGuild spg = Data.getInstance().guildsplayers.get(e.getPlayer().getUniqueId());
            if(spg.getClanTag().equals("") || spg.getClanTag().equals("null")) {
                return;
            } 
            if(e.getFormat().contains("{OpenGuildTag}") || e.getFormat().contains("%tag")) {
                e.setFormat(e.getFormat().replace("{OpenGuildTag}", spg.getClanTag()).replace("%tag", spg.getClanTag()));
            } else {
                e.setFormat("§7[§r" + spg.getClanTag() + "§7]§r " + e.getFormat());
            }
        }else{
            e.setFormat(e.getFormat().replace("{OpenGuildTag}", ""));
        }

    }

}
