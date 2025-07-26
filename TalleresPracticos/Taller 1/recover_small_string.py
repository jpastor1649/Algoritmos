# author: jpastor
# Complexities:
# time complexity:O(1)
# memory complexity: M(1)
import string

abc = list(string.ascii_lowercase)  # lowercase letters O(1), array of 26 chr


def small_string(x):
    if x < 3 or x > 79:  # at least 3 chr, zzz = 78; O(1), M(1)
        return "impossible"
    if x <= 26:  # O(1), M(1)
        third = x - 2
        return "aa" + abc[third - 1]  # O(1) direct access to index,, M(1)
    else:
        for first in range(
            max(1, x - 52), 27
        ):  # max 26 iterations, bcs 78 is the max number for x ;O(1), M(1)
            remain = (
                x - first
            )  # first is the first min letter so second and third will be max 26
            for second in range(1, 27):  # max 26 iterations O(1), M(1)
                third = (
                    remain - second
                )  # when we find a number where second and third are between 1 and 26, end job
                if 1 <= third <= 26:
                    return (
                        abc[first - 1] + abc[second - 1] + abc[third - 1]
                    )  # direct access to index -1 :O(1), M(1)
        return "impossible"


cases = int(input(""))
for i in range(1, cases + 1):  # O(cases) -- > O(1)
    x = int(input(""))
    print(small_string(x))
