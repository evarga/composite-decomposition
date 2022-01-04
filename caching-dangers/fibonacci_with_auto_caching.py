from functools import cache


@cache  # @lru_cache(maxsize=None)
def fib_with_auto_caching(n):
    if n < 2:
        return n, 1

    fib1 = fib_with_auto_caching(n - 1)
    fib2 = fib_with_auto_caching(n - 2)
    return fib1[0] + fib2[0], 2 + fib1[1]
