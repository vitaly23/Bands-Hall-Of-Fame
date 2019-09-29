package Ex2;

import java.io.IOException;
import java.util.Comparator;
import java.util.Stack;

import Ex2.BandsArrayList.BandsListIterator;

public class BandsDataControllerImpl implements BandsDataController {

	private static BandsDataControllerImpl instance = null;
	private Stack<BandsDataCommand> commands;
	private BandsArrayList list;
	private BandsHashMap map;
	private BandsListIterator bli;
	private Band band;
	
	
	
	private BandsDataControllerImpl() throws ClassNotFoundException, IOException {
		commands = new Stack<>();
		list = BandDataAccessObject.getInstance().readAllBands();
		map = BandDataAccessObject.getInstance().getBandsMappedByName();
		bli = (BandsListIterator) list.listIterator();
		
	}

	public static BandsDataControllerImpl getInstance() throws ClassNotFoundException, IOException {
		if(instance == null)
			instance = new BandsDataControllerImpl();
		
		return instance;
		
	}
	
	@Override        	 
	public Band previous() {
		PreviousCommand previousCommand = new PreviousCommand(this);
		previousCommand.execute();
		commands.push(previousCommand);
		return band;
	}
	

	@Override
	public Band next() {
		
		NextCommand nextCommand = new NextCommand(this);
		nextCommand.execute();
		commands.push(nextCommand);
		return band;
	}

	@Override
	public void sort(Comparator<Band> comparator) {
		// TODO Auto-generated method stub
		SortCommand sortCommand = new SortCommand(comparator, list);
		sortCommand.execute();
		commands.push(sortCommand);
	}

	@Override
	public void add(Band band) {
		AddCommand addCommand = new AddCommand(this, band);
		addCommand.execute();
		commands.push(addCommand);
		
	}

	@Override
	public void remove() {
		RemoveCommand rc = new RemoveCommand(this);
		rc.execute();
		commands.push(rc);
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		if (!commands.isEmpty()) {
			commands.pop().undo();
		}
	}

	@Override
	public void revert() {
		while(!commands.isEmpty())
			commands.pop().undo();
	}

	@Override
	public void save() throws IOException {
		BandDataAccessObject.getInstance().saveBands((Band[]) list.toArray());
	}

	@Override
	public Band getBandByName(String name) {
		return map.get(name);
	}
	

	public BandsArrayList getList() {
		return list;
	}

	public void setList(BandsArrayList list) {
		this.list = list;
	}

	public BandsListIterator getBli() {
		return bli;
	}

	public void setBli(BandsListIterator bli) {
		this.bli = bli;
	}

	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
	}

	


}
