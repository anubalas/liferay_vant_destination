from flask import Flask, jsonify, request

app = Flask(__name__)


def fibonacci(n):
    """Calculate the Fibonacci number at position n."""
    if n < 0:
        raise ValueError("Input must be a non-negative integer.")
    elif n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        a, b = 0, 1
        for _ in range(2, n + 1):
            a, b = b, a + b
        return b


@app.route('/fibonacci', methods=['GET'])
def get_fibonacci():
    """API endpoint to get Fibonacci number at position n."""
    try:
        n = int(request.args.get('n'))
        if n < 0:
            return jsonify({'error': 'Input must be a non-negative integer.'}), 400
        result = fibonacci(n)
        return jsonify({'position': n, 'fibonacci': result})
    except ValueError:
        return jsonify({'error': 'Invalid input. Please provide an integer.'}), 400


if __name__ == '__main__':
    app.run(debug=True)
