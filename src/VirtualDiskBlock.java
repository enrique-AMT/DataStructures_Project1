import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class VirtualDiskBlock {
	
	private RandomAccessFile diskBlock;
	public VirtualDiskBlock(){
		 try {
	         diskBlock = new RandomAccessFile("OriginalBlock", "rw");
	     }
	     catch (IOException e) {
	         System.err.println ("Unable to start the disk");
	         System.exit(1);
	     }
		
	}
	public VirtualDiskBlock(int blockCapacity){
		try {
			diskBlock = new RandomAccessFile("ModifiedBlock", "rw");
			diskBlock.setLength((long) blockCapacity);
			diskBlock.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int getCapacity(){
		int capacity=0;
		try {
			capacity = (int) diskBlock.length();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return capacity;
		
	}
	public void setElement(int index, byte nuevo){
		try {
			diskBlock.seek((long) index);
			diskBlock.write(nuevo);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public byte getElement(int index){
		byte element=0;
		try {
			diskBlock.seek((long) index);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			element= diskBlock.readByte();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return element;
	}
}
