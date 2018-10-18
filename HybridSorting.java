import java.util.Arrays;
import java.util.ArrayList;

public class HybridSorting implements SortingAlgorithm {
	
	public void sort(int[] a) {
		sort(a, 16);
	}

	public static void sort(int[] arr, int run_size) {
		ArrayList<Integer> runIndex = findRuns(arr, run_size);	
		ArrayList<Integer> newRuns = sortNonRuns(arr, runIndex, run_size);	
		mergeSort(arr, newRuns);
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
		int count = 1;
		for (int i = 0; i < arr.length - run_size +1; i++) {
			if (count == run_size) {
				runIndexes.add(i);
				i += count;
				break;
			}
			if (arr[i] < arr[i+1]) {
				count++;
			} else {
				count = 1;
				break;
			}
			
		} /* e-for */
		return runIndexes;
	}

	
	public static ArrayList<Integer> sortNonRuns(int[] arr, ArrayList<Integer> runIndexes, int run_size) {
		if (runIndexes.size() == 0) {
			QuickSort.sort(arr, 0, arr.length - 1);
			return new ArrayList<>();
		}	
		ArrayList<Integer> newIndexes = new ArrayList<>();
		newIndexes.addAll(runIndexes);
		if (runIndexes.get(0) != 0) {
			QuickSort.sort(arr, 0, runIndexes.get(0) -1);
			newIndexes.add(0, 0);
		}
		for (int i = 1; i < runIndexes.size() - 1; i++) {
			QuickSort.sort(arr, runIndexes.get(i) + run_size, runIndexes.get(i + 1) - 1); 
			newIndexes.add(i + 2, runIndexes.get(i) + run_size);
		}
		return newIndexes;
	}
	
	public static void mergeSort(int[] arr, ArrayList<Integer> runIndex) {
		if (runIndex.isEmpty()) {
			return;
		}
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
