/*
 *	Author:      Makki Fourati
 *	Date:        6 déc. 2016
 */
package platform.game;

public class Not implements Signal {
	private final Signal signal;
	
	public Not(Signal signal) { 
		if (signal == null)
			throw new NullPointerException(); 
		this.signal = signal;
	}
	@Override
	public boolean isActive() {
		return !signal.isActive(); 
	}
}
