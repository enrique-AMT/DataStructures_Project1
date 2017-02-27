package diskUtilities;

/**
 * Creates a virtual disk block, composed of a sequence of numbers or bytes, in which the data
 * will be stored.
 * @author Enrique A. Marrero Torres
 *
 */
public class VirtualDiskBlock {
	
	public byte[] diskBlock;
	private static final int
    DEFAULT_BLOCK_SIZE = 256; // default number of bytes per block
	/**
	 * Creates a Virtual Disk Block with the default capacity of 256 bytes.
	 */
	public VirtualDiskBlock(){
		this.diskBlock= new byte[DEFAULT_BLOCK_SIZE];
		
	}
	/**
	 * Creates a Virtual Disk Block with a specified block capacity
	 * @param blockCapacity the capacity to append to the disk block.
	 */
	public VirtualDiskBlock(int blockCapacity){
		this.diskBlock= new byte[blockCapacity];
		
	}
	
	/**
	 * Method to obtain the capacity of the disk block in question.
	 * @return a non-negative integer which represents the amount of bytes the
	 * disk block contains.
	 */
	
	public int getCapacity(){
		return this.diskBlock.length;
		
	}
	
	/**
	 * Sets the byte in the selected index of the disk block
	 * @param index the disk block's index of memory, converted to long.
	 * @param nuevo the byte to write in the index of the Virtual Disk Block.
	 */
	
	public void setElement(int index, byte nuevo){		
			this.diskBlock[index]=nuevo;		
		
		
	}
	
	/**
	 * gets the element in a specified index
	 * @param index the index to search inside the Virtual Disk Block.
	 * @return the element contained in the specified index.
	 */
	
	public byte getElement(int index){
		return this.diskBlock[index];
}
}
