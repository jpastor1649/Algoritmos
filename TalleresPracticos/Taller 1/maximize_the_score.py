#   author: jpastor
import random


def max_score(number, arr):
    whiteboard = arr
    score = 0
    # while: Θ(n) iterations
    while whiteboard:
        whiteboard.sort(reverse=True)  # sort arr in descending order; # O(n^2 log n)
        a, b = whiteboard.pop(0), whiteboard.pop(
            0
        )  # always get the two bigger numbers in the arr, O(n)
        # all operations below O(1)
        e = min(a, b)
        score += e
    print(score)


def main():
    x = int(input())
    for i in range(0, x):  # O(n)
        y = int(input())  # O(1)
        arr = list(map(int, input().split()))  # way to convert inputs into arr, O(n)
        max_score(y, arr)  # O(n^2 log n)


main()

# Complejidades:
# time complexity:O(n^2 log n)
# memory complexity: O(n)
