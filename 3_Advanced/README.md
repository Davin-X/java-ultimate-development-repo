# 3. Java Advanced Concepts

Master professional-level Java development with concurrency, design patterns, reflection, and performance optimization. This section bridges intermediate knowledge with enterprise-grade development skills.

## 🎯 Section Overview

| Topic | Duration | Priority | Difficulty | Prerequisites |
|-------|----------|----------|------------|---------------|
| [Multithreading & Concurrency](notebooks/01_Multithreading_Concurrency.ipynb) | 6-8 hours | Critical | Hard | OOP, Collections |
| [Design Patterns](notebooks/02_Design_Patterns.ipynb) | 8-10 hours | High | Medium-Hard | OOP, Inheritance |
| [Java Reflection API](notebooks/03_Reflection_API.ipynb) | 5-7 hours | Medium | Hard | OOP, Annotations |
| [JVM Internals & Memory Management](notebooks/04_JVM_Internals.ipynb) | 7-9 hours | High | Hard | Memory Understanding |
| [Native Methods & JNI](notebooks/05_Native_Methods_JNI.ipynb) | 4-6 hours | Medium | Hard | C/C++ Knowledge |
| [Advanced Collections & Utilities](notebooks/06_Advanced_Collections.ipynb) | 5-6 hours | Medium | Medium | Collections Framework |
| [Networking & Sockets](notebooks/07_Networking_Sockets.ipynb) | 6-8 hours | Medium | Medium | I/O Operations |

## 📋 Prerequisites

**Before starting this section:**
- ✅ **Strong OOP Foundation**: Classes, inheritance, polymorphism, interfaces
- ✅ **Intermediate Concepts**: Generics, collections, exception handling, I/O
- ✅ **Basic Threading Knowledge**: At least understand `Thread` and `Runnable`
- ✅ **Performance Awareness**: Basic understanding of Big O notation
- ✅ **Java 8+ Familiarity**: Lambda expressions and functional interfaces
- ✅ **Build Tools Experience**: Maven or Gradle usage

## 🎓 Learning Objectives

By the end of this section, you will be able to:

- ✅ **Concurrent Programming**: Write thread-safe, efficient multithreaded applications
- ✅ **Design Pattern Mastery**: Apply 15+ design patterns in real-world scenarios
- ✅ **Reflection Expertise**: Use reflection for dynamic code analysis and manipulation
- ✅ **JVM Optimization**: Tune JVM parameters and understand garbage collection
- ✅ **Native Integration**: Create and use JNI for performance-critical code
- ✅ **Advanced Collections**: Master concurrent and specialized collections
- ✅ **Network Programming**: Build client-server applications with sockets
- ✅ **Enterprise Architecture**: Design scalable, maintainable systems

## 📓 Interactive Notebooks

### [01. Multithreading & Concurrency](notebooks/01_Multithreading_Concurrency.ipynb)
- **Thread Lifecycle**: Creation, execution, termination
- **Synchronization**: `synchronized`, `ReentrantLock`, atomic variables
- **Thread Communication**: `wait()`, `notify()`, `notifyAll()`
- **Executor Framework**: `ThreadPoolExecutor`, `Executors`, `Future`
- **Concurrent Collections**: `ConcurrentHashMap`, `BlockingQueue`, `CopyOnWriteArrayList`
- **Memory Model**: Happens-before, volatile, final fields
- **Synchronization Patterns**: Monitors, semaphores, barriers

```java
// Thread-safe counter using AtomicInteger
public class AtomicCounter {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public void decrement() {
        count.decrementAndGet();
    }

    public int get() {
        return count.get();
    }
}

// Producer-Consumer pattern with BlockingQueue
public class ProducerConsumer {
    private final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public void produce(int value) throws InterruptedException {
        queue.put(value);
    }

    public int consume() throws InterruptedException {
        return queue.take();
    }
}
```

### [02. Design Patterns](notebooks/02_Design_Patterns.ipynb)
- **Creational Patterns**: Singleton, Factory, Builder, Prototype
- **Structural Patterns**: Adapter, Decorator, Facade, Proxy, Composite
- **Behavioral Patterns**: Observer, Strategy, Command, State, Iterator
- **Concurrency Patterns**: Thread Pool, Producer-Consumer, Read-Write Lock
- **Pattern Selection**: Trade-offs and appropriate usage scenarios

