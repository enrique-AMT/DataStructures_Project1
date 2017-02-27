package diskUtilities;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.InvalidParameterException;

import ExceptionHandlers.*;
/**
 * Simulates a Virtual Disk Unit, which is able to read, write, mount and create units. 
 * In most cases, it utilizes the VirtualDiskBlock class to handle blocks of data.
 * a Random Access File is created to store the disk blocks and to preserve data in
 * a non-volatile manner.
 * 
 * @author Enrique A. Marrero Torres
 */
public class DiskUnit { 
	/**
	 * The default capacity (in bytes) of blocks that a disk unit will contain.
	 */
	private static final int 
	DEFAULT_CAPACITY = 1024;
	/**
	 * The default number of bytes per block.
	 */
	private static final int
	DEFAULT_BLOCK_SIZE = 256; 
	/**
	 * Reserved space for the capacity and block size fields.
	 */
	private static final int RESERVED_SPACE = 8;

	/**
	 * Number of blocks of the current disk instance.
	 */
	private int capacity;
	/**
	 * Size of each block of the current disk instance.
	 */
	private int blockSize;
	/**
	 * This file represents the simulated disk, where all the virtual disk blocks are stored.
	 */
	private RandomAccessFile disk;

	/**
	 * Constructor for the Disk Unit.
     * @param name is the name of the disk
	 **/
	private DiskUnit(String name) {
		try {
			disk = new RandomAccessFile(name, "rw");
		}
		catch (IOException e) {
			System.err.println ("Unable to start the disk");
			System.exit(1);
		}
	}


