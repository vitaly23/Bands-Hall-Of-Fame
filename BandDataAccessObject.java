package Ex2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BandDataAccessObject implements BandDataAccess {  //**Singleton Design Pattern
	
	private static BandDataAccessObject instance = null;
	private final String FILE_NAME = "bands.bin";
	
	
	private BandDataAccessObject() {
		
	}
	
	public static BandDataAccessObject getInstance() { //Singleton instance
		if(instance == null)
			instance = new BandDataAccessObject();
		
		return instance;
	}
	
	@Override
	public BandsArrayList readAllBands() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
		Band bands[] = (Band[]) ois.readObject();
		BandsArrayList bal = new BandsArrayList(bands);
		ois.close();
		return bal;
	}

	@Override
	public BandsHashMap getBandsMappedByName() throws IOException, ClassNotFoundException {
		BandsHashMap bands = new BandsHashMap();
		BandsArrayList arr = readAllBands();
		
		for(Band b : arr) {
			if(b != null)
				bands.put(b.getName(), b);
		}
		return bands;
	}

	@Override
	public void saveBands(Band[] bands) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
		oos.writeObject(bands);
		oos.close();
	}

}
