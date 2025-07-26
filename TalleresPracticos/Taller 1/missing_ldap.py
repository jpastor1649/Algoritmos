# author: jpastor
# Complexities:
# Time complexity: two for loops
# Memory complexity: O(N²) two for loops
# CORREGIDO
import sys


def flush():
    sys.stdout.flush()  # O(1) M(1)


def generate_ldap_variants(first_name, second_name, first_surname, second_surname):
    variants = []

    second_initial = second_name[0] if second_name else ""  # O(1) M(1)
    max_length_first_name = len(first_name)
    max_length_second_surname = len(second_surname)

    for i in range(1, max_length_first_name + 1):  # O(N) M(1)
        fg_part = first_name[:i]  # O(i)  M(i)
        base = fg_part + second_initial + first_surname  # O(N) M(N)

        for j in range(0, min(i, max_length_second_surname) + 1):
            ldap = base + second_surname[:j]
            variants.append(ldap)

    for k in range(max_length_first_name + 1, max_length_second_surname + 1):
        ldap = first_name + second_initial + first_surname + second_surname[:k]
        variants.append(ldap)
    return variants  # O(1) M(O(N^2))


def binary_search(variants):
    low = 0
    high = len(variants) - 1
    ans = -1
    print(f"{variants[low]}")
    flush()

    query = int(input())
    if query == 0:
        print(f"! {variants[low]}")
        return
    else:
        while low <= high:
            mid = low + (high - low) // 2
            print(f"{variants[mid]}")
            flush()

            query = int(input())
            if query == 1:
                low = mid + 1
            elif query == 0:
                high = mid - 1
        print(f"! {variants[low]}")
        flush()


def main():
    full_name = input().strip().split()  # O(1) M(1)

    if len(full_name) == 3:
        first_name, first_surname, second_surname = full_name  # O(1) M(1)
        second_name = ""  # O(1) M(1)
    else:
        # all below O(1) M(1)
        first_name = full_name[0]
        second_name = full_name[1]
        first_surname = full_name[2]
        second_surname = full_name[3]

    variants = generate_ldap_variants(
        first_name, second_name, first_surname, second_surname
    )  # O(N^2) M(N^2)
    binary_search(variants)  # O(log N) M(1)


main()
