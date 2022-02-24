from math import sqrt


def num_pins_full_row(n: int, k: int) -> int:
    return (n // k + 1) * k + n % k + (n % k > 0) if n > 0 else 0


def num_pins_square(n: int, k: int) -> int:
    m = int(sqrt(n))
    used_pins = (m + 1)**2
    n -= m * m
    if 0 < n <= m:
        used_pins += n + 1
    elif n > m:
        used_pins += n + 2
    return used_pins if m > 0 else 0


data = tuple(map(int, input().split()))
print(min(num_pins_full_row(*data), num_pins_square(*data)))