/**
 * The assignment is to modify the class implementation without changing the functionality.
 * 
 * Remove the feet and inches data members and replace them with one data member named total_inches. Re-implement all the class methods so they have he same functionality
 * as originally. This means that any program written using the public members of the new class must produce exactly the same results as the original implementation.
 * 
 * Also, add a scale method that allows a length to be scaled by an integer factor:
 * 
 * if x is a FeetInches object x.scale(2) would return a length object double the length of x, x itself is unchanged.
 * 
 * Add code to main() test the scale() method.
 * 
 * Hint:
 * 
 * When implementing the add() and scale() methods, the last line will return a new FeetInches object. A useful thing to keep in mind is that in the new implementation
 * you have just one data member, total_inches, and this simplifies things. You can just do this:
 * 
 * return new FeetInches(0, put_total_inches_here);
 * 
 * Call the FeetInches constructor with zero feet and the value you computed for the total inches of the length you want to return. Done properly, the add() and scale()
 * methods can be done in one line of code.
 *
 * @author Joshua Ciffer
 * @version 06/10/2020
 */
package dataAbstraction.feetInchesClass;