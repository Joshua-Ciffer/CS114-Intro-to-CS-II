package dataAbstraction.feetInchesClass;

/**
 * @author Joshua Ciffer
 * @version 6/10/2020
 */
@SuppressWarnings("javadoc")
class FeetInchesTest {

	public static void main(String[] args) throws java.lang.Exception {
		FeetInches f = new FeetInches(2, 6);
		FeetInches g = new FeetInches(3, 8);
		System.out.println(f.add(g));
		System.out.println(f);
		f.setFeet(4);
		System.out.println(f);
		f.setInches(7);
		System.out.println(f);
		System.out.println(f.equals(g));
		g = f;
		System.out.println(f.equals(g));
		FeetInches original = new FeetInches(1, 0);	// 1 foot object
		System.out.println(original.scale(2));	// Scale that object by a factor of 2
	}
}

/**
 * The FeetInches class holds distances measured in
 * feet and inches.
 */
@SuppressWarnings("javadoc")
class FeetInches {

	private int total_inches; 

	/**
	 * This constructor assigns 0 to the feet
	 * and inches fields.
	 */
	public FeetInches() {
		total_inches = 0;
	}

	/**
	 * This constructor accepts two arguments which
	 * are assigned to the feet and inches fields.
	 * The simplify method is then called.
	 * 
	 * @param f
	 *        The value to assign to feet.
	 * @param i
	 *        The value to assign to inches.
	 */
	public FeetInches(int f, int i) {
		total_inches = (f * 12) + i;
//		simplify();
	}

	/**
	 * The following is a copy constructor. It accepts a
	 * reference to another FeetInches object. The feet
	 * and inches fields are set to the same values as
	 * those in the argument object.
	 * 
	 * @param object2
	 *        The object to copy.
	 */
	public FeetInches(FeetInches object2) {
		this.total_inches = object2.total_inches;
	}

//	/**
//	 * The simplify method adjusts the values
//	 * in feet and inches to conform to a
//	 * standard measurement.
//	 */
//	private void simplify() {
//		if (inches > 11) {
//			feet = feet + (inches / 12);
//			inches = inches % 12;
//		}
//	}

	/**
	 * The setFeet method assigns a value to
	 * the feet field.
	 * 
	 * @param f
	 *        The value to assign to feet.
	 */
	public void setFeet(int f) {
		total_inches = f * 12;
	}

	/**
	 * The setInches method assigns a value to
	 * the inches field.
	 * 
	 * @param i
	 *        The value to assign to inches.
	 */
	public void setInches(int i) {
		total_inches = i;
	}

	/**
	 * getFeet method
	 * 
	 * @return The value in the feet field.
	 */
	public int getFeet() {
		return total_inches / 12;
	}

	/**
	 * getInches method
	 * 
	 * @return The value in the inches field.
	 */
	public int getInches() {
		return total_inches % 12;
	}

	public FeetInches scale(int scaleFactor) {
		FeetInches scaled = new FeetInches(this);
		scaled.total_inches *= scaleFactor;
		return scaled;
	}
	
	/**
	 * toString method
	 * 
	 * @return a reference to a String stating
	 *         the feet and inches.
	 */
	public String toString() {
		String str = getFeet() + " feet " + getInches() + " inches";
		return str;
	}

	/**
	 * The add method returns a FeetInches object
	 * that holds the sum of this object and another
	 * FeetInches object.
	 * 
	 * @param object2
	 *        The other FeetInches object.
	 * @return A reference to a FeetInches object.
	 */
	public FeetInches add(FeetInches object2) {
		return new FeetInches(0, this.total_inches + object2.total_inches);
	}

	/**
	 * The equals method compares this object to the
	 * argument object. If both have the same values,
	 * the method returns true.
	 * 
	 * @return true if the objects are equal, false
	 *         otherwise.
	 */
	public boolean equals(FeetInches object2) {
		boolean status;
		if (object2 == null)
			status = false;
		else if (this.total_inches == object2.total_inches)
			status = true;
		else
			status = false;
		return status;
	}

	/**
	 * The copy method makes a copy of the
	 * the calling object.
	 * 
	 * @return A reference to the copy.
	 */
	public FeetInches copy() {
		// Make a new FeetInches object and
		// initialize it with the same data
		// as the calling object.
		FeetInches newObject = new FeetInches(0, total_inches);

		// Return a reference to the new object.
		return newObject;
	}
}