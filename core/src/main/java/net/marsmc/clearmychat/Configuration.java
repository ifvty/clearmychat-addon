package net.marsmc.clearmychat;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;

@ConfigName("settings")
public class Configuration extends AddonConfig {

  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SwitchSetting
  private final ConfigProperty<Boolean> autoClear = new ConfigProperty<>(false);

  @TextFieldSetting
  private final ConfigProperty<String> confirmationMessage = new ConfigProperty<>("Your chat has been successfully cleared!");

  @SliderSetting(min = 0, max = 5, steps = 1)
  private final ConfigProperty<Integer> clearDelay = new ConfigProperty<>(0);

  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<Boolean> autoClear() {
    return this.autoClear;
  }

  public ConfigProperty<String> confirmationMessage() {
    return this.confirmationMessage;
  }

  public ConfigProperty<Integer> clearDelay() {
    return this.clearDelay;
  }
}
