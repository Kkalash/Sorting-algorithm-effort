
public class ProcessHilper  {
	 
	 long endTime;
	 long startTime;
	 long elapsedTime;
	 int timesRepetition;
	 String algorithmName;
	 int[] arrAfterProcess;
	 MultiplikativDataSet[] multiplikativDataSet;
	 
	 public MultiplikativDataSet[] getMultiplikativDataSet() {
		return multiplikativDataSet;
	}

	public void setMultiplikativDataSet(MultiplikativDataSet[] multiplikativDataSet) {
		this.multiplikativDataSet = multiplikativDataSet;
	}
 
	public long getStartTime() {
		return startTime;
	}
	
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	public long getEndTime() {
		return endTime;
	}
	
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	public String getAlgorithmName() {
		return algorithmName;
	}
	
	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}
	
	public int[] getArrAfterProcess() {
		return arrAfterProcess;
	}
	
	public void setArrAfterProcess(int[] arrAfterProcess) {
		this.arrAfterProcess = arrAfterProcess;
	}
	
	public long getElapsedTime() {
		return elapsedTime;
	}
	
	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
	public void setRimesRepetition(int timesRepetition) {
		this.timesRepetition = timesRepetition;
	}
	
	public long getMeanTime() {
		return elapsedTime / timesRepetition;
	} 
}

class MultiplikativDataSet {
	 int numberCount;
	 long elapsedTime;
}