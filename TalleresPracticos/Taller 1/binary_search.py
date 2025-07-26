# author: jpastor
# Complexities:
# time complexity: O(log n)
# memory complexity: M(n)
def binary_search(arr, x) -> int:
    low, mid = 0, 0  # O(1),M(1)
    high = len(arr) - 1  # O(1),M(1)
    result = -1
    while low <= high:  # O(log n)
        mid = low + (high - low) // 2
        if arr[mid] < x:
            low = mid + 1  # O(1),M(1)
        elif arr[mid] > x:
            high = mid - 1  # O(1),M(1)
        else:
            result = mid  # O(1)
            high = mid - 1
    return result


n, q = map(int, input("").split())  # O(1),M(1)
arr = list(map(int, input("").split()))  # O(n),M(n)
for i in range(0, q):  # O(q)
    x = int(input(""))  # O(1),M(1)
    print(binary_search(arr, x))  # O(log n) for binary search
