def fibonacci(n):
    """
    Generate the Fibonacci sequence up to n terms.

    Parameters:
    n (int): The number of terms in the Fibonacci sequence.

    Returns:
    list: A list containing the Fibonacci sequence up to n terms.
    """
    if n < 0:
        return []
    elif n == 0:
        return []
    elif n == 1:
        return [0]

    fib_sequence = [0, 1]
    for i in range(2, n):
        next_value = fib_sequence[-1] + fib_sequence[-2]
        fib_sequence.append(next_value)
    return fib_sequence

if __name__ == '__main__':
    import sys

    try:
        n = int(input('Enter the number of terms for the Fibonacci sequence: '))
        result = fibonacci(n)
        print(f'Fibonacci sequence up to {n} terms: {result}')
    except ValueError:
        print('Please enter a valid integer.')