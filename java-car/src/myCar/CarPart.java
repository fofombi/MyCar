package voiture;

// abstract
public abstract class CarPart implements Functional, Interactive {
// instanceCount can help us assign a serial number to each part,to help out the mechanics.
	private static long instanceCount = 0;

	protected String partName;
	protected long serialNumber;
	protected float bestCondition;
	protected String conditionMeasure;

	protected float condition;
	protected float currentTotalMiles;

	/* CONSTRUCTOR */

	public CarPart(String name, String conditionMeasure, float bestCondition) {
		instanceCount += 1;
		this.serialNumber = instanceCount;
		this.partName = name;
		this.bestCondition = bestCondition;
		this.conditionMeasure = conditionMeasure;
		this.condition = this.bestCondition;
		this.currentTotalMiles = 0;
	}

	/* GETTERS */

	// boolean
	public boolean isBroken() { return this.condition <= 0; }
	public boolean isLikeNew() { return this.condition >= this.bestCondition; }

	// float
	public float getCondition() { return this.condition; }
	public float getCurrentTotalMiles() { return this.currentTotalMiles; }
	public float getBestCondition() { return this.bestCondition; }

 // String
	public String getPartName() { return this.partName; }
	public String getConditionMeasure() { return this.conditionMeasure; }

	// long
	public long getSerialNumber() { return this.serialNumber; }



	/* SETTERS */

	public void setCondition(float newCondition) {
		this.condition = newCondition;
		if (this.condition < 0) {
			this.condition = 0;
		} else if (this.condition > this.bestCondition) {
			this.condition = this.bestCondition;
		}
	}

	public void changeCondition(float delta) {
		this.condition += delta;
		if (this.condition < 0) {
			this.condition = 0;
		} else if (this.condition > this.bestCondition) {
			this.condition = this.bestCondition;
		}
	}

	public void makeBroken() {
		this.condition = 0;
	}

	public void replacePart() {
		this.condition = this.bestCondition;
		this.currentTotalMiles = 0;
	}

	// The status method can accept an extra message
	public void status() {
		System.out.println("Your " + this.partName + " (serial #111000222" + this.serialNumber + ") is at "
				+ this.condition + this.conditionMeasure + ".");
	}
	public void status(String extraMessage) {
		System.out.print("Your " + this.partName + " ("
				+ this.condition + this.conditionMeasure + ") say: ");
		System.out.println(extraMessage);
	}

	public void crashCar() throws CarCrashException {
		throw new CarCrashException("The " + this.partName + " broke and crashed the car!");
	}

	public void function(float milesDriven) throws CarCrashException {
		this.currentTotalMiles += milesDriven;
	}
}