```java
// Singleton with thread safety (Bill Pugh solution)
public class ThreadSafeSingleton {
    private ThreadSafeSingleton() {}

    private static class SingletonHelper {
        private static final ThreadSafeSingleton INSTANCE = new ThreadSafeSingleton();
    }

    public static ThreadSafeSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}

// Builder pattern for complex object construction
public class Computer {
    private final String processor;
    private final int ram;
    private final int storage;
    private final String graphics;

    private Computer(Builder builder) {
        this.processor = builder.processor;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphics = builder.graphics;
    }

    public static class Builder {
        private String processor;
        private int ram;
        private int storage;

        public Builder processor(String processor) {
            this.processor = processor;
            return this;
        }

        public Builder ram(int ram) {
            this.ram = ram;
            return this;
        }

        // ... other setters

        public Computer build() {
            return new Computer(this);
        }
    }
}
```

### [03. Java Reflection API](notebooks/03_Reflection_API.ipynb)
- **Class Loading**: Dynamic class loading with `Class.forName()`
- **Runtime Inspection**: Fields, methods, constructors discovery
- **Dynamic Invocation**: Method invocation, field access at runtime
- **Annotation Processing**: Custom annotations and processors
- **Bytecode Manipulation**: ASM framework for runtime class modification
- **Security Considerations**: Access restrictions and security manager

```java
// Runtime method invocation using reflection
public class ReflectionUtils {
    public static void invokeMethod(Object obj, String methodName, Object... args) {
        try {
            Class<?> clazz = obj.getClass();
            Class<?>[] paramTypes = Arrays.stream(args)
                .map(Object::getClass)
                .toArray(Class<?>[]::new);

            Method method = clazz.getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            method.invoke(obj, args);

        } catch (Exception e) {
            throw new RuntimeException("Reflection invocation failed", e);
        }
    }
}

// Generic object instantiation
public class ObjectFactory {
    public static <T> T createInstance(Class<T> clazz) throws Exception {
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        return constructor.newInstance();
    }
}
```

### [04. JVM Internals & Memory Management](notebooks/04_JVM_Internals.ipynb)
- **JVM Architecture**: Class loader, runtime data areas, execution engine
- **Heap Memory**: Young generation, old generation, metaspace
- **Garbage Collectors**: G1, CMS, ZGC performance characteristics
- **JVM Tuning**: Memory flags, GC parameters, diagnostic tools
- **Memory Leaks**: Detection and prevention strategies
- **Profiling Tools**: VisualVM, JConsole, JMX for monitoring

```java
// JVM memory monitoring
public class JVMMemoryMonitor {
    public static void printHeapUsage() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();

        System.out.println("Heap Memory Usage:");
        System.out.printf("  Initial: %s%n", formatBytes(heapUsage.getInit()));
        System.out.printf("  Used:    %s%n", formatBytes(heapUsage.getUsed()));
        System.out.printf("  Committed: %s%n", formatBytes(heapUsage.getCommitted()));
        System.out.printf("  Max:     %s%n", formatBytes(heapUsage.getMax()));
    }

    private static String formatBytes(long bytes) {
        // Formatting logic for bytes to human readable
        if (bytes < 1024) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        char pre = "KMGTPE".charAt(exp - 1);
        return String.format("%.1f %cB", bytes / Math.pow(1024, exp), pre);
    }
}
```

### [05. Native Methods & JNI](notebooks/05_Native_Methods_JNI.ipynb)
- **JNI Fundamentals**: `native` keyword, header generation
- **C Integration**: Calling C functions from Java
- **Data Type Mapping**: Java types to C types conversion
- **Memory Management**: JNI memory allocation and cleanup
- **Exception Handling**: Java-C exception propagation
- **Performance Optimization**: Using native code for bottlenecks

```java
// Native method declaration
public class FastMath {
    // Load native library
    static {
        System.loadLibrary("fastmath");
    }

    // Native method for performance-critical calculations
    public native double fastSqrt(double value);

    public native int fastFactorial(int n);

    // Hybrid method: Java wrapper around native function
    public double safeSqrt(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number");
        }
        return fastSqrt(value);
    }
}

/* Corresponding C header file (generated by javah):
#ifndef _Included_com_example_FastMath
#define _Included_com_example_FastMath
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jdouble JNICALL Java_com_example_FastMath_fastSqrt
  (JNIEnv *, jobject, jdouble);

JNIEXPORT jint JNICALL Java_com_example_FastMath_fastFactorial
  (JNIEnv *, jobject, jint);

#ifdef __cplusplus
}
#endif
#endif
*/
```

