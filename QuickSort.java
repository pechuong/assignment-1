import java.util.Arrays;

public class QuickSort {

	/*
	public static void main(String[] args) {
		int[] test = new int[] {3, 5, 1, 2, 4};
		System.out.println("Beginning Array: " + Arrays.toString(test));
		sort(test);
		System.out.println("Ending Array: " + Arrays.toString(test));

	}
	*/

	public static void sort(int[] a, int left, int right) {
		quickSort(a, left, right);
	}
	
	public static void quickSort(int[] a, int left, int right) {
		if (left < right) {
			int pivot = partition(a, left, right);
			quickSort(a, left, pivot - 1);
			quickSort(a, pivot + 1, right);
		}
	}
	public static int partition(int[] a, int left, int right) {
		int pivot = (int)(Math.random() * right + left);
		int i = left;
		int k = right;

		while (i < pivot && k > pivot) {
			while (i < right && a[i] <= a[pivot]) {
				i++;
			}
			while (k >= i && a[k] > a[pivot]) {
				k--;
			}
			if (i < k) {
				swap(a, i, k);
			}
		}
		swap(a, pivot, k);
		return k;
		
	}
	/*
	public static int partition(int[] a, int left, int right) {
		int pivot = left;
		int i = left + 1;
		int k = right;

		while (i < k) {
			while (i < right && a[i] <= a[pivot]) {
				i++;
			}
			while (k >= i && a[k] > a[pivot]) {
				k--;
			}
			if (i < k) {
				swap(a, i, k);
			}
		}
		swap(a, pivot, k);
		return k;
		
	}
	*/
	static void swap(int[] a, int index1, int index2) {
		int temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}

}
