/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Model.Account;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class AccountsManagmentController implements Initializable {

    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button createNewAccountrBtn;
    @FXML
    private Button showAllAccountsBtn;
    @FXML
    private Button updateSelectedAccountBtn;
    @FXML
    private Button deleteSelectedAccountBtn;
    @FXML
    private Button searchAccountBtn;
    @FXML
    private TextField accontSearchTF;
    @FXML
    private TableView<Account> accountsTableView;
    @FXML
    private TableColumn<Account, Integer> idCol;
    @FXML
    private TableColumn<Account, Integer> userIdCol;
    @FXML
    private TableColumn<Account, Integer> accountNumberCol;
    
    @FXML
    private TableColumn<Account, String> usernameCol;

    @FXML
    private TableColumn<Account, String> currencyCol;
    @FXML
    private TableColumn<Account, Double> balanceCol;
    @FXML
    private TableColumn<Account, Date> creationDateCol;

    public static Account selectedAccountToUpdate;
    public static Stage updateStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       
    }


    @FXML
    private void showAccountCreationPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToCreateAccount();

    }

    @FXML
    private void showAllAccounts(ActionEvent event) throws SQLException, ClassNotFoundException {
idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        userIdCol.setCellValueFactory(new PropertyValueFactory("user_id"));
       usernameCol.setCellValueFactory(new PropertyValueFactory("username"));
        accountNumberCol.setCellValueFactory(new PropertyValueFactory("account_number"));
        currencyCol.setCellValueFactory(new PropertyValueFactory("currency"));
        balanceCol.setCellValueFactory(new PropertyValueFactory("balance"));
        creationDateCol.setCellValueFactory(new PropertyValueFactory("creation_date"));
        ObservableList<Account> accountsList
                = FXCollections.observableArrayList(Account.getAllAccounts());
        System.out.println(accountsList);

        accountsTableView.setItems(accountsList);
    }

    @FXML
    private void updateSelectedAccount(ActionEvent event) throws IOException {
        if (accountsTableView.getSelectionModel().getSelectedItem() != null) {
            //store the selected user from the TableView in our global var user selectedUserToUpdate   
            selectedAccountToUpdate = accountsTableView.getSelectionModel().getSelectedItem();
            //load update page fxml
            FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("../View/UpdateAccountPage.fxml"));
            Parent rootUpdate = loaderUpdate.load();
            Scene updateAccountScene = new Scene(rootUpdate);
            //store loaded fxml in our global stage updateStage and show it
            updateStage = new Stage();
            updateStage.setScene(updateAccountScene);
            updateStage.setTitle("Update account " + selectedAccountToUpdate.getUsername());
            updateStage.show();
        }
    }

    @FXML
    private void deleteSelectedAccount(ActionEvent event) {
        if (accountsTableView.getSelectionModel().getSelectedItem() != null) {
            Account selectedAccount = accountsTableView.getSelectionModel().getSelectedItem();
            System.out.println(selectedAccount.getAccount_number());
            //show an confirmation alert and make the deletion on confirm event
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("Account delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this account ?");
           deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        //delete the selected user from database table using delete method in our User model
                        selectedAccount.delete();
                   } catch (SQLException ex) {
                        System.out.println(ex);
//                       Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        System.out.println(ex);
//                        Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("Account deleted");
                    deletedSuccessAlert.setContentText("Account deleted");
                    deletedSuccessAlert.show();
                }
            });

        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
           warnAlert.setTitle("Select an Account");
            warnAlert.setContentText("Please select an Account from the table view");
            warnAlert.show();
        }
    }

    @FXML
    private void searchForAnAccount(ActionEvent event) {
    }

}
