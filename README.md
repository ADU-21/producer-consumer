# Producer Consumer Demo

Producer Consumer Solution using blocking Queue:

<https://blog.duyidong.com/2020/07/20/data-structure-and-time-complexity/>

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
for q in {A..C}; do ( ab -n 10000 -c 5 localhost:8080/search?q=$q &>/dev/null & ); done && sleep 1 && tail -f metrics.txt
```

You should be able to see metric statistics print from `metrics.txt` to your terminal.