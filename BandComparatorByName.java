package Ex2;

import java.util.Comparator;

public class BandComparatorByName implements Comparator<Band>{

	@Override
	public int compare(Band o1, Band o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