	/** Simulates shutting-off the disk. Just closes the corresponding RAF. **/
	public void shutdown() {
		try {
			disk.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Turns on an existing disk unit whose name is given. If successful, it makes
	 * the particular disk unit available for operations suitable for a disk unit.
	 * @param name the name of the disk unit to activate
	 * @return the corresponding DiskUnit object
	 * @throws NonExistingDiskException whenever no
	 *    ¨disk¨ with the specified name is found.
	 */
	
	public static DiskUnit mount(String name)
			throws NonExistingDiskException
	{
		File file=new File(name);
		if (!file.exists())
			throw new NonExistingDiskException("No disk has name : " + name);

		DiskUnit dUnit = new DiskUnit(name);
		try {
			dUnit.disk.seek(0);
			dUnit.capacity = dUnit.disk.readInt();
			dUnit.blockSize = dUnit.disk.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dUnit;     	
	}

	/***
	 * Creates a new disk unit with the given name. The disk is formatted
	 * as having default capacity (number of blocks), each of default
	 * size (number of bytes). Those values are: DEFAULT_CAPACITY and
	 * DEFAULT_BLOCK_SIZE. The created disk is left as in off mode.
	 * @param name the name of the file that is to represent the disk.
	 * @throws ExistingDiskException whenever the name attempted is
	 * already in use.
	 */
	public static void createDiskUnit(String name)
			throws ExistingDiskException
	{
		createDiskUnit(name, DEFAULT_CAPACITY, DEFAULT_BLOCK_SIZE);
	}

	/**
	 * Creates a new disk unit with the given name. The disk is formatted
	 * as with the specified capacity (number of blocks), each of specified
	 * size (number of bytes).  The created disk is left as in off mode.
	 * @param name the name of the file that is to represent the disk.
	 * @param capacity number of blocks in the new disk
	 * @param blockSize size per block in the new disk
	 * @throws ExistingDiskException whenever the name attempted is
	 * already in use.
	 * @throws InvalidParameterException whenever the values for capacity
	 *  or blockSize are not valid according to the specifications
	 */
	public static void createDiskUnit(String name, int capacity, int blockSize)
			throws ExistingDiskException, InvalidParameterException
			{
		
		File file=new File(name);
		if (file.exists())
			throw new ExistingDiskException("Disk name is already used: " + name);

		RandomAccessFile disk = null;
		if (capacity < 0 || blockSize < 0 ||
				!Utils.powerOf2(capacity) || !Utils.powerOf2(blockSize))
			throw new InvalidParameterException("Invalid values: " +
					" capacity = " + capacity + " block size = " +
					blockSize);
		try {
			disk = new RandomAccessFile(name, "rw");
		}
		catch (IOException e) {
			System.err.println ("Unable to start the disk");
			System.exit(1);
		}

		reserveDiskSpace(disk, capacity, blockSize);

		try {
			disk.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Returns the capacity of the disk Unit.
	 * @return a non-negative integer that represents the capacity of the disk.
	 */
	public int getCapacity(){
		int currentCapacity = 0;
		try {
			this.disk.seek(0);

			currentCapacity=this.disk.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return currentCapacity;
	}
	/**
	 * Reserves the disk space for the creation of a virtual disk unit.
	 * @param disk the disk to reserve space for.
	 * @param capacity the capacity of the disk unit.
	 * @param blockSize the block size of the disk unit.
	 */
	private static void reserveDiskSpace(RandomAccessFile disk, int capacity,
			int blockSize)
	{
		try {
			disk.setLength(blockSize * capacity);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			disk.seek(0);
			disk.writeInt(capacity);  
			disk.writeInt(blockSize);
		} catch (IOException e) {
			e.printStackTrace();
		} 	
	}
	/**
	 * Gets the block size of the disk Unit.
	 * @return the block size of the disk unit.
	 */
	public int getBlockSize(){
		int returnedBlockSize = 0;
		try {
			this.disk.seek(RESERVED_SPACE/2);
			returnedBlockSize = this.disk.readInt();


		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnedBlockSize;
	}

	/**
	 * Reads a given block from the disk. This block is set to the instance disk block b.
	 * Concurrently, b must be an existing variable of VirtualDiskBlock.
	 * @param blockNum the index of the block to read.
	 * @param b the block to be set.
	 * @throws InvalidBlockNumberException when the specified block number is not valid for
	 * the current disk instance.
	 * @throws InvalidBlockException when b doesn't represent a valid block of the disk instance,
	 * an example of this is when b is null or when the block instance doesn't match the block
	 * size of the current disk instance.
	 */
	public void read(int blockNum, VirtualDiskBlock b) throws InvalidBlockNumberException, InvalidBlockException{
		int index = blockNum*this.getBlockSize();

		try {
			for(int i=0; i<this.getBlockSize(); i++){
				this.disk.seek(index+i);
				b.setElement(i, this.disk.readByte());

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * writes a specified block to an index of the disk instance, denoted by blockNum.
	 * It is also capable of overwriting the block, given that it is a valid instance of
	 * VirtualDiskBlock.
	 * @param blockNum the index at which the block will be written.
	 * @param b the Virtual Disk block that will be written into the disk unit's index.
	 * @throws InvalidBlockNumberException when the specified block number is not valid for
	 * the current disk instance.
	 * @throws InvalidBlockException when b doesn't represent a valid block of the disk instance,
	 * an example of this is when b is null or when the block instance doesn't match the block
	 * size of the current disk instance.
	 */
	public void write(int blockNum, VirtualDiskBlock b) throws InvalidBlockNumberException, InvalidBlockException{
		int index = blockNum*this.getBlockSize();
		try {
			this.disk.seek(index);
		} catch (IOException e1) {
			throw new InvalidBlockNumberException();
		}
		try {
			this.disk.write(b.diskBlock);
		} catch (IOException e) {
			throw new InvalidBlockException();
		}

	}
	
	/**
	 * Formats the Disk Unit by writing 0 to every block of the Disk Unit, except when otherwise
	 * noted.
	 */
	public void lowLevelFormat(){
		VirtualDiskBlock formattedDiskBlock = new VirtualDiskBlock(this.blockSize);
		for(int i=1; i<this.capacity; i++){
			this.write(i, formattedDiskBlock);
		}
	}


}
