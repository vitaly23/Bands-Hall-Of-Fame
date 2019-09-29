package Ex2;

import java.util.Comparator;

public class BandComparatorByOrigin implements Comparator<Band>{

	@Override
	public int compare(Band o1, Band o2) {
		return o1.getOrigin().compareTo(o2.getOrigin());
	}

}
