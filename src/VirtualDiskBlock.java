
import java.io.IOException;
import java.io.RandomAccessFile;

public class VirtualDiskBlock {
	
	private RandomAccessFile diskBlock;
	/**
	 * Creates a Virtual Disk Block with the default capacity of 256 bytes.
	 */
	public VirtualDiskBlock(){
		 try {
	         diskBlock = new RandomAccessFile("OriginalBlock", "rw");
	     }
	     catch (IOException e) {
	         System.err.println ("Unable to start the disk block");
	         System.exit(1);
	     }
		
	}
	/**
	 * Creates a Virtual Disk Block with a specified block capacity
	 * @param blockCapacity the capacity (multiplier of the original 256 bytes) to append
	 * to the disk block.
	 */
	public VirtualDiskBlock(int blockCapacity){
		try {
			diskBlock = new RandomAccessFile("ModifiedBlock", "rw");
			diskBlock.setLength((long) blockCapacity);
			diskBlock.close();
		}  catch (IOException e) {
	         System.err.println ("Unable to start the disk block");
	         System.exit(1);
	     }
		
	}
	
	/**
	 * Method to obtain the capacity of the disk block in question.
	 * @return a non-negative integer which represents the amount of bytes the
	 * disk block contains.
	 */
	
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
	
	/**
	 * Sets the byte in the selected index of the disk block
	 * @param index the disk block's index of memory, converted to long.
	 * @param the byte to write in the index of the Virtual Disk Block.
	 */
	
	public void setElement(int index, byte nuevo){
		try {
			diskBlock.seek((long) index);
			diskBlock.write(nuevo);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * gets the element in a specified index
	 * @param index the index to search inside the Virtual Disk Block.
	 * @return the element contained in the specified index.
	 */
	
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
