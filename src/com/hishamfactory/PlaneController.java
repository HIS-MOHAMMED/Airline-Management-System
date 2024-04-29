package com.hishamfactory;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PlaneController {
    Scanner sc = new Scanner(System.in);

    public static Plane getPlaneById(String plane_code) {
        for (Plane plane : Company.planes) {
            if (plane_code.equals(plane.getPlane_id())) return plane;
        }
        return null;
    }

    public void printAllPlanes() {
        System.out.println("................List of Planes.............");
        for (Plane plane : Company.planes) {
            System.out.println(plane.toString());
        }
        System.out.println("...........................................");

    }

    public void editPlane(String plane_code) {
        Plane plane = getPlaneById(plane_code);
        if(plane != null) {
            System.out.println("1.Edit Model");
            System.out.println("2.Edit Manufacturer");
            System.out.println("3.Edit Capacity");
            // System.out.println("4.Edit Current Passengers: ");
            System.out.println("4.Quit");
            System.out.print("select option: ");
            try {
                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        System.out.print("Enter new Model: ");
                        plane.setPlane_model(sc.next());
                        System.out.println("Model changed");
                        break;

                    case 2:
                        System.out.print("Enter new Manufacturer: ");
                        plane.setPlane_manufacturer(sc.next());
                        System.out.println("Manufacturer changed");
                        break;

                    case 3:
                        System.out.print("Enter new Capacity: ");
                        plane.setPlane_capacity(sc.nextInt());
                        System.out.println("Capacity changed");
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("*** Please enter a valid choice ***");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("*** Please enter capacity as integer ***");
                sc.next();
            }
        }else {
            System.out.println("*** This plane not exits ***");
        }
    }
    public void deletePlane(String plane_code){
        Plane plane = getPlaneById(plane_code);
        if(plane != null) {
            Company.planes.remove(plane);
            System.out.println("Plane has " + plane.getPlane_id() + " was deleted...");
        }
        else{
            System.out.println("*** This plane not exits");
        }
    }
    public boolean showPlaneMenu(Company company,PlaneController controller) {
        boolean flag = true;
        try {
            System.out.println(".....................Plane Menu....................");
            System.out.println("1.Add new plane");
            System.out.println("2.Print all planes");
            System.out.println("3.Edit plane info");
            System.out.println("4.Delete plane");
            System.out.println("5.Quit");
            System.out.print("Enter a choice: ");
            int option = sc.nextInt();
            String plane_code = null;
            if(option >= 3 && option <= 4){
                System.out.println("Enter plane code: ");
                plane_code = sc.next();
            }
            switch (option) {
                case 1:
                    company.addPlane(company);
                    break;
                case 2:
                    controller.printAllPlanes();
                    break;
                case 3:
                    controller.editPlane(plane_code);
                    break;
                case 4:
                    controller.deletePlane(plane_code);
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.out.println("*** Please enter a valid choice ***");
            }
        }catch (NoSuchElementException e){
            System.out.println("Input not found. Please enter text without spaces");
            sc.next();
        }
        return flag;
    }
}
