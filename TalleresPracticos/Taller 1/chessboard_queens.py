# author: jpastor
# Complexities:
# Time complexity: O(8!) worst case checks all queen places
# Memory complexity: O(64) for the board


def is_valid(board, row, col) -> bool:  # O(row), M(1)
    for i in range(row):  # check row by row, O(row)
        if board[i][col] == 1:
            return False  # column

        diag_left = col - (row - i)
        if diag_left >= 0 and board[i][diag_left] == 1:  # diagonal \
            return False

        diag_right = col + (row - i)
        if diag_right < 8 and board[i][diag_right] == 1:  # diagonal /
            return False
    return True


def solve_chessboard(row, board) -> int:  # O(8!), M(8)
    if row == 8:
        return 1  # 1 valid solution

    total = 0
    for col in range(8):  # O(8)
        if board[row][col] == -1:
            continue  # Blocked
        if is_valid(board, row, col):  # O(row)
            board[row][col] = 1  # put Queen
            total += solve_chessboard(row + 1, board)  # O(1),M(1)
            board[row][col] = 0  # remove Queen,backtrack
    return total


def main():  # O(64), M(64) = board
    # 0 = free, -1 = blocked or reserved
    board = [[0] * 8 for _ in range(8)]
    for i in range(8):  # O(8)
        line = input().strip()
        for j in range(8):  # O(8)
            if line[j] == "*":
                board[i][j] = -1  # Block
    print(solve_chessboard(0, board))


main()
