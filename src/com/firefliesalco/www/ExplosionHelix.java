package com.firefliesalco.www;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.inventivetalent.particle.ParticleEffect;

public class ExplosionHelix extends Spell {

	public ExplosionHelix(Location loc, Vector speed, Player p) {
		super(loc, speed, p);
		this.particle = ParticleEffect.FLAME;
	}

	@Override
	public Vector particleOffset() {
		double distance = 1;
		double lifer = (double)life;
		Vector l = new Vector(Math.sin(lifer)*Math.cos(loc.getYaw()), Math.cos(lifer), Math.sin(lifer)*Math.sin(loc.getY()));
		return l.multiply(distance);
	}

	
	
	@Override
	public void onIntersect() {

		loc.getWorld().createExplosion(loc.getX(), loc.getY(),loc.getZ(), 10, false, true);
		remove();
	}

	@Override
	public void hitEntity(ArrayList<Entity> e) {

		onIntersect();
		
	}



}
