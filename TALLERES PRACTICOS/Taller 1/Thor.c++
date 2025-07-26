#include <iostream>
#include <vector>
#include <set>
#include <deque>

using namespace std;

void thor_notifs(int n, int q) {
    int curr_id = 0;
    int unread_notif = 0;
    set<int> read_notif;
    deque<pair<int,int>> notif_queue;
    vector<set<int>> app_notif(n+1);

    int read_pointer = 0;  // <-- puntero global para evento tipo 3

    for (int i = 0; i < q; ++i) {
        int event_type, x;
        cin >> event_type >> x;

        if (event_type == 1) {
            ++curr_id;
            app_notif[x].insert(curr_id);
            notif_queue.emplace_back(curr_id, x);
            ++unread_notif;
        }
        else if (event_type == 2) {
            for (int id : app_notif[x]) {
                if (!read_notif.contains(id)) {
                    read_notif.insert(id);
                    --unread_notif;
                }
            }
            app_notif[x].clear();
        }
        else if (event_type == 3) {
            // Leemos todas las notificaciones desde read_pointer hasta x (t)
            while (read_pointer < x && read_pointer < (int)notif_queue.size()) {
                auto [id, app_id] = notif_queue[read_pointer];
                if (!read_notif.contains(id)) {
                    read_notif.insert(id);
                    --unread_notif;
                    app_notif[app_id].erase(id);
                }
                ++read_pointer;
            }
        }

        cout << unread_notif << '\n';
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, q;
    cin >> n >> q;
    thor_notifs(n, q);
    return 0;
}
