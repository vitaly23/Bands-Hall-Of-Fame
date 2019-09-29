package Ex2;


import Ex2.BandsArrayList.BandsListIterator;

public class PreviousCommand implements BandsDataCommand{
	
	private BandsDataControllerImpl controller;
	private BandsArrayList list;
	private BandsListIterator li;
	private Band band;
	
	public PreviousCommand(BandsDataControllerImpl control){
		controller = control;
		list = controller.getList();
		li = controller.getBli();
	}
	
	@Override
	public void execute() {
		if(li.hasPrevious())
			controller.setBand(li.previous());
		else {
				int listSize = list.size();
				li = (BandsListIterator) list.listIterator(listSize);
				controller.setBli(li);
				controller.setBand(li.previous());
		}
	}

	@Override
	public void undo() {
		if(li.hasNext())
			controller.setBand(li.next());
		else {
			li = (BandsListIterator) list.listIterator(0);
			controller.setBli(li);
			controller.setBand(li.next());
		}
	}

	public Band getBand() {
		return band;
	}

}