### [06. Advanced Collections & Utilities](notebooks/06_Advanced_Collections.ipynb)
- **Concurrent Collections**: High-performance thread-safe collections
- **Specialized Collections**: LRU cache, priority queues, skip lists
- **Custom Implementations**: Building efficient data structures
- **Collection Utilities**: `Collections`, `Arrays`, `Objects` utilities
- **Stream API Advanced**: Parallel streams, custom collectors
- **Performance Optimization**: Choosing right collection for use case

```java
// High-performance concurrent cache
public class ConcurrentLRUCache<K, V> {
    private final Map<K, V> cache;
    private final Deque<K> accessOrder;

    public ConcurrentLRUCache(int capacity) {
        this.cache = new ConcurrentHashMap<>(capacity, 0.75f, 16);
        this.accessOrder = new ConcurrentLinkedDeque<>();
    }

    public V get(K key) {
        V value = cache.get(key);
        if (value != null) {
            // Move to end (most recently used)
            accessOrder.remove(key);
            accessOrder.addLast(key);
        }
        return value;
    }

    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            accessOrder.remove(key);
        } else if (cache.size() == capacityLimit) {
            K oldest = accessOrder.removeFirst();
            cache.remove(oldest);
        }
        cache.put(key, value);
        accessOrder.addLast(key);
    }

    // Implementation continues...
}
```

### [07. Networking & Sockets](notebooks/07_Networking_Sockets.ipynb)
- **Socket Programming**: TCP/UDP client-server implementations
- **HTTP Clients**: URLConnection, HttpClient (Java 11+)
- **Asynchronous I/O**: NIO.2 channels and selectors
- **Network Protocols**: Custom protocol design and implementation
- **Security**: SSL/TLS encryption, certificate handling
- **Performance**: Connection pooling, non-blocking I/O

```java
// Simple TCP server using NIO.2
public class EchoServer {
    public static void main(String[] args) throws IOException {
        AsynchronousServerSocketChannel server =
            AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(8080));

        System.out.println("Server listening on port 8080...");

        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel client, Void att) {
                server.accept(null, this); // Accept next connection

                ByteBuffer buffer = ByteBuffer.allocate(1024);

                // Read from client
                client.read(buffer, null, new CompletionHandler<Integer, Void>() {
                    @Override
                    public void completed(Integer bytesRead, Void att) {
                        if (bytesRead == -1) {
                            try {
                                client.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;
                        }

                        buffer.flip();
                        // Echo back to client
                        client.write(buffer, null, new CompletionHandler<Integer, Void>() {
                            @Override
                            public void completed(Integer bytesWritten, Void att) {
                                buffer.clear();
                                // Continue reading
                                client.read(buffer, null, this);
                            }
                            // ... handle failures
                        });
                    }
                    // ... handle failures
                });
            }
            // ... handle failures
        });

        // Keep server running
        System.in.read();
    }
}
```

## 🛠️ Practical Projects

### [Concurrent Web Crawler](projects/Concurrent_Web_Crawler/)
- **Difficulty**: Advanced
- **Duration**: 8-10 hours
- **Features**: Multithreaded web crawling, URL deduplication, configurable depth
- **Skills**: Concurrency, networking, data structures, thread safety

### [Plugin Architecture Framework](projects/Plugin_Architecture_Framework/)
- **Difficulty**: Advanced
- **Duration**: 10-12 hours
- **Features**: Extensible plugin system, dynamic loading, reflection-based discovery
- **Skills**: Reflection, design patterns, class loading, interfaces

### [In-Memory Database Engine](projects/In_Memory_Database_Engine/)
- **Difficulty**: Advanced
- **Duration**: 12-15 hours
- **Features**: CRUD operations, indexing, transaction support, query optimization
- **Skills**: Collections, concurrency, data structures, algorithms

