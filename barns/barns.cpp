// Issue the following command from a command line to build the executable code:
// gcc -std=c++11 -lstdc++ barns.cpp -o barns

#include <iostream>
using namespace std;


const size_t C = 100;
int num_barns[C][C];

int main() {
    ios_base::sync_with_stdio(false);

    int n, c;
    cin >> n >> c;

    // Calculates n (mod m) like it is done in Python.
    // Source: https://stackoverflow.com/a/1907585
    auto mod = [](long n, long m) {
        return ((n % m) + m) % m;
    };

    for (int i = 0; i < n; i++) {
        long x, y;
        cin >> x >> y;
        num_barns[mod(x, c)][mod(y, c)]++;
    }

    int max_visitable_barns = 1;

    for (int i = 0; i < c; i++)
        for (int j = 0; j < c; j++)
            if (num_barns[i][j] > 0) {
                int visitable_barns = 0;

                for (int k = 0; k < c; k++) {
                    visitable_barns += num_barns[i][k];
                    if (i != k)
                        visitable_barns += num_barns[k][j];
                    if (k > 0) {
                        visitable_barns += num_barns[mod(i + k, c)][mod(j - k, c)];
                        if (mod(j - k, c) != mod(j - c + k, c))
                            visitable_barns += num_barns[mod(i + k, c)][mod(j - c + k, c)];
                    }
                }

                if (max_visitable_barns < visitable_barns)
                    max_visitable_barns = visitable_barns;
            }

    cout << max_visitable_barns << endl;
    return 0;
}