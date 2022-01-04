def fib_without_caching(n):
    if n < 2:
        return n, 1

    fib1 = fib_without_caching(n - 1)
    fib2 = fib_without_caching(n - 2)
    return fib1[0] + fib2[0], 1 + fib1[1] + fib2[1]
