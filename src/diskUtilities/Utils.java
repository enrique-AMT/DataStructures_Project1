package diskUtilities;
/**
 * Class containing certain utilities to be used by the other classes in this project.
 * @author Enrique A. Marrero Torres
 *
 */
public class Utils{
	/**
	 * Verifies if the block size is a power of 2.
	 * @param blockSize the block size to check.
	 * @return a boolean value representing if the block size is correct.
	 */
	public static boolean powerOf2(int blockSize) {
		
		return (blockSize%2==0);
	}

}