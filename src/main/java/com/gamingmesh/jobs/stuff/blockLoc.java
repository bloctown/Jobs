package com.gamingmesh.jobs.stuff;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class blockLoc {
    private int x;
    private int y;
    private int z;
    private String worldName;
    private World w;

    public blockLoc(String loc) {
	fromString(loc);
    }

    public blockLoc(Location loc) {
	x = loc.getBlockX();
	y = loc.getBlockY();
	z = loc.getBlockZ();
	w = loc.getWorld();
	worldName = loc.getWorld().getName();
    }

    public String getWorldName() {
	return worldName;
    }

    public void setWorldName(String worldName) {
	this.worldName = worldName;
    }

    public void setX(int x) {
	this.x = x;
    }

    public void setY(int y) {
	this.y = y;
    }

    public void setZ(int z) {
	this.z = z;
    }

    @Override
    public String toString() {
	return (w == null ? worldName : w.getName()) + ":" + x + ":" + y + ":" + z;
    }

    public String toVectorString() {
	return  x + ":" + y + ":" + z;
    }

    public boolean fromString(String loc) {
	String[] split = loc.split(":", 4);
	if (split.length == 0) {
	    return false;
	}

	World w = Bukkit.getWorld(split[0]);
	if (w == null)
	    return false;
	this.w = w;
	this.worldName = w.getName();

	if (split.length < 4) {
	    return false;
	}

	try {
	    x = Integer.parseInt(split[1]);
	    y = Integer.parseInt(split[2]);
	    z = Integer.parseInt(split[3]);
	    return true;
	} catch (NumberFormatException e) {
	    return false;
	}
    }

    public Block getBlock() {
	Location loc = getLocation();
	return loc == null ? null : loc.getBlock();
    }

    public Location getLocation() {
	if (worldName == null && w == null)
	    return null;

	// Make sure cached world is loaded
	World w = this.w == null ? Bukkit.getWorld(worldName) : Bukkit.getWorld(this.w.getName());
	if (w == null)
	    return null;

	this.w = w;

	return new Location(w, x, y, z);
    }
}
