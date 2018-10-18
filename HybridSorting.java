import java.util.Arrays;
import java.util.ArrayList;

public class HybridSorting /*implements SortingAlgorithm*/ {
	
	public static void main(String[] args) {
		int[] arr = new int[] {1, 3, 5, 7, 2, 4, 6, 8, 9, 0, 5};
		System.out.println("Begin Array: " + Arrays.toString(arr));
		sort(arr, 3);
		System.out.println("Ending Array: " + Arrays.toString(arr));
	}

	public void sort(int[] a) {
		sort(a, 6);
	}

	public static void sort(int[] arr, int run_size) {
		ArrayList<Integer> runIndex = findRuns(arr, run_size);	
		System.out.println("Run Index: " + runIndex);
		//ArrayList<Integer> newRuns = sortNonRuns(arr, runIndex, run_size);	
		mergeSort(arr, runIndex);
		//System.out.println(Arrays.toString(arr));
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
		int prev = 0;
		try {
			for (int i = 0; i < arr.length - 1; i++) {
				if (arr[i] < arr[i+1]) {
					count++;
				} else {
					count = 1;
					continue;
				}
				if (count == run_size) {
					sortNonRun(arr, prev, i - run_size + 1);
					if (prev == (i - run_size + 2)) {
						runIndexes.add(prev);
					} else {
						runIndexes.add(prev);
						runIndexes.add(i - run_size + 2);
					}
					prev = i + 2;
					i++;
					count = 1;
				}

			} /* e-for */
			if (prev >= arr.length) {
				prev = arr.length;
			}
			runIndexes.add(prev);
			QuickSort.sort(arr, prev, arr.length - 1);
		} catch (Exception e) {
			System.out.println("Error in findRuns");
			System.out.println(e);
		}
		if (runIndexes.size() == 0) {
			QuickSort.sort(arr, 0, arr.length - 1);
		}
		return runIndexes;
	}

	public static void sortNonRun(int[] arr, int prev, int curr) {
		QuickSort.sort(arr, prev, curr);
		
	}	
	
	public static void mergeSort(int[] arr, ArrayList<Integer> runIndex) {
		System.out.println("Array after QuickSort: " + Arrays.toString(arr));
		boolean odd = false;
		int odd_value = 0;
		if (runIndex.isEmpty()) {
			return;
		}
		if (runIndex.size() % 2 == 1) {
			odd = true;	
			odd_value = runIndex.remove(runIndex.size() - 1);
		}
		try {
			/*
			while (runIndex.size() > 2) {
				for (int i = 0; i < runIndex.size() - 1; i++) {
					merge(arr, runIndex.get(i), runIndex.get(i + 1), runIndex.get(i + 1), runIndex.get(i + 2));
					runIndex.remove(i + 1);
					i++;
				}
			}
			*/
			while (runIndex.size() > 3) {
				for (int i = 0; i < runIndex.size() - 2; i++) {
				//	System.out.println("First Array: " + Arrays.toString(Arrays.copyOfRange(arr, runIndex.get(i), runIndex.get(i + 1))));
				//	System.out.println("Second Array: " + Arrays.toString(Arrays.copyOfRange(arr, runIndex.get(i + 1), runIndex.get(i + 2))));
					merge(arr, runIndex.get(i), runIndex.get(i + 1), runIndex.get(i + 1), runIndex.get(i + 2));
				//	System.out.println("Final Array: " + Arrays.toString(Arrays.copyOfRange(arr, 0, runIndex.get(i + 2))));
				//	System.out.println("Final sorted = " + Practice05Test.isSorted(Arrays.copyOfRange(arr, 0, runIndex.get(i + 2))));
					
					runIndex.remove(i + 1);
					System.out.println("Run Index: " + runIndex);
				}
			}
			if (odd) {
				merge(arr, runIndex.get(1), runIndex.get(2), runIndex.get(2), odd_value);
				runIndex.remove(2);
				merge(arr, runIndex.get(0), runIndex.get(1), runIndex.get(1), odd_value);
				merge(arr, runIndex.get(0), odd_value, odd_value, arr.length);
			} else {
				merge(arr, runIndex.get(1), runIndex.get(2), runIndex.get(2), arr.length);
				runIndex.remove(2);
				merge(arr, runIndex.get(0), runIndex.get(1), runIndex.get(1), arr.length);
			}
		} catch (Exception e) {
			System.out.println("Error in mergeSort");
			System.out.println(e);
		}
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
