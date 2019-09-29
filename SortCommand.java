package Ex2;

import java.util.Comparator;

public class SortCommand implements BandsDataCommand {
	
	private Comparator<Band> compatator;
	private BandsArrayList list;
	
	public SortCommand(Comparator<Band> comparator, BandsArrayList list) {
		this.list = list;
		this.compatator = comparator;
	}

	@Override
	public void execute() {
		list.sort(compatator);
	}

	@Override
	public void undo() {
		list.sort(new BandComparatorBySerialNumber());
	}

}
