import java.awt.EventQueue;
import java.util.Arrays;
import java.util.Random;

public class Sortieralgorithmen {

	public static void main(String[] args) {

		int length = 100;
		boolean ascending = true;
		int[] arr = randomArray(length);
		
		/**
		 * Stufe1 & 2-a
		 */
		
		// sortAlgorithm ist ein enum und mit dem kann man belibig ein sortmethode ausweahlen
		ProcessHilper arrAfterProcess = SortArrayAndGetElapsedTime(arr, sortAlgorithm.bubbleSort, ascending);
		System.out.println("***Stufe 1 & 2-a***");
		System.out.println("Orgenales Array: " + Arrays.toString(arr));
		System.out.println("Sortiertes Array: " + Arrays.toString(arrAfterProcess.getArrAfterProcess()));
		System.out.println("Die abgelaufene Zeit fuer " + arrAfterProcess.getAlgorithmName() + " bei einmal Ausführung ist: " + arrAfterProcess.getElapsedTime());
		System.out.println("Der Auswahl der SortAlg is ->->" + arrAfterProcess.algorithmName + "<-<- (Auswahl der Sortieralgorihmen in die Methode-Signatur MÖGLICH!)");
		System.out.println();
		
		/**
		 * Stufe2-b & 3-a
		 */
		
		int averageTimesRepetition = 10;
		ProcessHilper averageBubbleSort = meanTimeCalculatorForRepeatedExecution(length, sortAlgorithm.bubbleSort, ascending, averageTimesRepetition);
		ProcessHilper averageQuickSort = meanTimeCalculatorForRepeatedExecution(length, sortAlgorithm.quickSort, ascending, averageTimesRepetition);
		ProcessHilper averageInsertionSort = meanTimeCalculatorForRepeatedExecution(length, sortAlgorithm.insertionSort, ascending, averageTimesRepetition);
		ProcessHilper averageSelectionSort = meanTimeCalculatorForRepeatedExecution(length, sortAlgorithm.selectionSort, ascending, averageTimesRepetition);
		
		System.out.println("***Stufe 2-b & 3-a***");
		System.out.println("Die abgelaufene Zeit fuer " + averageBubbleSort.getAlgorithmName() + " bei mehrfach Ausführung ist: " + averageBubbleSort.getMeanTime());
		System.out.println("Die abgelaufene Zeit fuer " + averageQuickSort.getAlgorithmName() + " bei mehrfach Ausführung ist: " + averageQuickSort.getMeanTime());
		System.out.println("Die abgelaufene Zeit fuer " + averageSelectionSort.getAlgorithmName() + " bei mehrfach Ausführung ist: " + averageSelectionSort.getMeanTime());
		System.out.println("Die abgelaufene Zeit fuer " + averageInsertionSort.getAlgorithmName() + " bei mehrfach Ausführung ist: " + averageInsertionSort.getMeanTime());
		
		/**
		 * stufe3-b && c
		 */
		
		int linMuliTimesRepetition = 10;
		ProcessHilper linMuliArrQuickSort = linearMultiplikativAnsteigenSortiertesArray(arr, sortAlgorithm.selectionSort, ascending, linMuliTimesRepetition);
		ProcessHilper linMuliArrBubbleSort = linearMultiplikativAnsteigenSortiertesArray(arr, sortAlgorithm.insertionSort, ascending, linMuliTimesRepetition);
		ProcessHilper linMuliArrInsertionSort = linearMultiplikativAnsteigenSortiertesArray(arr, sortAlgorithm.bubbleSort, ascending, linMuliTimesRepetition);
		ProcessHilper linMuliArrSelectionSort = linearMultiplikativAnsteigenSortiertesArray(arr, sortAlgorithm.quickSort, ascending, linMuliTimesRepetition);
	
        EventQueue.invokeLater(() -> {
        	LineChartEx4 ex = new LineChartEx4(linMuliArrQuickSort,linMuliArrBubbleSort, linMuliArrInsertionSort, linMuliArrSelectionSort);
            ex.setVisible(true);
        });
        
//		bubbleSort					 		 //T(n) = O(n²)
//		quickSort	 						 //T(n) = O(n log n)
//		insertionSort						 //T(n) = O(n²)
//		selectionSort					     //T(n) = O(n²)
		
	}
	
