package Ex2;

import java.util.Comparator;

public class BandComparatorByFansDecreasing implements Comparator<Band> {

	@Override
	public int compare(Band o1, Band o2) {
		return o2.getNumOfFans()-o1.getNumOfFans();
	}

}
