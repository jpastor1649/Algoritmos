# author: jpastor
# Complexities:
# time complexity:O(n)
# memory complexity: M(1)


def new_year() -> int:
    # all variables are M(1)
    n, t_travel = map(int, input().split())  # problems and time traveling
    T_TOTAL = 240  # time to solve problems and get to the party
    FLAG = 0  # amount of problems that could be solved

    for i in range(1, n + 1):  # O(n) or in the best case O(1) for the break
        T_TOTAL -= 5 * i
        if (
            T_TOTAL >= t_travel
        ):  # if there is enough time, so the number of problems to solve increase 1
            FLAG += 1
        else:
            break
    return FLAG


print(new_year())
