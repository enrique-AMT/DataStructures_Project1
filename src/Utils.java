import java.io.IOException;
import java.io.RandomAccessFile;

public class Utils{

	public static boolean powerOf2(int blockSize) {
		
		return (blockSize%2==0);
	}

}