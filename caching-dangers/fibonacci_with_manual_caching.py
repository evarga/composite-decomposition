def fib_with_manual_caching_ok(n, store=None):
    if store is None:
        store = {}

    if n < 2:
        return n, 1
    elif n in store:
        return store[n]

    fib1 = fib_with_manual_caching_ok(n - 1, store)
    fib2 = fib_with_manual_caching_ok(n - 2, store)
    store[n] = fib1[0] + fib2[0], 2 + fib1[1]
    return store[n]


def fib_with_manual_caching_bad(n, store={}):
    if n < 2:
        return n, 1
    elif n in store:
        return store[n]

    fib1 = fib_with_manual_caching_bad(n - 1, store)
    fib2 = fib_with_manual_caching_bad(n - 2, store)
    store[n] = fib1[0] + fib2[0], 2 + fib1[1]
    return store[n]
