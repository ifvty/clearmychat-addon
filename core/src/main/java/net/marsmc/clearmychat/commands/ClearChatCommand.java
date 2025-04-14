package net.marsmc.clearmychat.commands;

import net.labymod.api.Laby;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.resources.ResourceLocation;
import net.marsmc.clearmychat.ClearMyChatAddon;

public class ClearChatCommand extends Command {

  private final ClearMyChatAddon addon;

  public ClearChatCommand(ClearMyChatAddon addon) {
    super("cmc", "clearmychat");
    this.addon = addon;
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {

    Laby.references().chatController().clear();
    Laby.labyAPI().minecraft().sounds().playSound(ResourceLocation.create("minecraft", "note.bass"), 1f, 1f);

    Component confirmationMessage = Component.text("▍ ", NamedTextColor.DARK_GRAY)
        .append(Component.text("Clear", NamedTextColor.WHITE))
        .append(Component.text("My", NamedTextColor.AQUA))
        .append(Component.text("Chat", NamedTextColor.WHITE))
        .append(Component.text(" ┃ ", NamedTextColor.DARK_GRAY))
        .append(Component.text("✔ " + this.addon.configuration().confirmationMessage().get(), NamedTextColor.GREEN));

    this.displayMessage(confirmationMessage);

    return true;
  }
}