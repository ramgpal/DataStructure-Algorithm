public class Queries {
    static int tree[];

    public static void initialize(int n) {
        tree = new int[4 * n];
    }

    // Function to create Segment tree
    public static int buildST(int arr[], int index, int start, int end) {
        // for leaf nodes
        if (start == end) { // index -> index of the tree
            tree[index] = arr[start];
            return arr[start];
        }

        int mid = (start + end) / 2;
        int left = buildST(arr, 2 * index + 1, start, mid); // for left subtree -> 2*i+1
        int right = buildST(arr, 2 * index + 2, mid + 1, end); // for right subtree -> 2*i+2
        // parent node 
        tree[index] = left + right;
        return tree[index];
    }

// Queries in Segment Tree -> finding subarray sum in a given query range i to j
public static int getSumUtil(int i, int si, int sj, int qi, int qj) {
    // Case1: Non-Overlapping
    if (qi >= sj || qj <= si) {
        return 0;
    }
    // Case2: Overlapping
    else if (si >= qi && sj <= qj) {
        return tree[i];
    }
    // Case3: Partially Overlapping
    else {
        int mid = (si + sj) / 2;
        int leftHalf = getSumUtil(2 * i + 1, si, mid, qi, qj);
        int rightHalf = getSumUtil(2 * i + 2, mid + 1, sj, qi, qj);
        return leftHalf + rightHalf;
    }
}

    public static int getSum(int arr[], int qi, int qj) {
        return getSumUtil(0, 0, arr.length-1, qi, qj);
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8};
        initialize(arr.length);
        buildST(arr, 0, 0, arr.length - 1);

        // for (int i = 0; i < tree.length; i++) {
        //     System.out.print(tree[i] + " ");
        // }
        System.out.println(getSum(arr, 2, 5));
    }
}
