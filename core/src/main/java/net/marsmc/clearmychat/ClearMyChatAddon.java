package net.marsmc.clearmychat;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import net.marsmc.clearmychat.commands.ClearChatCommand;
import net.marsmc.clearmychat.listener.AutoClearListener;

@AddonMain
public class ClearMyChatAddon extends LabyAddon<Configuration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.registerCommand(new ClearChatCommand(this));
    this.registerListener(new AutoClearListener(this));

    this.logger().info("ClearMyChat Addon enabled!");
  }

  @Override
  protected Class<Configuration> configurationClass() {
    return Configuration.class;
  }
}