	public static ProcessHilper linearMultiplikativAnsteigenSortiertesArray(int[] arr, sortAlgorithm sortAlgorithmName, boolean ascending, int timesRepetition) {
		int [] cloneArr = arr.clone();
		ProcessHilper processHelper = new ProcessHilper();
		MultiplikativDataSet[] multiplikativDataSetArray = new MultiplikativDataSet[timesRepetition];
		
		for (int i = 0; i < timesRepetition; i++) {
			
			MultiplikativDataSet multiplikativDataSet = new MultiplikativDataSet();
			
			switch (sortAlgorithmName) {
			
			case bubbleSort:
				processHelper.startTime = System.nanoTime();
				bubbleSort(cloneArr, ascending);				
				processHelper.endTime = System.nanoTime();
				processHelper.elapsedTime = processHelper.endTime - processHelper.startTime;
				break;
			case quickSort:
				processHelper.startTime = System.nanoTime();
				quickSort(cloneArr, 0, cloneArr.length - 1, ascending);
				processHelper.endTime = System.nanoTime();
				processHelper.elapsedTime = processHelper.endTime - processHelper.startTime;
				break;
			case insertionSort:
				processHelper.startTime = System.nanoTime();
				insertionSort(cloneArr, ascending);
				processHelper.endTime = System.nanoTime();
				processHelper.elapsedTime = processHelper.endTime - processHelper.startTime;
				break;
			case selectionSort:
				processHelper.startTime = System.nanoTime();
				selectionSort(cloneArr, ascending);
				processHelper.endTime = System.nanoTime();
				processHelper.elapsedTime = processHelper.endTime - processHelper.startTime;
				break;
			default:
			}
			
			multiplikativDataSet.numberCount = cloneArr.length;
			multiplikativDataSet.elapsedTime = processHelper.elapsedTime;
			multiplikativDataSetArray[i] = multiplikativDataSet;
			
			cloneArr = Arrays.copyOf(cloneArr, cloneArr.length + arr.length);
			cloneArr = randomArray(cloneArr.length);
		}
		
		processHelper.arrAfterProcess = cloneArr;
		processHelper.algorithmName = sortAlgorithmName.name();
		processHelper.multiplikativDataSet = multiplikativDataSetArray;
		
		return processHelper;
	}
	
	public static ProcessHilper SortArrayAndGetElapsedTime(int[] arr, sortAlgorithm sortAlgorithmName, boolean ascending) {
		ProcessHilper processHelper = new ProcessHilper();
		processHelper.arrAfterProcess = arr.clone();
		
		switch (sortAlgorithmName) {
		
		case bubbleSort:
			processHelper.startTime = System.nanoTime();
			bubbleSort(processHelper.arrAfterProcess, ascending);
			processHelper.endTime = System.nanoTime();
			processHelper.elapsedTime = processHelper.endTime - processHelper.startTime;
			break;
		case quickSort:
			processHelper.startTime = System.nanoTime();
			quickSort(processHelper.arrAfterProcess, 0, processHelper.arrAfterProcess.length - 1, ascending);
			processHelper.endTime = System.nanoTime();
			processHelper.elapsedTime = processHelper.endTime - processHelper.startTime;
			break;
		case insertionSort:
			processHelper.startTime = System.nanoTime();
			insertionSort(processHelper.arrAfterProcess, ascending);
			processHelper.endTime = System.nanoTime();
			processHelper.elapsedTime = processHelper.endTime - processHelper.startTime;
			break;
		case selectionSort:
			processHelper.startTime = System.nanoTime();
			selectionSort(processHelper.arrAfterProcess, ascending);
			processHelper.endTime = System.nanoTime();
			processHelper.elapsedTime = processHelper.endTime - processHelper.startTime;
			break;
		default:
		break;
		}
		
		processHelper.algorithmName = sortAlgorithmName.name();		
		return processHelper;
	}
	
