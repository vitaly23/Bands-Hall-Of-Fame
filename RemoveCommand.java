package Ex2;

import java.util.ListIterator;

import Ex2.BandsArrayList.BandsListIterator;

public class RemoveCommand implements BandsDataCommand {
	
	private BandsDataControllerImpl controller;
	private BandsArrayList list;
	private BandsListIterator li;
	private Band band;
	
	
	
	public RemoveCommand(BandsDataControllerImpl bci) {
		this.controller = bci;
		list = bci.getList();
		li = (BandsListIterator) list.listIterator();
	}


	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
	}

	
	@Override
	public void execute() {
		setBand(controller.getBand());
		controller.getList().remove(band);
		
	}

	@Override
	public void undo() {
		if(!list.contains(band))
			li.add(getBand());		
	}

}
