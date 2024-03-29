package voiture;

public class Engine extends CarPart{
	private long creationTime;
	private float lifeInMinutes;

	/* CONSTRUCTOR */

	// The engine lasts 12 years (12 real-life minutes), and as it gets
	// older, the oil tank needs more frequent changes
	public Engine(float yearsOld) {
		super("engine", " years left", 12);
		this.creationTime = System.currentTimeMillis();
		this.lifeInMinutes = 0;
	}

	/* GETTERS */

	public float getLifeInMinutes() { return this.lifeInMinutes; }

	/* SETTERS */

	// override replacePart()
	// field of the CarPart class.
	public void replacePart() {
		super.replacePart();
		this.lifeInMinutes = 0;
	}

	public void function(float milesDriven) throws CarCrashException {
		super.function(milesDriven);
		this.lifeInMinutes = (System.currentTimeMillis() - this.creationTime) / 60000f;
		this.setCondition(this.bestCondition - lifeInMinutes);
		if (this.condition <= 0) {
			this.crashCar();
		} else if (this.condition <= 2) {
			this.status("Your engine is getting old!");
			if (getBoolean("Replace?")) {
				this.replacePart();
				this.status("Engine replaced!");
			}
		} else {
			this.status("You'll need a new engine in " + this.condition + " years.");
		}
	}

}
