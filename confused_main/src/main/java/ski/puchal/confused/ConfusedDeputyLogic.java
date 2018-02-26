package ski.puchal.confused;

public class ConfusedDeputyLogic {
	
	private BillLogic billLogic = new BillLogic();
	private WorkLogic workLogic = new WorkLogic();

	public void run(String inString, String outString) {
		workLogic.encodeFile(inString, outString);
		billLogic.addBillInfo(outString);
	}

}
