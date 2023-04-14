CC=gcc
CFLAGS=-I.
DEPS = main.h

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

main: main.o mainfunc.o 
	$(CC) -o main main.o mainfunc.o 