package ua.telesens.io;

import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Реализация интерфейса Queue стандартный интерфейс Queue, для представления очереди ограниченной длины
 * с двумя текущими указателями (на начало и на конец очереди). Элементы очереди хранятся в массиве.
 * Фиксированная длина массива задается в конструкторе и далее считается неизменной.
 * Элементы добавляются в конец и берутся из начала. При добавлении и изъятии элементов указатели
 * смещаются внутри массива. После того, как конец очереди сместился на конец массива, он перемещается
 * на начало. Аналогичная процедура производится с указателем на начало очереди. Если указатель на конец
 * «догоняет» указатель на начало, очередь считается переполненной. Если указатель на начало «догоняет»
 * указатель на конец, очередь считается пустой.
 *
 * @param <E> тип элемента очереди
 */
public class ArrayQueue<E> extends AbstractQueue<E> {
    // Флаг, используемый в методе toString().
    private static boolean debug = false;

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        ArrayQueue.debug = debug;
    }

    private Object[] arr; // внутренний массив элементов очереди
    //private int count;    // реальная длина очереди
    private int head;     // позиция начала очереди
    private int tail;     // позиция конца очереди

    /**
     * Конструктор, определяющий длину массива, в котором размещаются элементы массива
     *
     * @param maxCount максимальная длина очереди (длина масиива)
     */
    public ArrayQueue(int maxCount) {
        arr = new Object[maxCount];
        head = tail = 0;
    }

    /**
     * Возвращает итератор, обеспечивающий обход очереди от начала до конца
     *
     * @return итератор
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayQueueIterator();
    }

    @Override
    public int size() {
        return tail - head;
    }

    @Override
    public boolean offer(E e) {
        if (size() < arr.length) {
            arr[tail % arr.length] = e;
            tail++;
            if (tail > Integer.MAX_VALUE - 10) {
                tail -= Integer.MAX_VALUE / 2;
                head -= Integer.MAX_VALUE / 2;
            }
            return true;
        }
        return false;
    }

    @Override
    public E poll() {
        if (size() > 0) {
            E elem = peek();
            head++;
            return elem;
        }
        return null;
    }

    @Override
    public E peek() {
        return (E) arr[head % arr.length];
    }

    // В случае отладки выводит расширенную информацию об очереди
    @Override public String toString() {
        if (isDebug()) {
            String s = ", count: " + size() + ", head: " + head % arr.length + ", tail: " + tail % arr.length;
            return super.toString() + s + ", inner array: " + Arrays.toString(arr);
        }
        return super.toString();
    }

    /**
     * Класс, реализующий итератор
     * @param <T>
     */
    private class ArrayQueueIterator<T> implements Iterator<T> {
        private int cursor = head - 1;

        @Override
        public boolean hasNext() {
            return cursor + 1 < tail;
        }

        @Override
        public T next() {
            if (hasNext()) {
                return (T) arr[++cursor % arr.length];
            }
            return null;
        }
    }
}