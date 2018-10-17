import java.util.Arrays;

public class MergeSort implements SortingAlgorithm {
	/*
	public static void main(String[] args) {
		int[] test = new int[] {1, 5, 2, 7, 4};
		//System.out.println("Array before sort: " + Arrays.toString(test));
		sort(test);
		//System.out.println("Array after sort: " + Arrays.toString(test));

	}
	*/
	public void sort(int[] a) {
		mergeSort(a, 0, a.length - 1);
	}
	
	static void mergeSort(int[] a, int left, int right) {
		if (right <= left) {
			return;
		}
		int midPoint = (left + right)/2;
		//System.out.println("My broken left Array is: " + Arrays.toString(Arrays.copyOfRange(a, left, midPoint + 1)));
		//System.out.println("My broken right Array is: " + Arrays.toString(Arrays.copyOfRange(a, midPoint + 1, right + 1)));
		mergeSort(a, left, midPoint);
		mergeSort(a, midPoint + 1, right);
		merge(a, midPoint, left, right);
	}
	
	static void merge(int[] a, int mid, int left, int right) {
		//System.out.println("Left Array: " + Arrays.toString(Arrays.copyOfRange(a, left, mid + 1)));
		//System.out.println("Right Array: " + Arrays.toString(Arrays.copyOfRange(a, mid + 1, right + 1)));
		//System.out.println("Whole Array: " + Arrays.toString(a));
		merge(left, Arrays.copyOfRange(a, left, mid + 1), Arrays.copyOfRange(a, mid + 1, right + 1), a);

	}
	
	static void merge(int left, int[] leftArr, int[] rightArr, int[] a) {
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
