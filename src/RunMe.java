/**
 * Created by barto on 01.05.17.
 *
 * Basic class explaining the repo
 *
 */
public class RunMe {

    private RunMe() {
        System.out.println("Author: Bartosz Rodziewicz");
        System.out.println();
        System.out.println("This repo contains 6 basic apps that I was supposed to code during labs on my University.");
        System.out.println("The labs was all about learning basics of Java programming language.");
        System.out.println();
        System.out.println("This repo contains 6 'programs' that I coded during them:");
        System.out.println("1. ConsoleApplication.BasicOnlineStore - app that imitates basic store with console interface");
        System.out.println("2. BasicGraphicEditor - first window app");
        System.out.println("3.1. CollectionComparisonApp - app showing differences between basic collection structures using strings as data");
        System.out.println("3.2. RoomCollectionComparisonApp - same as above but using custom class objects as data");
        System.out.println("4. ProducerConsumerApp - app representing producer-consumer problem as a basic way to show usage of threads");
        System.out.println("5. ExamApp - unfinished app done as a refresh of knowledge before exam");
        System.out.println();
        System.out.println("Interfaces of all the apps are in Polish.");
    }

    public static void main(String[] args) {
        new RunMe();
    }

}

