import java.util.Arrays;
import java.util.ArrayList;

public class HybridSorting2 implements SortingAlgorithm {

	public void sort(int[] a) {
		sort(a, 3);
	}

	public static void sort(int[] arr, int run_size) {
		ArrayList<Integer> runIndex = findRuns(arr, run_size);	
		//System.out.println("Run Index: " + runIndex);
		//System.out.println("Array atm: " + Arrays.toString(arr));
		ArrayList<Integer> newRuns = sortNonRuns(arr, runIndex, run_size);	
		//System.out.println("Array after nonRunsSort: " + Arrays.toString(arr));
		//System.out.println("Old Runs: " + runIndex);
		System.out.println("New Runs: " + newRuns);
		System.out.println("Starting Array: " + Arrays.toString(arr));
		//mergeSort(arr, newRuns, newRuns.get(0), newRuns.size() - 1);
		mergeSort(arr, newRuns);
		System.out.println("Ending Array: " + Arrays.toString(arr));
	}
	
	/**
	 * Returns the starting position of the run
	 * A run is defined as a sequence of ascending numbers.
	 *
	 * @param arr - the array to find the run from
	 * @param run_size - the size of the run to find
	 */
	public static ArrayList<Integer> findRuns(int[] arr, int run_size) {
		ArrayList<Integer> runIndexes = new ArrayList<>();
		
		for (int i = 0; i < arr.length - run_size +1; i++) {
			int count = 1;
			for (int j = i; j < i + run_size; j++) {
				/*
				System.out.println("i : " + i);
				System.out.println("j : " + j);
				System.out.println("arr[j] : " + arr[j]);
				try {
					System.out.println("arr[j+1] : " + arr[j+1]);
				} catch (Exception e) {
					System.out.println("Array Index out of bounds");
				}
				System.out.println("count : " + count);
				*/
				if (count == run_size) {
					runIndexes.add(i);
					i += count;
					break;
				}
				
				if (arr[j] < arr[j+1]) {
					count++;
				} else {
					break;
				}
			}
		} /* e-for */
		return runIndexes;
	}
	
	public static ArrayList<Integer> sortNonRuns(int[] arr, ArrayList<Integer> runIndexes, int run_size) {
		ArrayList<Integer> newIndexes = new ArrayList<>();
		newIndexes.addAll(runIndexes);
		QuickSort.sort(arr, 0, runIndexes.get(0) -1);
		newIndexes.add(0, 0);
		for (int i = 0; i < runIndexes.size() - 1; i++) {
			QuickSort.sort(arr, runIndexes.get(i) + run_size, runIndexes.get(i + 1) - 1); 
			newIndexes.add(i + 2, runIndexes.get(i) + run_size);
		}
		return newIndexes;
	}
	/*
	public static void mergeSort(int[] arr, ArrayList<Integer> runIndex, int leftIndex, int rightIndex) {
		if (rightIndex <= leftIndex) {
			return;
		}
		int midPoint = (leftIndex + rightIndex) / 2;	
		mergeSort(arr, runIndex, leftIndex, midPoint);
		mergeSort(arr, runIndex, midPoint + 1, rightIndex);
		System.out.println("Left: " + leftIndex);
		System.out.println("Left Index: " + runIndex.get(leftIndex));
		System.out.println("Right: " + rightIndex);
		System.out.println("Right Index: " + runIndex.get(rightIndex));
		System.out.println("MidPoint: " + midPoint);
		System.out.println("Mid Index: " + runIndex.get(midPoint));
		merge(arr, runIndex, runIndex.get(midPoint), runIndex.get(leftIndex), runIndex.get(rightIndex));
	}
	*/
	
	public static void mergeSort(int[] arr, ArrayList<Integer> runIndex) {
		while (runIndex.size() > 2) {
			for (int i = 0; i < runIndex.size() - 1; i++) {
				merge(arr, runIndex.get(i), runIndex.get(i + 1), runIndex.get(i + 1), runIndex.get(i + 2));
				runIndex.remove(i + 1);
				i++;
			}
		}
		merge(arr, runIndex.get(0), runIndex.get(1), runIndex.get(1), arr.length);
	}
	public static void merge(int[] arr, int left, int leftEnd, int right, int rightEnd) {
		merge(left, Arrays.copyOfRange(arr, left, leftEnd), Arrays.copyOfRange(arr, right, rightEnd), arr);
	}
	public static void merge(int left, int[] leftArr, int[] rightArr, int[] a) {
		int leftCount = 0, rightCount = 0, target = left;
		System.out.println("Left Arr: " + Arrays.toString(leftArr));
		System.out.println("Right Arr: " + Arrays.toString(rightArr));
		while (leftCount < leftArr.length && rightCount < rightArr.length) {
			if (leftArr[leftCount] <= rightArr[rightCount]) {
				a[target++] = leftArr[leftCount++];
			} else {
				a[target++] = rightArr[rightCount++];
			}

		}
		while (leftCount < leftArr.length) {
			a[target++] = leftArr[leftCount++];
		}
		while (rightCount < rightArr.length) {
			a[target++] = rightArr[rightCount++];
		}

	}

}
