# Task 2 Analysis

## 1. What is the exact cause of ConcurrentModificationException in Java?

ConcurrentModificationException occurs when a collection is structurally modified while it is being iterated using an Iterator, enhanced for-loop, or stream.

Java collections use a fail-fast mechanism. The iterator internally checks whether the collection’s modification count (modCount) has changed unexpectedly during iteration. If modified outside the iterator’s own methods, the iterator throws ConcurrentModificationException.

---

## 2. What code pattern at line 142 most likely triggered this error?

Most likely code pattern:

for (Transaction txn : transactions) {
    if (txn.isInvalid()) {
        transactions.remove(txn);
    }
}

or:

Iterator<Transaction> itr = transactions.iterator();

while (itr.hasNext()) {
    Transaction txn = itr.next();

    if (txn.isInvalid()) {
        transactions.remove(txn);
    }
}

The list is being modified directly while iterating over it.

---

## 3. Provide the minimal code change that resolves this safely.

Use iterator.remove() instead of list.remove().

Correct fix:

Iterator<Transaction> itr = transactions.iterator();

while (itr.hasNext()) {
    Transaction txn = itr.next();

    if (txn.isInvalid()) {
        itr.remove();
    }
}

Alternative Java 8+ fix:

transactions.removeIf(Transaction::isInvalid);