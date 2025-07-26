# author: jpastor
# Complejidades:
# Time complexity: O(n log sum),  sum = sum(trash)
# Memory complexity: O(n)


def is_valid_workers(n, trash, workers) -> bool:  # O(n), M(1)
    carry = 0
    for i in range(n - 1):  # O(n), M(1)
        total_trash = trash[i] + carry
        if total_trash <= workers:  # check if is possible to move all the trash
            carry = 0
        else:
            # check how many movements needed to do, 1 movement = 1 worker can move 2kg of trash
            movement = total_trash - workers  # O(1), M(1)
            if (
                movement > total_trash // 2
            ):  # if movements > totaltrash/2, means #workers are not enough
                return False
            carry = 2 * movement  # O(1), M(1)
    if (trash[-1] + carry) > workers:  # O(1), M(1)
        return False
    return True


def binary_search_workers(n, trash) -> int:  # O(n log Sum), M(1)
    low = 0
    high = sum(trash)  # O(n), M(1)
    workers = high
    while low <= high:  # O(log Sum), M(1)
        mid = low + (high - low) // 2
        # checks if mid could work as number of workers nedeed
        if is_valid_workers(n, trash, mid):  # O(n), M(1)
            workers = mid
            high = mid - 1
        else:
            low = mid + 1
    return workers


def main():
    n = int(input())
    trash = list(map(int, input().split()))  # O(n), M(n)
    workers = binary_search_workers(n, trash)  # O(n log Sum), M(1)
    print(workers)


main()
