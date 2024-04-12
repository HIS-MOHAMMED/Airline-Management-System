package com.hishamfactory;

import java.util.Scanner;

public class PlaneController {
    Scanner sc = new Scanner(System.in);
    public static Plane getPlaneById(String id){
        for(Plane plane:Company.planes){
            if(id.equals(plane.getPlane_id())) return plane;
        }
        return null;
    }
    public void printAllPlanes(){
        System.out.println(".........................");
        for(Plane plane: Company.planes){
            System.out.println(plane.toString());
            System.out.println(".........................");
        }
        System.out.println(".............................");

    }
    public void editPlane(){
        System.out.print("Write plane Id: ");
        Plane plane = getPlaneById(sc.next());
        try {
            System.out.println("1.Edit Model");
            System.out.println("2.Edit Manufacturer");
            System.out.println("3.Edit Capacity");
            System.out.println("4.Edit Current Passengers: ");
            System.out.println("5.Quit");
            System.out.print("select option: ");
            int option = sc.nextInt();
            switch (option){
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
                    System.out.println("Id changed");
                    break;
            }

        }catch (Exception e){
            System.out.println("The given value is null.check it");
        }
    }
    public void deletePlane(){
        System.out.print("Enter plane id: ");
        String id = sc.next();
        Plane plane = getPlaneById(id);
        Company.planes.remove(plane);
        System.out.println("Plane has "+ plane.getPlane_id() + " was deleted...");
    }
    public void showPlaneMenu(Company company,PlaneController controller){
        System.out.println("1.Add new plane");
        System.out.println("2.Print all planes");
        System.out.println("3.Edit plane info");
        System.out.println("4.Delete plane");
        System.out.println("5.Quit");
        System.out.print("Enter a choice: ");
        int option = sc.nextInt();
        switch (option){
            case 1:
                company.addPlane();
                break;
            case 2:
                controller.printAllPlanes();
                break;
            case 3:
                controller.editPlane();
                break;
            case 4:
                controller.deletePlane();
            default:
                System.out.println("Thank you..");
                break;
        }
    }
}
