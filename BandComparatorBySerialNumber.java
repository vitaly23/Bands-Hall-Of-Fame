package Ex2;

import java.util.Comparator;

public class BandComparatorBySerialNumber implements Comparator<Band> {

	@Override
	public int compare(Band o1, Band o2) {
		// TODO Auto-generated method stub
		return o1.getSerialNumber() - o2.getSerialNumber();
	}

}
