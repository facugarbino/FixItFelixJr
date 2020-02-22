package animaciones;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JViewport;

public class AnimacionScroll implements Runnable {

	private int yDeseado;
	private JViewport viewport;
	public AnimacionScroll(int yDeseado, JViewport viewport) {
		this.yDeseado=yDeseado;
		this.viewport=viewport;
	}
	boolean terminado;
	@Override
	public void run() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			int actualY = (int) viewport.getViewPosition().getY();
			public void run() {
				if (actualY > yDeseado) {
					actualY-=5;
					viewport.setViewPosition(new Point(0, actualY));
				} else if (actualY < yDeseado) {
					actualY+=5;
					viewport.setViewPosition(new Point(0, actualY++));
				} else {
					timer.cancel();
					terminado=true;
				}
			}
		}, 0, 20);
		while (!terminado) {
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
