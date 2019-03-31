package salariati.Ui;

import salariati.enumeration.DidacticFunction;
import salariati.service.EmployeeService;

import java.util.Scanner;

public class UI {

    private EmployeeService service;
    private Scanner scanner = new Scanner(System.in);

    public UI( EmployeeService service){
        this.service = service;
    }

    public void showMenu(){
        while (true){
            try{
                int option = showOptions();
                if (option < 0 || option > 4){
                    throw new NumberFormatException();
                }
                if (option == 0){
                    break;
                }
                showSubmenu(option);
            }catch (NumberFormatException ex){
                System.out.println("Invalid option!");
            }
        }
    }

    private void showSubmenu(int option) {
        switch (option){
            case 1:
                getEmployeesList();
                break;
            case 2:
                addEmployee();
                break;
            case 3:
                modifyEmployee();
                break;
            case 4:
                getSortedEmployeeList();
                break;
            default:
                break;
        }
    }

    private void modifyEmployee() {
        System.out.println("Numele angajatului modificat : ");
        String firstName = scanner.nextLine();

        try{
            System.out.println("Functia didactica : ASISTENT/LECTURER/TEACHER/CONFERENTIAR");
            DidacticFunction didacticFunction = Enum.valueOf(DidacticFunction.class, scanner.nextLine());

            service.modifyEmployee(firstName, didacticFunction);

        }catch (IllegalArgumentException ex){
            System.out.println("Date de input invalide!");
        }
    }

    private void getSortedEmployeeList() {
        service.getSortedEmployeeList().forEach(System.out::println);
    }

    private void addEmployee() {
        System.out.println("Nume: ");
        String firstName = scanner.nextLine();
        System.out.println("Prenume: ");
        String lastName = scanner.nextLine();
        System.out.println("Cnp: ");
        String cnp = scanner.nextLine();
        try{
            System.out.println("Functia didactica : ASISTENT/LECTURER/TEACHER/CONFERENTIAR");
            DidacticFunction didacticFunction = Enum.valueOf(DidacticFunction.class, scanner.nextLine());

            System.out.println("Salariu : ");
            float salariu = Float.parseFloat(scanner.nextLine());

            service.addEmployee(firstName, lastName, cnp, didacticFunction, salariu);

        }catch (IllegalArgumentException ex){
            System.out.println("Date de input invalide!");
        }
    }

    private void getEmployeesList() {
        service.getEmployeesList().forEach(System.out::println);
    }

    private int showOptions(){
        System.out.println("1. Afiseaza lista de angajati");
        System.out.println("2. Adauga un nou angajat.");
        System.out.println("3. Modifica functia didactica a unui angajat.");
        System.out.println("4. Afiseaza salariatii ordonati descrescator dupa salariu si crescator dupa varsta (CNP). ");
        System.out.println("0. Exit");
        System.out.print("Option: ");
        return Integer.valueOf(scanner.nextLine());
    }

}
