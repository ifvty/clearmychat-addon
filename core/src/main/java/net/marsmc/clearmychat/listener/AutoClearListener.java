package net.marsmc.clearmychat.listener;

import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.network.server.ServerJoinEvent;
import net.labymod.api.notification.Notification;
import net.labymod.api.notification.Notification.Type;
import net.labymod.api.client.network.server.ServerData;
import net.marsmc.clearmychat.ClearMyChatAddon;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AutoClearListener {

  private final ClearMyChatAddon addon;
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  public AutoClearListener(ClearMyChatAddon addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onServerJoin(ServerJoinEvent event) {
    if (!this.addon.configuration().autoClear().get()) {
      return;
    }

    int delay = this.addon.configuration().clearDelay().get();
    ServerData serverData = Laby.labyAPI().serverController().getCurrentServerData();

    Runnable clearChatTask = () -> {
      Laby.references().chatController().clear();

      Component confirmationMessage = createConfirmationMessage();
      this.addon.labyAPI().chatProvider().chatController().addMessage(confirmationMessage);
      pushNotification(serverData);
    };

    if (delay > 0) {
      scheduler.schedule(clearChatTask, delay, TimeUnit.SECONDS);
    } else {
      clearChatTask.run();
    }
  }

  private Component createConfirmationMessage() {
    return Component.text("▍ ", NamedTextColor.DARK_GRAY)
        .append(Component.text("Clear", NamedTextColor.WHITE))
        .append(Component.text("My", NamedTextColor.AQUA))
        .append(Component.text("Chat", NamedTextColor.WHITE))
        .append(Component.text(" ┃ ", NamedTextColor.DARK_GRAY))
        .append(Component.text("✔ Your chat has been automatically cleared successfully!", NamedTextColor.GREEN));
  }

  private void pushNotification(ServerData serverData) {
    Icon icon = (serverData != null) ? Icon.currentServer() : Icon.head("minecraft:grass_block");
    pushNotificationIcon(
        "Automatically cleared!",
        "Chat has been cleared automatically.",
        icon
    );
  }

  private void pushNotificationIcon(String title, String text, Icon icon) {
    Notification.Builder builder = Notification.builder()
        .title(Component.text(title))
        .text(Component.text(text))
        .icon(icon)
        .type(Type.SYSTEM);
    this.addon.labyAPI().notificationController().push(builder.build());
  }
}