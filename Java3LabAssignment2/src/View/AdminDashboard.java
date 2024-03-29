/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Yahya
 */
public class AdminDashboard extends Stage{
    
    // All Scenes that AdminDashboard page have
    private Scene accountsManagmentScene;
    private Scene createAccountScene;

    //private Scene operationsScene;

    public AdminDashboard() throws IOException {
        

        
      // load AccountsManagment FXML File in AccountsManagment Scene
        FXMLLoader accountsLoader = new FXMLLoader(getClass().getResource("AccountsManagment.fxml"));
        Parent accountsRoot = accountsLoader.load();     
        accountsManagmentScene = new Scene(accountsRoot);
////        
       FXMLLoader createAccountLoader = new FXMLLoader(getClass().getResource("CreateAccount.fxml"));
        Parent createAccountRoot = createAccountLoader.load();     
      createAccountScene = new Scene(createAccountRoot);
       
//        // Set Main Scene in Admin Dasboard ( UsersManagment Scene )
        this.setScene(accountsManagmentScene);
        this.setTitle("Admin Dashboard");
        
        
    }
   
    
    public void changeSceneToAccountsManagment(){
        this.setScene(accountsManagmentScene);
    }

    public void changeSceneToCreateAccount() {
        this.setScene(createAccountScene);
    }

 
    
    
    
}
