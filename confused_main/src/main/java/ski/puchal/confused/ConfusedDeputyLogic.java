package ski.puchal.confused;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfusedDeputyLogic {
	
	@Autowired
	private BillLogic billLogic;
	
	@Autowired
	private WorkLogic workLogic;

	public void run(String inString, String outString) {
		workLogic.encodeFile(inString, outString);
		billLogic.addBillInfo(outString);
	}

}
