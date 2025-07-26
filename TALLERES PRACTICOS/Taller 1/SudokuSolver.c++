#include <iostream>
#include <vector>
#include <string>
// same as python but in c++
using namespace std;

bool is_valid_num(const vector<vector<int>> &board, int row, int col, int num)
{
    for (int i = 0; i < 9; ++i)
    {
        if (board[row][i] == num || board[i][col] == num)
        {
            return false;
        }
    }
    int startRow = row - row % 3;
    int startCol = col - col % 3;
    for (int i = 0; i < 3; ++i)
    {
        for (int j = 0; j < 3; ++j)
        {
            if (board[startRow + i][startCol + j] == num)
            {
                return false;
            }
        }
    }
    return true;
}

pair<int, int> find_best_cell(const vector<vector<int>> &board)
{
    int min_options = 10;
    pair<int, int> best_cell = {-1, -1};
    for (int row = 0; row < 9; ++row)
    {
        for (int col = 0; col < 9; ++col)
        {
            if (board[row][col] == 0)
            {
                int options = 0;
                for (int num = 1; num <= 9; ++num)
                {
                    if (is_valid_num(board, row, col, num))
                    {
                        ++options;
                    }
                }
                if (options < min_options)
                {
                    min_options = options;
                    best_cell = {row, col};
                    if (options == 1)
                    {
                        return best_cell;
                    }
                }
            }
        }
    }
    return best_cell;
}

bool sudoku_solver(vector<vector<int>> &board)
{
    pair<int, int> cell = find_best_cell(board);
    if (cell.first == -1 && cell.second == -1)
    {
        return true; // no empty cells, solved
    }
    int row = cell.first;
    int col = cell.second;
    for (int num = 1; num <= 9; ++num)
    {
        if (is_valid_num(board, row, col, num))
        {
            board[row][col] = num;
            if (sudoku_solver(board))
            {
                return true;
            }
            board[row][col] = 0; // backtrack
        }
    }
    return false;
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;
    string dummy;
    getline(cin, dummy); // consume newline after t

    for (int case_num = 1; case_num <= t; ++case_num)
    {
        getline(cin, dummy); // blank line before each case

        vector<vector<int>> board(9, vector<int>(9));
        for (int i = 0; i < 9; ++i)
        {
            string line;
            getline(cin, line);
            for (int j = 0; j < 9; ++j)
            {
                if (line[j] == '.')
                {
                    board[i][j] = 0;
                }
                else
                {
                    board[i][j] = line[j] - '0';
                }
            }
        }

        sudoku_solver(board);

        cout << "Case " << case_num << ":\n";
        for (int i = 0; i < 9; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                cout << board[i][j];
            }
            cout << "\n";
        }
    }

    return 0;
}
