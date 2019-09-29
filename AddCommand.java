package Ex2;

import Ex2.BandsArrayList.BandsListIterator;

public class AddCommand implements BandsDataCommand {

	private BandsArrayList list;
	private BandsListIterator li;
	private Band band;
	
	
	
	public AddCommand(BandsDataControllerImpl bci, Band band) {
		list = bci.getList();
		li = (BandsListIterator) list.listIterator();
		setBand(band);
	}

	
	@Override
	public void execute() {
		li.add(getBand());
	}

	@Override
	public void undo() {
		li = (BandsListIterator) list.listIterator(0);
		while(li.hasNext()) {
		  li.next();
			if(li.equals(getBand())){
				li.remove();
				break;
			}
			
		}
	}

	public Band getBand() {
		return band;
	}


	private void setBand(Band band) {
		this.band = band;
	}
	
	

}
