package com.company;
import java.util.List;
import java.util.Scanner;

public class UI {
    public UI(List<IPracownik> m_employess) {
        this.m_employess = m_employess;
    }

    private List<IPracownik> m_employess = null;
    public void Run()
    {
        int choice = '1';
        while(choice != 'Q') {
            choice = printMenu().charAt(0);
            switch (choice) {
                case '1':
                {
                    break;
                }
                case '2':
                {
                    break;
                }
                case '3':
                {
                    break;
                }
                case '4': {
                    break;
                }
                default:
                    break;
            }
        }
    }
    public void printEmplyess() {
        Scanner scanner = new Scanner(System.in);
        int index = 0;
        byte choice = '\n';
        while(choice != 'Q') {
            System.out.println("1. Lista pracownikow");
            if(index < m_employess.size()) {
                System.out.print(m_employess.get(index));
            }
            System.out.printf("[Pozycja : %i/%i]\n",index+1,m_employess.size());
            System.out.println("[Enter] : nastepny");
            System.out.println("[Q] : powrot");
            choice = scanner.nextByte();
            if(choice == '\n') {index++;}
        }
    }
    public String printMenu() {
        System.out.println("MENU:");
        System.out.println("\t 1. Lista pracowanikow");
        System.out.println("\t 2. Dodaj pracownika");
        System.out.println("\t 3. Usun pracownika");
        System.out.println("\t 4. Kopia zapasowa");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

}
