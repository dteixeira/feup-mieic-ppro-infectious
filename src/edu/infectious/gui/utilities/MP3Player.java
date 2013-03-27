package edu.infectious.gui.utilities;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MP3Player extends Thread {

	private final String filename;
	private final Player player;
	private final boolean loop;
	
	public MP3Player(String filename, boolean loop) {
		Player p = null;
		try {
			FileInputStream fis;
			fis = new FileInputStream(filename);
			BufferedInputStream bis = new BufferedInputStream(fis);
	        p = new Player(bis);
		} catch (FileNotFoundException | JavaLayerException e) {
			e.printStackTrace();
		} finally {
			this.loop = loop;
			this.player = p;
			this.filename = filename;
		}
	}
	
	@Override
	public void run() {
		try {
			player.play();
			if(loop)
				new MP3Player(filename, loop).start();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}

}
