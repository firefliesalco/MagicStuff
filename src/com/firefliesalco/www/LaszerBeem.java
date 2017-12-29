package com.firefliesalco.www;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.inventivetalent.particle.ParticleEffect;

public class LaszerBeem extends Spell {

	public LaszerBeem(Location loc, Vector speed, Player owner) {
		super(loc, speed, owner);
		particle = ParticleEffect.DRIP_WATER;
	}

	@Override
	public Vector particleOffset() {
		return new Vector(0, 0, 0);
	}

	@Override
	public void onIntersect() {
		remove();
		
	}

	@Override
	public void hitEntity(ArrayList<Entity> e) {
		e.get(0).remove();
		remove();
		
	}

	
	
}
