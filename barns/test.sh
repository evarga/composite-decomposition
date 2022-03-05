#!/bin/bash

# Run this script from the folder where your solution code is located.
# Tests are expected to be located inside the 'tests' subfolder.
# Input is assumed to be stored in the '#test_number.in' and the matching 
# output in the '#test_number.out' files, respectively.

failures=0

for file in tests/[0-9]*.in; do
    # Extract the extension from a full file path.
    # Source: https://stackoverflow.com/a/965072
    filename=$(basename -- "$file")
    test_number="${filename%.*}"

    echo "Test number: " $test_number
    actual=$(./barns < $file)
    expected=$(cat tests/$test_number.out)

    if [ "$actual" != "$expected" ]; then
        echo "Failed!"
        echo "Actual: $actual"
        echo "Expected: $expected"
        failures=$((failures + 1))
    else
        echo "Passed"
    fi
done

echo
if [ $failures -gt 0 ]; then
  echo There were $failures failures!
else
  echo All tests have passed!
fi
