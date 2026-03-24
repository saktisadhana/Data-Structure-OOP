#include <stdio.h>
#include <string.h>

#define MAX 100

typedef struct {
    char title[50];
} Song;
Song queue[MAX];
int front = -1, rear = -1;

Song stack[MAX];
int top = -1;

void enqueue(Song s) {
    if (rear == MAX - 1) return;
    if (front == -1) front = 0;
    queue[++rear] = s;
}
Song dequeue() {
    Song s = {"None"};
    if (front == -1) return s;

    s = queue[front];
    if (front == rear) front = rear = -1;
    else front++;

    return s;
}
void push(Song s) {
    if (top == MAX - 1) return;
    stack[++top] = s;
}
Song pop() {
    Song s = {"None"};
    if (top == -1) return s;
    return stack[top--];
}

int main() {
    int option;
    Song current;

    while (1) {
        printf("\n=== Nel's Playlist ===\n");
        printf("1. Add Song\n");
        printf("2. Next\n");
        printf("3. Prev\n");
        printf("4. Exit\n");
        printf("What's for today, Nel?: ");
        scanf("%d", &option);

        switch (option) {
            case 1: {
                Song s;
                printf("Song title: ");
                scanf(" %[^\n]", s.title);
                enqueue(s);
                printf("Song added :D\n");
                break;
            }
            case 2:
                current = dequeue();

                if (strcmp(current.title, "None") == 0) {
                    printf("No songs available :(\n");
                } else {
                    printf("Now Playing: %s\n", current.title);
                    push(current);
                }
                break;
            case 3:
                current = pop();
                if (strcmp(current.title, "None") == 0) {
                    printf("No song to replay :(\n");
                } else {
                    printf("Replaying: %s\n", current.title);
                }
                break;
            case 4:
                printf("Thanks for listening, Nel! Have a pleasent day!\n");
                return 0;
            default:
                printf("Error. Invalid option.\n");
                break;
        }
    }
    return 0;
}