	public static ProcessHilper meanTimeCalculatorForRepeatedExecution(int len, sortAlgorithm sortAlgorithmName, boolean ascending, int timesRepetition) {
		ProcessHilper processHelper = new ProcessHilper();
		processHelper.timesRepetition = timesRepetition;
		
		for (int i = 0; i < 10; i++) {
			
			processHelper.arrAfterProcess = randomArray(len);
			
			switch (sortAlgorithmName) {
			
			case bubbleSort:
				processHelper.startTime = System.nanoTime();
				bubbleSort(processHelper.arrAfterProcess, ascending);
				processHelper.endTime = System.nanoTime();
				processHelper.elapsedTime = processHelper.elapsedTime + ( processHelper.endTime - processHelper.startTime);
				break;
			case quickSort:
				processHelper.startTime = System.nanoTime();
				quickSort(processHelper.arrAfterProcess, 0, processHelper.arrAfterProcess.length - 1, ascending);
				processHelper.endTime = System.nanoTime();
				processHelper.elapsedTime = processHelper.elapsedTime + ( processHelper.endTime - processHelper.startTime);
				break;
			case insertionSort:
				processHelper.startTime = System.nanoTime();
				insertionSort(processHelper.arrAfterProcess, ascending);
				processHelper.endTime = System.nanoTime();
				processHelper.elapsedTime = processHelper.elapsedTime + ( processHelper.endTime - processHelper.startTime);
				break;
			case selectionSort:
				processHelper.startTime = System.nanoTime();
				selectionSort(processHelper.arrAfterProcess, ascending);
				processHelper.endTime = System.nanoTime();
				processHelper.elapsedTime = processHelper.elapsedTime + ( processHelper.endTime - processHelper.startTime);
				break;
			default:
			}		
		}
		
		processHelper.algorithmName = sortAlgorithmName.name();
		return processHelper;
	}
		
	public static int[] randomArray(int len) {
		Random random = new Random();
		int low = 100;
		int high = 999;
		int[] arr = new int[len];
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(high-low) + low;
		}
		
		return arr;
	}
	
	//https://de.wikipedia.org/wiki/Bubblesort
	public static void bubbleSort(int[] sortieren, boolean ascending) {
		int swapTemp;
		for (int i = 1; i < sortieren.length; i++) {
			
			for (int j = 0; j < sortieren.length - i; j++) {
				
				if ((ascending && sortieren[j] > sortieren[j + 1]) || (!ascending && sortieren[j] < sortieren[j + 1])) {
					swapTemp = sortieren[j];
					sortieren[j] = sortieren[j + 1];
					sortieren[j + 1] = swapTemp;
				}	
			}
		}
	}
	
    //https://de.wikipedia.org/wiki/Selectionsort
    public static void selectionSort(int[] sortArray, boolean ascending) {
        
        for (int insertionIndex = 0; insertionIndex < sortArray.length; insertionIndex++) {
            int minPos = insertionIndex;

            for (int i = insertionIndex + 1; i < sortArray.length; i++) {
            	           	
                if((ascending && sortArray[i] < sortArray[minPos]) || (!ascending && sortArray[i] > sortArray[minPos])) {
                    minPos = i;
                }
            }

            int temp = sortArray[insertionIndex];
            sortArray[insertionIndex] = sortArray[minPos];
            sortArray[minPos] = temp;
        }
    }
    
	//https://de.wikipedia.org/wiki/Quicksort
	public static void quickSort(int arr[], int begin, int end, boolean ascending) {
	    if (begin < end) {
	        int partitionIndex = partition(arr, begin, end, ascending);
	 
	        quickSort(arr, begin, partitionIndex - 1, ascending);
	        quickSort(arr, partitionIndex + 1, end, ascending);
	    }
	}
	
	private static int partition(int arr[], int begin, int end, boolean ascending) {
	    int pivot = arr[end];
	    int i = (begin - 1);
	 
	    for (int j = begin; j < end; j++) {
	        if ((ascending && arr[j] <= pivot) || (!ascending && arr[j] >= pivot)) {
	            i++;
	 
	            int swapTemp = arr[i];
	            arr[i] = arr[j];
	            arr[j] = swapTemp;
	        }
	    }
	 
	    int swapTemp = arr[i + 1];
	    arr[i + 1] = arr[end];
	    arr[end] = swapTemp;
	 
	    return i + 1;
	}
	
	//https://de.wikipedia.org/wiki/Insertionsort
	public static void insertionSort(int[] sortieren, boolean ascending) {
		int temp;
		
		for (int i = 1; i < sortieren.length; i++) {
			temp = sortieren[i];
			int j = i;
			
			while ((ascending && j > 0 && sortieren[j - 1] > temp) || (!ascending && j > 0 && sortieren[j - 1] < temp)) {
				sortieren[j] = sortieren[j - 1];
				j--;
			}
			
			sortieren[j] = temp;
		}
	}
	
	public enum sortAlgorithm  {
		quickSort,
		bubbleSort,
		insertionSort,
		selectionSort
	}
	
}
 

