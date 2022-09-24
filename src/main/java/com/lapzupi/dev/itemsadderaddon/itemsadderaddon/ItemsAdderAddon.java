package com.lapzupi.dev.itemsadderaddon.itemsadderaddon;

import com.lapzupi.dev.configurablemenus.ConfigurableMenusPlugin;
import com.lapzupi.dev.configurablemenus.addons.ItemAddon;
import dev.lone.itemsadder.api.CustomStack;
import dev.lone.itemsadder.api.Events.ItemsAdderLoadDataEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class ItemsAdderAddon extends ItemAddon implements Listener {
    private boolean iaStatus = false;
    private final ConfigurableMenusPlugin plugin;

    public ItemsAdderAddon() {
        this.plugin = (ConfigurableMenusPlugin) Bukkit.getPluginManager().getPlugin("ConfigurableMenus");
    }

    @Contract(pure = true)
    @Override
    public @NotNull ItemStack getItemStack(final String id) {
        try {
            return CustomStack.getInstance(id).getItemStack();
        } catch (NullPointerException e) {
            this.plugin.debug("There was a problem obtaining this item: %s".formatted(id));
            this.plugin.debug("ItemsAdder: %s".formatted(getIaStatus()));
            this.plugin.debug("returning temporary AIR itemstack.");
            return new ItemStack(Material.AIR);
        }
    }

    @Contract(pure = true)
    @Override
    public @NotNull String getPrefix() {
        return "itemsadder";
    }

    @Contract(pure = true)
    @Override
    public @NotNull String getPluginName() {
        return "ItemsAdder";
    }

    @Contract(pure = true)
    @Override
    public @NotNull String getAuthor() {
        return "Lapzupi Development Team";
    }

    @Contract(pure = true)
    @Override
    public @NotNull String getVersion() {
        return "1.0.2";
    }

    @EventHandler
    public void onItemsAdderLoad(ItemsAdderLoadDataEvent event) {
        this.plugin.getLogger().info("Detected that ItemsAdder has loaded, reloading menus...");
        this.iaStatus = true;
        this.plugin.reloadMenus();
    }

    @Contract(pure = true)
    private @NotNull String getIaStatus() {
        return iaStatus ? "Loaded" : "Not Loaded";
    }

    @Contract(pure = true)
    @Override
    public @NotNull String getUrl() {
        return "https://github.com/Lapzupi/CM-IA-Addon/releases";
    }
}
