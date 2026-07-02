package com.jdbc.main;


import com.jdbc.dto.OwnerDTO;
import com.jdbc.service.OwnerService;
import com.jdbc.service.impl.OwnerServiceImpl;
import com.jdbc.util.InputUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    public void run() {
        OwnerService ownerService=new OwnerServiceImpl();
        System.out.println("Welcome to petistaan");
        try{
            Scanner sc=new Scanner(System.in);
            do{
                int choice = InputUtil.acceptMenuOption(sc);
                switch (choice){
                    case 1:
                        System.out.println("Enter Owner Details");
                        OwnerDTO ownerDTO=InputUtil.acceptOwnerDetailsToSave(sc);
                        ownerService.saveOwner(ownerDTO);
                        System.out.println("Owner Details Save Successfully");
                        break;
                    case 2:
                        int OwnerId=InputUtil.acceptOwnerIdToOperate(sc);
                        ownerDTO=ownerService.findOwner(OwnerId);
                        System.out.println(OwnerId);
                        break;
                    case 3:
                        OwnerId=InputUtil.acceptOwnerIdToOperate(sc);
                        String petName=InputUtil.acceptPetDetailsToUpdate(sc);
                        ownerService.updatePetDetails(OwnerId,petName);
                        System.out.println("pet details update successfully");
                        break;
                    case 4:
                        OwnerId=InputUtil.acceptOwnerIdToOperate(sc);
                        ownerService.deleteOwner(OwnerId);
                        System.out.println("pet details delete successfully ");
                        break;
                    case 5:
                        ownerService.findAllOwners();
                        List<OwnerDTO>ownerList=ownerService.findAllOwners();
                        ownerList.forEach(System.out::println);
                        break;
                    case 6:
                       String email_id = InputUtil.acceptOwnerEmailIdToOperate(sc);
                        LocalDate petBirthDate=InputUtil.acceptPetBirthDateToOperate(sc);
                        ownerList=ownerService.findOwner(email_id,petBirthDate);

                        ownerList.forEach(System.out::println);
                        break;
                    case 7:
                        break;


                }
            }while(InputUtil.wantToContinue(sc));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
