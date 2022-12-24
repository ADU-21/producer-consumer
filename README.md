# Producer Consumer Demo

Producer Consumer Solution using blocking Queue

# Environment

- JDK 17

# How to use

```bash
git clone https://github.com/ADU-21/producer-consumer.git
cd producer-consumer
./gradlew bootRun
```

# Run test

```bash
for q in {A..C}; do ( ab -n 5000 -c 5 localhost:8080/search?q=$q 1>/dev/null & ); done
```