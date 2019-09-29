package Ex2;

import Ex2.BandsArrayList.BandsListIterator;

public class NextCommand implements BandsDataCommand {

	
	private BandsDataControllerImpl controller;
	private Band band;
	private BandsArrayList list;
	private BandsListIterator li;
	
	public NextCommand(BandsDataControllerImpl controller) {
		this.controller = controller;
		list = controller.getList();
		li = (BandsListIterator) controller.getBli();
	}

	@Override
	public void execute() {
		
		if(li.hasNext()) {
			band = li.next();
			controller.setBand(band);
			controller.setBli(li);
		}
		else {
			li = (BandsListIterator) list.listIterator();
			band = li.next();
			controller.setBli(li);
			controller.setBand(band);
			}
		}

	@Override
	public void undo() {
		
		if(li.hasPrevious()) {
			band = li.previous();
			
			controller.setBand(band);
			controller.setBli(li);
		}
			else {
				int listSize = list.size();
				li = (BandsListIterator) list.listIterator(listSize);
				band = li.next();
				controller.setBli(li);
				controller.setBand(band);
				}	
		}

	public Band getBand() {
		return band;
	}

	private void setBand(Band band) {
		this.band = band;
	}
	
	

}
