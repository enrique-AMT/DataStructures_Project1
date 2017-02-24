import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.InvalidParameterException;

public class DiskUnit { 
  private static final int 
    DEFAULT_CAPACITY = 1024;  // default number of blocks 	
  private static final int
    DEFAULT_BLOCK_SIZE = 256; // default number of bytes per block

  private int capacity;     	// number of blocks of current disk instance
  private int blockSize; 	// size of each block of current disk instance
	
  // the file representing the simulated  disk, where all the disk blocks
  // are stored
  private RandomAccessFile disk;

  /**
    @param name is the name of the disk
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


  // methods corresponding to disk operations, as well as the class 
  // static methods as specified �
  
  /**
   * Turns on an existing disk unit whose name is given. If successful, it makes
   * the particular disk unit available for operations suitable for a disk unit.
   * @param name the name of the disk unit to activate
   * @return the corresponding DiskUnit object
   * @throws NonExistingDiskException whenever no
   *    �disk� with the specified name is found.
  */
  public static DiskUnit mount(String name)
  throws NonExistingDiskException
  {
     File file=new File(name);
     if (!file.exists())
         throw new NonExistingDiskException("No disk has name : " + name);
    
     DiskUnit dUnit = new DiskUnit(name);
     	
     // get the capacity and the block size of the disk from the file
     // representing the disk
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
      // disk parameters are valid... hence create the file to represent the
      // disk unit.
      try {
          disk = new RandomAccessFile(name, "rw");
      }
      catch (IOException e) {
          System.err.println ("Unable to start the disk");
          System.exit(1);
      }

      reserveDiskSpace(disk, capacity, blockSize);
     	
      // after creation, just leave it in shutdown mode - just
      // close the corresponding file
      try {
          disk.close();
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  public int getCapacity(){
		int capacity=0;
		try {
			capacity = (int) disk.length();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return capacity;
		
	}
	private static void reserveDiskSpace(RandomAccessFile disk, int capacity,
            int blockSize)
{
try {
  disk.setLength(blockSize * capacity);
} catch (IOException e) {
   e.printStackTrace();
}

// write disk parameters (number of blocks, bytes per block) in
// block 0 of disk space
try {
   disk.seek(0);
   disk.writeInt(capacity);  
   disk.writeInt(blockSize);
} catch (IOException e) {
   e.printStackTrace();
} 	
}


	public int getBlockSize() {
		// TODO Auto-generated method stub
		return 0;
	}


	public void read(int b, VirtualDiskBlock block) {
		// TODO Auto-generated method stub
		
	}


	public void write(int i, VirtualDiskBlock vdb) {
		// TODO Auto-generated method stub
		
	}

}