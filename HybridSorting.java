import java.util.Arrays;
import java.util.ArrayList;

public class HybridSorting {

	public static void main(String[] args) {
		int[] arr = new int[] {5, 2, 3, 1, 3, 5, 2, 0, 6, 7};
		sort(arr, 3);	
	}

	public static void sort(int[] arr, int run_size) {
		ArrayList<Integer> runIndex = findRuns(arr, run_size);	
		System.out.println("Run Index: " + runIndex);
		System.out.println("Array atm: " + Arrays.toString(arr));
		sortNonRuns(arr, runIndex, run_size);	
		System.out.println("Array after nonRunsSort: " + Arrays.toString(arr));
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
	
	public static void sortNonRuns(int[] arr, ArrayList<Integer> runIndexes, int run_size) {
		QuickSort.sort(arr, 0, runIndexes.get(0) -1);
		for (int i = 0; i < runIndexes.size() - 1; i++) {
			QuickSort.sort(arr, runIndexes.get(i) + run_size, runIndexes.get(i + 1) - 1); 
		}
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