### [Distributed Cache System](projects/Distributed_Cache_System/)
- **Difficulty**: Expert
- **Duration**: 15-20 hours
- **Features**: Consistent hashing, replication, eviction policies, clustering
- **Skills**: Networking, concurrency, distributed systems, serialization

### [JVM Monitoring Dashboard](projects/JVM_Monitoring_Dashboard/)
- **Difficulty**: Advanced
- **Duration**: 8-10 hours
- **Features**: Real-time JVM metrics, memory analysis, thread monitoring, GC stats
- **Skills**: JMX, monitoring APIs, data visualization, concurrency

## 📚 Supporting Documentation

### [Performance Tuning Guide](documentation/Performance_Tuning.md)
- JVM parameter optimization
- Memory leak detection strategies
- Profiling tools and techniques

### [Concurrency Best Practices](documentation/Concurrency_Best_Practices.md)
- Thread safety patterns
- Deadlock prevention
- Performance optimization

### [Design Pattern Catalog](documentation/Design_Pattern_Catalog.md)
- 25+ pattern implementations
- Real-world usage examples
- Anti-patterns to avoid

### [JVM Deep Dive](documentation/JVM_Deep_Dive.md)
- Class loading mechanics
- JIT compilation process
- Bytecode interpretation

## 💡 Learning Tips

### 🏃‍♂️ **Master Threading Gradually**
- Start with basic Thread/Runnable
- Move to ExecutorService patterns
- Study concurrent collections
- Understand memory model deeply

### 🔍 **Study Source Code**
- Read JDK source code for collections
- Understand java.util.concurrent implementations
- Analyze Spring Framework patterns
- Study open-source Java projects

### 🛡️ **Practice Thread Safety**
- Identify race conditions first
- Use proper synchronization
- Test concurrency with stress testing
- Profile for performance bottlenecks

### 📊 **Understand JVM Internals**
- Experiment with different JVM flags
- Learn garbage collector tuning
- Use profiling tools regularly
- Understand heap dump analysis

## 🎯 Progress Tracking

Advanced learning requires careful progression:

### Month 1: Concurrency Foundation
- [ ] Multithreading fundamentals
- [ ] Synchronization patterns
- [ ] Concurrent collection usage
- [ ] Basic thread communication

### Month 2: Design Patterns Deep Dive
- [ ] Creational patterns mastery
- [ ] Structural patterns implementation
- [ ] Behavioral patterns understanding
- [ ] Pattern selection and trade-offs

### Month 3: System Programming
- [ ] Reflection API mastery
- [ ] JVM internals understanding
- [ ] Native method integration
- [ ] Advanced networking concepts

### Month 4: Proficiency Building
- [ ] Major project implementation
- [ ] Performance optimization
- [ ] Best practices application
- [ ] Enterprise patterns adoption

## 🧪 Knowledge Verification

### Technical Assessments
- Implement thread-safe singleton patterns
- Design concurrent data structures
- Use reflection for dynamic proxy creation
- Optimize JVM memory usage for high-load applications

### Code Review Exercises
- Analyze concurrency issues in legacy code
- Refactor synchronous code to asynchronous
- Evaluate design pattern implementations
- Assess JVM performance in production code

## 🔗 Navigation

| Previous Level | Current Level | Next Level |
|---------------|---------------|------------|
| [Intermediate](../2_Intermediate/) | **Advanced Level** | [Interview Prep](../4_Interview_Preparation/) |

## 📞 Section Completion Checklist

- [ ] Master concurrent programming concepts
- [ ] Implement 10+ design patterns from scratch
- [ ] Use reflection API confidently for advanced scenarios
- [ ] Tune JVM parameters for optimal performance
- [ ] Integrate native code using JNI
- [ ] Build networked applications with sockets
- [ ] Choose appropriate collections for specific use cases

## 🎉 Achievement Unlocked

**Congratulations!** 🏆🏆 **Java Advanced Developer**

You now possess enterprise-level Java skills and can design scalable, concurrent, and high-performance applications. Your understanding of JVM internals, design patterns, and advanced concepts prepares you for senior developer roles.

**Ready to conquer interview challenges?** Proceed to [Interview Preparation](../4_Interview_Preparation/) where you'll develop the algorithmic thinking required for top-tier technical interviews.

---

**🚀 Begin your advanced journey with [Multithreading & Concurrency](notebooks/01_Multithreading_Concurrency.ipynb)**
